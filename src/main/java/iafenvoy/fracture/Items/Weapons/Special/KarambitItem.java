package iafenvoy.fracture.Items.Weapons.Special;

import iafenvoy.fracture.Items.Weapons.WeaponItems;
import iafenvoy.fracture.Utils.Enum.Teams;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class KarambitItem extends WeaponItems {
    public KarambitItem(String name, ToolMaterial toolMaterial, int attackDamage, float attackSpeed,
                        Settings settings, Teams team) {
        super(name, toolMaterial, attackDamage, attackSpeed, settings, team);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (playerEntity.isSneaking()) {// 按住Shift才会使用传送
            double distance = 30.0d;
            double yaw = playerEntity.headYaw;
            double pitch = playerEntity.pitch;

            // x: 1 = -90, -1 = 90
            // y: 1 = -90, -1 = 90
            // z: 1 = 0, -1 = 180
            double temp = Math.cos(Math.toRadians(pitch));
            double dX = temp * -Math.sin(Math.toRadians(yaw));
            double dY = -Math.sin(Math.toRadians(pitch));
            double dZ = temp * Math.cos(Math.toRadians(yaw));
            Vec3d position = playerEntity.getPos().add(0, playerEntity.getEyeY() - playerEntity.getY(), 0);
            Vec3d endPosition = new Vec3d(position.x + dX * distance, position.y + dY * distance, position.z + dZ * distance);
            BlockPos blockPos = new BlockPos(endPosition.x, endPosition.y, endPosition.z);

            BlockPos[] blockPoses = {blockPos, blockPos.up(), blockPos};

            double down = endPosition.y;
            double maxDown = down - distance - 1 > 0 ? down - distance - 1 : 0;
            double up = endPosition.y + 1;
            double maxUp;
            if (playerEntity.getEntityWorld().getDimension().isRespawnAnchorWorking()) {
                maxUp = up + distance - 1 < 127 ? up + distance - 1 : 127;
            } else {
                maxUp = up + distance - 1 < 255 ? up + distance - 1 : 255;
            }
            double near = distance;
            // Check to Teleport
            if (world.isChunkLoaded(blockPos)) {
                int foundSpace = 0;

                while (foundSpace == 0 && (blockPoses[0].getY() > maxDown || blockPoses[1].getY() < maxUp)) {
                    // CheckDown
                    if (blockPoses[0].getY() > maxDown) {
                        if (checkBlocks(world, blockPoses[0])) {
                            foundSpace = 1;
                        } else {
                            --down;
                            blockPoses[0] = blockPoses[0].down();
                        }
                    }
                    // CheckUp
                    if (blockPoses[1].getY() < maxUp) {
                        if (checkBlocks(world, blockPoses[1])) {
                            foundSpace = 2;
                        } else {
                            ++up;
                            blockPoses[1] = blockPoses[1].up();
                        }
                    }
                    // CheckNear
                    if (near > 2) {
                        if (checkBlocks(world, blockPoses[2])) {
                            foundSpace = 3;
                        } else {
                            --near;
                            blockPoses[2] = blockPoses[2].add(-dX, -dY, -dZ);
                        }
                    }
                }
                if (foundSpace == 0 && !world.getBlockState(blockPos).getMaterial().blocksMovement() && !world.getBlockState(blockPos.up()).getMaterial().blocksMovement())
                    foundSpace = 4;


                if (foundSpace > 0 && (position.y + dY * near) < maxUp && (position.y + dY * near) > maxDown) {
                    playerEntity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    switch (foundSpace) {
                        case 1: // Down
                            playerEntity.teleport(endPosition.x, Math.max(down, maxDown), endPosition.z);
                            break;
                        case 2: // Up
                            playerEntity.teleport(endPosition.x, Math.min(up, maxUp), endPosition.z);
                            break;
                        case 4: // Air
                            near = distance / 2;
                        case 3: // Near
                            playerEntity.teleport(position.x + dX * near, position.y + dY * near, position.z + dZ * near);
                            break;
                    }
                    playerEntity.getItemCooldownManager().set(this, 30);
                    world.sendEntityStatus(playerEntity, (byte) 46);
                    playerEntity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                }
            } else
                return new TypedActionResult<>(ActionResult.FAIL, playerEntity.getStackInHand(hand));
        } else
            return new TypedActionResult<>(ActionResult.FAIL, playerEntity.getStackInHand(hand));
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }

    protected boolean checkBlocks(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().blocksMovement()
                && !world.getBlockState(pos).getMaterial().blocksMovement()
                && !world.getBlockState(pos.up()).getMaterial().blocksMovement();
    }
}

package net.iafenvoy.fracture.Recipe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FractureCraftingTableBlock extends Block {
  public FractureCraftingTableBlock(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
      BlockHitResult hit) {
    if (world.isClient) {
      return ActionResult.SUCCESS;
    } else {
      Block[][] block = new Block[9][9];
      for (int i = -4; i <= 4; i++)
        for (int j = -4; j <= 4; j++) {
          if (i == 0 && j == 0)
            continue;
          block[i + 4][j + 4] = world.getBlockState(pos.add(i, 0, j)).getBlock();
        }
      FractureRecipe recipe = FractureRecipe.hasAvailable(block);
      if (recipe != FractureRecipe.EMPTY) {
        ItemStack result = recipe.getOutput();
        String command = "summon item " + pos.getX() + " " + (pos.getY() + 1) + " " + pos.getZ() + " {Item:{id:\""
            + result.getItem().getName().getString() + "\",Count:" + result.getCount()
            + "}}";
        world.getServer().getCommandManager().execute(world.getServer().getCommandSource(), command);
        // TODO:Clean Blocks
      } else
        player.sendMessage(new TranslatableText("fracture.craft.fail"), false);
      return ActionResult.SUCCESS;
    }
  }
}

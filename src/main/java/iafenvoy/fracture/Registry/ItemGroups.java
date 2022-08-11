package iafenvoy.fracture.Registry;

import iafenvoy.fracture.Fracture;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemGroups {
    public static final ItemGroup _fracture = FabricItemGroupBuilder.create(new Identifier(Fracture.MOD_ID, "fracture"))
            .icon(() -> new ItemStack(Items.end_greatsword)).appendItems(stacks -> {
                stacks.add(new ItemStack(Items.CRAFTING_TABLE_ITEM));
                stacks.add(new ItemStack(Items.end_laser_arm_sword));
                stacks.add(new ItemStack(Items.ser_patricks_pike));
                stacks.add(new ItemStack(Items.end_greatsword));
                stacks.add(new ItemStack(Items.end_karambit));
                stacks.add(new ItemStack(Items.end_handled_greatsword));
                stacks.add(new ItemStack(Items.nether_greatsword));
                stacks.add(new ItemStack(Items.golden_longsword));
                stacks.add(new ItemStack(Items.ruby_pike));
                stacks.add(new ItemStack(Items.tech_wing));
                stacks.add(new ItemStack(Items.dragon_wing));

                stacks.add(new ItemStack(Cape.human));
                stacks.add(new ItemStack(Cape.nether));
                stacks.add(new ItemStack(Cape.undead));
                stacks.add(new ItemStack(Cape.end));
            }).build();
}

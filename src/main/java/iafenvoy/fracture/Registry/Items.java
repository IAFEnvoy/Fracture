package iafenvoy.fracture.Registry;

import iafenvoy.fracture.Items.Weapons.FractureMaterial;
import iafenvoy.fracture.Items.Weapons.Special.KarambitItem;
import iafenvoy.fracture.Items.Weapons.WeaponItems;
import iafenvoy.fracture.Items.Wings.WingItems;
import iafenvoy.fracture.Recipe.FractureCraftingTableBlock;
import iafenvoy.fracture.Utils.Enum.Teams;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class Items {
    public static final ElytraItem tech_wing = new WingItems("tech_wing", new FabricItemSettings().maxDamage(432).rarity(Rarity.UNCOMMON).equipmentSlot((stack) -> EquipmentSlot.CHEST), Teams.HUMAN);
    public static final ElytraItem dragon_wing = new WingItems("dragon_wing", new FabricItemSettings().maxDamage(432).rarity(Rarity.UNCOMMON).equipmentSlot((stack) -> EquipmentSlot.CHEST), Teams.END);

    public static final ToolItem end_laser_arm_sword = new WeaponItems("end_laser_arm_sword", FractureMaterial.INSTANCE,
            30, 10, new Settings().rarity(Rarity.RARE), Teams.END);
    public static final ToolItem ser_patricks_pike = new WeaponItems("ser_patricks_pike", FractureMaterial.INSTANCE, 25,
            3, new Settings().rarity(Rarity.RARE), Teams.HUMAN);
    public static final ToolItem end_handled_greatsword = new WeaponItems("end_handled_greatsword",
            FractureMaterial.INSTANCE, 27, 4, new Settings().rarity(Rarity.RARE), Teams.END);
    public static final ToolItem end_greatsword = new WeaponItems("end_greatsword", FractureMaterial.INSTANCE, 10, 6,
            new Settings().rarity(Rarity.RARE), Teams.END);
    public static final ToolItem nether_greatsword = new WeaponItems("nether_greatsword", FractureMaterial.INSTANCE, 10,
            2, new Settings().rarity(Rarity.RARE), Teams.NETHER);
    public static final ToolItem golden_longsword = new WeaponItems("golden_longsword", FractureMaterial.INSTANCE, 10,
            2, new Settings().rarity(Rarity.RARE), Teams.NETHER);
    public static final ToolItem ruby_pike = new WeaponItems("ruby_pike", FractureMaterial.INSTANCE,
            10, 2, new Settings().rarity(Rarity.RARE), Teams.NETHER);
    public static final ToolItem end_karambit = new KarambitItem("end_karambit", FractureMaterial.INSTANCE, 6,
            2, new Settings().rarity(Rarity.RARE), Teams.HUMAN);

    public static final Block CRAFTING_TABLE = Registry.register(Registry.BLOCK, "fracture:crafting_table",
            new FractureCraftingTableBlock(
                    FabricBlockSettings.of(Material.METAL).hardness(5.0f).breakByTool(FabricToolTags.PICKAXES, 3)));
    public static final Item CRAFTING_TABLE_ITEM = Registry.register(Registry.ITEM, "fracture:crafting_table",
            new BlockItem(CRAFTING_TABLE, new Settings().fireproof()));
}

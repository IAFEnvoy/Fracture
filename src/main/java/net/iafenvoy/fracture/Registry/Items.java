package net.iafenvoy.fracture.Registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.iafenvoy.fracture.Items.Weapons.FractureMaterial;
import net.iafenvoy.fracture.Items.Weapons.WeaponItems;
import net.iafenvoy.fracture.Items.Weapons.Special.KarambitItem;
import net.iafenvoy.fracture.Items.Wings.WingItems;
import net.iafenvoy.fracture.Recipe.FractureCraftingTableBlock;
import net.iafenvoy.fracture.Utils.Enum.Teams;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class Items {
  public static final ElytraItem tech_wing = new WingItems("tech_wing",
      new FabricItemSettings().maxDamage(432).rarity(Rarity.UNCOMMON).equipmentSlot((stack) -> {
        return EquipmentSlot.CHEST;
      }));
  public static final ToolItem saber = new WeaponItems("saber", FractureMaterial.INSTANCE, 30, 10,
      new Settings().rarity(Rarity.RARE), Teams.END);
  public static final ToolItem patrick_halberd = new WeaponItems("patrick_halberd", FractureMaterial.INSTANCE, 25, 3,
      new Settings().rarity(Rarity.RARE), Teams.HUMAN);
  public static final ToolItem dancer_arm = new WeaponItems("dancer_arm", FractureMaterial.INSTANCE, 27, 4,
      new Settings().rarity(Rarity.RARE), Teams.END);
  public static final ToolItem ceris_big_knief = new WeaponItems("ceris_big_knief", FractureMaterial.INSTANCE, 10, 6,
      new Settings().rarity(Rarity.RARE), Teams.END);
  public static final ToolItem blackbone_arm = new WeaponItems("blackbone_arm", FractureMaterial.INSTANCE, 10, 2,
      new Settings().rarity(Rarity.RARE), Teams.NETHER);
  public static final ToolItem long_golden_sword = new WeaponItems("long_golden_sword", FractureMaterial.INSTANCE, 10,
      2, new Settings().rarity(Rarity.RARE), Teams.NETHER);
  public static final ToolItem nether_princess_arm = new WeaponItems("nether_princess_arm", FractureMaterial.INSTANCE,
      10, 2, new Settings().rarity(Rarity.RARE), Teams.NETHER);
  public static final ToolItem ciara_karambit = new KarambitItem("ciara_karambit", FractureMaterial.INSTANCE, 6,
      2, new Settings().rarity(Rarity.RARE), Teams.HUMAN);

  public static final Block CRAFTING_TABLE = Registry.register(Registry.BLOCK, "fracture:crafting_table",
      new FractureCraftingTableBlock(
          FabricBlockSettings.of(Material.METAL).hardness(5.0f).breakByTool(FabricToolTags.PICKAXES, 3)));
  public static final Item CRAFTING_TABLE_ITEM = Registry.register(Registry.ITEM, "fracture:crafting_table",
      new BlockItem(CRAFTING_TABLE, new Settings().fireproof().rarity(Rarity.EPIC)));

  public static void Init() {
  }
}

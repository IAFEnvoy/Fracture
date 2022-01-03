package fracture.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import fracture.arms.BoomerangAction;
import fracture.arms.fracture_material;
import fracture.arms.wing_material;
import fracture.fracture;

public class items {
  public static ToolItem ceris_knief = new SwordItem(fracture_material.INSTANCE, 30 - 1, 10 - 4,
      new Item.Settings().group(fracture._fracture).rarity(Rarity.RARE));
  public static ToolItem patrick_halberd = new SwordItem(fracture_material.INSTANCE, 25 - 1, 3 - 4,
      new Item.Settings().group(fracture._fracture).rarity(Rarity.RARE));
  public static ToolItem dancer_arm = new SwordItem(fracture_material.INSTANCE, 27 - 1, 4 - 4,
      new Item.Settings().group(fracture._fracture).rarity(Rarity.RARE));
  public static ToolItem ceris_big_knief = new SwordItem(fracture_material.INSTANCE, 10 - 1, 6 - 4,
      new Item.Settings().group(fracture._fracture).rarity(Rarity.RARE));
  public static ToolItem blackbone_arm = new SwordItem(fracture_material.INSTANCE, 10 - 1, 2 - 4,
      new Item.Settings().group(fracture._fracture).rarity(Rarity.RARE));
  public static ToolItem long_golden_sword = new SwordItem(fracture_material.INSTANCE, 8 - 1, 2 - 4,
      new Item.Settings().group(fracture._fracture).fireproof().rarity(Rarity.RARE));
  public static ToolItem nether_princess_arm = new SwordItem(fracture_material.INSTANCE, 10 - 1, 7 - 4,
      new Item.Settings().group(fracture._fracture).fireproof().rarity(Rarity.RARE));

  public static ToolItem ciara_boomerang = new BoomerangAction(fracture_material.INSTANCE, 6, -2.4F,
      new Item.Settings().group(fracture._fracture).rarity(Rarity.RARE));

  public static final ArmorMaterial wing_m = new wing_material();
  public static final Item wings = new ArmorItem(wing_m, EquipmentSlot.HEAD,
      new Item.Settings());

  public static void Init() {
    Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "ceris_knief"), ceris_knief);
    Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "patrick_halberd"), patrick_halberd);
    Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "dancer_arm"), dancer_arm);
    Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "ceris_big_knief"), ceris_big_knief);
    Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "ciara_boomerang"), ciara_boomerang);
    Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "blackbone_arm"), blackbone_arm);
    Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "long_golden_sword"), long_golden_sword);
    Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "nether_princess_arm"), nether_princess_arm);
    Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "wings"), wings);
  }
}

package net.iafenvoy.fracture.Items.Capes;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.iafenvoy.fracture.Fracture;
import net.iafenvoy.fracture.Utils.Enum.Teams;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CapeItem extends Item {
  public static final List<CapeItem> INSTANCE = new ArrayList<>();
  private final String name;
  private final Identifier texture;
  private final Teams team;

  public CapeItem(String name, String texture, Teams team) {
    super(new FabricItemSettings().maxCount(1).maxDamage(1024)
        .equipmentSlot(stack -> EquipmentSlot.CHEST));
    this.name = name;
    this.texture = new Identifier(Fracture.MOD_ID, "textures/cape/" + texture + ".png");
    this.team = team;
    Registry.register(Registry.ITEM, this.texture, this);
    INSTANCE.add(this);
  }

  @Override
  public Text getName() {
    return new LiteralText(name);
  }

  public Identifier getTexture() {
    return texture;
  }

  public Teams getTeam() {
    return team;
  }
}

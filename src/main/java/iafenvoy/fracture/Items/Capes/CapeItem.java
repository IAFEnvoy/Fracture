package iafenvoy.fracture.Items.Capes;

import iafenvoy.fracture.Fracture;
import iafenvoy.fracture.Utils.Enum.Teams;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("fracture.team." + team.getTranslateKey() + ".tooltip"));
    }
}

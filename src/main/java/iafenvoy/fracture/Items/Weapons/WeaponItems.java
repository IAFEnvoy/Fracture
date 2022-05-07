package iafenvoy.fracture.Items.Weapons;

import java.util.HashMap;
import java.util.List;

import iafenvoy.fracture.Fracture;
import iafenvoy.fracture.Utils.Enum.Teams;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class WeaponItems extends SwordItem {
    public static final HashMap<String, WeaponItems> INSTANCE = new HashMap<>();
    private final Teams team;

    public WeaponItems(String name, ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings,
            Teams team) {
        super(toolMaterial, attackDamage - 1, attackSpeed - 4, settings);
        this.team = team;
        Registry.register(Registry.ITEM, new Identifier(Fracture.MOD_ID, name), this);
        INSTANCE.put(name, this);
    }

    public Teams getTeam() {
        return team;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("fracture.team." + team.getTranslateKey() + ".tooltip"));
    }

    public static WeaponItems get(String name) {
        return INSTANCE.get(name);
    }

    public static WeaponItems get(Identifier name) {
        return INSTANCE.get(name.getPath());
    }

}

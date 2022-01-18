package fracture.wings;

import fracture.fracture;
import fracture.wings.WingsAPI.DeleteHungerMessage;
import fracture.wings.WingsAPI.WingItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class wings {

    // item
    public static final ElytraItem tech_wing = create(
            new WingItem(Rarity.RARE, "tech_wing",
                    new FabricItemSettings().maxDamage(432).rarity(Rarity.UNCOMMON).equipmentSlot((stack -> {
                        return EquipmentSlot.CHEST;
                    }))));

    public static void Init() {
        ServerSidePacketRegistry.INSTANCE.register(DeleteHungerMessage.ID, DeleteHungerMessage::handle);
    }

    private static ElytraItem create(WingItem wing) {
        Item item = Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, wing.getWingName()), wing);
        return (ElytraItem) item;
    }
}

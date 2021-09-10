package fracture.wings;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import fracture.fracture;

public class wings {

    //item
    public static final Item wings = Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "customizable_elytra"), new CustomizableElytraItem(new FabricItemSettings().maxDamage(432).rarity(Rarity.UNCOMMON).equipmentSlot((stack ->
    {
        return EquipmentSlot.CHEST;
    }))));

    public static void Init(){
        ServerSidePacketRegistry.INSTANCE.register(DeleteHungerMessage.ID, DeleteHungerMessage::handle);
    }
}

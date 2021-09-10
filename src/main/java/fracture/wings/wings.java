package fracture.wings;

import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import fracture.fracture;
import fracture.wings.WingItem.WingType;

public class wings {

    //item
    public static final Item wings = Registry.register(Registry.ITEM, new Identifier(fracture.MOD_ID, "wings"), new WingItem(DyeColor.WHITE, DyeColor.WHITE, WingType.FEATHERED));

    public static void Init(){
        ServerSidePacketRegistry.INSTANCE.register(DeleteHungerMessage.ID, DeleteHungerMessage::handle);
    }
}

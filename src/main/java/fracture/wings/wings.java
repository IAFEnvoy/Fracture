package fracture.wings;

import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

import fracture.wings.WingItem.WingType;

public class wings {

    public static final Item wings = new WingItem(DyeColor.WHITE, DyeColor.WHITE, WingType.UNIQUE);

    public static void Init(){
        ServerSidePacketRegistry.INSTANCE.register(DeleteHungerMessage.ID, DeleteHungerMessage::handle);
        Registry.register(Registry.ITEM, "wings", wings);
    }
}

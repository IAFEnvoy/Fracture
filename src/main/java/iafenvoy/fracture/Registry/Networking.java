package iafenvoy.fracture.Registry;

import iafenvoy.fracture.Items.Wings.DeleteHungerMessage;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class Networking {
    public static void Init(){
        ServerPlayNetworking.registerGlobalReceiver(DeleteHungerMessage.ID, new DeleteHungerMessage());
    }
}

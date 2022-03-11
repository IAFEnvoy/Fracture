package net.iafenvoy.fracture.Registry;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.iafenvoy.fracture.Items.Wings.DeleteHungerMessage;

public class Networking {
    public static void Init(){
        ServerPlayNetworking.registerGlobalReceiver(DeleteHungerMessage.ID, new DeleteHungerMessage());
    }
}

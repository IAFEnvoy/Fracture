package iafenvoy.fracture.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import iafenvoy.fracture.Config.ConfigLoader;
import iafenvoy.fracture.Config.PlayerProfile;
import iafenvoy.fracture.Utils.CommandUtil;
import iafenvoy.fracture.Utils.Enum.Teams;
import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
  @Inject(method = "runServer", at = @At("HEAD"))
  private void onServerStart(CallbackInfo ci) {
    ConfigLoader.loadConfig();
    PlayerProfile.loadProfile();
    CommandUtil.server = (MinecraftServer) (Object) this;
    Teams.initTeam();
  }

  @Inject(method = "shutdown", at = @At("HEAD"))
  private void onServerShutdown(CallbackInfo ci) {
    ConfigLoader.saveConfig();
    PlayerProfile.saveProfile();
  }
}

package iafenvoy.fracture.mixins;

import com.mojang.brigadier.CommandDispatcher;
import iafenvoy.fracture.Commands;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandsMixin {
  @Shadow
  @Final
  private CommandDispatcher<ServerCommandSource> dispatcher;

  @Inject(method = "<init>", at = @At("RETURN"))
  private void onRegister(CommandManager.RegistrationEnvironment arg, CallbackInfo ci) {
    Commands.register(dispatcher);
  }
}

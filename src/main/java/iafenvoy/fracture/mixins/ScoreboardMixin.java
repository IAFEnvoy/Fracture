package iafenvoy.fracture.mixins;

import java.util.Map;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Scoreboard.class)
public class ScoreboardMixin {
  @Shadow
  @Final
  private Map<String, Team> teams;

  @Inject(method = "addTeam", at = @At("HEAD"), cancellable = true)
  private void onAddTeam(String name, CallbackInfoReturnable<Team> cir) {
    if (teams.containsKey(name))
      cir.setReturnValue(teams.get(name));
  }
}

package iafenvoy.fracture.Config;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import iafenvoy.fracture.Utils.FileUtils;
import iafenvoy.fracture.Utils.LogUtil;
import iafenvoy.fracture.Utils.Enum.Teams;

public class PlayerProfile {
  public static final HashMap<String, PlayerProfile> profiles = new HashMap<String, PlayerProfile>();
  private static final String configPath = "./config/fracture/players.json";
  private Teams team;
  private int level;

  public PlayerProfile() {
    this(Teams.NONE);
  }

  public PlayerProfile(Teams team) {
    this(team, 0);
  }

  public PlayerProfile(Teams team, int level) {
    this.team = team;
    this.level = level;
  }

  public Teams getTeam() {
    return team;
  }

  public int getLevel() {
    return level;
  }

  public void setTeam(Teams team) {
    this.team = team;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public static void setTeam(String uuid, Teams team) {
    PlayerProfile profile = profiles.get(uuid);
    if (profile != null)
      profile.setTeam(team);
    else
      profiles.put(uuid, new PlayerProfile(team));
  }

  public static void loadProfile() {
    LogUtil.info("Start to load Fracture player profiles");
    profiles.clear();
    try {
      JsonObject jsonObject = new JsonParser().parse(FileUtils.readFile(configPath)).getAsJsonObject();
      for (Entry<String, JsonElement> key : jsonObject.entrySet())
        profiles.put(key.getKey(),
            new PlayerProfile(Teams.getByName(key.getValue().getAsJsonObject().get("team").getAsString()),
                key.getValue().getAsJsonObject().get("level").getAsInt()));
      LogUtil.info("Succeed to load player profiles");
    } catch (Exception e) {
      LogUtil.error("Fail to load player profiles, reason : " + e.getMessage());
    }
  }

  public static void saveProfile() {
    LogUtil.info("Start to save Fracture player profiles");
    try {
      JsonObject object = new JsonObject();
      for (Entry<String, PlayerProfile> entry : profiles.entrySet()) {
        JsonObject o = new JsonObject();
        o.addProperty("team", entry.getValue().team.getTranslateKey());
        o.addProperty("level", entry.getValue().level);
        object.add(entry.getKey(), (JsonElement) o);
      }
      FileUtils.saveFile(configPath, object.toString());
      LogUtil.info("Succeed to save player profiles");
    } catch (Exception e) {
      LogUtil.error("Fail to save player profiles, reason : " + e.getMessage());
    }
  }
}

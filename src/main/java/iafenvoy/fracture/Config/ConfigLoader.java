package iafenvoy.fracture.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConfigLoader {
  private static final HashMap<String, String> data = new HashMap<>();
  private static final String configPath = "./config/fracture.json";

  public static void loadConfig() {
    data.clear();
    try {
      // load config from config path
      InputStream inputStream = new FileInputStream(new File(configPath));
      StringBuilder stringBuilder = new StringBuilder();
      int i;
      while ((i = inputStream.read()) != -1)
        stringBuilder.append((char) i);
      inputStream.close();
      // parse config
      JsonObject jsonObject = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
      for (Entry<String, JsonElement> key : jsonObject.entrySet())
        data.put(key.getKey(), key.getValue().getAsString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void saveConfig() {
    // save config to config path
    JsonObject object = new JsonObject();
    for (Entry<String, String> entry : data.entrySet())
      object.addProperty(entry.getKey(), entry.getValue());
    // write file and rewrite exist file
    try {
      OutputStream outputStream = new FileOutputStream(new File(configPath));
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
      bufferedWriter.write(object.toString());
      bufferedWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String get(String key) {
    return data.get(key);
  }

  public static void set(String key, String value) {
    data.put(key, value);
  }
}

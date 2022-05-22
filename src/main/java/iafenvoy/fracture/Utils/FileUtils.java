package iafenvoy.fracture.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileUtils {
  public static String readByLines(InputStreamReader stream) throws IOException {
    BufferedReader in = new BufferedReader(stream);
    StringBuffer buffer = new StringBuffer();
    String line = "";
    while ((line = in.readLine()) != null)
      buffer.append(line).append("\n");
    String input = buffer.toString();
    return input;
  }

  public static String readFile(String path) throws IOException {
    InputStream inputStream = new FileInputStream(new File(path));
    StringBuilder stringBuilder = new StringBuilder();
    int i;
    while ((i = inputStream.read()) != -1)
      stringBuilder.append((char) i);
    inputStream.close();
    return stringBuilder.toString();
  }

  public static void saveFile(String path, String content) throws IOException {
    File configFolder = new File("./config/fracture");
    if (!configFolder.exists())
      configFolder.mkdir();
    OutputStream outputStream = new FileOutputStream(new File(path));
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    bufferedWriter.write(content);
    bufferedWriter.close();
  }
}

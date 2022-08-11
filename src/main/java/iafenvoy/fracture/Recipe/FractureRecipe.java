package iafenvoy.fracture.Recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import iafenvoy.fracture.Fracture;
import iafenvoy.fracture.Registry.Items;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FractureRecipe {
    public static final FractureRecipe EMPTY = new FractureRecipe(null, null, null);
    private static final List<FractureRecipe> INSTANCE = new ArrayList<>();
    private final Identifier id;
    private final Block[][] input;
    private final ItemStack output;

    public FractureRecipe(Identifier id, Block[][] input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
        if (id != null) {
            this.input[4][4] = Items.CRAFTING_TABLE;
            INSTANCE.add(this);
        }
    }

    public static Block[][] rotate(Block[][] input) {
        Block[][] output = new Block[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                output[i][j] = input[8 - j][i];
        return output;
    }

    public static FractureRecipe hasAvailable(Block[][] input) {
        Block[][] blocks = input.clone();
        for (int i = 0; i < 4; i++) {
            for (FractureRecipe recipe : INSTANCE)
                if (recipe.matches(blocks))
                    return recipe;
            blocks = rotate(blocks);
        }
        return EMPTY;
    }

    public static void read(String id, JsonObject json) {
        try {
            Block[][] input = new Block[9][9];
            JsonArray map = json.getAsJsonArray("map");
            HashMap<Character, String> keyMap = new HashMap<>();
            JsonArray keys = json.getAsJsonArray("keys");
            for (JsonElement jsonElement : keys)
                keyMap.put(jsonElement.getAsJsonObject().get("name").getAsCharacter(),
                        jsonElement.getAsJsonObject().get("item").getAsString());
            for (int i = 0; i < 9; i++) {
                JsonElement row = map.get(i);
                for (int j = 0; j < 9; j++) {
                    Character itemKey = row.getAsString().charAt(j);
                    if (itemKey == ' ')
                        input[i][j] = Blocks.AIR;
                    else if (keyMap.containsKey(itemKey))
                        input[i][j] = Registry.BLOCK.get(new Identifier(keyMap.get(itemKey)));
                    else
                        input[i][j] = Blocks.AIR;
                }
            }
            ItemStack output = new ItemStack(Registry.ITEM.get(new Identifier(json.get("output").getAsString())));
            new FractureRecipe(new Identifier(Fracture.MOD_ID, id), input, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean matches(Block[][] input) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (input[i][j] != this.input[i][j])
                    return false;
        return true;
    }

    public ItemStack getOutput() {
        return output;
    }

    public Identifier getId() {
        return id;
    }
}

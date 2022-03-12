package net.iafenvoy.fracture.Recipe;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class FractureCraftingInventory implements Inventory {
  private ItemStack[] inventory = new ItemStack[81];

  @Override
  public void clear() {
    for (int i = 0; i < this.inventory.length; i++)
      this.inventory[i] = ItemStack.EMPTY;
  }

  @Override
  public boolean canPlayerUse(PlayerEntity player) {
    return true;
  }

  @Override
  public ItemStack getStack(int slot) {
    if (slot >= 0 && slot < 81)
      return inventory[slot];
    else
      return ItemStack.EMPTY;
  }

  @Override
  public boolean isEmpty() {
    for (int i = 0; i < this.inventory.length; i++)
      if (!this.inventory[i].isEmpty())
        return false;
    return true;
  }

  @Override
  public void markDirty() {
  }

  @Override
  public ItemStack removeStack(int slot) {
    if (slot >= 0 && slot < 81) {
      ItemStack stack = inventory[slot];
      inventory[slot] = ItemStack.EMPTY;
      return stack;
    } else
      return ItemStack.EMPTY;
  }

  @Override
  public ItemStack removeStack(int slot, int amount) {
    if (slot >= 0 && slot < 81) {
      ItemStack stack = inventory[slot];
      if (inventory[slot].getCount() > amount) {
        inventory[slot].decrement(amount);
        return new ItemStack(inventory[slot].getItem(), amount);
      } else {
        inventory[slot] = ItemStack.EMPTY;
        return stack;
      }
    } else
      return ItemStack.EMPTY;
  }

  @Override
  public void setStack(int slot, ItemStack stack) {
    if (slot >= 0 && slot < 81)
      inventory[slot] = stack;
  }

  @Override
  public int size() {
    return 81;
  }

}

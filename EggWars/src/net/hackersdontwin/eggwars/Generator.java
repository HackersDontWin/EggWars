package net.hackersdontwin.eggwars;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class Generator {

    private int currentAmount;
    private int maxAmount;
    private int level;
    private String type;
    private Location location;
    private ArrayList<ItemStack> items;
    private int priceToUpgrade;
    private Material typeForUpgrade;

    public Generator(int maxAmount, int level, String type, Location location, int priceToUpgrade, Material typeForUpgrade) {
        this.currentAmount = 0;
        this.maxAmount = maxAmount;
        this.level = level;
        this.type = type;
        this.location = location;
        this.typeForUpgrade = typeForUpgrade;
        this.priceToUpgrade = priceToUpgrade;
        items = new ArrayList<ItemStack>();
    }

    public String getType() {
        return type;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public int getLevel() {
        return level;
    }

    public Location getLocation() {
        return location;
    }

    public void setCurrentAmount(int newCurrentAmount) {
        this.currentAmount = newCurrentAmount;
    }

    public void setMaxAmount(int newMaxAmount) {
        this.maxAmount = newMaxAmount;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }

    public void addItem() {
        if(currentAmount == maxAmount) {
            return;
        }

        switch(type) {
            case "iron":
                ItemStack isIron = new ItemStack(Material.IRON_INGOT, 1);
                net.minecraft.server.v1_12_R1.ItemStack nmsItemIron = CraftItemStack.asNMSCopy(isIron);
                NBTTagCompound tagIron = nmsItemIron.getTag();
                tagIron.setString("item", UUID.randomUUID().toString());
                nmsItemIron.setTag(tagIron);
                items.add(CraftItemStack.asBukkitCopy(nmsItemIron));
                break;
            case "gold":
                ItemStack isGold = new ItemStack(Material.GOLD_INGOT, 1);
                net.minecraft.server.v1_12_R1.ItemStack nmsItemGold = CraftItemStack.asNMSCopy(isGold);
                NBTTagCompound tagGold = nmsItemGold.getTag();
                tagGold.setString("item", UUID.randomUUID().toString());
                nmsItemGold.setTag(tagGold);
                items.add(CraftItemStack.asBukkitCopy(nmsItemGold));
                break;
            case "diamond":
                ItemStack isDiamond = new ItemStack(Material.DIAMOND, 1);
                net.minecraft.server.v1_12_R1.ItemStack nmsItemDiamond = CraftItemStack.asNMSCopy(isDiamond);
                NBTTagCompound tagDiamond = nmsItemDiamond.getTag();
//                tagDiamond.setString("item", UUID.randomUUID().toString());
                nmsItemDiamond.setTag(tagDiamond);
                items.add(CraftItemStack.asBukkitCopy(nmsItemDiamond));
        }
        currentAmount++;
    }

    public void removeItem() {
        items.remove(items.size()-1);
        currentAmount--;
    }

    public void setPriceToUpgrade(int newPriceAmount) {
        this.priceToUpgrade = newPriceAmount;
    }

    public int getPriceToUpgrade() {
        return priceToUpgrade;
    }

    public void setTypeForUpgrade(Material type) {
        this.typeForUpgrade = type;
    }

    public Material getTypeForUpgrade() {
        return typeForUpgrade;
    }

}

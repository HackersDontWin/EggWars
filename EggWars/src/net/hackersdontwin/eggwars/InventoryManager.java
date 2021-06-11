package net.hackersdontwin.eggwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryManager {

	private HashMap<String, Inventory> inventories = new HashMap<String, Inventory>();

	Inventory diamondGeneratorBroken = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Diamond generator - Broken");
	Inventory diamondGeneratorLvl1 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Diamond generator - Level 1");
	Inventory diamondGeneratorLvl2 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Diamond generator - Level 2");
	Inventory diamondGeneratorLvl3 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Diamond generator - Level 3");
	Inventory goldGeneratorBroken = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Gold generator - Broken");
	Inventory goldGeneratorLvl1 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Gold generator - Level 1");
	Inventory goldGeneratorLvl2 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Gold generator - Level 2");
	Inventory goldGeneratorLvl3 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Gold generator - Level 3");
	Inventory goldGeneratorLvl4 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Gold generator - Level 4");
	Inventory ironGeneratorBroken = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Iron generator - Broken");
	Inventory ironGeneratorLvl1 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Iron generator - Level 1");
	Inventory ironGeneratorLvl2 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Iron generator - Level 2");
	Inventory ironGeneratorLvl3 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Iron generator - Level 3");
	Inventory ironGeneratorLvl4 = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Iron generator - Level 4");


	public InventoryManager() {
		setupDiamondGeneratorInventory();
	}


	private void setupDiamondGeneratorInventory() {

		diamondGeneratorBroken.setItem(11, newItem(Material.DIAMOND, 1, getMaterialName("diamond", 0), getMarialLore(0.0)));
		diamondGeneratorBroken.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("diamond", 0, 3), getExpLore(10.0,5,"diamond", false)));
		inventories.put("diamond0", diamondGeneratorBroken);
		diamondGeneratorLvl1.setItem(11, newItem(Material.DIAMOND, 1, getMaterialName("diamond", 1), getMarialLore(10.0)));
		diamondGeneratorLvl1.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("diamond", 1, 3), getExpLore(5.0,10,"diamond", false)));
		inventories.put("diamond1", diamondGeneratorLvl1);
		diamondGeneratorLvl2.setItem(11, newItem(Material.DIAMOND, 1, getMaterialName("diamond", 2), getMarialLore(5.0)));
		diamondGeneratorLvl2.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("diamond", 2, 3), getExpLore(2.5,25,"diamond", false)));
		inventories.put("diamond2", diamondGeneratorLvl2);
		diamondGeneratorLvl3.setItem(11, newItem(Material.DIAMOND, 1, getMaterialName("diamond", 3), getMarialLore(2.5)));
		diamondGeneratorLvl3.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("diamond", 3, 3), getExpLore(0.0,0,"diamond", true)));
		inventories.put("diamond3", diamondGeneratorLvl3);

		goldGeneratorBroken.setItem(11, newItem(Material.GOLD_INGOT, 1, getMaterialName("gold", 0), getMarialLore(0.0)));
		goldGeneratorBroken.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("gold", 0, 3), getExpLore(5.0, 5, "gold", false)));
		inventories.put("gold0", goldGeneratorBroken);
		goldGeneratorLvl1.setItem(11, newItem(Material.GOLD_INGOT, 1, getMaterialName("gold", 1), getMarialLore(5.0)));
		goldGeneratorLvl1.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("gold", 1, 4), getExpLore(3.5,10,"gold", false)));
		inventories.put("gold1", goldGeneratorLvl1);
		goldGeneratorLvl2.setItem(11, newItem(Material.GOLD_INGOT, 1, getMaterialName("gold", 2), getMarialLore(3.5)));
		goldGeneratorLvl2.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("gold", 2, 4), getExpLore(2.0,10,"diamond", false)));
		inventories.put("gold2", goldGeneratorLvl2);
		goldGeneratorLvl3.setItem(11, newItem(Material.GOLD_INGOT, 1, getMaterialName("gold", 3), getMarialLore(2.0)));
		goldGeneratorLvl3.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("gold", 3, 4), getExpLore(1.0,25,"diamond", false)));
		inventories.put("gold3", goldGeneratorLvl3);
		goldGeneratorLvl4.setItem(11, newItem(Material.GOLD_INGOT, 1, getMaterialName("gold", 4), getMarialLore(1.0)));
		goldGeneratorLvl4.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("gold", 4, 4), getExpLore(0.0,0,"diamond", true)));
		inventories.put("gold4", goldGeneratorLvl4);

		ironGeneratorBroken.setItem(11, newItem(Material.IRON_INGOT, 1, getMaterialName("iron", 0), getMarialLore(0.0)));
		ironGeneratorBroken.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("iron", 0, 4), getExpLore(1.0,10,"iron", false)));
		inventories.put("iron0", ironGeneratorBroken);
		ironGeneratorLvl1.setItem(11, newItem(Material.IRON_INGOT, 1, getMaterialName("iron", 1), getMarialLore(1.0)));
		ironGeneratorLvl1.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("iron", 1, 4), getExpLore(0.75,20,"iron", false)));
		inventories.put("iron1", ironGeneratorLvl1);
		ironGeneratorLvl2.setItem(11, newItem(Material.IRON_INGOT, 1, getMaterialName("iron", 2), getMarialLore(0.75)));
		ironGeneratorLvl2.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("iron", 2, 4), getExpLore(0.5,10,"gold", false)));
		inventories.put("iron2", ironGeneratorLvl2);
		ironGeneratorLvl3.setItem(11, newItem(Material.IRON_INGOT, 1, getMaterialName("iron", 3), getMarialLore(0.5)));
		ironGeneratorLvl3.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("iron", 3, 4), getExpLore(0.25,50,"gold", false)));
		inventories.put("iron3", ironGeneratorLvl3);
		ironGeneratorLvl4.setItem(11, newItem(Material.IRON_INGOT, 1, getMaterialName("iron", 4), getMarialLore(0.25)));
		ironGeneratorLvl4.setItem(15, newItem(Material.EXP_BOTTLE, 1, getExpName("iron", 4, 4), getExpLore(0.0,0,"gold", true)));
		inventories.put("iron4", ironGeneratorLvl4);
	}

	public void openInventory(Player player, String type, int level) {
		player.openInventory(inventories.get(type + level));
	}

	private ItemStack newItem(Material material, int amount, String displayName, List<String> lore) {
		ItemStack is = new ItemStack(material, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(displayName);
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

	private String getMaterialName(String type, int level) {
		String s = "";
		if(type.equalsIgnoreCase("iron")) {
			s += ChatColor.GRAY + ChatColor.BOLD.toString() + "Iron " + ChatColor.YELLOW + ChatColor.BOLD + " generator - ";
		} else if(type.equalsIgnoreCase("gold")) {
			s += ChatColor.GOLD + ChatColor.BOLD.toString() + "Gold " + ChatColor.YELLOW + ChatColor.BOLD + " generator - ";
		} else if(type.equalsIgnoreCase("diamond")) {
			s += ChatColor.AQUA + ChatColor.BOLD.toString() + "Diamond " + ChatColor.YELLOW + ChatColor.BOLD + " generator - ";
		}
		if(level == 0) {
			s += ChatColor.YELLOW + ChatColor.BOLD.toString() + "Broken";
		} else {
			s += ChatColor.YELLOW + ChatColor.BOLD.toString() + "Level " + ChatColor.YELLOW + ChatColor.BOLD + level;
		}
		return s;
	}

	private String getExpName(String type, int currentLevel, int maxLevel) {
		String s = "";
		if(currentLevel == maxLevel) {
			s += ChatColor.YELLOW + ChatColor.BOLD.toString() + "Fully upgraded!";
		} else {
			if(type.equalsIgnoreCase("iron")) {
				s += ChatColor.YELLOW + ChatColor.BOLD.toString() + "Upgrade to: " + ChatColor.GRAY + ChatColor.BOLD + "Iron " + ChatColor.YELLOW + ChatColor.BOLD + "generator - Level " + (int)(currentLevel+1);
			} else if(type.equalsIgnoreCase("gold")) {
				s += ChatColor.YELLOW + ChatColor.BOLD.toString() + "Upgrade to: " + ChatColor.GOLD + ChatColor.BOLD + "Gold " + ChatColor.YELLOW + ChatColor.BOLD + "generator - Level " + (int)(currentLevel+1);
			} else if(type.equalsIgnoreCase("diamond")) {
				s += ChatColor.YELLOW + ChatColor.BOLD.toString() + "Upgrade to: " + ChatColor.AQUA + ChatColor.BOLD + "Diamond " + ChatColor.YELLOW + ChatColor.BOLD + "generator - Level " + (int)(currentLevel+1);
			}
		}
		return s;
	}

	private List<String> getMarialLore(double productionInterval) {
		List<String> lore = new ArrayList<String>();
		String s = ChatColor.DARK_PURPLE + "Production interval: " + ChatColor.GREEN + productionInterval + " seconds";
		lore.add(" ");
		lore.add(s);
		return lore;
	}

	private List<String> getExpLore(double productionInterval, int priceAmount, String priceType, boolean isMaxLevel) {
		List<String> lore = new ArrayList<String>();
		if(isMaxLevel) {
			lore.add(ChatColor.GRAY + "This generator has reached the max level");
		} else {
			lore.add(" ");
			lore.add(ChatColor.DARK_PURPLE + "Production interval: " + ChatColor.GREEN + productionInterval + " seconds");
			if(priceType.equalsIgnoreCase("iron")) {
				lore.add(ChatColor.DARK_PURPLE + "Cost: " + ChatColor.GRAY + priceAmount + " Iron");
			} else if(priceType.equalsIgnoreCase("gold")) {
				lore.add(ChatColor.DARK_PURPLE + "Cost: " + ChatColor.GOLD + priceAmount + " Gold");
			} else if(priceType.equalsIgnoreCase("diamond")) {
				lore.add(ChatColor.DARK_PURPLE + "Cost: " + ChatColor.AQUA + priceAmount + " Diamond");
			}
			lore.add(ChatColor.GOLD + "Click to upgrade");
		}
		return lore;
	}

	public HashMap<String, Inventory> getInventories() {
		return inventories;
	}

}

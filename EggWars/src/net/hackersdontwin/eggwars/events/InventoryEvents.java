package net.hackersdontwin.eggwars.events;

import net.hackersdontwin.eggwars.EggWars;
import net.hackersdontwin.eggwars.Generator;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;

public class InventoryEvents implements Listener {

    EggWars plugin;

    public InventoryEvents(EggWars plugin) {
        this.plugin = plugin;
    }

//    @EventHandler
//    public void onInventoryClick(InventoryClickEvent e) {
//    	if(e.getClickedInventory() == null) {
//    		return;
//		}
//		InventoryAction inventoryAction = e.getAction();
//        if(e.getClickedInventory().getTitle().contains("generator")) {
//            if(e.getRawSlot() == 15) {
//                onGeneratorUpgrade((Player) e.getWhoClicked());
//            }
//			e.setCancelled(true);
//		}
//
//		if(inventoryAction == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
//			e.setCancelled(true);
//		}
//    }

//    @EventHandler
//    public void onInventoryMove(InventoryMoveItemEvent e) {
//        if(e.getDestination() != null) {
//            if(e.getDestination().getTitle().contains("generator")) {
//                e.setCancelled(true);
//            }
//        }
//    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
		System.out.println("Test");
		System.out.println(e.getView().getTopInventory().getType());
		System.out.println(e.getView().getBottomInventory().getType());
        if(e.getView().getTopInventory().getType() == InventoryType.CHEST) {
			e.setCancelled(true);
        }
    }

    private void onGeneratorUpgrade(Player player) {
        Location location = plugin.getGeneratorManager().getCurrentOpenGenerator().get(player);
        Generator generator = plugin.getGeneratorManager().getGeneratorList().get(location);
        String type = generator.getType();
        int priceAmount = generator.getPriceToUpgrade();

        int amountInInventory = 0;
        for(ItemStack is : player.getInventory().getContents()) {
            if(is == null) {
                continue;
            }
            Material material = Material.matchMaterial(generator.getTypeForUpgrade().name().toUpperCase());
            if(is.getType() == material) {
                amountInInventory += is.getAmount();
            }
        }

        if(generator.getTypeForUpgrade() == Material.BEDROCK) {
            player.sendMessage(ChatColor.RED + "This generator is already fully upgraded.");
            return;
        }

        if(!(amountInInventory >= priceAmount)) {
            player.sendMessage(ChatColor.RED + "You need " + (priceAmount-amountInInventory) + " " + generator.getTypeForUpgrade().name().toUpperCase().replace("_", " ") + " more to upgrade this generator.");
            return;
        }


        switch(type) {
            case "diamond":
                removeItems(player.getInventory(), new ItemStack(generator.getTypeForUpgrade()), priceAmount);
                generator.setLevel(generator.getLevel()+1);
                if(generator.getLevel() == 1) {
                    generator.setPriceToUpgrade(10);
                    generator.setTypeForUpgrade(Material.DIAMOND);
                    generator.setMaxAmount(3);
                    plugin.getInventoryManager().openInventory(player, "diamond", 1);
                } else if(generator.getLevel() == 2) {
                    generator.setPriceToUpgrade(25);
                    generator.setTypeForUpgrade(Material.DIAMOND);
					generator.setMaxAmount(5);
                    plugin.getInventoryManager().openInventory(player, "diamond", 2);
                } else if(generator.getLevel() == 3) {
                    generator.setPriceToUpgrade(10000);
                    generator.setTypeForUpgrade(Material.BEDROCK);
					generator.setMaxAmount(10);
                    plugin.getInventoryManager().openInventory(player, "diamond", 3);
                }
                Firework fwDiamond = (Firework) Bukkit.getWorld("world").spawn(new Location(generator.getLocation().getWorld(), generator.getLocation().getX()+0.5, generator.getLocation().getY()+0.5, generator.getLocation().getZ()+0.5), Firework.class);
                FireworkEffect effectDiamond = FireworkEffect.builder().trail(true).flicker(true).withColor(Color.AQUA).with(FireworkEffect.Type.BALL).build();
                FireworkMeta fwmDiamond = fwDiamond.getFireworkMeta();
                fwmDiamond.clearEffects();
                fwmDiamond.addEffect(effectDiamond);
                fwmDiamond.setPower(0);
                fwDiamond.setFireworkMeta(fwmDiamond);
                fwDiamond.setSilent(true);
                fwDiamond.setVelocity(new Vector(0,0,0));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        fwDiamond.detonate();
                    }
                },1L);
                break;
            case "gold":
                removeItems(player.getInventory(), new ItemStack(generator.getTypeForUpgrade()), priceAmount);
                generator.setLevel(generator.getLevel()+1);
                if(generator.getLevel() == 1) {
                    generator.setPriceToUpgrade(10);
                    generator.setTypeForUpgrade(Material.GOLD_INGOT);
					generator.setMaxAmount(5);
                    plugin.getInventoryManager().openInventory(player, "gold", 1);
                } else if(generator.getLevel() == 2) {
                    generator.setPriceToUpgrade(10);
                    generator.setTypeForUpgrade(Material.DIAMOND);
					generator.setMaxAmount(7);
                    plugin.getInventoryManager().openInventory(player, "gold", 2);
                } else if(generator.getLevel() == 3) {
                    generator.setPriceToUpgrade(25);
                    generator.setTypeForUpgrade(Material.DIAMOND);
					generator.setMaxAmount(13);
                    plugin.getInventoryManager().openInventory(player, "gold", 3);
                } else if(generator.getLevel() == 4) {
                    generator.setPriceToUpgrade(10000);
                    generator.setTypeForUpgrade(Material.BEDROCK);
					generator.setMaxAmount(24);
                    plugin.getInventoryManager().openInventory(player, "gold", 4);
                }
                Firework fwGold = (Firework) Bukkit.getWorld("world").spawn(new Location(generator.getLocation().getWorld(), generator.getLocation().getX()+0.5, generator.getLocation().getY()+0.5, generator.getLocation().getZ()+0.5), Firework.class);
                FireworkEffect effectGold = FireworkEffect.builder().trail(true).flicker(true).withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build();
                FireworkMeta fwmGold = fwGold.getFireworkMeta();
                fwmGold.clearEffects();
                fwmGold.addEffect(effectGold);
                fwmGold.setPower(0);
                fwGold.setFireworkMeta(fwmGold);
                fwGold.setSilent(true);
                fwGold.setVelocity(new Vector(0,0,0));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        fwGold.detonate();
                    }
                },1L);
                break;
            case "iron":
                removeItems(player.getInventory(), new ItemStack(generator.getTypeForUpgrade()), priceAmount);
                generator.setLevel(generator.getLevel()+1);
                if(generator.getLevel() == 1) {
                    generator.setPriceToUpgrade(20);
                    generator.setTypeForUpgrade(Material.IRON_INGOT);
					generator.setMaxAmount(18);
                    plugin.getInventoryManager().openInventory(player, "iron", 1);
                } else if(generator.getLevel() == 2) {
                    generator.setPriceToUpgrade(20);
                    generator.setTypeForUpgrade(Material.GOLD_INGOT);
					generator.setMaxAmount(25);
                    plugin.getInventoryManager().openInventory(player, "iron", 2);
                } else if(generator.getLevel() == 3) {
                    generator.setPriceToUpgrade(50);
                    generator.setTypeForUpgrade(Material.GOLD_INGOT);
					generator.setMaxAmount(28);
                    plugin.getInventoryManager().openInventory(player, "iron", 3);
                } else if(generator.getLevel() == 4) {
                    generator.setPriceToUpgrade(10000);
                    generator.setTypeForUpgrade(Material.BEDROCK);
					generator.setMaxAmount(34);
                    plugin.getInventoryManager().openInventory(player, "iron", 4);
                }
                Firework fwIron = (Firework) Bukkit.getWorld("world").spawn(new Location(generator.getLocation().getWorld(), generator.getLocation().getX()+0.5, generator.getLocation().getY()+0.5, generator.getLocation().getZ()+0.5), Firework.class);
                FireworkEffect effectIron = FireworkEffect.builder().trail(true).flicker(true).withColor(Color.WHITE).with(FireworkEffect.Type.BALL).build();
                FireworkMeta fwmIron = fwIron.getFireworkMeta();
                fwmIron.clearEffects();
                fwmIron.addEffect(effectIron);
                fwmIron.setPower(0);
                fwIron.setFireworkMeta(fwmIron);
                fwIron.setSilent(true);
                fwIron.setVelocity(new Vector(0,0,0));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        fwIron.detonate();
                    }
                },1L);
        }

    }

    private void removeItems(Inventory inventory, ItemStack item, int toRemove) {
        if(inventory == null || item == null || toRemove <= 0) {
            return;
        }

        for(int i = 0; i < inventory.getSize(); i++) {
            ItemStack loopItem = inventory.getItem(i);
            if (loopItem == null || !item.isSimilar(loopItem)) {
                continue;
            }
            if (toRemove <= 0) {
                return;
            }
            if (toRemove < loopItem.getAmount()) {
                loopItem.setAmount(loopItem.getAmount() - toRemove);
                return;
            }
            inventory.clear(i);
            toRemove -= loopItem.getAmount();
        }
    }

}

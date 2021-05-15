package net.hackersdontwin.eggwars.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GenetaorEvents implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {

		if(e.getClickedBlock().getType() == Material.DIAMOND_BLOCK) {

		} else if(e.getClickedBlock().getType() == Material.GOLD_BLOCK) {

		} else if (e.getClickedBlock().getType() == Material.IRON_BLOCK) {

		}
	}


}

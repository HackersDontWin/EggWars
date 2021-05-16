package net.hackersdontwin.eggwars.events;

import net.hackersdontwin.eggwars.EggWars;
import net.hackersdontwin.eggwars.Generator;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class GeneratorEvents implements Listener {

	EggWars plugin;

	public GeneratorEvents(EggWars plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getClickedBlock() == null) {
			return;
		}

		try {
			if(e.getClickedBlock().getType() == Material.DIAMOND_BLOCK || e.getClickedBlock().getType() == Material.GOLD_BLOCK || e.getClickedBlock().getType() == Material.IRON_BLOCK) {
				Location location = e.getClickedBlock().getLocation();
				Generator generator = plugin.getGeneratorManager().getGeneratorList().get(location);
				plugin.getInventoryManager().openInventory(player, generator.getType(), generator.getLevel());
				plugin.getGeneratorManager().addCurrentOpenGenerator(player, location);
			}
		} catch (NullPointerException exception) {
			return;
		}
	}

	@EventHandler
	public void onEntityPickupItem(EntityPickupItemEvent e) {
		if(!(e.getEntity() instanceof Player)) {
			return;
		}

		ItemStack is = e.getItem().getItemStack();
		Location location = new Location(e.getItem().getWorld(), e.getItem().getLocation().getBlockX(), e.getItem().getLocation().getBlockY()-1, e.getItem().getLocation().getBlockZ());
		net.minecraft.server.v1_12_R1.ItemStack nmsIS = CraftItemStack.asNMSCopy(is);
		if(nmsIS.hasTag()) {
			NBTTagCompound tag = nmsIS.getTag();
			if(tag.hasKey("item")) {
				Generator generator = plugin.getGeneratorManager().getGeneratorList().get(location);
				generator.removeItem();
			}
		}

	}
}

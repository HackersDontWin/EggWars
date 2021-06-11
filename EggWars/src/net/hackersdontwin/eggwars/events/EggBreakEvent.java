package net.hackersdontwin.eggwars.events;

import net.hackersdontwin.eggwars.EggWars;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;

public class EggBreakEvent implements Listener {

	private EggWars ew;

	public EggBreakEvent(EggWars ew) {
		this.ew = ew;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getClickedBlock() == null) {
			return;
		}
		if(e.getClickedBlock().getType() == Material.DRAGON_EGG) {
			Location loc = e.getClickedBlock().getLocation();
			loc.getBlock().setType(Material.AIR);

			String teamName = ew.getTeamManager().getAllEggs().get(loc).getTeamName();
			Bukkit.getWorld("world").playSound(loc, Sound.ENTITY_LIGHTNING_THUNDER, 1.0F, 1.0F);
			Bukkit.broadcastMessage(ChatColor.GOLD + teamName + "'s egg has been destroyed!");

			ew.getTeamManager().getAllEggs().get(loc).setHasEgg(false);
		}
	}

	public <K,V> K getKey(Map<K, V> map, V value) {
		for(Map.Entry<K, V> entry : map.entrySet()) {
			if(value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

}

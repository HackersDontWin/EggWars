package net.hackersdontwin.eggwars.events;

import net.hackersdontwin.eggwars.EggWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
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
		if(e.getClickedBlock().getType() == Material.DRAGON_EGG) {
			Location loc = e.getClickedBlock().getLocation();
			loc.getBlock().setType(Material.AIR);

			String teamName = getKey(ew.getTeamManager().getEggLocations(), loc);
			Bukkit.broadcastMessage(ChatColor.GOLD + teamName + "'s egg has been destroyed!");

			ew.getTeamManager().removeRespawnableTeam(teamName);
			ew.getTeamManager().removeEgg(teamName);
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

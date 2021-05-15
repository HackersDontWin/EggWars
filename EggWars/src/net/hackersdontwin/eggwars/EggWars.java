package net.hackersdontwin.eggwars;

import net.hackersdontwin.eggwars.events.EggBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EggWars extends JavaPlugin {

	TeamManager tm;
	InventoryManager im;

	public void onEnable() {
		tm = new TeamManager();
		im = new InventoryManager();
		Bukkit.getPluginManager().registerEvents(new EggBreakEvent(this), this);
	}

	public TeamManager getTeamManager() {
		return tm;
	}

	public InventoryManager getInventoryManager() {
		return im;
	}

}

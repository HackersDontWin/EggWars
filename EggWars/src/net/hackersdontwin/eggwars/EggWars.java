package net.hackersdontwin.eggwars;

import net.hackersdontwin.eggwars.events.BlockEvents;
import net.hackersdontwin.eggwars.events.EggBreakEvent;
import net.hackersdontwin.eggwars.events.GeneratorEvents;
import net.hackersdontwin.eggwars.events.InventoryEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class EggWars extends JavaPlugin {

	TeamManager tm;
	InventoryManager im;
	GeneratorManager gm;
	BlockManager bm;

	public void onEnable() {
		tm = new TeamManager();
		im = new InventoryManager();
		gm = new GeneratorManager(this);
		bm = new BlockManager();
		Bukkit.getPluginManager().registerEvents(new EggBreakEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new GeneratorEvents(this), this);
		Bukkit.getPluginManager().registerEvents(new InventoryEvents(this), this);
		Bukkit.getPluginManager().registerEvents(new BlockEvents(this), this);

		gm.addGenerator("diamond", new Location(Bukkit.getWorld("world"), 0, 110, 11),2,5, 25, Material.DIAMOND);
		gm.addGenerator("gold", new Location(Bukkit.getWorld("world"), -14, 110, 15),3,13, 25, Material.DIAMOND);
		gm.addGenerator("iron", new Location(Bukkit.getWorld("world"), 15, 110, 13),3,27, 50, Material.GOLD_INGOT);

		gm.startGenerators();
	}

	public TeamManager getTeamManager() {
		return tm;
	}

	public InventoryManager getInventoryManager() {
		return im;
	}

	public GeneratorManager getGeneratorManager() {
		return gm;
	}

	public BlockManager getBlockManager() {
		return bm;
	}

}

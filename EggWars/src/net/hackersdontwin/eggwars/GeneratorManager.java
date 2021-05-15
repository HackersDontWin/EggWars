package net.hackersdontwin.eggwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;

public class GeneratorManager {

	private EggWars plugin;

	private HashMap<Location, String> generatorTypes = new HashMap<Location, String>();
	private HashMap<Location, Integer> generatorLevels = new HashMap<Location, Integer>();

	public GeneratorManager(EggWars plugin) {
		this.plugin = plugin;
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {

			}
		}, 5L);
	}

	public void addGenerator(String type, Location location, int level) {
		generatorTypes.put(location, type);
		generatorLevels.put(location, level);
	}

	public HashMap<Location, String> getGeneratorLocations() {
		return generatorTypes;
	}

	public HashMap<Location, Integer> getGeneratorLevels() {
		return generatorLevels;
	}

}

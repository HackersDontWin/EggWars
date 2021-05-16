package net.hackersdontwin.eggwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class GeneratorManager {

	private EggWars plugin;

	private HashMap<Location, Generator> generatorList = new HashMap<Location, Generator>();
	private HashMap<Player, Location> currentOpenGenerator = new HashMap<Player, Location>();

	public GeneratorManager(EggWars plugin) {
		this.plugin = plugin;
	}

	public HashMap<Location, Generator> getGeneratorList() {
		return generatorList;
	}



	public void addGenerator(String type, Location location, int level, int maxAmount, int priceToUpgrade, Material typeForUpgrade) {
		generatorList.put(location, new Generator(maxAmount,level,type, location, priceToUpgrade, typeForUpgrade));
	}


	public void removeCurrentOpenGenerator(Player player) {
		currentOpenGenerator.remove(player);
	}

	public void addCurrentOpenGenerator(Player player, Location location) {
		currentOpenGenerator.put(player, location);
	}

	public HashMap<Player, Location> getCurrentOpenGenerator() {
		return currentOpenGenerator;
	}

	public void startGenerators() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			double timePassed = 0;

			final double diamond1 = 10.0;
			final double diamond2 = 5.0;
			final double diamond3 = 2.5;
			final double gold1 = 5.0;
			final double gold2 = 3.5;
			final double gold3 = 2.0;
			final double gold4 = 1.0;
			final double iron1 = 1.0;
			final double iron2 = 0.75;
			final double iron3 = 0.5;
			final double iron4 = 0.25;

			@Override
			public void run() {

				Iterator iterator = generatorList.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry pair = (Map.Entry) iterator.next();
					Generator generator = (Generator) pair.getValue();
					if(generator.getType().equalsIgnoreCase("diamond")) {
						switch (generator.getLevel()) {
							case 1:
								if(diamond1%timePassed == 1) {
									generator.addItem();
									ItemStack diamond = generator.getItems().get(generator.getItems().size()-1);
									Bukkit.getWorld("world").dropItem(generator.getLocation().add(0.5,1.5,0.5), diamond);
								}
								break;
							case 2:
								if(diamond2%timePassed == 1) {
									generator.addItem();
									ItemStack diamond = generator.getItems().get(generator.getItems().size()-1);
									Bukkit.getWorld("world").dropItem(generator.getLocation().add(0.5,1.5,0.5), diamond);
								}
								break;
							case 3:
								if(diamond3%timePassed == 1) {
									generator.addItem();
									ItemStack diamond = generator.getItems().get(generator.getItems().size()-1);
									Bukkit.getWorld("world").dropItem(generator.getLocation().add(0.5,1.5,0.5), diamond);
								}
								break;
							default:
								break;
						}
					} else if(generator.getType().equalsIgnoreCase("gold")) {

					} else if(generator.getType().equalsIgnoreCase("iron")) {

					}
				}

				if(timePassed == 10.0) {
					timePassed = 0;
				}
				timePassed += 0.25;
			}
		}, 0L, 5L);
	}
}

package net.hackersdontwin.eggwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;

public class GeneratorManager {

	private EggWars plugin;
	private Random random;

	private HashMap<Location, Generator> generatorList = new HashMap<Location, Generator>();
	private HashMap<Player, Location> currentOpenGenerator = new HashMap<Player, Location>();

	public GeneratorManager(EggWars plugin) {
		this.plugin = plugin;
		random = new Random();
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
				double randomValueX = -0.3 + (0.3 - -0.3) * random.nextDouble();
				double randomValueZ = -0.3 + (0.3 - -0.3) * random.nextDouble();
				Iterator iterator = generatorList.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry pair = (Map.Entry) iterator.next();
					Generator generator = (Generator) pair.getValue();
					if(generator.getType().equalsIgnoreCase("diamond")) {
						switch (generator.getLevel()) {
							case 1:
								if(timePassed%diamond1 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack diamond = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), diamond).setVelocity(new Vector(0,0,0));
									}
								}
								break;
							case 2:
								if(timePassed%diamond2 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack diamond = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), diamond).setVelocity(new Vector(0,0,0));
									}
								}
								break;
							case 3:
								if(timePassed%diamond3 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack diamond = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), diamond).setVelocity(new Vector(0,0,0));
									}

								}
								break;
							default:
								break;
						}
					} else if(generator.getType().equalsIgnoreCase("gold")) {
						switch (generator.getLevel()) {
							case 1:
								if(timePassed%gold1 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack gold = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), gold).setVelocity(new Vector(0,0,0));
									}
								}
								break;
							case 2:
								if(timePassed%gold2 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack gold = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), gold).setVelocity(new Vector(0,0,0));
									}
								}
								break;
							case 3:
								if(timePassed%gold3 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack gold = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), gold).setVelocity(new Vector(0,0,0));
									}

								}
								break;
							case 4:
								if(timePassed%gold4 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack gold = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), gold).setVelocity(new Vector(0,0,0));
									}

								}
								break;
							default:
								break;
						}
					} else if(generator.getType().equalsIgnoreCase("iron")) {
						switch (generator.getLevel()) {
							case 1:
								if(timePassed%iron1 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack iron = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), iron).setVelocity(new Vector(0,0,0));
									}
								}
								break;
							case 2:
								if(timePassed%iron2 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack iron = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), iron).setVelocity(new Vector(0,0,0));
									}
								}
								break;
							case 3:
								if(timePassed%iron3 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack iron = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), iron).setVelocity(new Vector(0,0,0));
									}

								}
								break;
							case 4:
								if(timePassed%iron4 == 0) {
									if(generator.getCurrentAmount() < generator.getMaxAmount()) {
										generator.addItem();
										ItemStack iron = generator.getItems().get(generator.getItems().size()-1);
										Bukkit.getWorld("world").dropItem(new Location(generator.getLocation().getWorld(), generator.getLocation().getX() + 0.5 + randomValueX, generator.getLocation().getY() + 1, generator.getLocation().getZ() + 0.5 + randomValueZ), iron).setVelocity(new Vector(0,0,0));
									}

								}
								break;
							default:
								break;
						}
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

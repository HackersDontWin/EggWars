package net.hackersdontwin.eggwars.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hackersdontwin.eggwars.EggWars;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AdminCommand implements CommandExecutor {

	EggWars plugin;

	public AdminCommand(EggWars plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to do this!");
		}

		Player player = (Player) sender;

		if (label.equalsIgnoreCase("admin")) {
			if (!sender.hasPermission("eggwars.admin")) {
				sender.sendMessage(ChatColor.RED + "No permission");
			} else {
				if (args.length == 0) {
					player.sendMessage(ChatColor.RED + "Correct usage: /admin <addgenerator|team>");
					return true;
				}

				if (args[0].equalsIgnoreCase("addgenerator")) {
					addGenerator(args, player);
					return true;
				} else if (args[0].equalsIgnoreCase("team")) {
					addTeam(args, player);
					return true;
				} else {
					player.sendMessage(ChatColor.RED + "Correct usage: /admin <addgenerator|team>");
					return true;
				}
			}
		}
		return true;
	}

	private boolean addTeam(String[] args, Player player) {

		if (args.length == 1 || args.length > 5) {
			player.sendMessage(ChatColor.RED + "Correct usage: /admin team <add|set|remove>");
			return true;
		}

		if (args.length == 2) {
			if (args[1].equalsIgnoreCase("add")) {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team add <TeamName>");
			} else if (args[1].equalsIgnoreCase("set")) {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team set <TeamName> <shop|respawn|spawn|egg|color|teamsize>");
			} else if (args[1].equalsIgnoreCase("remove")) {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team remove <TeamName>");
			} else {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team <add|set>");
			}
			return true;
		}

		if (args.length == 3) {
			if (args[1].equalsIgnoreCase("add")) {
				if (doesTeamExist(args[2], plugin.getConfigManager().getConfig().getAsJsonArray("Teams"))) {
					player.sendMessage(ChatColor.RED + "The team with name " + args[2] + " already exists!");
					return true;
				}

				JsonObject newTeam = new JsonObject();
				newTeam.addProperty("name", args[2]);
				newTeam.addProperty("color", "WHITE");
				newTeam.addProperty("teamSize", 1);
				JsonObject eggLocation = new JsonObject();
				eggLocation.addProperty("x", 0);
				eggLocation.addProperty("y", 0);
				eggLocation.addProperty("z", 0);
				newTeam.add("eggLocation", eggLocation);
				JsonObject shopLocation = new JsonObject();
				shopLocation.addProperty("x", 0);
				shopLocation.addProperty("y", 0);
				shopLocation.addProperty("z", 0);
				shopLocation.addProperty("yaw", 0);
				shopLocation.addProperty("pitch", 0);
				newTeam.add("shopLocation", shopLocation);
				JsonObject respawnLocation = new JsonObject();
				respawnLocation.addProperty("x", 0);
				respawnLocation.addProperty("y", 0);
				respawnLocation.addProperty("z", 0);
				respawnLocation.addProperty("yaw", 0);
				respawnLocation.addProperty("pitch", 0);
				newTeam.add("respawnLocation", respawnLocation);
				JsonArray spawnLocations = new JsonArray();
				newTeam.add("spawnLocations", spawnLocations);
				plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().add(newTeam);
				plugin.getConfigManager().save();
			} else if (args[1].equalsIgnoreCase("set")) {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team set <TeamName> <shop|respawn|spawn|egg|color|teamsize>");
				return true;
			} else if (args[1].equalsIgnoreCase("remove")) {
				plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().remove(getExistingTeam(args[2]));
				plugin.getConfigManager().save();
				return true;
			} else if(args[1].equalsIgnoreCase("get")) {
				JsonObject team = getExistingTeam(args[2]);
				player.sendMessage(ChatColor.GREEN + "Information of team " + ChatColor.valueOf(team.get("color").getAsString()) + args[2] + ":\n" + ChatColor.GREEN +
						"Name: " + ChatColor.valueOf(team.get("color").getAsString()) + team.get("name").getAsString() + "\n" + ChatColor.GREEN +
						"Color: " + ChatColor.valueOf(team.get("color").getAsString()) + team.get("color").getAsString() + "\n" + ChatColor.GREEN +
						"Team Size: " + team.get("teamSize").getAsInt() + "\n" +
						"Egg Location: " + team.get("eggLocation").getAsJsonObject().get("x") + " " + team.get("eggLocation").getAsJsonObject().get("y") + " " + team.get("eggLocation").getAsJsonObject().get("z") + "\n" +
						"Shop Location: " + team.get("shopLocation").getAsJsonObject().get("x") + " " + team.get("shopLocation").getAsJsonObject().get("y") + " " + team.get("shopLocation").getAsJsonObject().get("z") + " " + team.get("shopLocation").getAsJsonObject().get("yaw") + " " + team.get("shopLocation").getAsJsonObject().get("pitch") + "\n" +
						"Respawn Location: " + team.get("respawnLocation").getAsJsonObject().get("x") + " " + team.get("respawnLocation").getAsJsonObject().get("y") + " " + team.get("respawnLocation").getAsJsonObject().get("z") + " " + team.get("respawnLocation").getAsJsonObject().get("yaw") + " " + team.get("respawnLocation").getAsJsonObject().get("pitch") + "\n"
						);
				for(int i = 0; i < team.get("spawnLocations").getAsJsonArray().size(); i++) {
					JsonObject spawnLocationObj = (JsonObject) team.get("spawnLocations").getAsJsonArray().get(i);
					player.sendMessage(ChatColor.GREEN + "Spawn " + i + ": \n" +
							"    x: " + spawnLocationObj.get("x") + "\n" +
							"    y: " + spawnLocationObj.get("y") + "\n" +
							"    z: " + spawnLocationObj.get("z") + "\n" +
							"    yaw: " + spawnLocationObj.get("yaw") + "\n" +
							"    pitch: " + spawnLocationObj.get("pitch") + "\n");
				}
			} else {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team <add|set|get>");
				return true;
			}
		}

		if (args.length == 4) {
			if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove")) {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team add|remove <TeamName>");
				return true;
			} else if (args[1].equalsIgnoreCase("set")) {
				if (doesTeamExist(args[2], plugin.getConfigManager().getConfig().getAsJsonArray("Teams"))) {
					if (args[3].equalsIgnoreCase("egg")) {
						JsonObject team = getExistingTeam(args[2]);
						JsonObject eggLocation = new JsonObject();
						eggLocation.addProperty("x", player.getLocation().getBlockX());
						eggLocation.addProperty("y", player.getLocation().getBlockY());
						eggLocation.addProperty("z", player.getLocation().getBlockZ());
						team.add("eggLocation", eggLocation);

						plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().remove(getExistingTeamIndex(args[2], plugin.getConfigManager().getConfig().getAsJsonArray("Teams")));
						plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().add(team);
						plugin.getConfigManager().save();
						return true;
					} else if (args[3].equalsIgnoreCase("shop")) {
						JsonObject team = getExistingTeam(args[2]);
						JsonObject shopLocation = new JsonObject();
						shopLocation.addProperty("x", player.getLocation().getX());
						shopLocation.addProperty("y", player.getLocation().getY());
						shopLocation.addProperty("z", player.getLocation().getZ());
						shopLocation.addProperty("pitch", player.getLocation().getPitch());
						shopLocation.addProperty("yaw", player.getLocation().getYaw());
						team.add("shopLocation", shopLocation);

						plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().remove(getExistingTeamIndex(args[2], plugin.getConfigManager().getConfig().getAsJsonArray("Teams")));
						plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().add(team);
						plugin.getConfigManager().save();
						return true;
					} else if (args[3].equalsIgnoreCase("respawn")) {
						JsonObject team = getExistingTeam(args[2]);
						JsonObject respawnLocation = new JsonObject();
						respawnLocation.addProperty("x", player.getLocation().getX());
						respawnLocation.addProperty("y", player.getLocation().getY());
						respawnLocation.addProperty("z", player.getLocation().getZ());
						respawnLocation.addProperty("pitch", player.getLocation().getPitch());
						respawnLocation.addProperty("yaw", player.getLocation().getYaw());
						team.add("respawnLocation",respawnLocation);
						plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().remove(getExistingTeamIndex(args[2], plugin.getConfigManager().getConfig().getAsJsonArray("Teams")));
						plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().add(team);
						plugin.getConfigManager().save();
						return true;
					} else if (args[3].equalsIgnoreCase("spawn")) {
						if (!(getExistingTeam(args[2]).get("spawnLocations").getAsJsonArray().size() >= getExistingTeam(args[2]).get("teamSize").getAsInt())) {
							JsonObject team = getExistingTeam(args[2]);

							JsonArray spawnLocations = team.get("spawnLocations").getAsJsonArray();
							for(JsonElement element : spawnLocations) {
								JsonObject spawnLocationObj = (JsonObject) element;
								if(spawnLocationObj.get("x").getAsDouble() == player.getLocation().getX()
										&& spawnLocationObj.get("y").getAsDouble() == player.getLocation().getY()
										&& spawnLocationObj.get("z").getAsDouble() == player.getLocation().getZ()) {
									player.sendMessage(ChatColor.RED + "You already have a spawn location set at this location!");
									return true;
								}
							}

							JsonObject spawnLocation = new JsonObject();
							spawnLocation.addProperty("x", player.getLocation().getX());
							spawnLocation.addProperty("y", player.getLocation().getY());
							spawnLocation.addProperty("z", player.getLocation().getZ());
							spawnLocation.addProperty("pitch", player.getLocation().getPitch());
							spawnLocation.addProperty("yaw", player.getLocation().getYaw());
							spawnLocations.add(spawnLocation);

							plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().remove(getExistingTeamIndex(args[2], plugin.getConfigManager().getConfig().getAsJsonArray("Teams")));
							plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().add(team);
							plugin.getConfigManager().save();
						} else {
							player.sendMessage(ChatColor.RED + "Cannot add another spawn location since adding a new one would make it greater than the team size");
						}
						return true;
					}
				} else {
					player.sendMessage(ChatColor.RED + "The team " + args[2] + " doesn't exist!");
					return true;
				}
			} else {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team <add|set>");
				return true;
			}
		}

		if (args.length == 5) {
			if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove")) {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team add|remove <TeamName>");
				return true;
			} else if (args[1].equalsIgnoreCase("set")) {
				if (doesTeamExist(args[2], plugin.getConfigManager().getConfig().getAsJsonArray("Teams"))) {
					if (args[3].equalsIgnoreCase("color") || args[3].equalsIgnoreCase("teamsize")) {
						if (args[3].equalsIgnoreCase("color")) {
							JsonObject team = getExistingTeam(args[2]);
							team.addProperty("color", args[4].toUpperCase());
							plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().remove(getExistingTeamIndex(args[2], plugin.getConfigManager().getConfig().getAsJsonArray("Teams")));
							plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().add(team);
							plugin.getConfigManager().save();
							return true;
						} else if (args[3].equalsIgnoreCase("teamsize")) {
							try {
								JsonObject team = getExistingTeam(args[2]);
								int teamSize = Integer.parseInt(args[4]);
								team.addProperty("teamSize", teamSize);
								plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().remove(getExistingTeamIndex(args[2], plugin.getConfigManager().getConfig().getAsJsonArray("Teams")));
								plugin.getConfigManager().getConfig().get("Teams").getAsJsonArray().add(team);
								plugin.getConfigManager().save();
								return true;
							} catch (Exception e) {
								player.sendMessage(ChatColor.RED + "The team size must be an integer!");
							}
						}
					} else {
						player.sendMessage(ChatColor.RED + "Correct usage: /admin team set <teamName> <color|teamsize>");
						return true;
					}
				} else {
					player.sendMessage(ChatColor.RED + "The team " + args[2] + " doesn't exist!");
					return true;
				}
			} else {
				player.sendMessage(ChatColor.RED + "Correct usage: /admin team <add|set|remove>");
				return true;
			}
		}

		return true;
	}

	private boolean addGenerator(String[] args, Player player) {
		if (args.length != 5) {
			player.sendMessage(ChatColor.RED + "Correct usage: /admin addgenerator <level> <maxAmount> <priceToUpgradeToNextLevel> <typeToUpgrade:IRON_INGOT|GOLD_INGOT|DIAMOND>");
			return true;
		}
		if (player.getTargetBlock(null, 5) == null || player.getTargetBlock(null, 5).getType() == Material.AIR) {
			player.sendMessage(ChatColor.RED + "You must be looking at a block to register a new generator");
			return true;
		}


		Block b = player.getTargetBlock(null, 5);
		Location loc = b.getLocation();
		int level = Integer.parseInt(args[1]);
		int maxAmount = Integer.parseInt(args[2]);
		int priceToUpgrade = Integer.parseInt(args[3]);
		String typeToUpgrade = args[4].toUpperCase();
		if (b.getType() == Material.DIAMOND_BLOCK) {
			JsonArray array = plugin.getConfigManager().getConfig().get("Generators").getAsJsonArray();
			JsonObject obj = new JsonObject();
			obj.addProperty("type", "diamond");
			obj.addProperty("level", level);
			obj.addProperty("maxAmount", maxAmount);
			JsonObject locObj = new JsonObject();
			locObj.addProperty("x", loc.getBlockX());
			locObj.addProperty("y", loc.getBlockY());
			locObj.addProperty("z", loc.getBlockZ());
			obj.add("location", locObj);
			obj.addProperty("priceToUpgrade", priceToUpgrade);
			obj.addProperty("typeToUpgrade", typeToUpgrade);
			array.add(obj);
			plugin.getConfigManager().save();
			player.sendMessage(ChatColor.GREEN + "Added new generator with the following properties:\n" +
					"Type: " + "Diamond\n" +
					"Level: " + level + "\n" +
					"Max Amount: " + maxAmount + "\n" +
					"Location: " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "\n" +
					"Price To Upgrade: " + priceToUpgrade + "\n" +
					"Type to Upgrade: " + typeToUpgrade);
		} else if (b.getType() == Material.GOLD_BLOCK) {
			JsonArray array = plugin.getConfigManager().getConfig().get("Generators").getAsJsonArray();
			JsonObject obj = new JsonObject();
			obj.addProperty("type", "gold");
			obj.addProperty("level", level);
			obj.addProperty("maxAmount", maxAmount);
			JsonObject locObj = new JsonObject();
			locObj.addProperty("x", loc.getBlockX());
			locObj.addProperty("y", loc.getBlockY());
			locObj.addProperty("z", loc.getBlockZ());
			obj.add("location", locObj);
			obj.addProperty("priceToUpgrade", priceToUpgrade);
			obj.addProperty("typeToUpgrade", typeToUpgrade);
			array.add(obj);
			plugin.getConfigManager().save();
			player.sendMessage(ChatColor.GREEN + "Added new generator with the following properties:\n" +
					"Type: " + "Gold\n" +
					"Level: " + level + "\n" +
					"Max Amount: " + maxAmount + "\n" +
					"Location: " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "\n" +
					"Price To Upgrade: " + priceToUpgrade + "\n" +
					"Type to Upgrade: " + typeToUpgrade);
		} else if (b.getType() == Material.IRON_BLOCK) {
			JsonArray array = plugin.getConfigManager().getConfig().get("Generators").getAsJsonArray();
			JsonObject obj = new JsonObject();
			obj.addProperty("type", "iron");
			obj.addProperty("level", level);
			obj.addProperty("maxAmount", maxAmount);
			JsonObject locObj = new JsonObject();
			locObj.addProperty("x", loc.getBlockX());
			locObj.addProperty("y", loc.getBlockY());
			locObj.addProperty("z", loc.getBlockZ());
			obj.add("location", locObj);
			obj.addProperty("priceToUpgrade", priceToUpgrade);
			obj.addProperty("typeToUpgrade", typeToUpgrade);
			array.add(obj);
			plugin.getConfigManager().save();
			player.sendMessage(ChatColor.GREEN + "Added new generator with the following properties:\n" +
					"Type: " + "Iron\n" +
					"Level: " + level + "\n" +
					"Max Amount: " + maxAmount + "\n" +
					"Location: " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "\n" +
					"Price To Upgrade: " + priceToUpgrade + "\n" +
					"Type to Upgrade: " + typeToUpgrade);
		} else {
			player.sendMessage(ChatColor.RED + "You can only use this command with iron, gold or diamond generators!");
			return true;
		}
		return true;
	}

	private boolean doesTeamExist(String teamName, JsonArray teams) {
		boolean result = false;
		for (JsonElement element : teams) {
			JsonObject team = (JsonObject) element;
			if (team.get("name").getAsString().equalsIgnoreCase(teamName)) {
				result = true;
				break;
			}
		}
		return result;
	}

	private JsonObject getExistingTeam(String teamName) {
		JsonArray teams = plugin.getConfigManager().getConfig().getAsJsonArray("Teams");
		for (JsonElement element : teams) {
			JsonObject team = (JsonObject) element;
			if (team.get("name").getAsString().equalsIgnoreCase(teamName)) {
				return team;
			}
		}
		return null;
	}

	private int getExistingTeamIndex(String teamName, JsonArray teams) {
		for (int i = 0; i < teams.size(); i++) {
			JsonObject team = teams.get(i).getAsJsonObject();
			if (team.get("name").getAsString().equalsIgnoreCase(teamName)) {
				return i;
			}
		}
		return -1;
	}
}

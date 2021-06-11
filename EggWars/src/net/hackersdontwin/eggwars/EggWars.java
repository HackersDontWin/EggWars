package net.hackersdontwin.eggwars;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hackersdontwin.eggwars.commands.AdminCommand;
import net.hackersdontwin.eggwars.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class EggWars extends JavaPlugin {

	TeamManager tm;
	InventoryManager im;
	GeneratorManager gm;
	BlockManager bm;
	ConfigManager cm;

	public void onEnable() {
		tm = new TeamManager();
		im = new InventoryManager();
		gm = new GeneratorManager(this);
		bm = new BlockManager();
		cm = new ConfigManager(this);

		cm.save();

		Bukkit.getPluginManager().registerEvents(new EggBreakEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new GeneratorEvents(this), this);
		Bukkit.getPluginManager().registerEvents(new InventoryEvents(this), this);
		Bukkit.getPluginManager().registerEvents(new BlockEvents(this), this);
		Bukkit.getPluginManager().registerEvents(new ShopEvents(), this);
		Bukkit.getPluginManager().registerEvents(new ConnectionEvents(), this);

		this.getCommand("admin").setExecutor(new AdminCommand(this));

		for(JsonElement element : cm.getConfig().get("Teams").getAsJsonArray()) {
			JsonObject team = (JsonObject) element;
			JsonObject egg = team.get("eggLocation").getAsJsonObject();
			Location eggLoc = new Location(
					Bukkit.getWorld("world"),
					egg.get("x").getAsInt(),
					egg.get("y").getAsInt(),
					egg.get("z").getAsInt());

			JsonObject shop = team.get("shopLocation").getAsJsonObject();
			Location shopLoc = new Location(
					Bukkit.getWorld("world"),
					shop.get("x").getAsInt(),
					shop.get("y").getAsInt(),
					shop.get("z").getAsInt());

			JsonObject respawn = team.get("respawnLocation").getAsJsonObject();
			Location respawnLoc = new Location(
					Bukkit.getWorld("world"),
					respawn.get("x").getAsInt(),
					respawn.get("y").getAsInt(),
					respawn.get("z").getAsInt());

			ArrayList<Location> spawnLocations = new ArrayList<Location>();
			for(JsonElement spawnLocElement : team.get("spawnLocations").getAsJsonArray()) {
				JsonObject spawnLocObj = (JsonObject) spawnLocElement;
				Location spawnLoc = new Location(
						Bukkit.getWorld("world"),
						spawnLocObj.get("x").getAsDouble(),
						spawnLocObj.get("y").getAsDouble(),
						spawnLocObj.get("z").getAsDouble(),
						spawnLocObj.get("yaw").getAsFloat(),
						spawnLocObj.get("pitch").getAsFloat()
				);
				spawnLocations.add(spawnLoc);
			}

			tm.addTeam(new Team(
					team.get("name").getAsString(),
					ChatColor.valueOf(team.get("color").getAsString()),
					eggLoc,
					shopLoc,
					respawnLoc,
					spawnLocations));
		}
		tm.setupTeams();

		for(JsonElement element : cm.getConfig().get("Generators").getAsJsonArray()) {
			JsonObject obj = (JsonObject) element;
			gm.addGenerator(
					obj.get("type").getAsString(),
					cm.jsonToLoc(obj.get("location").getAsJsonObject()),
					obj.get("level").getAsInt(),
					obj.get("maxAmount").getAsInt(),
					obj.get("priceToUpgrade").getAsInt(),
					Material.getMaterial(obj.get("typeToUpgrade").getAsString()));
		}

		//gm.startGenerators();
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

	public ConfigManager getConfigManager() {
		return cm;
	}

	public void onDisable() {
		for(Team team : tm.getTeams()) {
			for(org.bukkit.scoreboard.Team bukkitTeam : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
				if(bukkitTeam.getName().equalsIgnoreCase(team.getTeamName())) {
					bukkitTeam.unregister();
				}
			}
		}
	}

}

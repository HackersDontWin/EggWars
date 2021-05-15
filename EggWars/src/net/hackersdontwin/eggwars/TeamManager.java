package net.hackersdontwin.eggwars;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamManager {

	HashMap<String, List<Player>> teams = new HashMap<String, List<Player>>();
	HashMap<String, Location> eggLocations = new HashMap<String, Location>();
	HashMap<String, Location> respawnLocations = new HashMap<String, Location>();
	HashMap<String, Location> shopLocations = new HashMap<String, Location>();
	HashMap<String, List<Location>> spawnLocations = new HashMap<String, List<Location>>();
	List<String> respawnableTeams = new ArrayList<String>();

	public void addSpawnLocation(String teamName, Location loc) {
		if(spawnLocations.containsKey(teamName)) {
			List<Location> spawns = spawnLocations.get(teamName);
			spawnLocations.put(teamName, spawns);
		} else {
			List<Location> spawns = new ArrayList<Location>();
			spawnLocations.put(teamName, spawns);
		}
	}

	public void addShop(String teamName, Location loc) {
		shopLocations.put(teamName, loc);
	}

	public void addEgg(String teamName, Location loc) {
		eggLocations.put(teamName, loc);
	}

	public void removeEgg(String teamName) {
		eggLocations.remove(teamName);
	}

	public void addRespawnLocation(String teamName, Location loc) {
		eggLocations.put(teamName, loc);
	}

	public void addToTeam(String teamName, Player player) {
		if(teams.containsKey(teamName)) {
			List<Player> players = teams.get(teamName);
			players.add(player);
			teams.put(teamName, players);
		} else {
			List<Player> players = new ArrayList<Player>();
			players.add(player);
			teams.put(teamName, players);
		}
	}

	public void addRespawnableTeam(String teamName) {
		respawnableTeams.add(teamName);
	}

	public void removeRespawnableTeam(String teamName) {
		respawnableTeams.remove(teamName);
	}

	public HashMap<String, Location> getRespawnLocations() {
		return respawnLocations;
	}

	public HashMap<String, List<Player>> getTeams() {
		return teams;
	}

	public HashMap<String, Location> getEggLocations() {
		return eggLocations;
	}

	public HashMap<String, Location> getShopLocations() {
		return shopLocations;
	}

	public HashMap<String, List<Location>> getSpawnLocations() {
		return spawnLocations;
	}

	public List<String> getRespawnableTeams() {
		return respawnableTeams;
	}

}

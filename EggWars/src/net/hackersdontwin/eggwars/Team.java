package net.hackersdontwin.eggwars;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {

	ArrayList<Player> players;
	String teamName;
	ChatColor chatColor;
	Location eggLocation;
	Location shopLocation;
	Location respawnLocation;
	ArrayList<Location> spawnLocations;
	boolean hasEgg;

	public Team(String teamName, ChatColor color, Location eggLocation, Location shopLocation, Location respawnLocation, ArrayList<Location> spawnLocations) {
		players = new ArrayList<Player>();
		this.teamName = teamName;
		this.chatColor = color;
		this.eggLocation = eggLocation;
		this.shopLocation = shopLocation;
		this.hasEgg = true;
		this.respawnLocation = respawnLocation;
		this.spawnLocations = spawnLocations;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public String getTeamName() {
		return teamName;
	}

	public Location getEggLocation() {
		return eggLocation;
	}

	public ChatColor getChatColor() {
		return chatColor;
	}

	public Location getShopLocation() {
		return shopLocation;
	}

	public void setHasEgg(boolean hasEgg) {
		this.hasEgg = hasEgg;
	}

	public boolean getHasEgg() {
		return hasEgg;
	}

	public Location getRespawnLocation() {
		return respawnLocation;
	}

	public ArrayList<Location> getSpawnLocations() {
		return spawnLocations;
	}

}

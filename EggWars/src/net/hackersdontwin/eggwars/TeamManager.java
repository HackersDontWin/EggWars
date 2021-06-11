package net.hackersdontwin.eggwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamManager {

	ArrayList<Team> teams;
	HashMap<Location, Team> allEggsToTeam;

	public TeamManager() {
		teams = new ArrayList<Team>();
		allEggsToTeam = new HashMap<Location, Team>();
	}

	public void setupTeams() {
		for(Team team : teams) {
			allEggsToTeam.put(team.eggLocation, team);

			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getMainScoreboard();
			org.bukkit.scoreboard.Team newTeam = board.registerNewTeam(team.getTeamName());
			newTeam.setColor(team.getChatColor());

			Bukkit.getWorld("world").spawn(team.getShopLocation(), Villager.class, villager -> {
				villager.setAI(false);
				villager.setProfession(Villager.Profession.NITWIT);
				villager.setInvulnerable(true);
				villager.setCollidable(false);
			});
		}
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void addTeam(Team team) {
		teams.add(team);
	}

	public void removeTeam(Team team) {
		teams.remove(team);
	}

	public HashMap<Location, Team> getAllEggs() {
		return allEggsToTeam;
	}

}

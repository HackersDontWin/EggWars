package net.hackersdontwin.eggwars;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.*;
import java.util.HashMap;

public class ConfigManager {

	EggWars plugin;

	private File file;
	private Gson gson;
	private JsonObject config;

	public ConfigManager(EggWars plugin) {
		this.file = new File(plugin.getDataFolder(), "config.json");
		this.plugin = plugin;
		this.gson = new Gson();
		reload();
	}

	public void reload() {
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			if(file.exists()) {
				this.config = gson.fromJson(new FileReader(this.file), JsonObject.class);
			} else {
				setupConfig();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setupConfig() {
		config = new JsonObject();
		JsonArray teamArray = new JsonArray();
		JsonArray generatorsArray = new JsonArray();

		config.add("Teams", teamArray);
		config.add("Generators", generatorsArray);
	}

	public boolean save() {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(gson.toJson(config));
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;

	}

	public Location jsonToLoc(JsonObject obj) {
		return new Location(Bukkit.getWorld("world"),
				obj.get("x").getAsInt(),
				obj.get("y").getAsInt(),
				obj.get("z").getAsInt());
	}

	public JsonObject locToJson(Location loc) {
		JsonObject jso = new JsonObject();
		jso.addProperty("x", loc.getX());
		jso.addProperty("y", loc.getY());
		jso.addProperty("z", loc.getZ());
		return jso;
	}

	public JsonObject getConfig() {
		return config;
	}

}

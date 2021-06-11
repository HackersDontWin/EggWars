package net.hackersdontwin.eggwars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionEvents implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
//		e.getPlayer().setCollidable(false);
	}

}

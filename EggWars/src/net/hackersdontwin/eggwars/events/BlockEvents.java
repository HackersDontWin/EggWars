package net.hackersdontwin.eggwars.events;

import net.hackersdontwin.eggwars.EggWars;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class BlockEvents implements Listener {

    private EggWars plugin;

    public BlockEvents(EggWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(!plugin.getBlockManager().getBlocksPlaced().contains(e.getBlock().getLocation())) {
            plugin.getBlockManager().addBlockPlaced(e.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(plugin.getBlockManager().getBlocksPlaced().contains(e.getBlock().getLocation())) {
            plugin.getBlockManager().removeBlockPlaced(e.getBlock().getLocation());
        } else {
            e.getPlayer().sendMessage(ChatColor.RED + "You can only break blocks that have been placed during the game.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        e.setCancelled(true);
        if(e.isCancelled()) {
            e.getBlock().getState().update(false, false);
        }
    }

}

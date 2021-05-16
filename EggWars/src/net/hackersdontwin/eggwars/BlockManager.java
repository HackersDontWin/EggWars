package net.hackersdontwin.eggwars;

import org.bukkit.Location;

import java.util.ArrayList;

public class BlockManager {

    private ArrayList<Location> blocksPlaced = new ArrayList<Location>();

    public ArrayList<Location> getBlocksPlaced() {
        return blocksPlaced;
    }

    public void addBlockPlaced(Location location) {
        blocksPlaced.add(location);
    }

    public void removeBlockPlaced(Location location) {
        blocksPlaced.remove(location);
    }

}

package eu.kingconquest.chestgui.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import eu.kingconquest.conquest.Main;
import eu.kingconquest.conquest.hook.Vault;

public class Validate{

	/**
	 * Check to see whether the player is within an outpost
	 * @param targetPlayer
	 * @param loc
	 * @return
	 */
	public static boolean isWithinVillageArea(Player targetPlayer, Location loc){
		if (targetPlayer.getLocation().distanceSquared(loc) <= Config.CapDistance
				.get(targetPlayer.getWorld())
				&& (targetPlayer.getLocation().getY() <= loc.getY()
				+ Config.CaptureMaxY.get(targetPlayer.getWorld()) && (targetPlayer
						.getLocation().getY() >= loc.getY()
						- Config.CaptureMinY.get(targetPlayer.getWorld())))){
			return true;
		}
		return false;
	}
	
	public static boolean isNull(Object object) {
		if (object == null)
			return true;
		return false;
	}
	
    public static void isNull(Object object, String error) {
        if (object == null) 
            throw new NullPointerException(error);
    }
    
    public static boolean notZero(int i) {
    	if (i > 0)
    		return true;
    	return false;
    }

    public static boolean hasPerm(Player p, String path){
    	if (Vault.perms.has(p, Main.getInstance().getName() + path))
			return true;
		return false; 
    }
}

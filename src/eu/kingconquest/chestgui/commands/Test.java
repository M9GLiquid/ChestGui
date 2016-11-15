package eu.kingconquest.chestgui.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import eu.kingconquest.chestgui.Main;
import eu.kingconquest.chestgui.core.gui.TpGui;

public class Test implements CommandExecutor{
	private static Player p;
	private static TpGui tg;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args){

		if (sender instanceof Player)
			p = (Player) sender;
			tg = new TpGui();
			open(p);
		return false;
	}
	
	private static void open(Player p){
        new BukkitRunnable() {
            @Override
            public void run() {
            	tg.open(p);
            }
        }.runTaskLater(Main.getInstance(), 1);
	}
}

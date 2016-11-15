package eu.kingconquest.chestgui;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import eu.kingconquest.chestgui.commands.Test;
import eu.kingconquest.chestgui.core.gui.ChestGui;
import eu.kingconquest.chestgui.listener.ChestGuiListener;

public class Main extends JavaPlugin implements Listener{
	private static Main instance;

	@Override
	public void onEnable(){
		instance = this;
		
		onCommand();
		this.getServer().getPluginManager().registerEvents(new ChestGuiListener(), this);
	}

	@Override
	public void onDisable(){

	}

	public void onCommand(){
		this.getCommand("c").setExecutor(new Test());
		this.getCommand("cg").setExecutor(new Test());
		this.getCommand("chest").setExecutor(new Test());
	}

	@EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        ChestGui.openInventories.remove(playerUUID);
    }
	
	public static Main getInstance(){
		return instance;
	}
}
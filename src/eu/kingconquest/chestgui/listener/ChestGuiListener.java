package eu.kingconquest.chestgui.listener;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import eu.kingconquest.chestgui.event.ChestGui;

public class ChestGuiListener implements Listener{
	
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (!(e.getWhoClicked() instanceof Player))
            return;
        
        Player p = (Player) e.getWhoClicked();

        UUID inventoryUUID = ChestGui.openInventories.get(p.getUniqueId());
        if (inventoryUUID != null){
            e.setCancelled(true);
            ChestGui GUI = ChestGui.getInventoriesByUUID().get(inventoryUUID);
            ChestGui.onGuiAction action = GUI.getActions().get(e.getSlot());
          
            if (action != null)
                action.click(p);
        }
    }
    
    @EventHandler
    public void onClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        UUID playerUUID = player.getUniqueId();
      
        ChestGui.openInventories.remove(playerUUID);
    }
}

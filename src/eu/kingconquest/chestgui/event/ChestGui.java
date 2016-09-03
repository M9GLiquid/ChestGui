package eu.kingconquest.chestgui.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class ChestGui{
	private int invSize = 9;
	private String title = "";
    private UUID uniqueID;
	private Inventory inventory;
    private Map<Integer, onGuiAction> actions;
    public static Map<UUID, ChestGui> inventoriesByUUID = new HashMap<>();
    public static Map<UUID, UUID> openInventories = new HashMap<>();
	private static String[] ToolTip;
	
    /**
     * Greate a Chest GUI
     * @param invSize - int
     * @param invName - String
     */
	public ChestGui(){
	}
	
	public void create(int invSize) {
		create(invSize, getTitle());
	}
	public void create(int invSize, String invName) {
		
		//Create the ChestGui
		setChestSlots(invSize);
        generateUUID();
        inventory = Bukkit.createInventory(null, this.invSize, this.title);
        actions = new HashMap<>();
        inventoriesByUUID.put(getUuid(), this);
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	public interface onGuiAction{
        void click(Player p);
    }

	public void open(Player p){
        p.openInventory(inventory);
        openInventories.put(p.getUniqueId(), getUuid());
    }
	public void close(){
        for (Player p : Bukkit.getOnlinePlayers()){
            UUID u = openInventories.get(p.getUniqueId());
            if (u.equals(getUuid())){
                p.closeInventory();
            }
        }
        inventoriesByUUID.remove(getUuid());
    }

//Getters
	public UUID getUuid(){
		return uniqueID;
	}
	public String getTitle(){
		return this.title;
	}
	public static Map<UUID, ChestGui> getInventoriesByUUID() {
        return inventoriesByUUID;
    }
    public static Map<UUID, UUID> getOpenInventories() {
        return openInventories;
    }
    public Map<Integer, onGuiAction> getActions() {
        return actions;
    }
    public int getChestSlots(){
		return invSize;
	}
	
//Setters
    public void setChestSlots(int i){
		this.invSize =  getSlotsFromItems(i);
	}
	public void setItem(int slot, ItemStack stack){
        setItem(slot, stack, null, null, null);
    }
	public void setItem(int slot, ItemStack stack, onGuiAction action){
        setItem(slot, stack, action, null, null);
    }
	public void setItem(int slot, ItemStack stack, onGuiAction action, String itemName){
        setItem(slot, stack, action, itemName, null);
    }
	public void setItem(int slot, ItemStack stack, onGuiAction action, String itemName, String[] toolTip){
		ToolTip = toolTip;
        ItemMeta itemMeta = stack.getItemMeta();
		
		if (itemName != null)
			itemMeta.setDisplayName(itemName);
		if (ToolTip != null)
			itemMeta.setLore(Arrays.asList(ToolTip));
        
		stack.setItemMeta(itemMeta);
		inventory.setItem(slot, stack);
        if (action != null)
            actions.put(slot, action);
    }
	public void setTitle(String title){
		this.title = title;
	}
    
//Generator
	public void generateUUID(){
		uniqueID = UUID.randomUUID();
	}
	public int getSlotsFromItems(int i) {
    	if (i > 0 && i <= 9)
    		return 9;
    	else if (i > 9 && i <= 18)
    		return 18;
    	else if (i > 18 && i <= 27)
    		return 27;
    	else if (i > 27 && i <= 36)
    		return 36;
    	else if (i > 36 && i <= 45)
    		return 45;
    	else if (i > 45 && i <= 54)
    		return 54;
    	else if (i > 54 || i < 0)
    		return 54;
		return i;
	}
}

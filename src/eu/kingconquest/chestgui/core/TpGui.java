package eu.kingconquest.chestgui.core;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import eu.kingconquest.chestgui.core.gui.ChestGui;

// Example 
public class TpGui extends ChestGui{
	private int pageNr = 0;
	private int maxPageNr = 0;
	private int currentItem =  0;
	private int items = 100;
	private int itemsPerPage = 45;
	
	public TpGui(){
		super();
		setItems(items);
		setMaxPageNr();
		
		create(getItems());
        displayItems();
	}
	
	private void displayItems(){
        setItem(0, new ItemStack(Material.SKULL_ITEM), player -> {
            close();
        }, "§4Player Information", new String[] {"§1-----------------", "§4Economy"});
        
        setItem(8, new ItemStack(Material.BARRIER), player -> {
            close();
        }, "§4Close!", new String[] {"§1-----------------", "§cClick to close!"});
        
		if (getPageNr() > 0)
			previous();
		
		System.out.println(getItems() + " :: " + getOffset());
		if (getItems() > getOffset())
			next();
		
		for(int i = 9; i < 54; i++) {
			if (currentItem > getItems())
					return;
	        setItem(i, new ItemStack(Material.CHEST), player -> {
	            close();
	        }, "§2Test: " + i, new String[] {"§1-----------------", "§4" + currentItem++});
		}
		
	}
	
	private  void next(){
        setItem(5, new ItemStack(Material.CHORUS_FRUIT_POPPED), player -> {
    		pageNr++;
    		System.out.println(currentItem);
        	clearSlots();
    		displayItems();
        }, "§2>>", new String[] {"§1-----------------", "§4 Page " + (getPageNr() + 1) + " >>"});
	}

	private void previous(){
        setItem(3, new ItemStack(Material.CHORUS_FRUIT), player -> {
    		pageNr--;
    		System.out.println(currentItem);
    		currentItem = getPageNr() * itemsPerPage;
        	clearSlots();
    		displayItems();
        }, "§2<<", new String[] {"§1-----------------", "§4 Page " + (getPageNr() - 1) + " <<"});
	}
	
	private void clearSlots(){
		for(int i = 1; i < 54; i++) {
	        setItem(i, new ItemStack(Material.AIR), player -> {
	        });
		}
	}

//Getter's
	public int getItems() {
		return this.items;
	}
	private int getOffset() {
		return (pageNr + 1) * itemsPerPage;
	}
	
//Setter's
	private int setMaxPageNr(){
		//Set max page nr
		int i = Math.floorDiv(getItems(), 54); 
		if (getItems() % 54 != 0)
			i++;
		
		return i;
	}
	public void setItems(int items) {
		this.items = items;
	}
	public int getPageNr(){
		return pageNr;
	}
	public int getMaxPageNr(){
		return maxPageNr;
	}
}

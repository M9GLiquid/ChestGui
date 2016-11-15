package eu.kingconquest.chestgui.core.gui;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

// Example 
public class TpGui extends ChestGui{
	private ArrayList<Player> targets = new ArrayList<Player>();
	private Player p;
	
	public TpGui(Player p){
		super();
		this.p = p;
		create();
	}

	@Override
	public void create(){
		createGui(p, "&6Test Gui", 9);
		display();
	}
	
	@Override
	public void display(){
        setItem(0, new ItemStack(Material.SKULL_ITEM), player -> {
            close(player);
		},"&4Player Information", "§1-----------------"
        		+ "&4Economy");
        //Slot 3
		previous(this);
		//Slot 5
		next(this);
        //Slot 8
		closeButton();

		//backButton(ChestGui instance);
		
        //Slot MAIN
		for(int i = 9; i < 54; i++) {
			if (getCurrentItem() > (targets.size() -1) || getItems() == 0)
				break;
			setItem(8, new ItemStack(Material.ARROW), player -> {
				close(player);
			}, "§4<< Back","§1-----------------\n"
					+ "§cClick to go Home");
		}
		
	}


}

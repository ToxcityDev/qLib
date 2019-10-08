package me.toxcity.qlib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	UtilsAPI api = new UtilsAPI();
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "");
		
	}
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd,String label, String args[]) {

		/*
		 *Informational Command
		 */
		
		if (cmd.getName().equals("qlib")) {
			
			
			Player p = (Player) sender;
			p.sendMessage(ChatColor.RED + "Created by: " +  api.Creator() + " Version: " + api.Verion());
			
			
		}
		/*
		 *Items Command
		 */
		if (cmd.getName().equals("items")) {
			Player p = (Player) sender;
			//Check if user has certain permission
			if (!p.hasPermission("qLib.partneritems")) {
				p.sendMessage(ChatColor.RED +  "[qLib] " + ChatColor.GRAY + "You do not have the proper permissions.");
				
				return true;
				
				// If the player has the permission and is able to do the command
			}else {
				 
				//Gui 
				 Inventory gui = Bukkit.getServer().createInventory(p, 9, "Items");
				 //Grapple Item
				 ItemStack grap = new ItemStack(Material.FISHING_ROD);
	             ItemMeta grapm = grap.getItemMeta();
	             grapm.setDisplayName(ChatColor.RED + api.Grapple() + ChatColor.GRAY + " (Right Click)");
	             grap.setItemMeta(grapm);
	             gui.setItem(0, grap);
	             
	             
	             
	               
				 return true;
				
			}
		}
		return false;
			
			
			
			
			
			
			 
			
			
		}
	/*
	 * Grapple Event (Fishing Rod)
	 */
	
		 
		public void inventoryClick(InventoryClickEvent e){
		   
		         Player p = (Player) e.getWhoClicked();
		       
		          if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.RED + api.Grapple() + ChatColor.GRAY + " (Right Click)")){
		              e.setCancelled(true);
		              if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {
		                  return;
		              }
		             

		                 if(e.getSlot() == 0 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + api.Grapple() + ChatColor.GRAY + " (Right Click)"))){
		                	 
		                	 ItemStack grap = new ItemStack(Material.FISHING_ROD);
		    	             ItemMeta grapm = grap.getItemMeta();
		    	             grapm.setDisplayName(ChatColor.RED + api.Grapple() + ChatColor.GRAY + " (Right Click)");
		    	             grap.setItemMeta(grapm);
		    	             p.getInventory().addItem(grap);
		                 
		                 }
		          }
		}
		                 
		                   public void onPlayerFishingEvent(PlayerFishEvent event){
		                     Player p = event.getPlayer();
		                 Material item = p.getItemInHand().getType();
		                 if(item == Material.FISHING_ROD){
		                    
		                
		                    
		                     java.util.List<Entity> nearby = p.getNearbyEntities(50,50,50); 
		                     Entity hook = null; 
		                     for (Entity e : nearby) {
		                        
		                         if (e.getType() == EntityType.FISHING_HOOK) {
		                             hook = e;
		                             break;
		                         }
		                     }
		                     if (hook != null) {
		                         Location hookLocation = hook.getLocation();
		                         p.getLocation();
		                         p.teleport(hookLocation);
		                      
		                     } else {
		                         p.sendMessage(ChatColor.GREEN + "Sucessfully threw grappler!");
		                     }
		                 }
		             }
		                 
		               
		  }
	
		  
	


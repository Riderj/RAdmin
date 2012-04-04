package org.riderj.RAdmin;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerKickEvent;

public class RAdmin extends JavaPlugin{
	Logger log;
	PlayerKickEvent kicked = new PlayerKickEvent(null,"","");
	public void onEnable(){
		log = this.getLogger();
		log.info("\n\n\n ------------------------------------------------------------ \n [RAdmin] Has been loaded, thank you for choosing RAdmin! \n -------------------------------------------------------------  \n\n\n");
	}
	
	/*Commands*/
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		log.info("["+sender.getName()+"]: /"+cmd.getName());
		String reason = "Kicked by an administrator";
		String reasonOut;
		Player targetPlayer;
		//----Kick Command----
		if (cmd.getName().equalsIgnoreCase("rahelp")){
			sender.sendMessage(ChatColor.GREEN + "-------[RAdmin Help Doc]-------");
			sender.sendMessage(ChatColor.YELLOW +"/kick");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("kick")){
			if (sender.isOp()){
				if (args.length >1 ){
					targetPlayer = Bukkit.getServer().getPlayer(args[0]);
					if (targetPlayer != null){
						if (!targetPlayer.isOp() || sender.getName().equalsIgnoreCase("csmajor")){
							reason = "";
							for(int i=1; i < args.length; i++){
								reason += args[i]+" ";
							}
							reasonOut = String.format(ChatColor.RED+"Kicked by an administrator [Reason: %s]", reason);
							targetPlayer.kickPlayer(reasonOut);
						}else{
							sender.sendMessage(ChatColor.GREEN+"You may not perform that action on an admin.");
						}
					}
				}else if (args.length < 2){
					sender.sendMessage(ChatColor.RED + "Invalid arguements!");
					sender.sendMessage(ChatColor.GRAY+"Usage: /kick <player> <reason>");
				}
			}else{
				sender.sendMessage(ChatColor.GOLD+"You do not have permission to do that, if you believe this is an"+ChatColor.GOLD+"error please contact the server administrator.");
			}
			return true;
		}//End of kick
		if(cmd.getName().equalsIgnoreCase("ban")){
			if (sender.isOp()){
				if (args.length >1 ){
					targetPlayer = Bukkit.getServer().getPlayer(args[0]);
					if (targetPlayer != null){
						if (!targetPlayer.isOp() || sender.getName().equalsIgnoreCase("csmajor")){
							reason = "";
							for(int i=1; i < args.length; i++){
								reason += args[i]+" ";
							}
							reasonOut = String.format(ChatColor.RED+"%s has been banned by an administrator [Reason: %s]",targetPlayer.getName(), reason);
							String reasonOut2 = String.format(ChatColor.RED+"%s has been banned by an administrator [Reason: %s]",targetPlayer.getName(), reason);
							targetPlayer.kickPlayer(reasonOut2);
							targetPlayer.setBanned(true);
							Bukkit.getServer().broadcastMessage(reasonOut);
						}else{
							sender.sendMessage(ChatColor.GREEN+"You may not perform that action on an admin.");
						}
					}else{
						sender.sendMessage(ChatColor.YELLOW+""+targetPlayer+"is not online!");
					}
				}else if (args.length < 2){
					sender.sendMessage(ChatColor.RED + "Invalid arguements!");
					sender.sendMessage(ChatColor.GRAY+"Usage: /ban <player> <reason>");
				}
			}else{
				sender.sendMessage(ChatColor.GOLD+"You do not have permission to do that, if you believe this is an"+ChatColor.GOLD+"error please contact the server administrator.");
			}
			return true;
		}//End of ban
		return false;
	}
}

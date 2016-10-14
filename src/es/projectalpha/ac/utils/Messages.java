package es.projectalpha.ac.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.achievements.Achievements;
import es.projectalpha.ac.cooldowns.Cooldowns;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.shops.Shops;

public class Messages {

	private static AVCAPI api = new AVCAPI();

	//TODO: Messages to config.yml

	//Utils
	public static String prefix = ChatColor.GRAY + " || " + ChatColor.GREEN + "AC" + ChatColor.GRAY + " || ";
	public static String noPerms = prefix + ChatColor.RED + "You don't have permissions to do this";

	//Game
	public static String newCompany = prefix + ChatColor.GREEN + "You have been created your own company";
	public static String tpCompany = prefix + ChatColor.GREEN + "You has been teleported to your Company";
	public static String hasManager = prefix + ChatColor.RED + "Sorry, but you have already this manager";
	public static String hasShop = prefix + ChatColor.RED + "Sorry, but you have already this shop";
	public static String notHasManager = prefix + ChatColor.RED + "Sorry, but this person doesn't have this manager";
	public static String notHasAchievement = prefix + ChatColor.RED + "Sorry, but this person doesn't have this achievement";
	public static String notHasShop = prefix + ChatColor.RED + "Sorry, but this person doesn't have this shop";
	public static String noDatabase = prefix + ChatColor.RED + "Sorry, you are not in the database";
	public static String notEnoughMoney = prefix + ChatColor.RED + "Sorry, but you don't have enough money";
	public static String buyManager = prefix + ChatColor.GREEN + "You have been bought the manager of " + ChatColor.YELLOW;
	public static String buyShop = prefix + ChatColor.GREEN + "You have been bought the shop of " + ChatColor.YELLOW;
	public static String notEnoughMoneyToRestart = prefix + ChatColor.RED + "You don't have enough money to re-start the game";
	public static String alreadyPlaying = prefix + ChatColor.RED + "You are already playing";

	//Angels Info
	public static void sendAngelsInfo(Player p){
		p.sendMessage(" ");

		p.sendMessage(ChatColor.GREEN + "Your Angels " + ChatColor.YELLOW + api.getAngels().getAngels(p));
		p.sendMessage(ChatColor.GREEN + "Angels at game re-start " + ChatColor.YELLOW + api.getAngels().calculateAngels(p));

		p.sendMessage(" ");
	}

	public static void sendAngelsInfo(Player p, Player pl){
		p.sendMessage(" ");

		p.sendMessage(ChatColor.GREEN + pl.getName() + " Angels " + ChatColor.YELLOW + api.getAngels().getAngels(pl));
		p.sendMessage(ChatColor.GREEN + "Angels at game re-start " + ChatColor.YELLOW + api.getAngels().calculateAngels(pl));

		p.sendMessage(" ");
	}

	//Map Info
	public static void sendMapInfo(Player p){
		p.sendMessage(" ");

		p.sendMessage(ChatColor.GREEN + "Map created by " + ChatColor.RED + "SrJonh");

		p.sendMessage(" ");
	}

	//Achievements
	public static void newAchievement(Achievements at, Player p){
		p.sendMessage(" ");

		p.sendMessage(Messages.prefix + ChatColor.GREEN + "You have get a new achievement: " + ChatColor.YELLOW + at.getDispName());

		p.sendMessage(" ");
	}

	public static void remAchievement(Achievements at, Player p, Player pl){
		p.sendMessage(Messages.prefix + ChatColor.GREEN + "You have successfuly removed achievement " + ChatColor.YELLOW + at.getDispName() + ChatColor.GREEN + " to a player " + ChatColor.RED + pl.getName());
	}

	//Managers
	public static void remManager(Managers m, Player p, Player pl){
		p.sendMessage(Messages.prefix + ChatColor.GREEN + "You have successfuly removed manager " + ChatColor.YELLOW + m.getdataName() + ChatColor.GREEN + " to a player " + ChatColor.RED + pl.getName());
	}

	//Shops
	public static void remShops(Shops s, Player p, Player pl){
		p.sendMessage(Messages.prefix + ChatColor.GREEN + "You have successfuly removed shop " + ChatColor.YELLOW + s.toString().toLowerCase() + ChatColor.GREEN + " to a player " + ChatColor.RED + pl.getName());
	}

	//Location Debug
	public static String parseLoc(Location l){
		return "World: " + l.getWorld() + " X: " + l.getX() + " Y: " + l.getY() + " Z: " + l.getZ();
	}

	public static String parseLoc(Location l, Location l2){
		return "World: " + l.getWorld() + " - " + l2.getWorld() + " X: " + l.getX() + " - " + l2.getX() + " Y: " + l.getY() + " - " + l2.getY() + " Z: " + l.getZ() + " - " + l2.getZ();
	}

	//Progress Bar
	public static String getProgress(Player p, Location l, Shops shop){
		int time = 0;
		String name = api.getGame().progressBar.get(l);

		if(shop.getTimer() >= 100){
			time = (int) ((Cooldowns.getRemaining(p.getName(), name) * 100) / shop.getTimer());
		}else{
			time = (int) (Cooldowns.getRemaining(p.getName(), name) * 0.2);
		}

		if(time <= 0){
			return ChatColor.GRAY + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 0) && (time < 6)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 5) && (time < 11)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 10) && (time < 16)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 15) && (time < 21)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 20) && (time < 26)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 25) && (time < 31)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 30) && (time < 36)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 35) && (time < 41)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 40) && (time < 46)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 45) && (time < 51)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 50) && (time < 56)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 55) && (time < 61)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 60) && (time < 66)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 65) && (time < 71)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 70) && (time < 76)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍▍▍";
		}
		if((time > 75) && (time < 81)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍▍▍";
		}
		if((time > 80) && (time < 86)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍▍▍";
		}
		if((time > 85) && (time < 91)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍▍▍";
		}
		if((time > 90) && (time < 96)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍" + ChatColor.GRAY + ChatColor.BOLD + "▍▍";
		}
		if((time > 95) && (time < 101)){
			return ChatColor.RED + ChatColor.BOLD.toString() + "▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		return "Error";
	}
}

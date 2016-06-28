package es.projectalpha.ac;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import es.projectalpha.ac.cmd.Help;
import es.projectalpha.ac.events.BuildInteract;
import es.projectalpha.ac.events.ProtectWorld;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.game.Game;
import es.projectalpha.ac.utils.Messages;
import es.projectalpha.ac.utils.ServerVersion;
import es.projectalpha.ac.world.Generator;

public class AC extends JavaPlugin {

	private static AC plugin;

	public void onEnable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
		Bukkit.getConsoleSender().sendMessage(" ");

		plugin = this;

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Checking Server Version. . .");
		if (!ServerVersion.isMC110() && !ServerVersion.isMC19()) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Please, update your server to 1.9.X or 1.10.X to use this plugin");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if (ServerVersion.isMC18()) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Please, update your server to 1.9.X or 1.10.X to use this plugin, 1.8.X is not supported");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Checking Complete");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Checking and Creating files. . .");
		Files.setupFiles();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Setup Files Complete");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Registering Commands and Events. . .");
		regCMDs();
		regEvents();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Register Complete");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Loading Game. . .");
		Game.startTimer(this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Game Loaded");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AC enabled");
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AC Version: " + ChatColor.RED + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AC Autor: " + ChatColor.RED + getDescription().getAuthors().toString());
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "AC Utils: " + ChatColor.RED + "https://github.com/ProjectAlphaES/AdVenture-Capitalist");

		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
	}

	private void regEvents(){
		new BuildInteract(this);
		new ProtectWorld(this);
	}

	private void regCMDs(){
		getCommand("ac").setExecutor(new Help(this));
	}

	public static AC getPlugin(){
		return plugin;
	}

	//For Multiverse or Bukkit Settings
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new Generator();
	}
}

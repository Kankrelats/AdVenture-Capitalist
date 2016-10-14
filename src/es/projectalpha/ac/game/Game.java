package es.projectalpha.ac.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import es.projectalpha.ac.AVCAPI;
import es.projectalpha.ac.api.fancy.BossBarAPI;
import es.projectalpha.ac.api.fancy.HoloAPI;
import es.projectalpha.ac.cooldowns.Cooldowns;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.managers.Managers;
import es.projectalpha.ac.managers.SpawnManagers;
import es.projectalpha.ac.utils.Messages;

public class Game {

	public ArrayList<Player> playing = new ArrayList<Player>();

	public HashMap<Location, String> progressBar = new HashMap<Location, String>();

	public ArrayList<HoloAPI> holos = new ArrayList<HoloAPI>();

	//Utils
	private AVCAPI api;

	public void startTimer(){
		api = new AVCAPI();

		//Test
		System.out.println(playing.toString());
		System.out.println(progressBar.toString());
		System.out.println(holos.toString());
		//

		for(Player p : playing){

			//Destroy Hologram
			for(HoloAPI holo : holos){
				holo.destroy(p);
			}

			//Show Money
			int id = Files.players.getInt(p.getName() + ".id");

			double x = Files.locs.getDouble("id" + id + ".x");
			double y = Files.locs.getDouble("id" + id + ".y");
			double z = Files.locs.getDouble("id" + id + ".z");

			Location l = new Location(Bukkit.getWorld("ac"), x, y + 3, z);

			HoloAPI holo = new HoloAPI(l, "$ " + api.getCurrency().getMoney(p));
			holo.display(p);

			holos.add(holo);

			//Check if Enough Money
			for(Managers m : Managers.values()){
				if(api.getCurrency().getMoney(p) >= m.getPrice()){
					BossBarAPI.sendMessageToPlayerRecurring(ChatColor.GREEN + "You can buy " + ChatColor.RED + m.getName() + ChatColor.GREEN + " manager", 8, BarColor.WHITE, BarStyle.SOLID, p);
				}

				//Managers
				for(Player pl : api.getManagers().getPlayersWithManager(m)){
					if(Cooldowns.isCooling(pl.getName(), m.getName())){
						return;
					}

					for(Villager npc : SpawnManagers.npcs){
						if(npc.getName().equalsIgnoreCase(m.getManagerName())){
							progressBar.put(npc.getLocation().add(0, 3, 0), m.getManagerName());

							HoloAPI pro = new HoloAPI(npc.getLocation().add(0, 3, 0), Messages.getProgress(p, npc.getLocation().add(0, 3, 0), m.getShop()));

							pro.display(p);

							holos.add(pro);
						}
					}
					Cooldowns.add(pl.getName(), m.getName(), (long) m.getShop().getTimer(), System.currentTimeMillis());
				}
			}
		}
	}
}

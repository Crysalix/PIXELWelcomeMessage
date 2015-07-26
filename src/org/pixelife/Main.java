package org.pixelife;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;

public class Main extends Plugin {
	
	public String firstLine;
	public String secondLine;
	private int stay;
	
	public void onEnable()
	{
		loadConfig();
		getProxy().getPluginManager().registerListener(this, new EventListener());
	}
	
	public class EventListener implements net.md_5.bungee.api.plugin.Listener
	{
		@net.md_5.bungee.event.EventHandler
		public void onPlayerJoin(PostLoginEvent e)
		{	
			ProxiedPlayer player = e.getPlayer();
			
			firstLine = ChatColor.translateAlternateColorCodes('&', firstLine);
			secondLine = ChatColor.translateAlternateColorCodes('&', secondLine);
			
			String finalSecondLine = secondLine.replace("{DISPLAYNAME}", player.getName());
			
		    e.getPlayer().sendTitle(ProxyServer.getInstance().createTitle().reset().title(new ComponentBuilder(String.format(firstLine)).create()).subTitle(new ComponentBuilder(String.format(finalSecondLine)).create()).stay(stay));
		}
	}

	public void loadConfig()
	{
		ConfigurationLoader configurationLoader = new ConfigurationLoader(this, "config.yml");
    
		try
		{
			this.firstLine = configurationLoader.getConfiguration().getString("firstLine");			
			this.secondLine = configurationLoader.getConfiguration().getString("secondLine");
			
			this.stay = configurationLoader.getConfiguration().getInt("howLongStay");
			
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}

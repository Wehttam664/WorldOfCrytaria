package net.mayateck.GameCities;

import java.util.logging.Logger;

import net.mayateck.GameCities.OrgHandler.DataFetch;
import net.mayateck.GameCities.OrgHandler.ProtectionHandler;
import net.mayateck.GameCities.factory.FactoryHandler;

import org.bukkit.plugin.java.JavaPlugin;

public class GameCities extends JavaPlugin {
	public static final String head = "&8&l||============|[&eCrytarian Organizations&8&l]|============||&r";
	public static final String tag = "&8&l[&eWoC Orgs&8&l]&7 ";
	public static String version = "";
	public static Logger logger;
	
	public DataFetch fetcher = null;
	public ProtectionHandler protEventHandler = null;
	public FactoryHandler factoryHandler = null;
	
	@Override
	public void onEnable(){
		fetcher = new DataFetch(this);
		protEventHandler = new ProtectionHandler(this);
		factoryHandler = new FactoryHandler(this);
		CommandHandler cmdh = new CommandHandler(this, protEventHandler);
		getServer().getPluginManager().registerEvents(protEventHandler, this);
		getCommand("organization").setExecutor(cmdh);
		
		version = this.getDescription().getVersion();
		saveDefaultConfig();
		protEventHandler.loadBlockDataFromDisk();
		
		this.getConfig().addDefaults(this.getConfig());
		
		logger = getLogger();
		getLogger().info("Enabled WoC.Organizations.");
	}
	
	@Override
	public void onDisable(){
		protEventHandler.saveBlockDataToDisk();
		saveConfig();
		getLogger().info("Disabled WoC.Organizations");
	}
	
	public ProtectionHandler getProtHandler(){
		return protEventHandler;
	}
}

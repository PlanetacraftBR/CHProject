/**
 *  ProtocolLib - Bukkit server library that allows access to the Minecraft protocol.
 *  Copyright (C) 2012 Kristian S. Stangeland
 *
 *  This program is free software; you can redistribute it and/or modify it under the terms of the
 *  GNU General Public License as published by the Free Software Foundation; either version 2 of
 *  the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with this program;
 *  if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 *  02111-1307 USA
 */
package me.hub;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.executors.BukkitExecutors;
import com.comphenix.protocol.Application;
import com.comphenix.protocol.ProtocolConfig;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolLogger;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.async.AsyncFilterManager;
import com.comphenix.protocol.error.BasicErrorReporter;
import com.comphenix.protocol.error.DelegatedErrorReporter;
import com.comphenix.protocol.error.DetailedErrorReporter;
import com.comphenix.protocol.error.ErrorReporter;
import com.comphenix.protocol.error.Report;
import com.comphenix.protocol.error.ReportType;
import com.comphenix.protocol.injector.DelayedSingleTask;
import com.comphenix.protocol.injector.InternalManager;
import com.comphenix.protocol.injector.PacketFilterManager;
import com.comphenix.protocol.metrics.Statistics;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.reflect.compiler.BackgroundCompiler;
import com.comphenix.protocol.updater.Updater;
import com.comphenix.protocol.utility.ChatExtensions;
import com.comphenix.protocol.utility.EnhancerFactory;
import com.comphenix.protocol.utility.MinecraftVersion;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;

import me.acf.lobby.MagicChest.ChestMagic;
import me.hub.API.Util.BarAPI;
import me.hub.API.Util.UtilHolo;
import me.hub.API.Util.UtilTime;
import me.hub.NMS.BossBar.wither.BossBarFake;
import me.hub.atualizar.Update;
import me.hub.config.Config;
import me.libraryaddict.disguise.LibsDisguises;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.AgeableWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.ArrowWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.GuardianWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.HorseWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.MinecartWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.SkeletonWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.SpiderWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.TNTWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.TameableWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.ZombieWatcher;
import me.libraryaddict.disguise.utilities.DisguiseSound;
import me.libraryaddict.disguise.utilities.DisguiseValues;
import me.libraryaddict.disguise.utilities.FakeBoundingBox;
import me.libraryaddict.disguise.utilities.ReflectionManager;
import net.citizensnpcs.EventListen;
import net.citizensnpcs.Metrics;
import net.citizensnpcs.PaymentListener;
import net.citizensnpcs.Settings;
import net.citizensnpcs.Settings.Setting;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.CitizensPlugin;
import net.citizensnpcs.api.ai.speech.SpeechFactory;
import net.citizensnpcs.api.command.CommandContext;
import net.citizensnpcs.api.command.CommandManager;
import net.citizensnpcs.api.command.CommandManager.CommandInfo;
import net.citizensnpcs.api.command.Injector;
import net.citizensnpcs.api.event.CitizensDisableEvent;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.event.CitizensPreReloadEvent;
import net.citizensnpcs.api.event.CitizensReloadEvent;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.exception.NPCLoadException;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCDataStore;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.npc.SimpleNPCDataStore;
import net.citizensnpcs.api.scripting.EventRegistrar;
import net.citizensnpcs.api.scripting.ObjectProvider;
import net.citizensnpcs.api.scripting.ScriptCompiler;
import net.citizensnpcs.api.trait.TraitFactory;
import net.citizensnpcs.api.util.Messaging;
import net.citizensnpcs.api.util.NBTStorage;
import net.citizensnpcs.api.util.Storage;
import net.citizensnpcs.api.util.Translator;
import net.citizensnpcs.api.util.YamlStorage;
import net.citizensnpcs.commands.AdminCommands;
import net.citizensnpcs.commands.EditorCommands;
import net.citizensnpcs.commands.NPCCommands;
import net.citizensnpcs.commands.TemplateCommands;
import net.citizensnpcs.commands.TraitCommands;
import net.citizensnpcs.commands.WaypointCommands;
import net.citizensnpcs.editor.Editor;
import net.citizensnpcs.npc.CitizensNPCRegistry;
import net.citizensnpcs.npc.CitizensTraitFactory;
import net.citizensnpcs.npc.NPCSelector;
import net.citizensnpcs.npc.ai.speech.Chat;
import net.citizensnpcs.npc.ai.speech.CitizensSpeechFactory;
import net.citizensnpcs.npc.profile.ProfileFetcher;
import net.citizensnpcs.npc.skin.Skin;
import net.citizensnpcs.util.Messages;
import net.citizensnpcs.util.NMS;
import net.citizensnpcs.util.PlayerUpdateTask;
import net.citizensnpcs.util.StringHelper;
import net.citizensnpcs.util.Util;
import net.milkbowl.vault.economy.Economy;

/**
 * The main entry point for ProtocolLib.
 *
 * @author Kristian
 */
public class Main extends JavaPlugin implements Listener, CitizensPlugin {
	// Every possible error or warning report type
	public static Main plugin;
	public static String NomeDoServidor = "§f§lPlanetaCraft_BR";
	public static String site = "http://api.planetacraft.com.br";
    public static String servidor_ligado = "00/00/00 00:00:00";
	
	public static final ReportType REPORT_CANNOT_LOAD_CONFIG = new ReportType("Cannot load configuration");
	public static final ReportType REPORT_CANNOT_DELETE_CONFIG = new ReportType("Cannot delete old ProtocolLib configuration.");
	public static final ReportType REPORT_CANNOT_PARSE_INJECTION_METHOD = new ReportType("Cannot parse injection method. Using default.");

	public static final ReportType REPORT_PLUGIN_LOAD_ERROR = new ReportType("Cannot load ProtocolLib.");
	public static final ReportType REPORT_PLUGIN_ENABLE_ERROR = new ReportType("Cannot enable ProtocolLib.");

	public static final ReportType REPORT_METRICS_IO_ERROR = new ReportType("Unable to enable metrics due to network problems.");
	public static final ReportType REPORT_METRICS_GENERIC_ERROR = new ReportType("Unable to enable metrics due to network problems.");

	public static final ReportType REPORT_CANNOT_PARSE_MINECRAFT_VERSION = new ReportType("Unable to retrieve current Minecraft version. Assuming %s");
	public static final ReportType REPORT_CANNOT_DETECT_CONFLICTING_PLUGINS = new ReportType("Unable to detect conflicting plugin versions.");
	public static final ReportType REPORT_CANNOT_REGISTER_COMMAND = new ReportType("Cannot register command %s: %s");

	public static final ReportType REPORT_CANNOT_CREATE_TIMEOUT_TASK = new ReportType("Unable to create packet timeout task.");
	public static final ReportType REPORT_CANNOT_UPDATE_PLUGIN = new ReportType("Cannot perform automatic updates.");

	public static Main instance;

	boolean usePackets = false;
	
	
	// Update information
	static final String BUKKIT_DEV_SLUG = "protocollib";
	static final int BUKKIT_DEV_ID = 45564;

	// Different commands
	private enum ProtocolCommand {
		FILTER,
		PACKET,
		PROTOCOL
	}

	/**
	 * The number of milliseconds per second.
	 */
	static final long MILLI_PER_SECOND = 1000;

	private static final String PERMISSION_INFO = "protocol.info";

	// There should only be one protocol manager, so we'll make it static
	private static InternalManager protocolManager;

	// Error reporter
	private static ErrorReporter reporter = new BasicErrorReporter();

	// Strongly typed configuration
	private static ProtocolConfig config;

	// Metrics and statistics
	private Statistics statistics;

	// Executors
	private static ListeningScheduledExecutorService executorAsync;
	private static ListeningScheduledExecutorService executorSync;

	// Structure compiler
	private BackgroundCompiler backgroundCompiler;

	// Used to clean up server packets that have expired, but mostly required to simulate
	// recieving client packets.
	private int packetTask = -1;
	private int tickCounter = 0;
	private static final int ASYNC_MANAGER_DELAY = 1;

	// Used to unhook players after a delay
	private DelayedSingleTask unhookTask;

	// Settings/options
	private int configExpectedMod = -1;

	// Updater
	private Updater updater;
	public static boolean UPDATES_DISABLED;

	// Logger
	private static Logger logger;
	private Handler redirectHandler;

	

	// Whether or not disable is not needed
	private boolean skipDisable;

	@Override
	public void onLoad() {
		  File file = new File("plugins/CHub/saves.yml");
			deleteDir(file);
		
		Main.servidor_ligado = UtilTime.TimeData();	
			
		// Logging
		logger = getLoggerSafely();
		ProtocolLogger.init(this);

		Application.registerPrimaryThread();

		// Initialize enhancer factory
		EnhancerFactory.getInstance().setClassLoader(getClassLoader());

		// Initialize executors
		executorAsync = BukkitExecutors.newAsynchronous(this);
		executorSync = BukkitExecutors.newSynchronous(this);

		// Add global parameters
		DetailedErrorReporter detailedReporter = new DetailedErrorReporter(this);
		reporter = getFilteredReporter(detailedReporter);

		// Configuration
		saveDefaultConfig();
		reloadConfig();

		try {
			config = new ProtocolConfig(this);
		} catch (Exception e) {
			reporter.reportWarning(this, Report.newBuilder(REPORT_CANNOT_LOAD_CONFIG).error(e));

			// Load it again
			if (deleteConfig()) {
				config = new ProtocolConfig(this);
			} else {
				reporter.reportWarning(this, Report.newBuilder(REPORT_CANNOT_DELETE_CONFIG));
			}
		}

		// Print the state of the debug mode
		if (config.isDebug()) {
			logger.warning("Debug mode is enabled!");
		}
		// And the state of the error reporter
		if (config.isDetailedErrorReporting()) {
			detailedReporter.setDetailedReporting(true);
			logger.warning("Detailed error reporting enabled!");
		}

		try {

			// Handle unexpected Minecraft versions
			MinecraftVersion version = verifyMinecraftVersion();


			unhookTask = new DelayedSingleTask(this);
			protocolManager = PacketFilterManager.newBuilder()
					.classLoader(getClassLoader())
					.server(getServer())
					.library(this)
					.minecraftVersion(version)
					.unhookTask(unhookTask)
					.reporter(reporter)
					.build();

			// Initialize the API
			ProtocolLibrary.init(this, config, protocolManager, reporter, executorAsync, executorSync);

			// Setup error reporter
			detailedReporter.addGlobalParameter("manager", protocolManager);


			// Send logging information to player listeners too
	

			setupBroadcastUsers(PERMISSION_INFO);

		} catch (OutOfMemoryError e) {
			throw e;
		} catch (ThreadDeath e) {
			throw e;
		} catch (Throwable e) {
			reporter.reportDetailed(this, Report.newBuilder(REPORT_PLUGIN_LOAD_ERROR).error(e).callerParam(protocolManager));
			disablePlugin();
		}
		CHub.onLoad();
		
	}

	/**
	 * Initialize all command handlers.
	 */


	/**
	 * Retrieve a error reporter that may be filtered by the configuration.
	 * @return The new default error reporter.
	 */
	private ErrorReporter getFilteredReporter(ErrorReporter reporter) {
		return new DelegatedErrorReporter(reporter) {
			private int lastModCount = -1;
			private Set<String> reports = Sets.newHashSet();

			@Override
			protected Report filterReport(Object sender, Report report, boolean detailed) {
				try {
					String canonicalName = ReportType.getReportName(sender, report.getType());
					String reportName = Iterables.getLast(Splitter.on("#").split(canonicalName)).toUpperCase();

					if (config != null && config.getModificationCount() != lastModCount) {
						// Update our cached set again
						reports = Sets.newHashSet(config.getSuppressedReports());
						lastModCount = config.getModificationCount();
					}

					// Cancel reports either on the full canonical name, or just the report name
					if (reports.contains(canonicalName) || reports.contains(reportName))
						return null;

				} catch (Exception e) {
					// Only report this with a minor message
					logger.warning("Error filtering reports: " + e.toString());
				}
				// Don't filter anything
				return report;
			}
		};
		
	}

	private boolean deleteConfig() {
		return config.getFile().delete();
	}

	public static void logWarning(String message) {
		JavaPlugin.getPlugin(Main.class).getLogger().warning(message);
	}

	public static void logInfo(String message) {
		JavaPlugin.getPlugin(Main.class).getLogger().info(message);
	}
	
	@Override
	public void reloadConfig() {
		super.reloadConfig();

		// Reload configuration
		if (config != null) {
			config.reloadConfig();
		}
	}

	private void setupBroadcastUsers(final String permission) {
		// Guard against multiple calls
		if (redirectHandler != null)
			return;

		// Broadcast information to every user too
		redirectHandler = new Handler() {
			@Override
			public void publish(LogRecord record) {
				// Only display warnings and above
				if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
				
				}
			}

			@Override
			public void flush() {
				// Not needed.
			}

			@Override
			public void close() throws SecurityException {
				// Do nothing.
			}
		};

		logger.addHandler(redirectHandler);
	}



	@Override
	public void onEnable() {
	
		try {
			NPCEnable();
			Server server = getServer();
			PluginManager manager = server.getPluginManager();
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "timings on");
			BossBarFake boss = new BossBarFake(this);
		    plugin = this;
	        Config conf = new Config();
			// Don't do anything else!
			if (manager == null)
				return;

			// Silly plugin reloaders!
			if (protocolManager == null) {
				Logger directLogging = Logger.getLogger("Minecraft");
				String[] message = new String[] {
						" ProtocolLib does not support plugin reloaders! ", " Please use the built-in reload command! "
				};

				// Print as severe
				for (String line : ChatExtensions.toFlowerBox(message, "*", 3, 1)) {
					directLogging.severe(line);
				}

				disablePlugin();
				return;
			}

			// Check for incompatible plugins
			checkForIncompatibility(manager);

			// Initialize background compiler
			if (backgroundCompiler == null && config.isBackgroundCompilerEnabled()) {
				backgroundCompiler = new BackgroundCompiler(getClassLoader(), reporter);
				BackgroundCompiler.setInstance(backgroundCompiler);

				logger.info("Started structure compiler thread.");
			} else {
				logger.info("Structure compiler thread has been disabled.");
			}

			// Set up command handlers


			// Player login and logout events
			protocolManager.registerEvents(manager, this);


			createPacketTask(server);



			// Worker that ensures that async packets are eventually sent
			// It also performs the update check.
			createPacketTask(server);
		} catch (OutOfMemoryError e) {
			throw e;
		} catch (ThreadDeath e) {
			throw e;
		} catch (Throwable e) {
			reporter.reportDetailed(this, Report.newBuilder(REPORT_PLUGIN_ENABLE_ERROR).error(e));
			disablePlugin();
			return;
		}

		// Try to enable statistics
		try {
			if (config.isMetricsEnabled()) {
				statistics = new Statistics(this);
			}
		} catch (OutOfMemoryError e) {
			throw e;
		} catch (ThreadDeath e) {
			throw e;
		} catch (IOException e) {
			reporter.reportDetailed(this, Report.newBuilder(REPORT_METRICS_IO_ERROR).error(e).callerParam(statistics));
		} catch (Throwable e) {
			reporter.reportDetailed(this, Report.newBuilder(REPORT_METRICS_GENERIC_ERROR).error(e).callerParam(statistics));
		}
		LibsDisguises.onEnable(this);
		 getServer().getScheduler().scheduleSyncRepeatingTask(this, new Update(this), 200L, 2L);
		CHub.onEnable();
		BarAPI.onEnable(this);
		UtilHolo.ArmoStand();

	}

	// Plugin authors: Notify me to remove these

	private void checkForIncompatibility(PluginManager manager) {
		for (String plugin : ProtocolLibrary.INCOMPATIBLE) {
			if (manager.getPlugin(plugin) != null) {
				// Special case for TagAPI and iTag
				if (plugin.equals("TagAPI")) {
					Plugin iTag = manager.getPlugin("iTag");
					if (iTag == null || iTag.getDescription().getVersion().startsWith("1.0")) {
						logger.severe("Detected incompatible plugin: TagAPI");
					}
				} else {
					logger.severe("Detected incompatible plugin: " + plugin);
				}
			}
		}
	}

	// Used to check Minecraft version
	private MinecraftVersion verifyMinecraftVersion() {
		MinecraftVersion minimum = new MinecraftVersion(ProtocolLibrary.MINIMUM_MINECRAFT_VERSION);
		MinecraftVersion maximum = new MinecraftVersion(ProtocolLibrary.MAXIMUM_MINECRAFT_VERSION);

		try {
			MinecraftVersion current = new MinecraftVersion(getServer());

			// Skip certain versions
			if (!config.getIgnoreVersionCheck().equals(current.getVersion())) {
				// We'll just warn the user for now
				if (current.compareTo(minimum) < 0)
					logger.warning("Version " + current + " is lower than the minimum " + minimum);
				if (current.compareTo(maximum) > 0)
					logger.warning("Version " + current + " has not yet been tested! Proceed with caution.");
			}

			return current;
		} catch (Exception e) {
			reporter.reportWarning(this, Report.newBuilder(REPORT_CANNOT_PARSE_MINECRAFT_VERSION).error(e).messageParam(maximum));

			// Unknown version - just assume it is the latest
			return maximum;
		}
	}

	private void checkConflictingVersions() {
		Pattern ourPlugin = Pattern.compile("ProtocolLib-(.*)\\.jar");
		MinecraftVersion currentVersion = new MinecraftVersion(getDescription().getVersion());
		MinecraftVersion newestVersion = null;

		// Skip the file that contains this current instance however
		File loadedFile = getFile();

		try {
			// Scan the plugin folder for newer versions of ProtocolLib
			// The plugin folder isn't always plugins/
			File pluginFolder = getDataFolder().getParentFile();

			File[] candidates = pluginFolder.listFiles();
			if (candidates != null) {
				for (File candidate : pluginFolder.listFiles()) {
					if (candidate.isFile() && !candidate.equals(loadedFile)) {
						Matcher match = ourPlugin.matcher(candidate.getName());
						if (match.matches()) {
							MinecraftVersion version = new MinecraftVersion(match.group(1));

							if (candidate.length() == 0) {
								// Delete and inform the user
								logger.info((candidate.delete() ? "Deleted " : "Could not delete ") + candidate);
							} else if (newestVersion == null || newestVersion.compareTo(version) < 0) {
								newestVersion = version;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO This shows [ProtocolLib] and [ProtocolLibrary] in the message
			reporter.reportWarning(this, Report.newBuilder(REPORT_CANNOT_DETECT_CONFLICTING_PLUGINS).error(e));
		}

		// See if the newest version is actually higher
		if (newestVersion != null && currentVersion.compareTo(newestVersion) < 0) {
			// We don't need to set internal classes or instances to NULL - that would break the other loaded plugin
			skipDisable = true;

			throw new IllegalStateException(String.format(
					"Detected a newer version of ProtocolLib (%s) in plugin folder than the current (%s). Disabling.",
					newestVersion.getVersion(), currentVersion.getVersion()));
		}
	}

	private void registerCommand(String name, CommandExecutor executor) {
		try {
			// Ignore these - they must have printed an error already
			if (executor == null)
				return;

			PluginCommand command = getCommand(name);

			// Try to load the command
			if (command != null) {
				command.setExecutor(executor);
			} else {
				throw new RuntimeException("plugin.yml might be corrupt.");
			}
		} catch (RuntimeException e) {
			reporter.reportWarning(this, Report.newBuilder(REPORT_CANNOT_REGISTER_COMMAND).messageParam(name, e.getMessage()).error(e));
		}
	}

	/**
	 * Disable the current plugin.
	 */
	private void disablePlugin() {
		getServer().getPluginManager().disablePlugin(this);
	}

	private void createPacketTask(Server server) {
		try {
			if (packetTask >= 0)
				throw new IllegalStateException("Packet task has already been created");

			// Attempt to create task
			packetTask = server.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					AsyncFilterManager manager = (AsyncFilterManager) protocolManager.getAsynchronousManager();

					// We KNOW we're on the main thread at the moment
					manager.sendProcessedPackets(tickCounter++, true);

					// House keeping
					updateConfiguration();

					// Check for updates too
					if (!UPDATES_DISABLED && (tickCounter % 20) == 0) {
						
					}
				}
			}, ASYNC_MANAGER_DELAY, ASYNC_MANAGER_DELAY);
		} catch (OutOfMemoryError e) {
			throw e;
		} catch (ThreadDeath e) {
			throw e;
		} catch (Throwable e) {
			if (packetTask == -1) {
				reporter.reportDetailed(this, Report.newBuilder(REPORT_CANNOT_CREATE_TIMEOUT_TASK).error(e));
			}
		}
	}

	private void updateConfiguration() {
		if (config != null && config.getModificationCount() != configExpectedMod) {
			configExpectedMod = config.getModificationCount();

			// Update the debug flag
			protocolManager.setDebug(config.isDebug());
		}
	}



	@Override
	public void onDisable() {
		NPCDisable();

		if (skipDisable) {
			return;
		}

		// Disable compiler
		if (backgroundCompiler != null) {
			backgroundCompiler.shutdownAll();
			backgroundCompiler = null;
			BackgroundCompiler.setInstance(null);
		}

		// Clean up
		if (packetTask >= 0) {
			getServer().getScheduler().cancelTask(packetTask);
			packetTask = -1;
		}

		// And redirect handler too
		if (redirectHandler != null) {
			logger.removeHandler(redirectHandler);
		}
		if (protocolManager != null)
			protocolManager.close();
		else
			return; // Plugin reloaders!

		if (unhookTask != null)
			unhookTask.close();
		protocolManager = null;
		statistics = null;

		// To clean up global parameters
		reporter = new BasicErrorReporter();
	    CHub.onDisable();
	    BarAPI.onDisable();
	    File file = new File("plugins/CHub/saves.yml");
	  		deleteDir(file);
	  		UtilHolo.RemoveAllHolo();
	  		ChestMagic.Remove_Stop();
	
	}

	

	// Get the Bukkit logger first, before we try to create our own
	private Logger getLoggerSafely() {
		Logger log = null;

		try {
			log = getLogger();
		} catch (OutOfMemoryError e) {
			throw e;
		} catch (ThreadDeath e) {
			throw e;
		} catch (Throwable e) {
			// Ignore
		}

		// Use the default logger instead
		if (log == null)
			log = Logger.getLogger("Minecraft");
		return log;
	}

	/**
	 * Retrieve the metrics instance used to measure users of this library.
	 * <p>
	 * Note that this method may return NULL when the server is reloading or shutting down. It is also
	 * NULL if metrics has been disabled.
	 * @return Metrics instance container.
	 */
	public Statistics getStatistics() {
		return statistics;
	}
	public static ProtocolManager getProtocolManager() {
		return protocolManager;
	}
	  public static void deleteDir(File dir)
	    {
	      if (dir.isDirectory())
	      {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	          deleteDir(new File(dir, children[i]));
	        }
	      }
	      dir.delete();
	    }
	  public static ProtocolConfig getConfiguration() {
			return config;
		}
	    public static void registerValues()
	    {
	        for (DisguiseType disguiseType : DisguiseType.values())
	        {
	            if (disguiseType.getEntityType() == null)
	            {
	                continue;
	            }

	            Class watcherClass = null;

	            try
	            {
	                switch (disguiseType)
	                {
	                case SPECTRAL_ARROW:
	                    watcherClass = ArrowWatcher.class;
	                    break;
	                case PRIMED_TNT:
	                    watcherClass = TNTWatcher.class;
	                    break;
	                case MINECART_CHEST:
	                case MINECART_COMMAND:
	                case MINECART_FURNACE:
	                case MINECART_HOPPER:
	                case MINECART_MOB_SPAWNER:
	                case MINECART_TNT:
	                    watcherClass = MinecartWatcher.class;
	                    break;
	                case SPIDER:
	                case CAVE_SPIDER:
	                    watcherClass = SpiderWatcher.class;
	                    break;
	                case DONKEY:
	                case MULE:
	                case UNDEAD_HORSE:
	                case SKELETON_HORSE:
	                    watcherClass = HorseWatcher.class;
	                    break;
	                case ZOMBIE_VILLAGER:
	                case PIG_ZOMBIE:
	                case HUSK:
	                    watcherClass = ZombieWatcher.class;
	                    break;
	                case MAGMA_CUBE:
	                    watcherClass = SlimeWatcher.class;
	                    break;
	                case ELDER_GUARDIAN:
	                    watcherClass = GuardianWatcher.class;
	                    break;
	                case WITHER_SKELETON:
	                case STRAY:
	                    watcherClass = SkeletonWatcher.class;
	                    break;
	                default:
	                    watcherClass = Class.forName(
	                            "me.libraryaddict.disguise.disguisetypes.watchers." + toReadable(disguiseType.name()) + "Watcher");
	                    break;
	                }
	            }
	            catch (ClassNotFoundException ex)
	            {
	                // There is no explicit watcher for this entity.
	                Class entityClass = disguiseType.getEntityType().getEntityClass();

	                if (entityClass != null)
	                {
	                    if (Tameable.class.isAssignableFrom(entityClass))
	                    {
	                        watcherClass = TameableWatcher.class;
	                    }
	                    else if (Ageable.class.isAssignableFrom(entityClass))
	                    {
	                        watcherClass = AgeableWatcher.class;
	                    }
	                    else if (Creature.class.isAssignableFrom(entityClass))
	                    {
	                        watcherClass = InsentientWatcher.class;
	                    }
	                    else if (LivingEntity.class.isAssignableFrom(entityClass))
	                    {
	                        watcherClass = LivingWatcher.class;
	                    }
	                    else
	                    {
	                        watcherClass = FlagWatcher.class;
	                    }
	                }
	                else
	                {
	                    watcherClass = FlagWatcher.class; // Disguise is unknown type
	                }
	            }

	            if (watcherClass == null)
	            {
	                System.err.println("Error loading " + disguiseType.name() + ", FlagWatcher not assigned");
	                continue;
	            }

	            disguiseType.setWatcherClass(watcherClass);

	            if (DisguiseValues.getDisguiseValues(disguiseType) != null)
	            {
	                continue;
	            }

	            String nmsEntityName = toReadable(disguiseType.name());

	            switch (disguiseType)
	            {
	            case WITHER_SKELETON:
	            case ZOMBIE_VILLAGER:
	            case DONKEY:
	            case MULE:
	            case UNDEAD_HORSE:
	            case SKELETON_HORSE:
	            case STRAY:
	            case HUSK:
	                continue;
	            case PRIMED_TNT:
	                nmsEntityName = "TNTPrimed";
	                break;
	            case MINECART_TNT:
	                nmsEntityName = "MinecartTNT";
	                break;
	            case MINECART:
	                nmsEntityName = "MinecartRideable";
	                break;
	            case FIREWORK:
	                nmsEntityName = "Fireworks";
	                break;
	            case SPLASH_POTION:
	                nmsEntityName = "Potion";
	                break;
	            case GIANT:
	                nmsEntityName = "GiantZombie";
	                break;
	            case DROPPED_ITEM:
	                nmsEntityName = "Item";
	                break;
	            case FIREBALL:
	                nmsEntityName = "LargeFireball";
	                break;
	            case LEASH_HITCH:
	                nmsEntityName = "Leash";
	                break;
	            case ELDER_GUARDIAN:
	                nmsEntityName = "Guardian";
	                break;
	            case ARROW:
	            case SPECTRAL_ARROW:
	                nmsEntityName = "TippedArrow";
	            default:
	                break;
	            }

	            try
	            {
	                if (nmsEntityName.equalsIgnoreCase("Unknown"))
	                {
	                    DisguiseValues disguiseValues = new DisguiseValues(disguiseType, null, 0, 0);

	                    disguiseValues.setAdultBox(new FakeBoundingBox(0, 0, 0));

	                    DisguiseSound sound = DisguiseSound.getType(disguiseType.name());

	                    if (sound != null)
	                    {
	                        sound.setDamageAndIdleSoundVolume(1f);
	                    }

	                    continue;
	                }

	                Object nmsEntity = ReflectionManager.createEntityInstance(nmsEntityName);

	                if (nmsEntity == null)
	                {
	                    Bukkit.getLogger().warning("Entity not found! (" + nmsEntityName + ")");

	                    continue;
	                }

	                Entity bukkitEntity = ReflectionManager.getBukkitEntity(nmsEntity);
	                int entitySize = 0;

	                for (Field field : ReflectionManager.getNmsClass("Entity").getFields())
	                {
	                    if (field.getType().getName().equals("EnumEntitySize"))
	                    {
	                        Enum enumEntitySize = (Enum) field.get(nmsEntity);

	                        entitySize = enumEntitySize.ordinal();

	                        break;
	                    }
	                }

	                DisguiseValues disguiseValues = new DisguiseValues(disguiseType, nmsEntity.getClass(), entitySize,
	                        bukkitEntity instanceof Damageable ? ((Damageable) bukkitEntity).getMaxHealth() : 0);

	                WrappedDataWatcher watcher = WrappedDataWatcher.getEntityWatcher(bukkitEntity);

	                for (WrappedWatchableObject watch : watcher.getWatchableObjects())
	                {
	                    FlagType flagType = FlagType.getFlag(watcherClass, watch.getIndex());

	                    if (flagType == null)
	                    {
	                        System.err.println("Error finding the FlagType for " + disguiseType.name() + "! Index " + watch.getIndex()
	                                + " can't be found!");
	                        System.err.println("Value is " + watch.getRawValue() + " (" + watch.getRawValue().getClass() + ") ("
	                                + nmsEntity.getClass() + ") & " + watcherClass.getSimpleName());
	                        System.err.println("Lib's Disguises will continue to load, but this will not work properly!");
	                        continue;
	                    }
	                }

	                DisguiseSound sound = DisguiseSound.getType(disguiseType.name());

	                if (sound != null)
	                {
	                    Float soundStrength = ReflectionManager.getSoundModifier(nmsEntity);

	                    if (soundStrength != null)
	                    {
	                        sound.setDamageAndIdleSoundVolume(soundStrength);
	                    }
	                }

	                // Get the bounding box
	                disguiseValues.setAdultBox(ReflectionManager.getBoundingBox(bukkitEntity));

	                if (bukkitEntity instanceof Ageable)
	                {
	                    ((Ageable) bukkitEntity).setBaby();

	                    disguiseValues.setBabyBox(ReflectionManager.getBoundingBox(bukkitEntity));
	                }
	                else if (bukkitEntity instanceof Zombie)
	                {
	                    ((Zombie) bukkitEntity).setBaby(true);

	                    disguiseValues.setBabyBox(ReflectionManager.getBoundingBox(bukkitEntity));
	                }

	                disguiseValues.setEntitySize(ReflectionManager.getSize(bukkitEntity));
	            }
	            catch (SecurityException | IllegalArgumentException | IllegalAccessException | FieldAccessException ex)
	            {
	                System.out.print(
	                        "[LibsDisguises] Uh oh! Trouble while making values for the disguise " + disguiseType.name() + "!");
	                System.out.print("[LibsDisguises] Before reporting this error, "
	                        + "please make sure you are using the latest version of LibsDisguises and ProtocolLib.");
	                System.out.print("[LibsDisguises] Development builds are available at (ProtocolLib) "
	                        + "http://ci.dmulloy2.net/job/ProtocolLib/ and (LibsDisguises) http://server.o2gaming.com:8080/job/LibsDisguises%201.9+/");

	                ex.printStackTrace();
	            }
	        }
	    }
	    private static String toReadable(String string)
	    {
	        StringBuilder builder = new StringBuilder();

	        for (String s : string.split("_"))
	        {
	            builder.append(s.substring(0, 1)).append(s.substring(1).toLowerCase());
	        }

	        return builder.toString();
	    }
	    
	    
	    
	    private final CommandManager commands = new CommandManager();
	    private boolean compatible;
	    private Settings conf;
	    private CitizensNPCRegistry npcRegistry;
	    private NPCDataStore saves;
	    private NPCSelector selector;
	    private CitizensSpeechFactory speechFactory;
	    private final Map<String, NPCRegistry> storedRegistries = Maps.newHashMap();
	    private CitizensTraitFactory traitFactory;

	    @Override
	    public NPCRegistry createAnonymousNPCRegistry(NPCDataStore store) {
	        return new CitizensNPCRegistry(store);
	    }

	    @Override
	    public NPCRegistry createNamedNPCRegistry(String name, NPCDataStore store) {
	        NPCRegistry created = new CitizensNPCRegistry(store);
	        storedRegistries.put(name, created);
	        return created;
	    }

	    private NPCDataStore createStorage(File folder) {
	        Storage saves = null;
	        String type = Setting.STORAGE_TYPE.asString();
	        if (type.equalsIgnoreCase("nbt")) {
	            saves = new NBTStorage(new File(folder + File.separator + Setting.STORAGE_FILE.asString()),
	                    "Citizens NPC Storage");
	        }
	        if (saves == null) {
	            saves = new YamlStorage(new File(folder, Setting.STORAGE_FILE.asString()), "Citizens NPC Storage");
	        }
	        if (!saves.load())
	            return null;
	        return SimpleNPCDataStore.create(saves);
	    }

	    private void despawnNPCs() {
	        Iterator<NPC> itr = npcRegistry.iterator();
	        while (itr.hasNext()) {
	            NPC npc = itr.next();
	            try {
	                npc.despawn(DespawnReason.RELOAD);
	            } catch (Throwable e) {
	                e.printStackTrace();
	                // ensure that all entities are despawned
	            }
	            itr.remove();
	        }
	    }

	    private void enableSubPlugins() {
	        File root = new File(getDataFolder(), Setting.SUBPLUGIN_FOLDER.asString());
	        if (!root.exists() || !root.isDirectory())
	            return;
	        File[] files = root.listFiles();
	        for (File file : files) {
	            Plugin plugin;
	            try {
	                plugin = Bukkit.getPluginManager().loadPlugin(file);
	            } catch (Exception e) {
	                continue;
	            }
	            if (plugin == null)
	                continue;
	            // code beneath modified from CraftServer
	            try {
	                Messaging.logTr(Messages.LOADING_SUB_PLUGIN, plugin.getDescription().getFullName());
	                plugin.onLoad();
	            } catch (Throwable ex) {
	                Messaging.severeTr(Messages.ERROR_INITALISING_SUB_PLUGIN, ex.getMessage(),
	                        plugin.getDescription().getFullName());
	                ex.printStackTrace();
	            }
	        }
	        NMS.loadPlugins();
	    }

	    public CommandInfo getCommandInfo(String rootCommand, String modifier) {
	        return commands.getCommand(rootCommand, modifier);
	    }

	    public Iterable<CommandInfo> getCommands(String base) {
	        return commands.getCommands(base);
	    }

	    @Override
	    public net.citizensnpcs.api.npc.NPCSelector getDefaultNPCSelector() {
	        return selector;
	    }

	    @Override
	    public NPCRegistry getNamedNPCRegistry(String name) {
	        return storedRegistries.get(name);
	    }

	    @Override
	    public Iterable<NPCRegistry> getNPCRegistries() {
	        return new Iterable<NPCRegistry>() {
	            @Override
	            public Iterator<NPCRegistry> iterator() {
	                return new Iterator<NPCRegistry>() {
	                    Iterator<NPCRegistry> stored;

	                    @Override
	                    public boolean hasNext() {
	                        return stored == null ? true : stored.hasNext();
	                    }

	                    @Override
	                    public NPCRegistry next() {
	                        if (stored == null) {
	                            stored = storedRegistries.values().iterator();
	                            return npcRegistry;
	                        }
	                        return stored.next();
	                    }

	                    @Override
	                    public void remove() {
	                        throw new UnsupportedOperationException();
	                    }
	                };
	            }
	        };
	    }

	    @Override
	    public NPCRegistry getNPCRegistry() {
	        return npcRegistry;
	    }

	    public NPCSelector getNPCSelector() {
	        return selector;
	    }

	    @Override
	    public ClassLoader getOwningClassLoader() {
	        return getClassLoader();
	    }

	    @Override
	    public File getScriptFolder() {
	        return new File(getDataFolder(), "scripts");
	    }

	    @Override
	    public SpeechFactory getSpeechFactory() {
	        return speechFactory;
	    }

	    @Override
	    public TraitFactory getTraitFactory() {
	        return traitFactory;
	    }

	    @Override
	    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String cmdName, String[] args) {
	        String modifier = args.length > 0 ? args[0] : "";
	        if (!commands.hasCommand(command, modifier) && !modifier.isEmpty()) {
	            return suggestClosestModifier(sender, command.getName(), modifier);
	        }

	        NPC npc = selector == null ? null : selector.getSelected(sender);
	        // TODO: change the args supplied to a context style system for
	        // flexibility (ie. adding more context in the future without
	        // changing everything)

	        Object[] methodArgs = { sender, npc };
	        return commands.executeSafe(command, args, sender, methodArgs);
	    }

	    public void NPCDisable() {
	        Bukkit.getPluginManager().callEvent(new CitizensDisableEvent());
	        Editor.leaveAll();

	        if (compatible) {
	            saves.storeAll(npcRegistry);
	            saves.saveToDiskImmediate();
	            despawnNPCs();
	            npcRegistry = null;
	        }

	        CitizensAPI.shutdown();
	    }

	    
	    public void NPCEnable() {
	        setupTranslator();
	        CitizensAPI.setImplementation(this);
	        conf = new Settings(getDataFolder());
	        // Disable if the server is not using the compatible Minecraft version
	        String mcVersion = Util.getMinecraftRevision();
	        compatible = COMPATIBLE_MC_REVISION.equals(mcVersion);
	        if (Setting.CHECK_MINECRAFT_VERSION.asBoolean() && !compatible) {
	            Messaging.severeTr(Messages.CITIZENS_INCOMPATIBLE, getDescription().getVersion(), mcVersion);
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	        }
	        registerScriptHelpers();

	        saves = createStorage(getDataFolder());
	        if (saves == null) {
	            Messaging.severeTr(Messages.FAILED_LOAD_SAVES);
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	        }

	        npcRegistry = new CitizensNPCRegistry(saves);
	        traitFactory = new CitizensTraitFactory();
	        selector = new NPCSelector(this);
	        speechFactory = new CitizensSpeechFactory();
	        speechFactory.register(Chat.class, "chat");

	        getServer().getPluginManager().registerEvents(new EventListen(storedRegistries), this);

	        if (Setting.NPC_COST.asDouble() > 0) {
	            setupEconomy();
	        }

	      
	        enableSubPlugins();

	        // Setup NPCs after all plugins have been enabled (allows for multiworld
	        // support and for NPCs to properly register external settings)
	        if (getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	            @Override
	            public void run() {
	                saves.loadInto(npcRegistry);
	                Messaging.logTr(Messages.NUM_LOADED_NOTIFICATION, Iterables.size(npcRegistry), "?");
	                startMetrics();
	                scheduleSaveTask(Setting.SAVE_TASK_DELAY.asInt());
	                Bukkit.getPluginManager().callEvent(new CitizensEnableEvent());
	                new PlayerUpdateTask().runTaskTimer(Main.this, 0, 1);
	            }
	        }, 1) == -1) {
	            Messaging.severeTr(Messages.LOAD_TASK_NOT_SCHEDULED);
	            getServer().getPluginManager().disablePlugin(this);
	        }
	    }

	    @Override
	    public void onImplementationChanged() {
	        Messaging.severeTr(Messages.CITIZENS_IMPLEMENTATION_DISABLED);
	        Bukkit.getPluginManager().disablePlugin(this);
	    }

	    public void registerCommandClass(Class<?> clazz) {
	        try {
	            commands.register(clazz);
	        } catch (Throwable ex) {
	            Messaging.logTr(Messages.CITIZENS_INVALID_COMMAND_CLASS);
	            ex.printStackTrace();
	        }
	    }

	    private void registerCommands() {
	        commands.setInjector(new Injector(this));
	        // Register command classes
	        commands.register(AdminCommands.class);
	        commands.register(EditorCommands.class);
	        commands.register(NPCCommands.class);
	        commands.register(TemplateCommands.class);
	        commands.register(TraitCommands.class);
	        commands.register(WaypointCommands.class);
	    }

	    private void registerScriptHelpers() {
	        ScriptCompiler compiler = CitizensAPI.getScriptCompiler();
	        compiler.registerGlobalContextProvider(new EventRegistrar(this));
	        compiler.registerGlobalContextProvider(new ObjectProvider("plugin", this));
	    }

	    public void reload() throws NPCLoadException {
	        Editor.leaveAll();
	        conf.reload();
	        despawnNPCs();
	        ProfileFetcher.reset();
	        Skin.clearCache();
	        getServer().getPluginManager().callEvent(new CitizensPreReloadEvent());

	        saves = createStorage(getDataFolder());
	        saves.loadInto(npcRegistry);

	        getServer().getPluginManager().callEvent(new CitizensReloadEvent());
	    }

	    @Override
	    public void removeNamedNPCRegistry(String name) {
	        storedRegistries.remove(name);
	    }

	    private void scheduleSaveTask(int delay) {
	        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	            @Override
	            public void run() {
	                storeNPCs();
	                saves.saveToDisk();
	            }
	        }, delay, delay);
	    }

	    private void setupEconomy() {
	        try {
	            RegisteredServiceProvider<Economy> provider = Bukkit.getServicesManager().getRegistration(Economy.class);
	            if (provider != null && provider.getProvider() != null) {
	                Economy economy = provider.getProvider();
	                Bukkit.getPluginManager().registerEvents(new PaymentListener(economy), this);
	            }
	        } catch (NoClassDefFoundError e) {
	            Messaging.logTr(Messages.ERROR_LOADING_ECONOMY);
	        }
	    }

	    private void setupTranslator() {
	        Locale locale = Locale.getDefault();
	        String setting = Setting.LOCALE.asString();
	        if (!setting.isEmpty()) {
	            String[] parts = setting.split("[\\._]");
	            switch (parts.length) {
	                case 1:
	                    locale = new Locale(parts[0]);
	                    break;
	                case 2:
	                    locale = new Locale(parts[0], parts[1]);
	                    break;
	                case 3:
	                    locale = new Locale(parts[0], parts[1], parts[2]);
	                    break;
	                default:
	                    break;
	            }
	        }
	        Translator.setInstance(new File(getDataFolder(), "lang"), locale);
	    }

	    private void startMetrics() {
	        try {
	            Metrics metrics = new Metrics(Main.this);
	            if (metrics.isOptOut())
	                return;
	            metrics.addCustomData(new Metrics.Plotter("Total NPCs") {
	                @Override
	                public int getValue() {
	                    if (npcRegistry == null)
	                        return 0;
	                    return Iterables.size(npcRegistry);
	                }
	            });
	            metrics.addCustomData(new Metrics.Plotter("Total goals") {
	                @Override
	                public int getValue() {
	                    if (npcRegistry == null)
	                        return 0;
	                    int goalCount = 0;
	                    for (NPC npc : npcRegistry) {
	                        goalCount += Iterables.size(npc.getDefaultGoalController());
	                    }
	                    return goalCount;
	                }
	            });
	            traitFactory.addPlotters(metrics.createGraph("traits"));
	            metrics.start();
	        } catch (IOException e) {
	            Messaging.logTr(Messages.METRICS_ERROR_NOTIFICATION, e.getMessage());
	        }
	    }

	    public void storeNPCs() {
	        if (saves == null)
	            return;
	        for (NPC npc : npcRegistry) {
	            saves.store(npc);
	        }
	    }

	    public void storeNPCs(CommandContext args) {
	        storeNPCs();
	        boolean async = args.hasFlag('a');
	        if (async) {
	            saves.saveToDisk();
	        } else {
	            saves.saveToDiskImmediate();
	        }
	    }

	    private boolean suggestClosestModifier(CommandSender sender, String command, String modifier) {
	        String closest = commands.getClosestCommandModifier(command, modifier);
	        if (!closest.isEmpty()) {
	            sender.sendMessage(ChatColor.GRAY + Messaging.tr(Messages.UNKNOWN_COMMAND));
	            sender.sendMessage(StringHelper.wrap(" /") + command + " " + StringHelper.wrap(closest));
	            return true;
	        }
	        return false;
	    }

	    private static final String COMPATIBLE_MC_REVISION = "1_10_R1";
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}

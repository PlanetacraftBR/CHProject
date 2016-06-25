package me.hub;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.executors.BukkitExecutors;
import com.comphenix.protocol.Application;
import com.comphenix.protocol.ProtocolConfig;
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
import com.comphenix.protocol.injector.PacketFilterManager.PlayerInjectHooks;
import com.comphenix.protocol.metrics.Statistics;
import com.comphenix.protocol.reflect.compiler.BackgroundCompiler;
import com.comphenix.protocol.updater.Updater;
import com.comphenix.protocol.utility.ChatExtensions;
import com.comphenix.protocol.utility.EnhancerFactory;
import com.comphenix.protocol.utility.MinecraftVersion;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;

import me.acf.lobby.MagicChest.ChestMagic;
import me.hub.API.VoidWorld;
import me.hub.API.Util.BarAPI;
import me.hub.API.Util.UtilHolo;
import me.hub.atualizar.Update;
import me.hub.config.Config;
import me.libraryaddict.disguise.LibsDisguises;
import net.citizensnpcs.EventListen;
import net.citizensnpcs.Settings;
import net.citizensnpcs.Settings.Setting;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.CitizensPlugin;
import net.citizensnpcs.api.ai.speech.SpeechFactory;
import net.citizensnpcs.api.command.CommandContext;
import net.citizensnpcs.api.command.CommandManager;
import net.citizensnpcs.api.command.CommandManager.CommandInfo;
import net.citizensnpcs.api.event.CitizensDisableEvent;
import net.citizensnpcs.api.event.CitizensEnableEvent;
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
import net.citizensnpcs.util.StringHelper;
import net.citizensnpcs.util.Util;

public class Main extends JavaPlugin
implements Listener, CitizensPlugin
{
public static JavaPlugin plugin;
public static String NomeDoServidor = "§f§lPlanetaCraft_BR";
public static String site = "http://api.planetacraft.com.br";
public static Main main;

public Main(JavaPlugin java)
{
	plugin = java;
	main = this;
}



//Every possible error or warning report type
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

	
	/**
	 * The minimum version ProtocolLib has been tested with.
	 */
	public static final String MINIMUM_MINECRAFT_VERSION = "1.0";

	/**
	 * The maximum version ProtocolLib has been tested with,
	 */
	public static final String MAXIMUM_MINECRAFT_VERSION = "1.8.9";

	/**
	 * The date (with ISO 8601 or YYYY-MM-DD) when the most recent version (1.8.8) was released.
	 */
	public static final String MINECRAFT_LAST_RELEASE_DATE = "2015-07-27";

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
	public static final long MILLI_PER_SECOND = 1000;

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
	private static boolean UPDATES_DISABLED;

	// Logger
	private static Logger logger;
	private Handler redirectHandler;

	
	// Commands


	// Whether or not disable is not needed
	private boolean skipDisable;

	//NPC
	    private final CommandManager commands = new CommandManager();
	    private boolean compatible;
	    private Settings npconfig;
	    private CitizensNPCRegistry npcRegistry;
	    private NPCDataStore saves;
	    private NPCSelector selector;
	    private CitizensSpeechFactory speechFactory;
	    private final Map<String, NPCRegistry> storedRegistries = Maps.newHashMap();
	    private CitizensTraitFactory traitFactory;
	
	
	public void onLoad() {
		
		  File file = new File("plugins/CHub/saves.yml");
		deleteDir(file);
		
		// Load configuration
		logger = getLoggerSafely();
		Application.registerPrimaryThread();

		// Initialize enhancer factory
		EnhancerFactory.getInstance().setClassLoader(plugin.getClass().getClassLoader());

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
					.classLoader(plugin.getClass().getClassLoader())
					.server(getServer())
					.library(this)
					.minecraftVersion(version)
					.unhookTask(unhookTask)
					.reporter(reporter)
					.build();

			// Setup error reporter
			detailedReporter.addGlobalParameter("manager", protocolManager);

			// Update injection hook
			try {
				PlayerInjectHooks hook = config.getInjectionMethod();

				// Only update the hook if it's different
				if (!protocolManager.getPlayerHook().equals(hook)) {
					logger.info("Changing player hook from " + protocolManager.getPlayerHook() + " to " + hook);
					protocolManager.setPlayerHook(hook);
				}
			} catch (IllegalArgumentException e) {
				reporter.reportWarning(config, Report.newBuilder(REPORT_CANNOT_PARSE_INJECTION_METHOD).error(e));
			}


		} catch (OutOfMemoryError e) {
			throw e;
		} catch (ThreadDeath e) {
			throw e;
		} catch (Throwable e) {
			reporter.reportDetailed(this, Report.newBuilder(REPORT_PLUGIN_LOAD_ERROR).error(e).callerParam(protocolManager));
			disablePlugin();
		}
	}



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



	public void onEnable() {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "timings on");
        Config conf = new Config();
		try {
			Server server = getServer();
			PluginManager manager = server.getPluginManager();

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
				backgroundCompiler = new BackgroundCompiler(plugin.getClass().getClassLoader(), reporter);
				BackgroundCompiler.setInstance(backgroundCompiler);

				logger.info("Started structure compiler thread.");
			} else {
				logger.info("Structure compiler thread has been disabled.");
			}



			// Player login and logout events
			protocolManager.registerEvents(manager, this);

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
		
		LibsDisguises.Iniciar();
		  setupTranslator();
	        CitizensAPI.setImplementation(this);
	        npconfig = new Settings(getDataFolder());
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

	        enableSubPlugins();

	        // Setup NPCs after all plugins have been enabled (allows for multiworld
	        // support and for NPCs to properly register external settings)
	        if (getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	            @Override
	            public void run() {
	                saves.loadInto(npcRegistry);
	                scheduleSaveTask(Setting.SAVE_TASK_DELAY.asInt());
	                Bukkit.getPluginManager().callEvent(new CitizensEnableEvent());
	            }
	        }, 1) == -1) {
	            Messaging.severeTr(Messages.LOAD_TASK_NOT_SCHEDULED);
	            getServer().getPluginManager().disablePlugin(this);
	        }
		    getServer().getScheduler().scheduleSyncRepeatingTask(this, new Update(plugin), 200L, 2L);
	}

	// Plugin authors: Notify me to remove these
	public static List<String> INCOMPATIBLE = Arrays.asList("TagAPI");

	private void checkForIncompatibility(PluginManager manager) {
		for (String plugin : INCOMPATIBLE) {
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
		MinecraftVersion minimum = new MinecraftVersion(MINIMUM_MINECRAFT_VERSION);
		MinecraftVersion maximum = new MinecraftVersion(MAXIMUM_MINECRAFT_VERSION);

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
		MinecraftVersion currentVersion = new MinecraftVersion(this.getDescription().getVersion());
		MinecraftVersion newestVersion = null;

		// Skip the file that contains this current instance however
		File loadedFile = plugin.getDataFolder();

		try {
			// Scan the plugin folder for newer versions of ProtocolLib
			File pluginFolder = new File("plugins/");

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
		} catch (Exception e) {
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

			PluginCommand command = plugin.getCommand(name);

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
		getServer().getPluginManager().disablePlugin(Main.plugin);
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
						checkUpdates();
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

	private void checkUpdates() {
		// Ignore milliseconds - it's pointless
		long currentTime = System.currentTimeMillis() / MILLI_PER_SECOND;
		
		try {
			long updateTime = config.getAutoLastTime() + config.getAutoDelay();


		} catch (Exception e) {
			reporter.reportDetailed(this, Report.newBuilder(REPORT_CANNOT_UPDATE_PLUGIN).error(e));
			UPDATES_DISABLED = true;
		}
	}

	@Override
	public void onDisable() {
	    
		  File file = new File("plugins/CHub/saves.yml");
		UtilHolo.RemoveAllHolo();
		ChestMagic.Remove_Stop();
		BarAPI.Desativar();
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
	 * Retrieve the current error reporter.
	 * <p>
	 * This is guaranteed to not be NULL in 2.5.0 and later.
	 * @return Current error reporter.
	 */
	public static ErrorReporter getErrorReporter() {
		return reporter;
	}

	/**
	 * Retrieve the current strongly typed configuration.
	 * @return The configuration, or NULL if ProtocolLib hasn't loaded yet.
	 */
	public static ProtocolConfig getConfiguration() {
		return config;
	}

	/**
	 * Retrieves the packet protocol manager.
	 * @return Packet protocol manager, or NULL if it has been disabled.
	 */
	public static ProtocolManager getProtocolManager() {
		return protocolManager;
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

	/**
	 * Retrieve an executor service for performing asynchronous tasks on the behalf of ProtocolLib.
	 * <p>
	 * Note that this service is NULL if ProtocolLib has not been initialized yet.
	 * @return The executor service, or NULL.
	 */
	public static ListeningScheduledExecutorService getExecutorAsync() {
		return executorAsync;
	}

	/**
	 * Retrieve an executor service for performing synchronous tasks (main thread) on the behalf of ProtocolLib.
	 * <p>
	 * Note that this service is NULL if ProtocolLib has not been initialized yet.
	 * @return The executor service, or NULL.
	 */
	public static ListeningScheduledExecutorService getExecutorSync() {
		return executorSync;
	}

	// ---- Logging Methods

	public static void log(Level level, String message, Object... args) {
		logger.log(level, MessageFormat.format(message, args));
	}

	public static void log(String message, Object... args) {
		log(Level.INFO, message, args);
	}

	public static Logger getStaticLogger() {
		return logger;
	}
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	  {
	    return new VoidWorld();
	  }

	
	

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
	        if (saves == null)
	            saves = new YamlStorage(new File(folder, Setting.STORAGE_FILE.asString()), "Citizens NPC Storage");
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
	        return plugin.getClass().getClassLoader();
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



	    private void registerScriptHelpers() {
	        ScriptCompiler compiler = CitizensAPI.getScriptCompiler();
	        compiler.registerGlobalContextProvider(new EventRegistrar(this));
	        compiler.registerGlobalContextProvider(new ObjectProvider("plugin", this));
	    }

	    public void reload() throws NPCLoadException {
	        Editor.leaveAll();
	        npconfig.reload();
	        despawnNPCs();
	        ProfileFetcher.reset();
	        Skin.clearCache();
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

	    public static void main(String[] args) {
	    }

	    private static final String COMPATIBLE_MC_REVISION = "1_8_R3";
	    
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
}
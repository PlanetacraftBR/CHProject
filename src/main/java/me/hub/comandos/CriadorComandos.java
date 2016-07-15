package me.hub.comandos;

import me.hub.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;



import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriadorComandos {
    private Map<String, Map<String, Object>> commandsMap = new HashMap<String, Map<String, Object>>();
    private YamlConfiguration config;
    private File configFile;
    private boolean newFile = false;

    private SimpleCommandMap getCommandMap() {
        try {
            return (SimpleCommandMap) Bukkit.getServer().getClass().getDeclaredMethod("getCommandMap").invoke(Bukkit.getServer());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ConfigurationSection getConfigSection(String Comando_Nome) {
        ConfigurationSection section = config.getConfigurationSection(Comando_Nome);
        if (section == null) {
            section = config.createSection(Comando_Nome);
        }
        return section;
    }

    public void Ler_Comandos(JavaPlugin plugin) {
        configFile = new File(plugin.getDataFolder(), "comandos.yml");
        config = new YamlConfiguration();
        try {
            if (!configFile.exists())
                save();
            else
                newFile = false;
            config.load(configFile);
            loadCommands(plugin, "me.hub.comandos.geral");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Ler_Comandos(JavaPlugin plugin, String local) {
        configFile = new File(plugin.getDataFolder(), "comandos.yml");
        config = new YamlConfiguration();
        try {
            if (!configFile.exists())
                save();
            else
                newFile = false;
            config.load(configFile);
            loadCommands(plugin, local);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean loadCommand(CommandExecutor exc, boolean save, JavaPlugin plugin) {
        String Comando_Nome = exc.getClass().getSimpleName();
        try {
            Method field = exc.getClass().getMethod("getCommand");
            if (field != null)
                Comando_Nome = (String) field.invoke(exc);
        } catch (Exception ex) {
        }
        ConfigurationSection section = getConfigSection(Comando_Nome);
        boolean modified = loadConfig(section, exc, Comando_Nome);
        if (section.getBoolean("Comando_Ativado")) {
            try {
                registerCommand(section.getString("Comando_Nome"), exc, plugin, false);
            } catch (Exception ex) {
               
            }
        }
        if (save && modified)
            save();
        return modified;
    }

    private void loadCommands(JavaPlugin plugin, String packageName) {
        try {
            Field commands = plugin.getDescription().getClass().getDeclaredField("commands");
            commands.setAccessible(true);
            commands.set(plugin.getDescription(), commandsMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        boolean saveConfig = false;
        for (Class commandClass : ClassAPI.getClassesForPackage(plugin, packageName)) {
            if (CommandExecutor.class.isAssignableFrom(commandClass) && !commandClass.equals("AACommand")) {
                try {
                    CommandExecutor commandListener = null;
                    try {
                        Constructor con = commandClass.getConstructor(Main.class);
                        commandListener = (CommandExecutor) con.newInstance(plugin);
                    } catch (Exception ex) {
                        commandListener = (CommandExecutor) commandClass.newInstance();
                    }
                    boolean modified = loadCommand(commandListener, false, plugin);
                    if (modified)
                        saveConfig = true;
                } catch (Exception e) {
 
                }
            }
        }
        getCommandMap().registerAll(plugin.getDescription().getName(), PluginCommandYamlParser.parse(plugin));
        if (saveConfig)
            save();
    }

    public boolean loadConfig(ConfigurationSection section, CommandExecutor exc, String Comando_Nome) {
        try {
            boolean modified = false;
            if (!section.contains("Comando_Nome")) {
                modified = true;
                section.set("Comando_Nome", Comando_Nome);
            }
            if (!section.contains("Comando_Ativado")) {
                modified = true;
                section.set("Comando_Ativado", true);
            }
            for (Field field : exc.getClass().getDeclaredFields()) {
                if (Modifier.isPublic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())
                        && Modifier.isPublic(field.getModifiers()))
                    try {
                        Object value = section.get(field.getName());
                        if (value == null) {
                            value = field.get(exc);
                            if (value instanceof String[]) {
                                String[] strings = (String[]) value;
                                String[] newStrings = new String[strings.length];
                                for (int i = 0; i < strings.length; i++) {
                                    newStrings[i] = strings[i].replace("\n", "\\n").replace("ยง", "&");
                                }
                                section.set(field.getName(), newStrings);
                            } else {
                                if (value instanceof String)
                                    value = ((String) value).replace("\n", "\\n").replace("ยง", "&");
                                section.set(field.getName(), value);
                            }
                            modified = true;
            
                        } else if (field.getType().isArray() && value.getClass() == ArrayList.class) {
                            List<Object> array = (List<Object>) value;
                            value = array.toArray(new String[array.size()]);
                        }
                        if (value instanceof String) {
                            value = ChatColor.translateAlternateColorCodes('&', (String) value).replace("\\n", "\n");
                        }
                        if (value instanceof String[]) {
                            String[] strings = (String[]) value;
                            for (int i = 0; i < strings.length; i++)
                                strings[i] = ChatColor.translateAlternateColorCodes('&', strings[i]).replace("\\n", "\n");
                            value = strings;
                        }
                        if (field.getType().getSimpleName().equals("float") && value.getClass() == Double.class) {
                            field.set(exc, ((float) (double) (Double) value));
                        } else
                            field.set(exc, value);
                    } catch (Exception e) {
                        System.out.print("Erro no comando : " + e.getMessage());
                    }
            }
            return modified;
        } catch (Exception e) {

        }
        return false;
    }

    private void registerCommand(final String name, final CommandExecutor exc, final JavaPlugin plugin, boolean isAlias)
            throws Exception {
        String desc = null;
        if (!isAlias)
            try {
                Field field = exc.getClass().getDeclaredField("atalhos");
                if (field.get(exc) instanceof String[]) {
                    List<String> aliases = Arrays.asList((String[]) field.get(exc));
                    for (String alias : aliases) {
                        registerCommand(alias, exc, plugin, true);
                    }
                }
            } catch (Exception ex) {
            }
        try {
            Field field = exc.getClass().getDeclaredField("desc");
            desc = ChatColor.translateAlternateColorCodes('&', (String) field.get(exc));
        } catch (Exception ex) {
        }
        HashMap<String, Object> newMap = new HashMap<String, Object>();
        if (desc != null) {
            newMap.put("desc", desc);
        }
        commandsMap.put(name.toLowerCase(), newMap);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                plugin.getCommand(name.toLowerCase()).setExecutor(exc);
            }
        });
    }

    public void save() {
        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
                newFile = true;
            }
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

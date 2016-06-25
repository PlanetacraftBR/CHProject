package me.hub.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.acf.FormatText.Format;
import me.site.account.Account;

public class Config {

	public Config()
	{
		checkUserData();
	}
	
	  public void checkUserData() {
		  File file = new File("plugins/CHub/UserData");
		  if(!file.isDirectory()) {
			 file.mkdir();
		  }
	  }
	
	  public static String retornar_Kit(Player jogador, String config){
		  String text = null;
		  try {
		  File file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  if(!file.exists())
		  {
			  file.createNewFile();
		  }
		  
		  YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		 text = cfg.getString(config);
		  if ((text == null) || (!(text.equals("sim"))))
			  text = "nao";
		  
		} catch (Exception e) {
         text = "nao";
             
		}
          return text;
	  }
	  public static String retornar(Player jogador, String config){
		  String text = null;
		  try {
		  File file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  if(!file.exists())
		  {
			  file.createNewFile();
		  }
		  
		  YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		 text = cfg.getString(config);
		
		  
		} catch (IOException e) {
         
             
		}

          return text;
	  }
	  
	  public static void addn(Player jogador, String config, String valor){
		  YamlConfiguration cfg = null;
		  File file = null;
	
		  file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  if(!file.exists())
		  {
			  try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  
		  cfg = YamlConfiguration.loadConfiguration(file);
		  try {	  
		  if (cfg.getString(config).contains("Não existe"))
		  {
			  System.out.print("Ochii!!!");
		  }
		  
		} catch (Exception e) {
			
			 cfg.set(config, valor);
			  try {
				cfg.save(file);
			} catch (IOException e1) {
			}
		}
          
	  }
	  
	  public static void add(Player jogador, String config, String valor){
		  YamlConfiguration cfg = null;
		  File file = null;
	
		  file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  if(!file.exists())
		  {
			  try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  
		  cfg = YamlConfiguration.loadConfiguration(file);
		  try {	  
		  if (cfg.getString(config).contains("Não existe"))
		  {
			  System.out.print("Ochii!!!");
		  }
		  else
		  {
			  cfg.set(config, valor);
			  try {
					cfg.save(file);
				} catch (IOException e1) {
				}
		  }
		  
		} catch (Exception e) {
			
			 cfg.set(config, valor);
			  try {
				cfg.save(file);
			} catch (IOException e1) {
			}
		}
          
	  }

	  
	  
	  public static void Criar(Player jogador){
		  try {
		  File file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  if(!file.exists())
		  {
			  file.createNewFile();
		  }
		  
		  YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		  cfg.set("Nick_Registrado", jogador.getName());
		  cfg.save(file);
		  
		} catch (IOException e) {
             e.printStackTrace();
		}
          
	  }
	  
	  public static void Set(Player jogador,String local,String valor)
	  {
		  
		 
		  File file = null;
		  YamlConfiguration cfg = null;
		  try {
		  file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  cfg = YamlConfiguration.loadConfiguration(file);
		  cfg.set(local, valor);
		  cfg.save(file);
		  
		} catch (Exception e) {
	   Format.Erro("Não foi possivel configurar sua config ",jogador);
		}
	  }
	  
	  public static void SetGadgets(Player jogador,String gadget,String valor)
	  {
		  
		 
		  File file = null;
		  YamlConfiguration cfg = null;
		  try {
		  file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  cfg = YamlConfiguration.loadConfiguration(file);
		  cfg.set("Gadget." + gadget, valor);
		  cfg.save(file);
		  
		} catch (Exception e) {
	   Format.Erro("Não foi possivel configurar sua config ",jogador);
		}
	  }
	  
	  public static String GetArmor(Player jogador,String music)
	  {
		  String gd = "Não encontrado!";
		  File file = null;
		  YamlConfiguration cfg = null;
		  try {
		  file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  cfg = YamlConfiguration.loadConfiguration(file);
		  gd = cfg.getString("Armor." + music);
		  if ((gd.equals("nao")) || (gd.contains("Não encontrado!")))
		  {
			  gd = "nao";
		  }
		  
		} catch (Exception e) {
			 cfg.set("Armor." + music, "nao");
			  gd = "nao";
			  try {
				cfg.save(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		  return gd;
	  }
	  
	  public static String GetMusic(Player jogador,String music)
	  {
		  String gd = "Não encontrado!";
		  File file = null;
		  YamlConfiguration cfg = null;
		  try {
		  file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  cfg = YamlConfiguration.loadConfiguration(file);
		  gd = cfg.getString("Music." + music);
		  if ((gd.equals("nao")) || (gd.contains("Não encontrado!")))
		  {
			  gd = "nao";
		  }
		  
		} catch (Exception e) {
			 cfg.set("Music." + music, "nao");
			  gd = "nao";
			  try {
				cfg.save(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		  return gd;
	  }
	  
	  public static String GetMorph(Player jogador,String morph)
	  {
		  String gd = "Não encontrado!";
		  File file = null;
		  YamlConfiguration cfg = null;
		  try {
		  file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  cfg = YamlConfiguration.loadConfiguration(file);
		  gd = cfg.getString("Morph." + morph);
		  if ((gd.equals("nao")) || (gd.contains("Não encontrado!")))
		  {
			  gd = "nao";
		  }
		  
		} catch (Exception e) {
			 cfg.set("Morph." + morph, "nao");
			  gd = "nao";
			  try {
				cfg.save(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		  return gd;
	  }
	  
	  public static String GetPet(Player jogador,String gadget)
	  {
		  String gd = "Não encontrado!";
		  File file = null;
		  YamlConfiguration cfg = null;
		  try {
		  file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  cfg = YamlConfiguration.loadConfiguration(file);
		  gd = cfg.getString("Pet." + gadget);
		  if ((gd.equals("nao")) || (gd.contains("Não encontrado!")))
		  {
			  gd = "nao";
		  }
		  
		} catch (Exception e) {
			 cfg.set("Pet." + gadget, "nao");
			  gd = "nao";
			  try {
				cfg.save(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		  return gd;
	  }
	  
	  public static String GetGadgets(Player jogador,String gadget)
	  {
		  String gd = "Não encontrado!";
		  File file = null;
		  YamlConfiguration cfg = null;
		  try {
		  file = new File("plugins/CHub/UserData/" + Account.getUuid(jogador).replace(" ", "").replace("ORIGINAL:", "").replace("PIRATA:", "")+ ".yml");
		  cfg = YamlConfiguration.loadConfiguration(file);
		  gd = cfg.getString("Gadget." + gadget);
		  if ((gd.equals("0")) || (gd.contains("Não encontrado!")))
		  {
			  gd = "0";
		  }
		  
		} catch (Exception e) {
			 cfg.set("Gadget." + gadget, "0");
			  gd = "0";
			  try {
				cfg.save(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		  return gd;
	  }
}

/**
  *           UtilServerPlayerStatos.java, Class CH-Group 26 de jun de 2016 - 00:39:52 by αdяiαиcf #Reloads
  *
  *###################################################################################
  *###################################################################################
  *
  *101101101101   101101101101   101101101101              10110110101    101101101101
  *101      101   101            101                       101            101
  *101      101   101            101                       101            101
  *101101101101   101            101101101101    101101    101            101
  *101      101   101            101                       101   101101   101    101101
  *101      101   101            101                       101      101   101       101
  *101      101   101101101101   101                       101101101101   101 101101101
  *
  *################################################################################### 
  *###################################################################################

                                   adriancf

  @Projeto CHProject --- proprietário CH-Group, projeto criado no dia 26 de jun de 2016 as 00:39:52        
   
  Todo o me.hub.API.Util e UtilServerPlayerStatos.java, esta sobre os direitos do CH-Group e de suas dependências.                                                     
  Portantando você, não pode retirar os direitos dos criadores desta class.


                                    @Info
                                   αdяiαиcf
     
    *  Dependências utilizadas por CH-Projets  
    *  Uso de projetos JSon
    *  Uso de Maven
    *  Uso de CGlib
    *  Entre outras...


Ass: αdяiαиcf - Códigos livres


*/



package me.hub.API.Util;

/**
 * Código por adriancf, Group CH-Project

 */
public class UtilServerPlayerStatos {

	public static String Embreve = "EmBreve";
	public static String Beta = "BETA";
	
	public void UpdateAll()
	{
		for (String nome : UtilNPC.entitys.keySet())
		{
			String name = UtilNPC.entitys.get(nome).getName();
			if (name.equals(Embreve))
			{
				UtilNPC.entitys.get(nome).setCustomName("§c- / -");
			}
			else {
				if (name.equals(Beta))
					UtilNPC.entitys.get(nome).setCustomName("§60 / 0" );
				else
				UtilNPC.entitys.get(nome).setCustomName("§e0 / 0");
			}
			
		}
	}
}

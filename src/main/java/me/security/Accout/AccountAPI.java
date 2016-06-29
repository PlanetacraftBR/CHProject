/**
  *           AccountAPI.java, Class CH-Group 29 de jun de 2016 - 13:31:11 by αdяiαиcf #Reloads
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

  @Projeto CH --- proprietário CH-Group, projeto criado no dia 29 de jun de 2016 as 13:31:11        
   
  Todo o me.security.Accout e AccountAPI.java, esta sobre os direitos do CH-Group e de suas dependências.                                                     
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



package me.security.Accout;

import org.json.JSONObject;

import me.acf.lobby.patentes.Patente;
import me.hub.Main;
import me.security.Accout.buffer.AccountBuffer;
import me.site.account.AccountWeb;
import me.site.account.rank.Rank;

/**
 * Código por adriancf, Group CH-Project

 */
public class AccountAPI {

	public String uuid;
	public String nome;
	public String kdr;
	public String cash;
	public String planets;
	public String nivel;
	public String exp;
	public String chave;
	public Rank rank;
	public Patente patente;
	public String ponto_staff;
	public String ip;
	public int Numero_Load;
	public JSONObject json;
	
	public AccountAPI(String nome)
	{
		AccountBuffer.TotalLoad++;
		Numero_Load = AccountBuffer.TotalLoad;
		this.nome = nome;
		JSONObject obj = new JSONObject(AccountWeb.Conectar(Main.site + "/API/conta.php?nick=" + nome));
		json = obj;
		uuid = obj.getString("uuid");
		cash = obj.getString("cash");
		kdr = obj.getString("kdr");
		nivel = obj.getString("nivel");
		exp = obj.getString("exp");
		chave = obj.getString("chaves");
		ponto_staff= obj.getString("pontos_staff");
		planets = obj.getString("planets"); 
		rank = Rank.valueOf(obj.getString("grupo"));
		patente = Patente.valueOf(obj.getString("patente"));
		ip = obj.getString("ip");	
	}
	
}

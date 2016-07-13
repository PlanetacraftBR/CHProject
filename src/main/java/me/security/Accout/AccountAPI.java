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

import org.bukkit.entity.Player;
import org.json.JSONObject;

import me.acf.lobby.patentes.Patente;
import me.hub.Main;
import me.hub.API.Util.UtilTime;
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
	public String chave;
	public Rank rank;
	public Patente patente;
	public String ponto_staff;
	public String ip;
	public int Numero_Load;
	public JSONObject json;
	public boolean erro = false;
	public String erro_log;
	public String erro_info;
	public int ExpAdd = 0;
	public int CashAdd = 0;
	public int PlanetsAdd = 0;
	//Tempo online
	public String info_on = "00/00/00 - 00:00:00";
	public String info_leave = "00/00/00 - 00:00:00";
	
	public AccountAPI(String nome)
	{
		AccountBuffer.TotalLoad++;
		Numero_Load = AccountBuffer.TotalLoad;
		this.nome = nome;
		AddJogador();
		if (erro)
			return;
		try {
	    JSONObject obj = new JSONObject(AccountWeb.Conectar(Main.site + "/API/Account_v2/conta.php?nick=" + nome));	
		json = obj;
		uuid = obj.getString("uuid");
		cash = obj.getString("cash");
		kdr = obj.getString("kdr");
		nivel = obj.getString("nivel");
		chave = obj.getString("chaves");
		ponto_staff= obj.getString("pontos_staff");
		planets = obj.getString("planets"); 
		rank = Rank.valueOf(obj.getString("grupo"));
		patente = Patente.valueOf(obj.getString("patente"));
		ip = obj.getString("ip");	
		info_on = UtilTime.TimeData();
		}
		 catch (Exception exception)
	    {
			 erro_log = exception.getMessage();
			 erro = true;
			 erro_info = "§7Erro ao recuperar informações da web, por favor, tente novamente em um minuto.";
			 exception.printStackTrace();
	    }
		}
	
	
	public String Tempo_online()
	{
	     String tempo = UtilTime.convertString(UtilTime.DataTempo(UtilTime.TimeData(), info_on), 0, UtilTime.TimeUnit.FIT);
	     
	     return tempo;
	}
	
	
	
	private void AddJogador()
	{
		try {
		  String site = AccountWeb.Conectar(Main.site + "/API/addconta.php?nick="+ nome);
		  if (site.contains("Servidor da mojang esta offline e nao conseguimos validar o jogador"))
  		{
			  erro = true;
			  erro_log = site;
			  erro_info = "§7Servidor da mojang esta offline tente novamente mais tarde";
  		}
		}
		 catch (Exception exception)
	    {
			 erro_log = exception.getMessage();
			 erro = true;
			 erro_info = "§7Erro ao recuperar informações da web, por favor, tente novamente em um minuto.";
			 exception.printStackTrace();
	    }
	}
	
	public void Pedente()
	{
		AddExp();
		AddPlanets();
		AddCash();
	}
	
	public int Planets()
	{
		return Integer.parseInt((String)planets.replace(".", ""));
	}
	
	
	public int Chave()
	{
		return Integer.parseInt((String)chave.replace(".", ""));
	}
	
	public int Cash()
	{
		return Integer.parseInt((String)cash.replace(".", ""));
	}
	
	 private void AddPlanets()
	 {
		 if (PlanetsAdd >= 1) {
			 String buscar = AccountWeb.Conectar(Main.site + "/API/planets.php?modo=ADD&nick=" + nome + "&quantidade=" + PlanetsAdd);
		     System.out.print(buscar);
		 }
		 if (PlanetsAdd < 0) {
			 //Não sei o URL da API de remover Planets kkkkkk
		 }
	 }
	 
	 
	
	 private void AddExp()
	  {
		 if (ExpAdd >= 1) {
		String buscar = AccountWeb.Conectar(Main.site + "/API/exp.php?modo=UPAR&nick=" + nome + "&quantidade=" + ExpAdd);
		  System.out.print(buscar);  
		 }
	  }
	 
	 private void AddCash()
	   {
		  if (CashAdd >= 1) {
			 String buscar = AccountWeb.Conectar(Main.site + "/API/cash.php?modo=ADD&nick=" + nome + "&quantidade=" + CashAdd);
		     System.out.print(buscar);
		  }
		  if (CashAdd < 0) {
			  //Não sei o URL da API de remover os Cash kkkkkkkk
		  }
			  
		  }
	 
	
}

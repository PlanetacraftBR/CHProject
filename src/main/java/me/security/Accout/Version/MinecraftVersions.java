package me.security.Accout.Version;

public enum MinecraftVersions {

	MINECRAFT_1_10(210, 14, "1.10"),
	MINECRAFT_1_9_4(110, 13, "1.9.4"),
	MINECRAFT_1_9_2(109, 12, "1.9.2"),
	MINECRAFT_1_9_1(108, 11, "1.9.1"),
	MINECRAFT_1_9(107, 10, "1.9"),
	MINECRAFT_1_8(47, 9, "1.8"),
	MINECRAFT_1_7_10(5, 8, "1.7.10"),
	MINECRAFT_1_7_5(4, 7, "1.7.5"),
	MINECRAFT_1_6_4(78, 6, "1.6.4"),
	MINECRAFT_1_6_2(74, 5, "1.6.2"),
	MINECRAFT_1_6_1(73, 4, "1.6.1"),
	MINECRAFT_1_5_2(61, 3, "1.5.2"),
	MINECRAFT_1_5_1(60, 2, "1.5.1"),
	MINECRAFT_1_4_7(51, 1, "1.4.7");
	
	private final int protocol;
	private final int ID;
	private final String nome;
	
	MinecraftVersions(int protocol, int ID, String nome) {
		this.protocol = protocol;
		this.ID = ID;
		this.nome = nome;
	}
	
	public int getProtocol() {
	return protocol;
	}
	
	public int getID() {
	return ID;
	}
	
	public String getNome() {
	return nome;
	}
		
	
	
	
	
}

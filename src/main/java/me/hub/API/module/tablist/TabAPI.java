/*
 * Copyright 2015-2016 inventivetalent. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and contributors and should not be interpreted as representing official policies,
 *  either expressed or implied, of anybody else.
 */

package me.hub.API.module.tablist;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class TabAPI {

	private static final Map<UUID, Tab> tabMap = new ConcurrentHashMap<>();

	/* Header & Footer */

	/**
	 * Updates the Tablist header
	 *
	 * @param p      {@link org.bukkit.entity.Player}
	 * @param header lines of the header
	 */
	public static void setHeader(@Nonnull Player p, @Nullable String... header) {
		setHeaderFooter(p, header, getFooter(p));
	}

	/**
	 * @param p {@link org.bukkit.entity.Player}
	 * @return The header, or null
	 */
	@Nullable
	public static String[] getHeader(@Nonnull Player p) {
		Tab tab = tabMap.get(p.getUniqueId());
		return tab != null ? tab.header : null;
	}

	/**
	 * Updates the Tablist footer
	 *
	 * @param p      {@link org.bukkit.entity.Player}
	 * @param footer lines of the footer
	 */
	public static void setFooter(@Nonnull Player p, @Nullable String... footer) {
		setHeaderFooter(p, getHeader(p), footer);
	}

	/**
	 * @param p {@link org.bukkit.entity.Player}
	 * @return The footer, or null
	 */
	@Nullable
	public static String[] getFooter(Player p) {
		Tab tab = tabMap.get(p.getUniqueId());
		return tab != null ? tab.footer : null;
	}

	/**
	 * Updates the header and footer
	 *
	 * @param p      {@link org.bukkit.entity.Player}
	 * @param header the header
	 * @param footer the footer
	 */
	public static void setHeaderFooter(@Nonnull Player p, @Nullable String header, @Nullable String footer) {
		setHeaderFooter(p, header == null ? null : new String[] { header }, footer == null ? null : new String[] { footer });
	}

	/**
	 * Updates the header and footer
	 *
	 * @param p      {@link org.bukkit.entity.Player}
	 * @param header lines of the header
	 * @param footer lines of the footer
	 */
	public static void setHeaderFooter(@Nonnull Player p, @Nullable String[] header, @Nullable String[] footer) {
		if (!p.isOnline()) { return; }
		if (!tabMap.containsKey(p.getUniqueId())) {
			tabMap.put(p.getUniqueId(), new Tab(p));
		}
		Tab tab = tabMap.get(p.getUniqueId());
		tab.header = convertJSON(header);
		tab.footer = convertJSON(footer);
		tab.updateHeaderFooter();
	}

	/* Content */

	/**
	 * Adds a {@link org.inventivetalent.tabapi.TabItem}
	 *
	 * @param p     {@link org.bukkit.entity.Player}
	 * @param items item to add
	 */
	public static void addItem(@Nonnull Player p, @Nonnull TabItem... items) {
		if (!p.isOnline()) { return; }
		if (!tabMap.containsKey(p.getUniqueId())) {
			tabMap.put(p.getUniqueId(), new Tab(p));
		}
		Tab tab = tabMap.get(p.getUniqueId());
		tab.items.addAll(Arrays.asList(items));
		tab.updateItems();
	}

	/**
	 * Sets the displayed {@link org.inventivetalent.tabapi.TabItem}s
	 *
	 * @param p     {@link org.bukkit.entity.Player}
	 * @param items {@link org.inventivetalent.tabapi.TabItem} collection
	 */
	public static void setItems(@Nonnull Player p, @Nonnull Collection<TabItem> items) {
		if (!p.isOnline()) { return; }
		if (!tabMap.containsKey(p.getUniqueId())) {
			tabMap.put(p.getUniqueId(), new Tab(p));
		}
		Tab tab = tabMap.get(p.getUniqueId());

		tab.clearItems();

		tab.items.clear();
		tab.items.addAll(items);
		tab.updateItems();
	}

	/**
	 * @param p {@link org.bukkit.entity.Player}
	 * @return {@link org.inventivetalent.tabapi.TabItem} collection
	 */
	@Nonnull
	public static Collection<TabItem> getItems(@Nonnull Player p) {
		Tab tab = tabMap.get(p.getUniqueId());
		return tab != null ? new ArrayList<>(tab.items) : new ArrayList<TabItem>();
	}

	/**
	 * Removes an item
	 *
	 * @param p     {@link org.bukkit.entity.Player}
	 * @param items {@link org.inventivetalent.tabapi.TabItem} to remove
	 */
	public static void removeItem(@Nonnull Player p, @Nonnull TabItem... items) {
		Tab tab = tabMap.get(p.getUniqueId());
		if (tab != null) {
			tab.clearItems();

			tab.items.removeAll(Arrays.asList(items));
			tab.updateItems();
		}
	}

	/**
	 * Adds the default items to the list (online players)
	 *
	 * @param player {@link org.bukkit.entity.Player}
	 */
	public static void fillDefaultItems(@Nonnull Player player) {
		List<TabItem> items = new ArrayList<>();
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (player.canSee(p)) {
				items.add(new TabItem(p.getUniqueId(), p.getPlayerListName(), p.getName(), 0, p.getGameMode()));
			}
		}
		setItems(player, items);
	}

	/**
	 * Removes all displayed items from the list (including the player)
	 *
	 * @param player {@link org.bukkit.entity.Player}
	 */
	public static void clearAllItems(@Nonnull Player player) {
		fillDefaultItems(player);
		Tab tab = tabMap.get(player.getUniqueId());
		if (tab != null) {
			tab.clearItems();
		}
	}

	/**
	 * Forces an update for the list
	 *
	 * @param player {@link org.bukkit.entity.Player}
	 */
	public static void updateTab(@Nonnull Player player) {
		Tab tab = tabMap.get(player.getUniqueId());
		if (tab != null) {
			tab.updateItems();
			tab.updateHeaderFooter();
		}
	}

	/**
	 * Removes a player's tab list (the default one will be used)
	 *
	 * @param player {@link org.bukkit.entity.Player}
	 */
	public static void removeTab(@Nonnull Player player) {
		tabMap.remove(player.getUniqueId());
	}

	// Util

	protected static String convertJSON(String noJson) {
		if (noJson == null) { return null; }
		if (noJson.startsWith("{") && noJson.endsWith("}")) {
			return noJson;
		}
		return String.format("{\"text\":\"%s\"}", noJson);
	}

	protected static String[] convertJSON(String... noJson) {
		if (noJson == null) { return null; }
		String[] converted = new String[noJson.length];
		for (int i = 0; i < noJson.length; i++) {
			if (i != 0 && (!noJson[i].startsWith("{") || !noJson[i].endsWith("}"))) {
				noJson[i] = "\\n" + noJson[i];
			}
			converted[i] = convertJSON(noJson[i]);
		}
		return converted;
	}

}

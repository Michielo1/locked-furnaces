package com.michielo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LockManager {

    /*
        Singleton implementation
     */

    private static LockManager instance;
    public static LockManager getInstance() {
        if (instance == null) instance = new LockManager();
        return instance;
    }

    public LockManager() {
        List<String> locked_list = Main.getInstance().getConfig().getStringList("Default_locked");

        locked = new ArrayList<>();

        for (String item : locked_list) {

            if (item.startsWith("%")) {
                item = item.substring(1);
                for (Material m : items) {
                    if (m.toString().toLowerCase().contains(item.toLowerCase())) {
                        locked.add(m);
                    }
                }
                return;
            }

            for (Material m : items) {
                if (m.toString().toLowerCase().equalsIgnoreCase(item)) {
                    locked.add(m);
                    break;
                }
            }
        }
    }

    /*
        Statics
     */

    private static List<Material> locked;

    private static Material[] items = {
            Material.POTATO,
            Material.KELP,
            Material.BEEF,
            Material.PORKCHOP,
            Material.MUTTON,
            Material.CHICKEN,
            Material.RABBIT,
            Material.COD,
            Material.SALMON,
            Material.RAW_IRON,
            Material.RAW_COPPER,
            Material.RAW_GOLD,
            Material.NETHER_GOLD_ORE,
            Material.ANCIENT_DEBRIS,
            Material.REDSTONE_ORE,
            Material.COAL_ORE,
            Material.LAPIS_LAZULI,
            Material.DIAMOND_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.IRON_ORE,
            Material.COPPER_ORE,
            Material.GOLD_ORE,
            Material.IRON_SHOVEL,
            Material.IRON_PICKAXE,
            Material.IRON_AXE,
            Material.IRON_HOE,
            Material.IRON_SWORD,
            Material.CHAINMAIL_HELMET,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_BOOTS,
            Material.IRON_HELMET,
            Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS,
            Material.IRON_BOOTS,
            Material.IRON_HORSE_ARMOR,
            Material.GOLDEN_SHOVEL,
            Material.GOLDEN_PICKAXE,
            Material.GOLDEN_AXE,
            Material.GOLDEN_HOE,
            Material.GOLDEN_SWORD,
            Material.GOLDEN_HELMET,
            Material.GOLDEN_CHESTPLATE,
            Material.GOLDEN_LEGGINGS,
            Material.GOLDEN_BOOTS,
            Material.GOLDEN_HORSE_ARMOR,
            Material.COBBLESTONE,
            Material.STONE,
            Material.STONE_BRICKS,
            Material.COBBLED_DEEPSLATE,
            Material.DEEPSLATE_BRICKS,
            Material.DEEPSLATE_TILES,
            Material.SANDSTONE,
            Material.RED_SANDSTONE,
            Material.NETHER_BRICK,
            Material.BASALT,
            Material.POLISHED_BLACKSTONE_BRICKS,
            Material.QUARTZ_BLOCK,
            Material.CLAY,
            Material.TERRACOTTA,
            Material.BLACK_TERRACOTTA,
            Material.BLUE_TERRACOTTA,
            Material.BROWN_TERRACOTTA,
            Material.CYAN_TERRACOTTA,
            Material.GRAY_TERRACOTTA,
            Material.WHITE_TERRACOTTA,
            Material.GREEN_TERRACOTTA,
            Material.LIME_TERRACOTTA,
            Material.MAGENTA_TERRACOTTA,
            Material.ORANGE_TERRACOTTA,
            Material.PURPLE_TERRACOTTA,
            Material.RED_TERRACOTTA,
            Material.SAND,
            Material.RED_SAND,
            Material.WET_SPONGE,
            Material.CHORUS_FRUIT,
            Material.SEA_PICKLE,
            Material.CACTUS,
            Material.CLAY_BALL,
            Material.NETHERRACK,
            Material.ACACIA_LOG,
            Material.BIRCH_LOG,
            Material.CHERRY_LOG,
            Material.JUNGLE_LOG,
            Material.OAK_LOG,
            Material.MANGROVE_LOG,
            Material.SPRUCE_LOG,
            Material.ACACIA_WOOD,
            Material.BIRCH_WOOD,
            Material.CHERRY_WOOD,
            Material.JUNGLE_WOOD,
            Material.OAK_WOOD,
            Material.MANGROVE_WOOD,
            Material.SPRUCE_WOOD,
            // TODO: add stripped logs, stripped wood
    };

    /*
        Map
     */

    // this map holds players with material that need to select a furnance to unlock/lock
    public HashMap<Player, Material> awaitingBind = new HashMap<>();
    // this map holds the player with whether or not they are locking the furnance (or unlocking)
    private HashMap<Player, Boolean> isLockBind = new HashMap<>();

    /*
        Handlers
     */

    public void unlock(Player player, String item) {
        Material m = Material.matchMaterial(item);
        if (m == null) {
            player.sendMessage(ChatColor.RED + "Unknown item!");
            return;
        }

        isLockBind.put(player, false);
        awaitingBind.put(player, m);
        player.sendMessage(ChatColor.GREEN + "Please click on the furnace you want to unlock " +
                m.toString().toLowerCase() + " on!");

        // create a runable to clear maps if it took too long
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isLockBind.get(player) != null) {
                    player.sendMessage(ChatColor.RED + "You took too long to bind a furnace!");
                    isLockBind.put(player, null);
                    awaitingBind.put(player, null);
                }
            }
        }.runTaskLater(Main.getInstance(), 20 * 10);
    }

    public void lock(Player player, String item) {
        Material m = Material.matchMaterial(item);
        if (m == null) {
            player.sendMessage(ChatColor.RED + "Unknown item!");
            return;
        }

        isLockBind.put(player, true);
        awaitingBind.put(player, m);
        player.sendMessage(ChatColor.GREEN + "Please click on the furnace you want to lock " +
                m.toString().toLowerCase() + " on!");

        // create a runable to clear maps if it took too long
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isLockBind.get(player) != null) {
                    player.sendMessage(ChatColor.RED + "You took too long to bind a furnace!");
                    isLockBind.put(player, null);
                    awaitingBind.put(player, null);
                }
            }
        }.runTaskLater(Main.getInstance(), 20 * 10);
    }

    public void handleLockOrUnlock(Player player, Block furnace) {
        List<String> modified_items = Main.getInstance().getData().getConfig().getStringList(furnace.getLocation().toString());
        modified_items.add(awaitingBind.get(player).name() + ":" + isLockBind.get(player));
        Main.getInstance().getData().getConfig().set(furnace.getLocation().toString(), modified_items);
        Main.getInstance().getData().saveConfig();

        String locked = ChatColor.GREEN + "Successfully locked the furnace!";
        if (!isLockBind.get(player)) locked = ChatColor.GREEN + "Successfully unlocked the furnace!";
        player.sendMessage(locked);

        isLockBind.put(player, null);
        awaitingBind.put(player, null);
    }

    public boolean allowedItemToBeSmolten(Material item, Block f) {
        if (!Main.getInstance().getData().getConfig().getStringList(f.getLocation().toString()).isEmpty()) {
            for (String value : Main.getInstance().getData().getConfig().getStringList(f.getLocation().toString())) {
                String itemname = value.split(":")[0];
                String locked = value.split(":")[1];
                if (Material.matchMaterial(itemname) == item) {
                    return !Boolean.valueOf(locked);
                }
            }
        }

        return !locked.contains(item);
    }

}

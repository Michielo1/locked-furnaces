package com.michielo.bukkit.listener;

import com.michielo.LockManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class ClickListener implements Listener {

    public static HashMap<HumanEntity, Block> isInFurnace = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();
            if (block.getType().equals(Material.FURNACE)) {
                isInFurnace.put(e.getPlayer(), block);
                if (LockManager.getInstance().awaitingBind.get(e.getPlayer()) != null) {
                    LockManager.getInstance().handleLockOrUnlock(e.getPlayer(), block);
                }
            }
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if (isInFurnace.get(e.getPlayer()) != null) isInFurnace.put(e.getPlayer(), null);
    }

}

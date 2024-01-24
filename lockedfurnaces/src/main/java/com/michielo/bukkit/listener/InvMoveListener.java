package com.michielo.bukkit.listener;

import com.michielo.LockManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InvMoveListener implements Listener {

    @EventHandler
    public void onInvMove(InventoryClickEvent e) {
        if (e.getView().getTopInventory().getType() == InventoryType.FURNACE) {
            if (!LockManager.getInstance().allowedItemToBeSmolten(
                    e.getCurrentItem().getType(), ClickListener.isInFurnace.get(e.getWhoClicked()))) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(ChatColor.RED + "You can't smelt this item here!");
            }
        }
    }

}

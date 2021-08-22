package com.github.sirblobman.freeze.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;


import com.github.sirblobman.freeze.FreezePlugin;
import com.github.sirblobman.freeze.manager.FreezeManager;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ListenerInteract extends FreezeListener {
    public ListenerInteract(FreezePlugin plugin) {
        super(plugin);
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onInteract(PlayerInteractEvent e) {
        FreezeManager freezeManager = getFreezeManager();
        if (freezeManager.isFrozen(e.getPlayer()) && e.getAction() == Action.RIGHT_CLICK_BLOCK && getPlugin().getConfig().getBoolean("prevent-interact")) {
            e.setCancelled(true);
            sendFrozenMessage(e.getPlayer());
        }
    }
}

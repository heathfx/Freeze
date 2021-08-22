package com.github.sirblobman.freeze.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;


import com.github.sirblobman.freeze.FreezePlugin;
import com.github.sirblobman.freeze.manager.FreezeManager;
import org.bukkit.event.block.BlockBreakEvent;

public final class ListenerBlockBreak extends FreezeListener {
    public ListenerBlockBreak(FreezePlugin plugin) {
        super(plugin);
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onBlockBreak(BlockBreakEvent e) {
        FreezeManager freezeManager = getFreezeManager();
        if (freezeManager.isFrozen(e.getPlayer()) && getPlugin().getConfig().getBoolean("prevent-break")) {
            e.setCancelled(true);
            sendFrozenMessage(e.getPlayer());
        }
    }

}

package com.github.sirblobman.freeze.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;


import com.github.sirblobman.freeze.FreezePlugin;
import com.github.sirblobman.freeze.manager.FreezeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public final class ListenerEntityDamage extends FreezeListener {
    public ListenerEntityDamage(FreezePlugin plugin) {
        super(plugin);
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onEntityDamage(EntityDamageEvent e) {
        FreezeManager freezeManager = getFreezeManager();
        if (e.getEntity() instanceof Player && getPlugin().getConfig().getBoolean("prevent-damage") && freezeManager.isFrozen((Player) e.getEntity())) {
            e.setCancelled(true);
        }
    }
}

package com.github.sirblobman.freeze.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;


import com.github.sirblobman.freeze.FreezePlugin;
import com.github.sirblobman.freeze.manager.FreezeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class ListenerEntityDamageEntity extends FreezeListener {
    public ListenerEntityDamageEntity(FreezePlugin plugin) {
        super(plugin);
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
        if (getPlugin().getConfig().getBoolean("prevent-damage")) {
        FreezeManager freezeManager = getFreezeManager();
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();
                if (freezeManager.isFrozen(p)) {
                    e.setCancelled(true);
                    sendFrozenMessage(p);
                } else if (e.getEntity() instanceof Player && freezeManager.isFrozen((Player) e.getEntity())) {
                    e.setCancelled(true);
                }
            } else if (e.getEntity() instanceof Player) {
                if (freezeManager.isFrozen((Player) e.getEntity())) {
                    e.setCancelled(true);
                }
            }
        }
    }

}

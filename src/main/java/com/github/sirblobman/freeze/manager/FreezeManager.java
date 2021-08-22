package com.github.sirblobman.freeze.manager;

import com.github.sirblobman.api.configuration.ConfigurationManager;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.sirblobman.api.utility.Validate;
import com.github.sirblobman.freeze.FreezePlugin;
import org.bukkit.configuration.file.YamlConfiguration;

public final class FreezeManager {
    private final FreezePlugin plugin;
    private final Set<UUID> frozenPlayerSet;
    private boolean allFrozen;

    public FreezeManager(FreezePlugin plugin) {
        this.plugin = Validate.notNull(plugin, "plugin must not be null!");
        this.frozenPlayerSet = new HashSet<>();
    }

    public FreezePlugin getPlugin() {
        return this.plugin;
    }
    
    //add setter for freeze all - heathfx
    public void setFreezeAll(boolean freezeAll) {
        this.allFrozen = freezeAll;
        //update persistent config option - heathfx
        ConfigurationManager configurationManager = getPlugin().getConfigurationManager();
        YamlConfiguration configuration = configurationManager.get("config.yml");
        configuration.set("freeze-all", freezeAll);
    }
    
    //add getter for freeze all - heathfx
    public boolean getFreezeAll() {
        return this.allFrozen;
    }

    public void setFrozen(Player player, boolean freeze) {
        UUID uuid = player.getUniqueId();
        if(freeze) this.frozenPlayerSet.add(uuid);
        else this.frozenPlayerSet.remove(uuid);
    }

    public boolean isFrozen(Player player) {
        //add check for freeze all - heathfx
        if(this.allFrozen) {
            return !player.hasPermission("freeze.immune");
        }
        
        UUID uuid = player.getUniqueId();
        return this.frozenPlayerSet.contains(uuid);
    }

    public void removeAll() {
        this.frozenPlayerSet.clear();
    }
}

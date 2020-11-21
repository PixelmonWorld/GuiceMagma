package org.bukkit.plugin;

import com.google.inject.Provides;
import world.pixelmon.inject.ProtectedModule;

public class PluginModule extends ProtectedModule {

    private Plugin plugin;

    public PluginModule(Plugin plugin) {
        this.plugin = plugin;
    }

    public void configure() {
        this.bind(Plugin.class).toInstance(this.plugin);
        this.plugin.configure(this.binder());
    }

    @Provides
    PluginLogger pluginLogger(Plugin plugin) {
        return (PluginLogger)plugin.getLogger();
    }
}
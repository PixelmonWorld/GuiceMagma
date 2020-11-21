package org.bukkit.craftbukkit.v1_12_R1;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.OptionalBinder;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.scheduler.CraftScheduler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.PluginModule;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.scheduler.BukkitScheduler;
import world.pixelmon.inject.ProtectedBinder;

public class CraftModule extends AbstractModule {

    private CraftServer server;
    private PluginManager pluginManager;
    private CraftScheduler scheduler;
    private ServicesManager servicesManager;
    private Plugin[] plugins;

    CraftModule(CraftServer server, PluginManager pluginManager, CraftScheduler scheduler, ServicesManager servicesManager, Plugin[] plugins) {
        this.server = server;
        this.pluginManager = pluginManager;
        this.scheduler = scheduler;
        this.servicesManager = servicesManager;
        this.plugins = plugins;
    }

    protected void configure() {
        OptionalBinder.newOptionalBinder(this.binder(), Server.class).setBinding().toInstance(this.server);
        OptionalBinder.newOptionalBinder(this.binder(), PluginManager.class).setBinding().toInstance(this.pluginManager);
        OptionalBinder.newOptionalBinder(this.binder(), BukkitScheduler.class).setBinding().toInstance(this.scheduler);
        OptionalBinder.newOptionalBinder(this.binder(), ServicesManager.class).setBinding().toInstance(this.servicesManager);

        for(Plugin plugin : plugins) {
            ProtectedBinder.newProtectedBinder(this.binder()).install(new PluginModule(plugin));
        }
    }
}
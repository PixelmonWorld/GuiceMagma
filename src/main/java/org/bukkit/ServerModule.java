package org.bukkit;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.OptionalBinder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.scheduler.BukkitScheduler;

public class ServerModule extends AbstractModule {

    public ServerModule() {
    }

    protected void configure() {
        OptionalBinder.newOptionalBinder(this.binder(), Server.class);
        OptionalBinder.newOptionalBinder(this.binder(), PluginManager.class);
        OptionalBinder.newOptionalBinder(this.binder(), BukkitScheduler.class);
        OptionalBinder.newOptionalBinder(this.binder(), ServicesManager.class);
    }
}
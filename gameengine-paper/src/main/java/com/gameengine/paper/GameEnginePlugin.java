package com.gameengine.paper;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Minimal plugin entry point.
 *
 * <p>Only packaging, logging, and startup/shutdown boundaries are established here.
 * Matchmaking, arenas, and gameplay are not implemented yet.
 */
public final class GameEnginePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(() -> "GameEngine " + getPluginMeta().getVersion() + " enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("GameEngine disabled.");
    }
}

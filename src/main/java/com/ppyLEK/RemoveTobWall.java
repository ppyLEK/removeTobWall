package com.ppyLEK;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Tob Wall Remover",
	description = "Removes roofs and other scenery at tob bank. Re-entering tob bank required.",
	tags = {"tob","theatre of blood","theater of blood"}
)
public class RemoveTobWall extends Plugin
{

	@Inject
	private Client client;

	@Inject
	private ExampleConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("RemoveTobWall started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("RemoveTobWall stopped!");
	}

	private void removeUpperFloors()
	{
		Scene scene = client.getScene();
		Tile[][][] tiles = scene.getTiles();

		for (int x = 0; x < Constants.SCENE_SIZE; ++x){
			for (int y = 0; y < Constants.SCENE_SIZE; ++y) {
					Tile tile1 = tiles[1][x][y];
					Tile tile2 = tiles[2][x][y];
					Tile tile3 = tiles[3][x][y];

					if (tile1 != null)
						scene.removeTile(tile1);

					if (tile2 != null)
						scene.removeTile(tile2);

					if(tile3 != null)
						scene.removeTile(tile3);

			}
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN && isRegionIdCorrect())
		{
			removeUpperFloors();
		}
	}

	private boolean isRegionIdCorrect()
	{
		int regionId = WorldPoint.fromLocalInstance(client, client.getLocalPlayer().getLocalLocation()).getRegionID();

        return regionId == 14642 || regionId == 14386;
	}

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}
}

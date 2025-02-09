package com.ppyLEK;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RemoveTobWallTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RemoveTobWall.class);
		RuneLite.main(args);
	}
}
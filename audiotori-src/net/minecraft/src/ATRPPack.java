package net.minecraft.src;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE 
                    Version 2, December 2004 

 Copyright (C) 2004 Sam Hocevar <sam@hocevar.net> 

 Everyone is permitted to copy and distribute verbatim or modified 
 copies of this license document, and changing it is allowed as long 
 as the name is changed. 

            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE 
   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION 

  0. You just DO WHAT THE FUCK YOU WANT TO. 
*/

public class ATRPPack
{
	private static final ResourceLocation CONFIG = new ResourceLocation("audiotori", "additional_sounds.json");
	
	private final AbstractResourcePack resourcePack;
	private final Set<String> additionalSounds;
	
	public ATRPPack(AbstractResourcePack resourcePack)
	{
		this.resourcePack = resourcePack;
		this.additionalSounds = new HashSet<String>();
		
		if (this.resourcePack.func_110589_b(CONFIG))
		{
			try
			{
				registerSounds();
			}
			catch (Exception e)
			{
				ATRPHaddon.log("Error while opening additional_sounds.json on pack" + resourcePack.func_130077_b());
				e.printStackTrace();
			}
		}
	}
	
	public AbstractResourcePack getResourcePack()
	{
		return this.resourcePack;
	}
	
	private void registerSounds() throws IOException
	{
		InputStream is = this.resourcePack.func_110591_a(CONFIG.toString());
		String jasonString = IOUtils.toString(is, "UTF-8");
		
		JsonObject jason = new JsonParser().parse(jasonString).getAsJsonObject();
		JsonArray array = jason.getAsJsonArray("array");
		for (JsonElement elt : array)
		{
			this.additionalSounds.add(elt.getAsString());
		}
	}
}

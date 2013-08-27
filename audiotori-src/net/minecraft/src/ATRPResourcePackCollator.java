package net.minecraft.src;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;

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

public class ATRPResourcePackCollator extends AbstractResourcePack
{
	private Map<String, AbstractResourcePack> assetToPackMap;
	
	public ATRPResourcePackCollator(File par1File)
	{
		super(par1File);
		
		this.assetToPackMap = new HashMap<String, AbstractResourcePack>();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set func_110587_b()
	{
		Set<ResourcePack> unique = new HashSet<ResourcePack>();
		for (ResourcePack pack : this.assetToPackMap.values())
		{
			unique.add(pack);
		}
		
		Set set = Sets.newHashSet();
		for (ResourcePack pack : unique)
		{
			set.addAll(pack.func_110587_b());
		}
		
		return set;
	}
	
	@Override
	protected InputStream func_110591_a(String requestedAsset) throws IOException
	{
		try
		{
			return this.assetToPackMap.get(requestedAsset).func_110591_a(requestedAsset);
		}
		catch (IOException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new IOException();
		}
	}
	
	@Override
	protected boolean func_110593_b(String requestedAsset)
	{
		return this.assetToPackMap.containsKey(requestedAsset);
	}
	
	public void clear()
	{
		this.assetToPackMap.clear();
	}
	
	public void overrideAsset(String asset, AbstractResourcePack pack)
	{
		this.assetToPackMap.put(asset, pack);
	}
}

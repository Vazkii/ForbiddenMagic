package com.spiteful.forbidden;

import java.io.File;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import cpw.mods.fml.common.FMLLog;

public class Config
{
	public static HashMap<String, Aspect> spawnerMobs = new HashMap<String, Aspect>();
	
	public static int clusterEnchID;
	public static int pigBaneEnchID;
	public static int greedyEnchID;
	public static int consumingEnchID;
	public static int educationalEnchID;
	public static int corruptingEnchID;
	public static int eternalEnchID;

	public static int thaumcraftTaintBiomeID;
	public static int thaumcraftTaintPotionID;
	public static ItemStack thaumcraftResourceID;
	public static ItemStack thaumcraftTaintBlockID;
	public static ItemStack thaumcraftOreID;
	public static ItemStack thaumcraftShardID;
	
	public static boolean noLust = false;
	public static boolean silverfishEmeralds = true;
	public static boolean tagResearch = true;
	public static boolean wrathCage = true;
	public static boolean spork = false;
	public static boolean greedyEnch = true;
	public static boolean emeraldTrans = true;
	public static boolean wrathCrazy = false;
	
	public static int wrathCost = 5;
	public static int wrathEff = 4;
	
	public static Material taintMaterial;

	public static void configurate(File targ)
	{
		Configuration conf = new Configuration(targ);

		try
		{
			conf.load();
			
			int enchCount = 66;
			clusterEnchID = conf.get("enchantments", "Fiery Core", enchCount++).getInt();
			pigBaneEnchID = conf.get("enchantments", "Porcivore", enchCount++).getInt();
			greedyEnchID = conf.get("enchantments", "Capitalist", enchCount++).getInt();
			consumingEnchID = conf.get("enchantments", "Consuming", enchCount++).getInt();
			educationalEnchID = conf.get("enchantments", "Educational", enchCount++).getInt();
			corruptingEnchID = conf.get("enchantments", "Corrupting", enchCount++).getInt();
			eternalEnchID = conf.get("enchantments", "Eternal", enchCount++).getInt();
			
			Property nl = conf.get("general", "No Lust", noLust);
			nl.comment = "Enable to remove Luxuria aspect and related items.";
			noLust = nl.getBoolean(false);
			Property sf = conf.get("general", "Silverfish Drop Emerald Nuggets", silverfishEmeralds);
			sf.comment = "Disable to prevent Silverfish from dropping emerald nuggets.";
			silverfishEmeralds = sf.getBoolean(true);
			Property ge = conf.get("general", "Capitalist Enchantment", greedyEnch);
			ge.comment = "Disable to remove the recipe and effects of the Capitalist enchantment.";
			greedyEnch = ge.getBoolean(true);
			Property et = conf.get("general", "Emerald Transmutation", emeraldTrans);
			et.comment = "Disable to remove the Emerald Transmutation research and recipe.";
			emeraldTrans = et.getBoolean(true);
			Property tr = conf.get("general", "Tag Research Items", tagResearch);
			tr.comment = "Disable to get rid of the [FM] tags in the Thaumonomicon.";
			tagResearch = tr.getBoolean(true);
			Property wc = conf.get("general", "Wrath Cage Enabled", wrathCage);
			wc.comment = "Disable if you don't want players using the Wrath Cage.";
			wrathCage = wc.getBoolean(true);
			Property wf = conf.get("general", "Wrath Cage Fuel Cost", wrathCost);
			wf.comment = "Cost of essentia per round of spawns in the Wrath Cage.  Raise to increase essentia costs.  Defaults to 5.  Set to 0 to remove the need to fuel the Wrath Cage.  Setting the cost above 64 is not recommended.";
			wrathCost = wf.getInt(5);
			Property we = conf.get("general", "Wrath Cage Fuel Efficiency", wrathEff);
			we.comment = "Number of spawns a Wrath Cage can get per fuel cost.  Defaults to 4.  Lower to make the cage less efficient and raise to make it more efficient.";
			wrathEff = we.getInt(4);
			if(wrathEff < 0)
				wrathEff = 4;
			Property cw = conf.get("general", "Wrath Cage Cries Havoc", wrathCrazy);
			cw.comment = "Enable to let the Wrath Cage imprint on ANY non-boss mob.  May break your game or make your game Awesome.";
			wrathCrazy = cw.getBoolean(false);
			
			Property sd = conf.get("silly", "Spork of Doom", spork);
			sd.comment = "What is this?  I don't even...";
			spork = sd.getBoolean(false);
		}
		catch (Exception e)
		{
			LogHandler.log(Level.ERROR, e, "Had a problem loading its configuration.");
		}
		finally
		{
			conf.save();
		}
		
		try {
		
			thaumcraftResourceID = ItemApi.getItem("itemResource", 0);
			thaumcraftShardID = ItemApi.getItem("itemShard", 0);
			thaumcraftTaintBlockID = ItemApi.getBlock("blockTaint", 0);
			thaumcraftOreID = ItemApi.getBlock("blockCustomOre", 0);
			taintMaterial = Block.getBlockFromItem(thaumcraftTaintBlockID.getItem()).getMaterial();
			
			thaumcraftTaintPotionID = Class.forName("thaumcraft.common.config.Config").getField("potionFluxTaintID").getInt(null);
			thaumcraftTaintBiomeID = Class.forName("thaumcraft.common.config.Config").getField("biomeTaintID").getInt(null);
		}
		catch(Exception e)
		{
			LogHandler.log(Level.ERROR, e, "There was problem when retrieving information from Thaumcraft.");
			e.printStackTrace();
		}
		
	}
	
	public static void spawnilify()
	{
		if(wrathCage){
			spawnerMobs.put("Zombie", Aspect.FLESH);
			spawnerMobs.put("Skeleton", Aspect.DEATH);
			spawnerMobs.put("Creeper", Aspect.FIRE);
			spawnerMobs.put("EntityHorse", Aspect.BEAST);
			spawnerMobs.put("Pig", Aspect.BEAST);
			spawnerMobs.put("Sheep", Aspect.CLOTH);
			spawnerMobs.put("Cow", Aspect.BEAST);
			spawnerMobs.put("MushroomCow", Aspect.PLANT);
			spawnerMobs.put("Ozelot", Aspect.BEAST);
			spawnerMobs.put("Chicken", Aspect.FLIGHT);
			spawnerMobs.put("Squid", Aspect.SENSES);
			spawnerMobs.put("Wolf", Aspect.BEAST);
			spawnerMobs.put("Bat", Aspect.FLIGHT);
			spawnerMobs.put("Spider", Aspect.CLOTH);
			spawnerMobs.put("Slime", Aspect.SLIME);
			spawnerMobs.put("Ghast", DarkAspects.NETHER);
			spawnerMobs.put("PigZombie", Aspect.GREED);
			spawnerMobs.put("Enderman", Aspect.ELDRITCH);
			spawnerMobs.put("CaveSpider", Aspect.POISON);
			if(silverfishEmeralds)
				spawnerMobs.put("Silverfish", Aspect.GREED);
			else
				spawnerMobs.put("Silverfish", Aspect.BEAST);
			spawnerMobs.put("Blaze", Aspect.FIRE);
			spawnerMobs.put("LavaSlime", Aspect.FIRE);
			spawnerMobs.put("Witch", Aspect.MAGIC);
			spawnerMobs.put("Thaumcraft.Firebat", Aspect.FIRE);
			spawnerMobs.put("Thaumcraft.Wisp", Aspect.AURA);
			spawnerMobs.put("Thaumcraft.ThaumSlime", Aspect.TAINT);
			spawnerMobs.put("Thaumcraft.BrainyZombie", Aspect.MIND);
			spawnerMobs.put("Thaumcraft.TaintSpider", Aspect.TAINT);
			spawnerMobs.put("Thaumcraft.TaintSwarm", Aspect.TAINT);
			spawnerMobs.put("Thaumcraft.TaintedPig", Aspect.TAINT);
			spawnerMobs.put("Thaumcraft.TaintedSheep", Aspect.TAINT);
			spawnerMobs.put("Thaumcraft.TaintedCow", Aspect.TAINT);
			spawnerMobs.put("Thaumcraft.TaintedChicken", Aspect.TAINT);
			spawnerMobs.put("Thaumcraft.TaintedVillager", Aspect.TAINT);
			//spawnerMobs.put("Taintacle", DarkAspects.LUST);
			
		}
	}
}
/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.ModInteract;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import Reika.DragonAPI.ModList;
import Reika.DragonAPI.Base.ModHandlerBase;
import Reika.DragonAPI.Libraries.Java.ReikaJavaLibrary;
import Reika.DragonAPI.ModRegistry.ModOreList;

public final class MekanismHandler extends ModHandlerBase {

	private static final MekanismHandler instance = new MekanismHandler();

	public final int oreID;
	public final int cableID;

	public static final int osmiumMeta = 0;
	public static final int copperMeta = 1;
	public static final int tinMeta = 2;

	private MekanismHandler() {
		super();
		int idore = -1;
		int idcable = -1;
		if (this.hasMod()) {
			try {
				Class blocks = this.getMod().getBlockClass();
				Field ore = blocks.getField("OreBlock");
				Block b = (Block)ore.get(null);
				idore = b.blockID;

				Field wire = blocks.getField("Transmitter");
				b = (Block)wire.get(null);
				idcable = b.blockID;
			}
			catch (NoSuchFieldException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: "+this.getMod()+" field not found! "+e.getMessage());
				e.printStackTrace();
			}
			catch (SecurityException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Cannot read "+this.getMod()+" (Security Exception)! "+e.getMessage());
				e.printStackTrace();
			}
			catch (IllegalArgumentException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Illegal argument for reading "+this.getMod()+"!");
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Illegal access exception for reading "+this.getMod()+"!");
				e.printStackTrace();
			}
			catch (NullPointerException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Null pointer exception for reading "+this.getMod()+"! Was the class loaded?");
				e.printStackTrace();
			}
		}
		else {
			this.noMod();
		}
		oreID = idore;
		cableID = idcable;
	}

	public static MekanismHandler getInstance() {
		return instance;
	}

	@Override
	public boolean initializedProperly() {
		return oreID != -1 && cableID != -1;
	}

	@Override
	public ModList getMod() {
		return ModList.MEKANISM;
	}

	public ModOreList getModOre(int id, int meta) {
		if (id != oreID)
			return null;

		if (meta == osmiumMeta)
			return ModOreList.OSMIUM;
		if (meta == tinMeta)
			return ModOreList.TIN;
		if (meta == copperMeta)
			return ModOreList.COPPER;

		return null;
	}

}

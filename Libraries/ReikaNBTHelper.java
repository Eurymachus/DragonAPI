/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.Libraries;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import Reika.DragonAPI.DragonAPICore;

public final class ReikaNBTHelper extends DragonAPICore {

	/** Saves an inventory to NBT. Args: Inventory, NBT Tag */
	public static void writeInvToNBT(ItemStack[] inv, NBTTagCompound NBT) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < inv.length; i++)
		{
			if (inv[i] != null)
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte)i);
				inv[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		NBT.setTag("Items", nbttaglist);
	}

	/** Reads an inventory from NBT. Args: NBT Tag */
	public static ItemStack[] getInvFromNBT(NBTTagCompound NBT) {
		NBTTagList nbttaglist = NBT.getTagList("Items");
		ItemStack[] inv = new ItemStack[nbttaglist.tagCount()];

		for (int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
			byte byte0 = nbttagcompound.getByte("Slot");

			if (byte0 >= 0 && byte0 < inv.length)
			{
				inv[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}
		return inv;
	}

	public static Fluid getFluidFromNBT(NBTTagCompound NBT) {
		String name = NBT.getString("liquid");
		if (name == null || name.isEmpty() || name.equals("empty"))
			return null;
		return FluidRegistry.getFluid(name);
	}

	public static void writeFluidToNBT(NBTTagCompound NBT, Fluid f) {
		String name = f != null ? f.getName() : "empty";
		NBT.setString("liquid", name);
	}

}

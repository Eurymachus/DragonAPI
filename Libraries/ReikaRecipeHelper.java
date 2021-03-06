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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import Reika.DragonAPI.DragonAPICore;
import Reika.DragonAPI.Instantiable.ExpandedOreRecipe;
import Reika.DragonAPI.Libraries.Java.ReikaJavaLibrary;
import Reika.DragonAPI.Libraries.Registry.ReikaItemHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class ReikaRecipeHelper extends DragonAPICore {

	private static final CraftingManager cr = CraftingManager.getInstance();
	private static final List<IRecipe> recipes = cr.getRecipeList();

	/** Finds a recipe by its product. */
	public static IRecipe getRecipeByOutput(ItemStack out) {
		return recipes.get(recipes.indexOf(out));
	}

	/** Finds recipes by product. */
	public static List<IRecipe> getRecipesByOutput(ItemStack out) {
		List<IRecipe> li = new ArrayList<IRecipe>();
		for (int i = 0; i < recipes.size(); i++) {
			IRecipe ir = recipes.get(i);
			if (ItemStack.areItemStacksEqual(ir.getRecipeOutput(), out))
				li.add(ir);
		}
		return li;
	}

	/** Finds recipes by product. */
	public static List<ShapedRecipes> getShapedRecipesByOutput(ItemStack out) {
		List<ShapedRecipes> li = new ArrayList<ShapedRecipes>();
		for (int i = 0; i < recipes.size(); i++) {
			IRecipe ir = recipes.get(i);
			if (ir instanceof ShapedRecipes) {
				if (ItemStack.areItemStacksEqual(ir.getRecipeOutput(), out))
					li.add((ShapedRecipes)ir);
			}
		}
		return li;
	}

	/** Finds recipes by product. */
	public static List<ShapedRecipes> getShapedRecipesByOutput(List<IRecipe> in, ItemStack out) {
		List<ShapedRecipes> li = new ArrayList<ShapedRecipes>();
		for (int i = 0; i < in.size(); i++) {
			IRecipe ir = in.get(i);
			//ReikaJavaLibrary.pConsole(ir.getRecipeOutput()+" == "+out);
			if (ir instanceof ShapedRecipes) {
				if (ReikaItemHelper.matchStacks(ir.getRecipeOutput(), out))
					li.add((ShapedRecipes)ir);
			}
		}
		//ReikaJavaLibrary.pConsole(li);
		return li;
	}

	/** Returns the item in a shaped recipe at x, y in the grid. */
	public static ItemStack getItemInRecipeAtXY(ShapedRecipes r, int x, int y) {
		int xy = x+r.recipeWidth*y;
		return r.recipeItems[xy];
	}

	/** Finds recipes by product. */
	public static List<IRecipe> getAllRecipesByOutput(List<IRecipe> in, ItemStack out) {
		List<IRecipe> li = new ArrayList<IRecipe>();
		for (int i = 0; i < in.size(); i++) {
			IRecipe ir = in.get(i);
			//ReikaJavaLibrary.pConsole(ir.getRecipeOutput()+" == "+out);
			if (ReikaItemHelper.matchStacks(ir.getRecipeOutput(), out))
				li.add(ir);
		}
		//ReikaJavaLibrary.pConsole(li);
		return li;
	}

	/** Finds recipes by product. */
	public static List<ShapedOreRecipe> getShapedOreRecipesByOutput(List<IRecipe> in, ItemStack out) {
		List<ShapedOreRecipe> li = new ArrayList<ShapedOreRecipe>();
		for (int i = 0; i < in.size(); i++) {
			IRecipe ir = in.get(i);
			//ReikaJavaLibrary.pConsole(ir.getRecipeOutput()+" == "+out);
			if (ir instanceof ShapedOreRecipe) {
				if (ReikaItemHelper.matchStacks(ir.getRecipeOutput(), out))
					li.add((ShapedOreRecipe)ir);
			}
		}
		//ReikaJavaLibrary.pConsole(li);
		return li;
	}

	/** Finds recipes by product. */
	public static List<ExpandedOreRecipe> getExpandedOreRecipesByOutput(List<IRecipe> in, ItemStack out) {
		List<ExpandedOreRecipe> li = new ArrayList<ExpandedOreRecipe>();
		for (int i = 0; i < in.size(); i++) {
			IRecipe ir = in.get(i);
			//ReikaJavaLibrary.pConsole(ir.getRecipeOutput()+" == "+out);
			if (ir instanceof ExpandedOreRecipe) {
				if (ReikaItemHelper.matchStacks(ir.getRecipeOutput(), out))
					li.add((ExpandedOreRecipe)ir);
			}
		}
		//ReikaJavaLibrary.pConsole(li);
		return li;
	}

	/** Finds recipes by product. */
	public static List<ShapelessRecipes> getShapelessRecipesByOutput(List<IRecipe> in, ItemStack out) {
		List<ShapelessRecipes> li = new ArrayList<ShapelessRecipes>();
		for (int i = 0; i < in.size(); i++) {
			IRecipe ir = in.get(i);
			if (ir instanceof ShapelessRecipes) {
				if (ReikaItemHelper.matchStacks(ir.getRecipeOutput(), out))
					li.add((ShapelessRecipes)ir);
			}
		}
		//ReikaJavaLibrary.pConsole(li);
		return li;
	}

	/** Finds recipes by product. */
	public static List<ShapelessOreRecipe> getShapelessOreRecipesByOutput(List<IRecipe> in, ItemStack out) {
		List<ShapelessOreRecipe> li = new ArrayList<ShapelessOreRecipe>();
		for (int i = 0; i < in.size(); i++) {
			IRecipe ir = in.get(i);
			if (ir instanceof ShapelessOreRecipe) {
				if (ReikaItemHelper.matchStacks(ir.getRecipeOutput(), out))
					li.add((ShapelessOreRecipe)ir);
			}
		}
		//ReikaJavaLibrary.pConsole(li);
		return li;
	}

	/** Turns a recipe into a 3x3 itemstack array for rendering. Args: ItemStack[] array, Recipe */
	public static void copyRecipeToItemStackArray(ItemStack[] in, IRecipe ire) {
		ItemStack[] isin = new ItemStack[9];
		int num;
		int w = 0;
		int h = 0;
		ReikaJavaLibrary.pConsole("Recipe is null!", ire == null);
		ReikaJavaLibrary.pConsole("ItemStack array is null!", in == null);
		if (ire instanceof ShapedRecipes) {
			ShapedRecipes r = (ShapedRecipes)ire;
			num = r.recipeItems.length;
			w = r.recipeWidth;
			h = r.recipeHeight;
			for (int i = 0; i < r.recipeItems.length; i++) {
				isin[i] = r.recipeItems[i];
			}

		}
		else if (ire instanceof ShapedOreRecipe) {
			ShapedOreRecipe so = (ShapedOreRecipe)ire;
			Object[] objin = so.getInput();
			//ReikaJavaLibrary.pConsole(Arrays.toString(objin));
			w = 3;
			h = 3;
			for (int i = 0; i < objin.length; i++) {
				if (objin[i] instanceof ItemStack)
					isin[i] = (ItemStack)objin[i];
				else if (objin[i] instanceof ArrayList) {
					if (!((List<IRecipe>)objin[i]).isEmpty())
						isin[i] = (ItemStack)((ArrayList)objin[i]).get(0);
				}
			}
		}
		else if (ire instanceof ExpandedOreRecipe) {
			ExpandedOreRecipe so = (ExpandedOreRecipe)ire;
			Object[] objin = so.getInputCopy();
			//ReikaJavaLibrary.pConsole(Arrays.toString(objin));
			w = so.getWidth();
			h = so.getHeight();
			for (int i = 0; i < objin.length; i++) {
				if (objin[i] instanceof ItemStack)
					isin[i] = (ItemStack)objin[i];
				else if (objin[i] instanceof ArrayList) {
					if (!((List<IRecipe>)objin[i]).isEmpty())
						isin[i] = (ItemStack)((ArrayList)objin[i]).get(0);
				}
			}
		}
		else if (ire instanceof ShapelessRecipes) {
			ShapelessRecipes sr = (ShapelessRecipes)ire;
			//ReikaJavaLibrary.pConsole(ire);
			for (int i = 0; i < sr.getRecipeSize(); i++) {
				in[i] = (ItemStack)sr.recipeItems.get(i);
			}
		}
		else if (ire instanceof ShapelessOreRecipe) {
			ShapelessOreRecipe so = (ShapelessOreRecipe)ire;
			for (int i = 0; i < so.getRecipeSize(); i++) {
				Object obj = so.getInput().get(i);
				if (obj instanceof ItemStack)
					in[i] = (ItemStack)obj;
				else if (obj instanceof ArrayList) {
					in[i] = (ItemStack)((ArrayList)obj).get(0);
				}
				//ReikaJavaLibrary.pConsole(ire);
			}
		}
		if (w == 3 && h == 3) {
			for (int i = 0; i < 9; i++)
				in[i] = isin[i];
		}
		if (w == 1 && h == 1) {
			in[4] = isin[0];
		}
		if (w == 2 && h == 2) {
			in[0] = isin[0];
			in[1] = isin[1];
			in[3] = isin[2];
			in[4] = isin[3];
		}
		if (w == 1 && h == 2) {
			in[4] = isin[0];
			in[7] = isin[1];
		}
		if (w == 2 && h == 1) {
			in[0] = isin[0];
			in[1] = isin[1];
		}
		if (w == 3 && h == 1) {
			in[0] = isin[0];
			in[1] = isin[1];
			in[2] = isin[2];
		}
		if (w == 1 && h == 3) {
			in[1] = isin[0];
			in[4] = isin[1];
			in[7] = isin[2];
		}
		if (w == 2 && h == 3) {
			in[0] = isin[0];
			in[1] = isin[1];
			in[3] = isin[2];
			in[4] = isin[3];
			in[6] = isin[4];
			in[7] = isin[5];
		}
		if (w == 3 && h == 2) {
			in[3] = isin[0];
			in[4] = isin[1];
			in[5] = isin[2];
			in[6] = isin[3];
			in[7] = isin[4];
			in[8] = isin[5];
		}
		for (int i = 0; i < in.length; i++) {
			//ReikaJavaLibrary.pConsole(in[i]+" for "+i);
			if (in[i] != null) {
				if (in[i].stackSize > 1)
					in[i].stackSize = 1;//in[1] = new ItemStack(in[i].itemID, 4, in[i].getItemDamage());
			}
		}
	}

	/** Get the smelting recipe of an item by output. Args: output */
	public static ItemStack getFurnaceInput(ItemStack out) {
		HashMap m = (HashMap)FurnaceRecipes.smelting().getMetaSmeltingList();
		Set ks = m.keySet();
		Object[] ob = ks.toArray();
		for (int i = 0; i < ob.length; i++) {
			//ReikaJavaLibrary.pConsole(ob[i]);
			try {
				int id = (Integer)((List)ob[i]).get(0);
				int meta = (Integer)((List)ob[i]).get(1);
				ItemStack is = new ItemStack(id, 1, meta);
				if (ReikaItemHelper.matchStacks(FurnaceRecipes.smelting().getSmeltingResult(is), out)) {
					return is;
				}
			}
			catch (ClassCastException e) {
				ReikaJavaLibrary.pConsole(e.getMessage());
			}
		}
		return null;
	}

	/** Adds a smelting recipe. Args; Item in, item out, xp */
	public static void addSmelting(ItemStack in, ItemStack out, float xp) {
		FurnaceRecipes.smelting().addSmelting(in.itemID, in.getItemDamage(), out, xp);
	}

	/** Returns true if succeeded. */
	public static boolean addOreRecipe(ItemStack out, Object... in) {
		ShapedOreRecipe so = new ShapedOreRecipe(out, in);
		boolean allowed = true;
		ArrayList<String> missing = new ArrayList();
		for (int i = 0; i < in.length; i++) {
			if (in[i] instanceof String) {
				String s = (String) in[i];
				if (i > 0 && in[i-1] instanceof Character) {
					if (!ReikaItemHelper.oreItemExists(s)) {
						allowed = false;
						missing.add(s);
					}
				}
			}
		}
		if (allowed)
			GameRegistry.addRecipe(so);
		else
			ReikaJavaLibrary.pConsole("Recipe for "+out.getDisplayName()+" requires missing Ore Dictionary items "+missing+", and has not been loaded.");
		return allowed;
	}
}

/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.Libraries.MathSci;

public class ReikaThermoHelper {

	public static final double WATER_HEAT = 4.18;
	public static final double GRAPHITE_HEAT = 0.71;

	public static final double SODIUM_HEAT = 1.23;

	public static final double WATER_BOIL_ENTHALPY = 2260; // kJ/kg

	/** Heat energy in one block */
	public static final double WATER_BLOCK_HEAT = 4180000D;

	/** E = mc delta T */
	public static double getTemperatureIncrease(double C, double M, double E) {
		return E/M/C;
	}
}

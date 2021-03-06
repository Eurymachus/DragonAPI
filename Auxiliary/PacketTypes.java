/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.Auxiliary;

public enum PacketTypes {

	DATA(),
	SOUND(),
	STRING(),
	UPDATE(),
	FLOAT(),
	SYNC(),
	TANK();

	private PacketTypes() {

	}

	public static PacketTypes getPacketType(int type) {
		return PacketTypes.values()[type];
	}

}

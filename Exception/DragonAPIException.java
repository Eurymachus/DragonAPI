/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.Exception;


public abstract class DragonAPIException extends RuntimeException {

	protected StringBuilder message = new StringBuilder();

	@Override
	public String getMessage() {
		return message.toString();
	}

	protected void crash() {
		//Minecraft.getMinecraft().crashed(CrashReport.makeCrashReport(this, this.getMessage()));
		//this.printStackTrace();
		throw this;
	}

}

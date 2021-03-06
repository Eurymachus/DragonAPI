/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.Instantiable.IO;

import java.util.ArrayList;

import Reika.DragonAPI.Base.DragonAPIMod;
import Reika.DragonAPI.Libraries.IO.ReikaChatHelper;
import Reika.DragonAPI.Libraries.Java.ReikaJavaLibrary;

public class ModLogger {

	private final boolean logLoading;
	private final boolean printDebug;
	private final boolean shouldWarn;

	private final DragonAPIMod mod;

	private static boolean logAll = false;
	private static boolean logNone = false;

	private static final ArrayList<ModLogger> loggers = new ArrayList();

	public ModLogger(DragonAPIMod mod, boolean load, boolean debug, boolean warn) {
		this.mod = mod;
		logLoading = load;
		printDebug = debug;
		shouldWarn = warn;
		if (mod == null)
			throw new IllegalArgumentException("Cannot create a logger for a null mod!");
		if (this.shouldLog())
			ReikaJavaLibrary.pConsole(mod.getTechnicalName()+": Creating logger. Log Loading: "+load+"; Debug mode: "+debug+"; Warnings: "+warn);
		loggers.add(this);
	}

	public void debug(Object o) {
		if (this.shouldDebug()) {
			ReikaJavaLibrary.pConsole(o);
			ReikaChatHelper.write(o);
		}
	}

	public void log(Object o) {
		if (this.shouldLog())
			ReikaJavaLibrary.pConsole(mod.getTechnicalName()+": "+o);
	}

	public void logError(Object o) {
		ReikaJavaLibrary.pConsole(mod.getTechnicalName()+": There was an error: "+o);
		//Thread.dumpStack();
	}

	public boolean shouldLog() {
		if (logNone)
			return false;
		if (logAll)
			return true;
		return logLoading;
	}

	public boolean shouldDebug() {
		return printDebug;
	}

	public boolean shouldWarn() {
		return shouldWarn;
	}

	public void warn(Object o) {
		if (this.shouldWarn()) {
			ReikaJavaLibrary.pConsole(o);
			ReikaChatHelper.write(o);
		}
	}

	public static void setAllLoggingTrue() {
		logAll = true;
		logNone = false;
	}

	public static void setAllLoggingFalse() {
		logAll = false;
		logNone = true;
	}

	public static void setAllLoggingDefault() {
		logNone = false;
		logAll = false;
	}

	public static int getActiveLoggers() {
		return loggers.size();
	}

}

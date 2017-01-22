package org.usfirst.frc.team610.robot.subsystems;

import org.spectrum3847.RIOdroid.RIOdroid;

public class AdbUtils{
	
	public static String adbReverseForward(int remotePort, int localPort) {
		try {
			System.out.println("adb reversing");
			return RIOdroid.executeCommand("adb reverse tcp:" + remotePort + " tcp:" + localPort);
		} catch(Exception e) {
			System.out.println("adb reverse failed");
			return e.getMessage();	
		}
	}
	
	public static void restartApp() {
		// TODO Restart app
	}
}

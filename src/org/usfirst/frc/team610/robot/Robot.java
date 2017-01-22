
package org.usfirst.frc.team610.robot;

import java.util.logging.Logger;

import org.spectrum3847.RIOdroid.RIOdroid;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static OI oi;
	private Command vision;
	public static Logger logger;
	public static VisionServer visionServer;
	boolean useConsole = true, useFile = true;

	public void robotInit() {
		oi = OI.getInstance();
		RIOdroid.initUSB();
		
		
	}

	public void disabledInit() {
//		vision.cancel();
		visionServer = VisionServer.getInstance();
	}

	public void disabledPeriodic() {
//		vision.cancel();
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
//		vision.cancel();
		visionServer = VisionServer.getInstance();
	}

	public void autonomousPeriodic() {
//		vision.cancel();
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		visionServer = VisionServer.getInstance();
	}

	public void teleopPeriodic() {
		SmartDashboard.putNumber("Value", visionServer.getDouble());
		SmartDashboard.putString("Raw", visionServer.getRawInput());
		Scheduler.getInstance().run();
		
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}

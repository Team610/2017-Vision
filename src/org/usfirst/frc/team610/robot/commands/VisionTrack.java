package org.usfirst.frc.team610.robot.commands;

import java.io.IOException;

import org.sixten.chareslib.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionTrack extends Command {
	private PID pid;
	private double target;
	private Preferences prefs;
	private Vision vision;
	boolean isPressed;

	public VisionTrack() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		pid = new PID(PIDConstants.VISION_P, 0.0, PIDConstants.VISION_D);
		prefs = Preferences.getInstance();
		vision = Vision.getInstance();
		isPressed = false;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		target = 0;
		pid.updatePID(PIDConstants.VISION_P, 0.0, PIDConstants.VISION_D);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		SmartDashboard.putString("Serial Output", vision.getValue());
		System.out.println("Serial Output: " + vision.getValue());

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

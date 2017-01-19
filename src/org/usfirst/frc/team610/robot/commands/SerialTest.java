package org.usfirst.frc.team610.robot.commands;

import org.sixten.chareslib.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.Serial;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SerialTest extends Command {
	private PID pid;
	private double target;
	private Preferences prefs;
	private Serial port;
	boolean isPressed;

	public SerialTest() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		prefs = Preferences.getInstance();
		port = Serial.getInstance(SerialPort.Port.kOnboard);
		
		isPressed = false;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		SmartDashboard.putString("Serial Output", port.getValue());
		System.out.println("Serial Output: " + port.getValue());

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

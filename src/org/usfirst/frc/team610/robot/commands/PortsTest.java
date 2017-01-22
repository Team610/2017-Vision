package org.usfirst.frc.team610.robot.commands;

import java.io.IOException;

import org.usfirst.frc.team610.robot.subsystems.ADBBridge;
import org.usfirst.frc.team610.robot.subsystems.Ports;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PortsTest extends Command {

	ADBBridge port;
	
    public PortsTest() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	port = ADBBridge.getInstance(3000);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	port.run();
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

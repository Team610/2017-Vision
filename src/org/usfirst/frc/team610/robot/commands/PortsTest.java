package org.usfirst.frc.team610.robot.commands;

import java.io.IOException;

import org.usfirst.frc.team610.robot.subsystems.Ports;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PortsTest extends Command {

	Ports port;
	
    public PortsTest() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	port = Ports.getInstance(8080);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			SmartDashboard.putNumber("Data", port.getInput());
			System.out.println(port.getInput());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e + " Cannot get data");
		}
    	
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

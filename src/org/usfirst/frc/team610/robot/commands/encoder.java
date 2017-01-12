package org.usfirst.frc.team610.robot.commands;

import org.sixten.chareslib.PID;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class encoder extends Command {

	
	private DriveTrain drive;
	private PID pid;
	private double target;
	private double set;
	private OI oi;
	
    public encoder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drive = DriveTrain.getInstance();
    	pid = new PID(PIDConstants.ENCODER_Kp, PIDConstants.ENCODER_Ki, PIDConstants.ENCODER_Kd);
    	oi = OI.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	target = 0.0;
    	set = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(oi.getDriver().getRawButton(LogitechF310Constants.BTN_Y)){
    		set ++;
    	} else if(oi.getDriver().getRawButton(LogitechF310Constants.BTN_A)){
    		set --;
    	}
    	
    	if(oi.getDriver().getRawButton(LogitechF310Constants.BTN_X)){
    		target += set;
    		set = 0;
    	}
    	
    	SmartDashboard.putNumber("Target: ", set);
    	
    	double leftSpeed = pid.getValue(drive.getLeftInches(), target);
    	double rightSpeed = pid.getValue(drive.getRightInches(), target);
    	
    	drive.setLeft(leftSpeed);
    	drive.setRight(rightSpeed);
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

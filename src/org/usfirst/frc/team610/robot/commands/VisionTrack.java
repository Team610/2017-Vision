package org.usfirst.frc.team610.robot.commands;

import java.util.List;

import org.sixten.chareslib.PID;
import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.LogitechF310Constants;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.subsystems.AdbBridge;
import org.usfirst.frc.team610.robot.subsystems.TargetInfo;
import org.usfirst.frc.team610.robot.subsystems.Vision;
import org.usfirst.frc.team610.robot.subsystems.VisionUpdate;

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
	VisionUpdate update;
	AdbBridge bridge;
	List<TargetInfo> info;

    public VisionTrack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	pid = new PID(PIDConstants.VISION_P, 0.0 , PIDConstants.VISION_D);
    	prefs = Preferences.getInstance();
    	vision = Vision.getInstance();
    	isPressed = false;
    	bridge = new AdbBridge();
    	update = new VisionUpdate();
   
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	target = 0;
    	pid.updatePID(PIDConstants.VISION_P, 0.0 , PIDConstants.VISION_D);
    	bridge.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.getInstance().getDriver().getRawButton(LogitechF310Constants.BTN_A) && !isPressed){
        	pid.updatePID(PIDConstants.VISION_P, 0.0 , PIDConstants.VISION_D);
    		isPressed = true;
    	}
    	if(!OI.getInstance().getDriver().getRawButton(LogitechF310Constants.BTN_A)){
    		isPressed = false;
    	}
    	// Test
    	
    	info = update.getTargets();
    	
    	double speed = pid.getValue(vision.getValue(), 0);
    	
    	vision.setMotor(speed);
    	
    	SmartDashboard.putNumber("Value", vision.getValue());
    	
    	PIDConstants.update();
    	
    	SmartDashboard.putNumber("P", PIDConstants.VISION_P);
    	SmartDashboard.putNumber("D", PIDConstants.VISION_D);
    	SmartDashboard.putNumber("Speed", speed);
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
package org.usfirst.frc.team610.robot.subsystems;

import org.sixten.chareslib.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Turret extends Subsystem {
	
	private static Turret instance;
	private Victor motor;
	private PID visionPID;
	private DigitalInput homingSensor;
	private double curMotorPow,curTarget;
	private boolean isLeft;
	
	
	public static Turret getInstance(){
		if(instance == null){
			instance = new Turret();
		}
		return instance;
	}

	private Turret(){
		visionPID = new PID(PIDConstants.VISION_P, 0, PIDConstants.VISION_D);
		motor = new Victor(9);
		homingSensor = new DigitalInput(13);
		curMotorPow = 0;
		curTarget = 0;
		isLeft = false;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void run(){
		PIDConstants.update();
		visionPID.updatePID(PIDConstants.VISION_P, 0, PIDConstants.VISION_D);
		curTarget = VisionServer.getInstance().getDouble();
		if(curTarget != 50000){
			curMotorPow = visionPID.getValue(curTarget, 0, 0);
			motor.set(curMotorPow);
			if(homingSensor.get()){
				if(curMotorPow>0)
					isLeft = true;
				else
					isLeft = false;
			}
		}
		else{
			if(homingSensor.get())
				motor.set(0);
			else if(isLeft)
				motor.set(-0.5);
			else
				motor.set(0.5);
		}
		
	}
	
	public void stop(){
		motor.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


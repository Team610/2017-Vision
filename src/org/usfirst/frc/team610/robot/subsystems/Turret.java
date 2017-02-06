package org.usfirst.frc.team610.robot.subsystems;

import org.sixten.chareslib.PID;
import org.usfirst.frc.team610.robot.constants.PIDConstants;
import org.usfirst.frc.team610.robot.vision.VisionServer;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Turret extends Subsystem {
	
	private static Turret instance;
	private Victor motor;
	private Victor light;
	private PID visionPID;
	
	
	public static Turret getInstance(){
		if(instance == null){
			instance = new Turret();
		}
		return instance;
	}

	private Turret(){
		visionPID = new PID(PIDConstants.VISION_P, PIDConstants.VISION_I, PIDConstants.VISION_D);
		motor = new Victor(0);
		light = new Victor(1);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void run(){
		PIDConstants.update();
		visionPID.updatePID(PIDConstants.VISION_P, PIDConstants.VISION_I, PIDConstants.VISION_D);
		motor.set(visionPID.getValue(VisionServer.getInstance().getDouble(), 0, 0));
		light.set(0.5);
	}
	
	public void stop(){
		motor.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


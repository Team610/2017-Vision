package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Subsystem {

	private static Vision instance;
	private NetworkTable table;
	private Talon motor;
	
	
	public static Vision getInstance(){
		if(instance == null){
			instance = new Vision();
		}
		return instance;
	}
	
	private Vision() {
    	table = NetworkTable.getTable("datatable");
    	motor = new Talon(0);
	}
	
	public double getValue(){
		return table.getNumber("xValue", 0);
	}
	
	public void setMotor(double speed){
		motor.set(speed);
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}


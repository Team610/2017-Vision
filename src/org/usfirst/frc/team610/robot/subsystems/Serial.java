package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Serial extends Subsystem {

	private static Serial instance;
	private SerialPort port;

	public static Serial getInstance() {
		if (instance == null) {
			instance = new Serial();
		}
		return instance;
	}
	

	private Serial() {
		port = new SerialPort(115200,Port.kMXP);
		
	}

	public String getValue() {
		return port.readString();
	}



	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

}

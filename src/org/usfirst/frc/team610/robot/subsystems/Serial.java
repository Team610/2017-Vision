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

	public static Serial getInstance(Port port) {
		if (instance == null) {
			instance = new Serial(port);
		}
		return instance;
	}
	

	private Serial(Port port) {
		this.port = new SerialPort(115200, port);
		
	}

	public String getValue() {
		return port.readString();
	}



	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

}

package org.usfirst.frc.team610.robot.subsystems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.spectrum3847.RIOdroid.RIOadb;
import org.spectrum3847.RIOdroid.RIOdroid;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Subsystem {

	private static Vision instance;
	private NetworkTable table;
	private Talon motor;
	private SerialPort port;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	
	public static Vision getInstance(){
		if(instance == null){
			instance = new Vision();
		}
		return instance;
	}
	
	private Vision() {
    	table = NetworkTable.getTable("datatable");
    	motor = new Talon(0);
    	port = new SerialPort(115200, Port.kOnboard);
	}
	
	public String getValue(){
		return port.readString();
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}


package org.usfirst.frc.team610.robot.subsystems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.spectrum3847.RIOdroid.RIOadb;
import org.spectrum3847.RIOdroid.RIOdroid;

import edu.wpi.first.wpilibj.SerialPort;
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
	}
	
	public void startADB(){

		RIOdroid.initUSB();
		RIOadb.init();
		Timer.delay(1);
		RIOadb.clearNetworkPorts();
		Timer.delay(1);
		RIOadb.forward(8080, 3800);
		try {
		    serverSocket = create(new int[] {3800});
		    clientSocket = serverSocket.accept();
		} catch (IOException ex) {
		    System.err.println("no available ports");
		}
		
	}
	
	public ServerSocket create(int[] ports) throws IOException {
	    for (int port : ports) {
	        try {
	            return new ServerSocket(port);
	        } catch (IOException ex) {
	            continue; // try next port
	        }
	    }

	    // if the program gets here, no port in the range was found
	    throw new IOException("no free port found");
	}
	
	public String getValue() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		return in.readLine();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}


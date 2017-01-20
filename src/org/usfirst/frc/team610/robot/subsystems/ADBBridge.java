package org.usfirst.frc.team610.robot.subsystems;

import java.io.IOException;

import org.spectrum3847.RIOdroid.RIOadb;
import org.spectrum3847.RIOdroid.RIOdroid;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ADBBridge extends Subsystem {

	private static ADBBridge instance;
	private Ports port;
	
	public static ADBBridge getInstance(int port){
		if(instance == null){
			instance = new ADBBridge(port);
		}
		return instance;
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private ADBBridge(int port){
		this.port = Ports.getInstance(port);
		startADB();
		
	}
	
	public void startADB(){
		RIOdroid.initUSB(); //Start LibUsb
        RIOadb.init();      //Start up ADB deamon and get an instance of jadb
        Timer.delay(1);
        System.out.println(RIOadb.clearNetworkPorts());
        Timer.delay(1);
        System.out.println("FOWARD ADB: " + RIOadb.forward(3800,8080));
        Timer.delay(1);
        System.out.println("FOWARD SOCAT: " + RIOadb.forwardToLocal(8080,3800));
        //System.out.println("FOWARD: " + RIOadb.forward(8080, 8080));
        System.out.println("FINISHED ROBOT INIT");
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getValue(){
    	double out;
    	try {
			out = port.getInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			out = 0;
		}
    	return out;
    }
}


package org.usfirst.frc.team610.robot.subsystems;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Ports extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private DataInputStream input;
	private static Ports instance;
	
	public static Ports getInstance(int port){
		if(instance == null){
			try {
				instance = new Ports(port);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
		return instance;
	}
	
	
	private Ports(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		input = new DataInputStream(clientSocket.getInputStream());
	}
	
	public double getInput() throws IOException{
		return input.readDouble();
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


package org.usfirst.frc.team610.robot.subsystems;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;

import org.spectrum3847.RIOdroid.RIOadb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ADBBridge extends Subsystem {

	private static ADBBridge instance;
	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream input;
	private int port;

	public static ADBBridge getInstance(int port) {
		if (instance == null) {
			instance = new ADBBridge(port);
		}
		return instance;
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private ADBBridge(int port) {
		this.port = port;
		System.out.println("VisionServer initializing");
		try {
			// Creating a socket and setting up connection over adb to start tcp
			serverSocket = new ServerSocket(port);
			RIOadb.init();
			System.out.println(AdbUtils.adbReverseForward(port, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Timer.delay(1);
		try {
			socket = serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
        // Once thread used, do not run entire loop
        if (socket == null) {
        	System.out.println("Socket disconnected");
        }
        try {
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[2048];
            int read;
            // Continue while connected and have messages to read
            if (socket.isConnected() && (read = is.read(buffer)) != -1) {
                String messageRaw = new String(buffer, 0, read);
                System.out.println(messageRaw);

            }
        } catch (IOException e) {
            System.err.println("Could not talk to socket");
            socket = null;
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

}

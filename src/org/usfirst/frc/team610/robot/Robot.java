
package org.usfirst.frc.team610.robot;

import org.usfirst.frc.team610.robot.commands.encoder;
import org.usfirst.frc.team610.robot.commands.vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

	public static OI oi;
	private Command enc;
	private Command gyro;
	private Command vision;


    public void robotInit() {
		oi = OI.getInstance();
       enc = new encoder();
       vision = new vision();      
    }
	
    public void disabledInit(){
    	vision.cancel();
    }
	
	public void disabledPeriodic() {
		vision.cancel();
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {

    }

    public void autonomousPeriodic() {
    	vision.cancel();
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	
    	vision.start();
    }

   
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}

package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.Robot;
import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Mixer extends Command {

	//Timer time;
	
    public Mixer()
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.harvester);
    //	time=new Timer();
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
//    	time.reset();
//    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
//    	if(time.get()%5==0)
//    	{
    		//Robot.harvester.mixerReverse();
//    	}
//    	else
//    	{
    	Robot.harvester.mixerGo();
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.harvester.mixerStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	//time.stop();
    	end();
    }
}

package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearMiddle extends CommandGroup {

    public GearMiddle(double theta, double adjacent) 
    {
//        // Add Commands here:
//        // e.g. addSequential(new Command1());
//        //      addSequential(new Command2());
//        // these will run in order.
//
//        // To run multiple commands at the same time,
//        // use addParallel()
//        // e.g. addParallel(new Command1());

   
    
 
       	SmartDashboard.putNumber("theta", theta);
       	if(Math.abs(theta) > 3)
       	{
    	addSequential(new AutoAlign(-theta*2.0));
    	SmartDashboard.putNumber("MiddleDrive", Math.abs(adjacent/Math.cos(Math.toRadians(90 - (Math.abs(theta)*2)))-16));
    	addSequential(new AutonDrive(.3,  Math.abs(adjacent/Math.sin(Math.toRadians(Math.abs(theta)*2) ) ) -16));
    	addSequential(new AutoAlign((theta*2.0)));
       	}
    //	SmartDashboard.putNumber("Leg",Math.cos(Math.toRadians(theta)*SmartDashboard.getNumber("gearDistanceToTarget",0)));
    	//addSequential(new AutonDrive(.3,));
    	 //Math.cos(Math.toRadians(theta)*SmartDashboard.getNumber("gearDistanceToTarget",0)))
      	
   
    	
    	addSequential(new AutonDrive(.3,Math.abs(SmartDashboard.getNumber("gearDistanceToTarget",0))-12));
    }
}

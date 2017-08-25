package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearLeft extends CommandGroup {

    public GearLeft() 
    {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]'''''''''''''''''''''''''''''''''''''''''''''''''
    	//''
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    
    	addSequential(new AutonDrive(.3,55,.2));
    	addSequential(new AutoAlign(-60,0.0002));
     	addSequential(new AutonDrive(.3,66.5,3,0.0008,.1));
    	addSequential(new Delay(1.5));
    	//original values: -.5,32.5
    //	addSequential(new AutonDrive(-.3,20,.1));
    }
}

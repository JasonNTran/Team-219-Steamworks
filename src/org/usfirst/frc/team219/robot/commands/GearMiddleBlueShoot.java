package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearMiddleBlueShoot extends CommandGroup {

    public GearMiddleBlueShoot() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,CCCCCCCCCCCCCCCCCCCCCCCCCCCC2
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	//Left Bolier
    	System.out.println("Initial");
    	addParallel(new ToggleShooter(15));
    	addSequential(new AutonDrive(.3,89,3.0,0.0008,.1)); 
    	addSequential(new Delay(1.1));
    	addSequential(new AutonDrive(-.5,32.5));
       	addSequential(new AutoAlign(101.5,0.0001)); 
     
       	addParallel(new Augurs_System());

    	addSequential(new AutonDrive(.3,13.0, 1.0,.0008,.1)); //weird encoders?
    
    	//addSequential(new AutonDrive(.3.0));

    	
    } 
}

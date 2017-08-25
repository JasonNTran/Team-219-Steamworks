package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearMiddleRedShoot extends CommandGroup {

    public GearMiddleRedShoot() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addParallel(new ToggleShooter(15));
    	//original values: .3,89,3.0,0.0008,.1
    	//.6, .45
    	addSequential(new AutonDrive(.7, 47.5, 1.5,0.0008,.1));
    	addSequential(new Delay(1.75));
    	//original values: -.5,32.5
    	addSequential(new AutonDrive(-.7,24.5,.2));
    	
    	
    	addParallel(new Augurs_System(.6));
    	addSequential(new AutoAlign(-92,0.0001));
    	//Augur system used to be down here
    	
    	
    	//original values: .3,13.0, .3
    
    	addSequential(new AutonDrive(.6,5.0,.5));
    	//addSequential(new AutoAlign(true));
    
    }
}

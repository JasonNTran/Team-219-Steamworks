package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Faster_Middle extends CommandGroup {

    public Faster_Middle() {
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
    	addSequential(new AutonDrive(.8,50.1,1,0.0008,.1));
    	addSequential(new Delay(.05));
    	addSequential(new AutonDrive(.3, 39.3,1,.088,.1));
    	addSequential(new Delay(.8));
    	addSequential(new AutonDrive(-.5,32.5,.2));
    	addSequential(new AutoAlign(-96,0.0001));
    	addParallel(new Augurs_System());
    	addSequential(new AutonDrive(.3,13.0,.2));
    	
    
    }
}

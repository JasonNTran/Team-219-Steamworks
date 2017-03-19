package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonBlueBoiler extends CommandGroup {

    public AutonBlueBoiler() {
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
    	addParallel(new ToggleShooter(10));
    	addSequential(new Delay(1.5));
    	addParallel(new Augurs_System());
    	addSequential(new Delay(8.5));
    	addSequential(new AutonShooterDrive(.35,-.50,.7));
    	addSequential(new AutonShooterDrive(-.6,-.55,1.3));
    	
    	
    }
}

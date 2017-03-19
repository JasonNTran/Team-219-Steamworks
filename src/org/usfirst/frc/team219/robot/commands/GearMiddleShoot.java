package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearMiddleShoot extends CommandGroup {

    public GearMiddleShoot() {
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
    	//Left Bolier
    	addParallel(new ToggleShooter(13));
    	addSequential(new AutonDrive(.3,80));
    	addSequential(new Delay(3.0));//2.0
    	addSequential(new AutonDrive(-.5,40));
    	addSequential(new AutoAlign(105,0.0001));
    	addSequential(new Augurs_System());
    }
}

package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Augurs_System extends CommandGroup {

    public Augurs_System() 
    {
    	requires(Robot.Augur);
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
    	
    	addParallel(new ForwardToggleAugur());
    	addParallel(new Mixer());
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
    public Augurs_System(double speed) 
    {
    	requires(Robot.Augur);
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
    	addSequential(new Delay(.25));
    	addSequential(new Delay(.1));
    	addParallel(new ForwardToggleAugur(speed));
    	addParallel(new Mixer());
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

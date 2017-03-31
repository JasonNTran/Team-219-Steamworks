package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonRedBoiler extends CommandGroup 
{

    public AutonRedBoiler() 
    {
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
    	
//    	addParallel(new ToggleShooter(10));
//    	addSequential(new Delay(1.5));
//    	addParallel(new Augurs_System());
//    	addSequential(new Delay(8));
//    	addSequential(new AutonShooterDrive(.8,.65,.8));
    	
//    	addParallel(new ToggleShooter(10.2));
//    	addSequential(new Delay(1.5));
//    	addParallel(new Augurs_System());
//    	addSequential(new Delay(8.5));
//    	addSequential(new AutonShooterDrive(-.8,.55,1.5));
//    	addSequential(new AutonShooterDrive(-.55,-.60,1.3));
    	addParallel(new ToggleShooter(10.2));
    	addSequential(new Delay(1.5));
    	addParallel(new Augurs_System());
    	addSequential(new Delay(8.5));
    	addSequential(new AutonShooterDrive(.5,.50,.3));
    	addSequential(new AutonShooterDrive(-.55,-.7,1.3));
    }
}

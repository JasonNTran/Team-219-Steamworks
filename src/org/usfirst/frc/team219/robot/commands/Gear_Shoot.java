package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Gear_Shoot extends CommandGroup {

    public Gear_Shoot() {
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
    	addSequential(new GearLeft());
    	addSequential(new ToggleShooter());
    	double theta=SmartDashboard.getNumber("shooterDegreeToMove",0);
    	double hyp=SmartDashboard.getNumber("shooterDistanceToMove",0);
//    	addSequential(new AutoAlign(2.0*theta));
//    	addSequential(new AutonDrive(.3,hyp));
//    	addSequential(new AutoAlign(-2.0*theta));
    	addSequential(new AutonDrive(.3,SmartDashboard.getNumber("shooterDegreeToTarget",0)));
    	addSequential(new Augurs_System());
    }
}

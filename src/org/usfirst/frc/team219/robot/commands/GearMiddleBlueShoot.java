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
    	addParallel(new ToggleShooter(14.5));
    	//original values: .3,89,3.0,0.0008,.1
    	//.6, .45
    	addSequential(new AutonDrive(.7,50,1.5,0.0008,.1));
    	addSequential(new Delay(1.75));//maybe we should increase
    	//original values: -.5,32.5
    	addSequential(new AutonDrive(-.7,24.5,.2));
    	
    	//101.5
    	addParallel(new Augurs_System(.6));
       	addSequential(new AutoAlign(100.5,0.0001, .01)); 
    
       	
       	//Augur system used to be down here
      
    	//original value: addSequential(new AutonDrive(.3,13.0, 1.0,.0008,.1));
      
    	addSequential(new AutonDrive(.6,5.0,.58));
//SEE IF NEEDED addSequential(new AutoAlign(true));
    	//addSequential(new AutonDrive(.3.0));

    	
    } 
}

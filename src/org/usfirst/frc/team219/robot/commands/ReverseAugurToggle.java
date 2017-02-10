package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *This command will toggle the augur to go in reverse in case some fuel units get stuck in the augur.
 */
public class ReverseAugurToggle extends Command 
{
	public ReverseAugurToggle() 
	{
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Augur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.Augur.reverse();	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.Augur.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	end();
    }
}

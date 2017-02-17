package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This command allows the operator to control the robot rather than an autonomous command.
 */
public class TeleopDrive extends Command 
{
	public TeleopDrive()
	{
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.drivetrain.tankDrive(Robot.oi.getRightSpeed(), Robot.oi.getLeftSpeed());
		if(Robot.oi.mainDriver.getPOV() == 0 ||  Robot.oi.mainDriver.getPOV() == 45|| Robot.oi.mainDriver.getPOV() == 315)
		{
			Robot.drivetrain.setInvertedStatis(true);
		}
		if(Robot.oi.mainDriver.getPOV() == 180 ||  Robot.oi.mainDriver.getPOV() == 225|| Robot.oi.mainDriver.getPOV() == 135)
		{
			Robot.drivetrain.setInvertedStatis(false);
		}
	
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end() 
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

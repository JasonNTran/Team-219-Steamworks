package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *This command allows the robot to turn to any specified angle using PID.
 */
public class AutonTurn extends Command 
{
	private double speed;
	private double angleToTurn;
	private double targetAngle;
	/**
	 * @param speed The speed the user wants the robot to turn at.
	 * @param angleToTurn The difference between the current yaw of the robot and the angle the user wants to turn to.
	 */
	public AutonTurn(double speed, double angleToTurn) 
	{
		this.speed = speed;
		this.angleToTurn = angleToTurn;
	}
	
    // Called just before this Command runs the first time
    protected void initialize()
    {
    	targetAngle = Robot.imu.getAngle() + angleToTurn;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	SmartDashboard.putNumber("Target ANGLE ", targetAngle);
    	if(angleToTurn > 0) {
    		Robot.drivetrain.tankDrive(speed-.045,-speed);
    	}
    	if(angleToTurn < 0) {
    		Robot.drivetrain.tankDrive(-speed+.045, speed );
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        if(angleToTurn > 0) 
        {
        	return Robot.imu.getAngle() >= targetAngle - 32;
        }
        else if(angleToTurn < 0)
        {
        	return Robot.imu.getAngle() <= targetAngle + 32;
        }
        else
        	return false;
    }
    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	end();
    }
}

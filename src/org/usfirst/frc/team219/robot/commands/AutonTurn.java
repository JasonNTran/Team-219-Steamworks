package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *This command allows the robot to turn to any specified angle using PID.
 */
public class AutonTurn extends Command implements PIDOutput
{
	private double speed;
	private double angleToTurn;
	private double targetAngle;
	private double rotateToAngleRate;
	
	private PIDController turnController;
	private final double kP = 0.00125;
	private final double kI = 0;
	private final double kD = 0;
	private final double kF = 0;
	
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
    	targetAngle = Robot.imu.getYaw() + angleToTurn;
    	turnController = new PIDController(kP, kI, kD, kF, Robot.imu, this);
    	turnController.setInputRange(-180f, 180f);
    	turnController.setOutputRange(-0.5, 0.5);
    	turnController.setContinuous(true);
    	turnController.setSetpoint(targetAngle);
    	turnController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	if(angleToTurn > 0) {
    		Robot.drivetrain.tankDrive(speed + rotateToAngleRate, 0);
    	}
    	if(angleToTurn < 0) {
    		Robot.drivetrain.tankDrive(0, speed + rotateToAngleRate);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        if(angleToTurn > 0) 
        {
        	return Robot.imu.getYaw() >= targetAngle;
        }
        else if(angleToTurn < 0)
        {
        	return Robot.imu.getYaw() <= targetAngle;
        }
        else
        	return false;
    }
    // Called once after isFinished returns true
    protected void end() 
    {
    	turnController.disable();
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	end();
    }

	@Override
	public void pidWrite(double output) 
	{
		rotateToAngleRate = output;
	}
}

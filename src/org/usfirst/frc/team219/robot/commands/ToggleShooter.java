package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *This command toggles the ball shooter, which runs off of PID.
 */
public class ToggleShooter extends Command implements PIDOutput
{
	private double kP = 0.003;
	private double kI = 0.01;
	private double kD = 0.075;
	private double speedUp = 0;
	private double setVelocity = -12.75;//-13
	private PIDController shooterController;

	public ToggleShooter() 
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooter);
	}
	public ToggleShooter(double vel) 
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooter);
		setVelocity = -vel;
	}
 
	// Called just before this Command runs the first time
	protected void initialize() 
	{
		shooterController = new PIDController(kP, kI, kD, Robot.shooter, this);
		shooterController.setSetpoint(setVelocity);
		shooterController.setInputRange(-20.0,20.0);
		shooterController.setPercentTolerance(.1);
		shooterController.setOutputRange(-1,1);
		shooterController.setContinuous();
		shooterController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() 
	{
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		shooterController.disable();
		Robot.shooter.setMotorSpeed(0);
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
		Robot.shooter.setMotorSpeed(output);
		//Robot.shooter.shooterMotor.set(-.5);
		SmartDashboard.putNumber("Current Velocity", Robot.shooter.getRotationRate());
	}

}

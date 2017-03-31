package org.usfirst.frc.team219.robot.commands;

import java.time.LocalDateTime;
import java.util.Date;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//left = +;
/**
 *Autonomous drive for the robot. The user can specify the speed the robot moves, the inches to drive, and the time to drive.
 *Note: Yaw is increased when turned to the left and decreased when turned to the right.
 */
public class AutonDrive extends Command implements PIDOutput
{
	private double speed;
	private double myInchesToDrive;
	private boolean timedDrive;

	private PIDController turnController;
	private double rotateToAngleRate;
	private double targetAngle;
	private static final double kP = 0.00;//.00225
	private static double kI = 0.0008;
	private static double kD = 0.1;
	private static final double kF = 0.0;
	private Timer timer;
	private double timeStop = 15;

	
	/**
	 * @param speed The speed the robot will move at.
	 * @param driveTime The amount of time the robot will run for.
	 */
	/*
	 * @param speed The speed the robot will move at.
	 * @param inchesToDrive The distance in inches the robot will move.
	 */
	public AutonDrive(double speed, double inchesToDrive) 
	{
		requires(Robot.drivetrain);
		Robot.drivetrain.resetEncoders();
		this.speed = speed;
		myInchesToDrive = inchesToDrive;
		timedDrive = false;
		timer = new Timer();
	}
	
	public AutonDrive(double speed, double inchesToDrive,double timeDrive,double i, double d) 
	{
		requires(Robot.drivetrain);
		Robot.drivetrain.resetEncoders();
		this.speed = speed;
		myInchesToDrive = inchesToDrive;
		kI=i;
		kD=d;
		timer = new Timer();
		timeStop = timeDrive;
	}

	// Called just before this Command runs the first time
	protected void initialize() 
	{
		Robot.drivetrain.resetEncoders();
		if(!timedDrive) 
			targetAngle = Robot.imu.getYaw();
		turnController = new PIDController(kP, kI, kD, kF, Robot.imu, this);
		turnController.setInputRange(-180f, 180f);
		turnController.setOutputRange(-0.5, 0.5);
		turnController.setPercentTolerance(.01);
		turnController.setContinuous(true);
		turnController.setSetpoint(targetAngle);
		turnController.enable();
		SmartDashboard.putData("Auton Drive controller", turnController);
		SmartDashboard.putNumber("Target Angle", targetAngle);
		timer.start();
		}

	// Called repeatedly when this Command is scheduled to run 
	protected void execute() 
	{	
		int direction = timedDrive || myInchesToDrive > 0 ? 1: -1;
		Robot.drivetrain.tankDrive(direction *(speed) - rotateToAngleRate,direction * (speed) +rotateToAngleRate );
		//direction *(speed) - rotateToAngleRate  -.01 

		SmartDashboard.putNumber("Set Inches", myInchesToDrive);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
		if(timer.get() > .3)
		{
			return myInchesToDrive <= Robot.drivetrain.getDistance()  || timeStop < timer.get();
		}
		else
		{
			return false;
		}
		
	}
	// Called once after isFinished returns true
	protected void end() 
	{
		turnController.disable();
		
		Robot.drivetrain.tankDrive(0,0);
		Robot.drivetrain.resetEncoders();
		
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
		SmartDashboard.putNumber("Auton Drive Output", output);
	}
}

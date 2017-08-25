package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoAlign extends Command implements PIDOutput
{
	private double angleToTurn;
	private double targetAngle;
	private double rotateToAngleRate;
	private boolean UsingVision=false;
	private boolean Vision2=false;

	private PIDController turnController;
	private double kP = 0.0026;
	private double kI = 0.000;
	private final double kD = 0.0;
	private final double kF = 0;
	private final double kTolerance = 2;
	

	public AutoAlign(double angleToTurn, double iValue) 
	{
		requires(Robot.drivetrain);
		this.angleToTurn = angleToTurn;
		kI = iValue;
		UsingVision=false;
	}
	public AutoAlign(double angleToTurn, double pValue, double iValue) 
	{
		requires(Robot.drivetrain);
		this.angleToTurn = angleToTurn;
		kI = iValue;
		kP = pValue;
		UsingVision=false;
	}
	public AutoAlign(boolean vision)
	{
		UsingVision=vision;
		angleToTurn=0;
	}
	public AutoAlign(double angleToTurn,  double iValue,boolean visiondos)
	{
		requires(Robot.drivetrain);
		this.angleToTurn = angleToTurn;
		kI = iValue;
	
		UsingVision=false;
		Vision2=visiondos;
	}
	// Called just before this Command runs the first time
	protected void initialize()
	{	
		Robot.drivetrain.resetEncoders();
		targetAngle = (Robot.imu.getYaw() + angleToTurn);
		if(UsingVision)//TO FIX LATER ON PRACTICE FIELD
			// +20?
			targetAngle=(Robot.imu.getYaw()+SmartDashboard.getNumber("shooterDegree", 0)-3);
		targetAngle = fixYaw(targetAngle);
		turnController = new PIDController(kP, kI, kD, kF, Robot.imu, this);
		turnController.setInputRange(-180f, 180f);
		turnController.setOutputRange(-0.45, 0.45);
		turnController.setContinuous(true);
		turnController.setSetpoint(targetAngle);
		turnController.setAbsoluteTolerance(kTolerance);
		turnController.enable();
		SmartDashboard.putNumber("Target Check", targetAngle);
		SmartDashboard.putString("Turn Running?", "Yes");
	}

	// Called repeatedly when this Command is scheduled to run 
	protected void execute()
	{
	
		Robot.drivetrain.tankDrive(-rotateToAngleRate, rotateToAngleRate);
		
	}
	public boolean OnTarget()
	{
		if(Vision2)
		{
			SmartDashboard.putString("Am i working?", "Yes");
			SmartDashboard.putNumber("ShooterActual", SmartDashboard.getNumber("shooterDegree",0));
			return SmartDashboard.getNumber("shooterDegree",0)>-47&&SmartDashboard.getNumber("shooterDegree",0)<20;
		}
		else
		{
			return Math.abs(Robot.imu.getYaw()-targetAngle)<kTolerance;
		}
				
	}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
		return OnTarget();
	}
	// Called once after isFinished returns true
	protected void end() 
	{
		SmartDashboard.putString("Turn Running?", "No");
		turnController.disable();
		Robot.drivetrain.tankDrive(0, 0);
		Robot.drivetrain.resetEncoders();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() 
	{
		SmartDashboard.putString("Turn Running?", "Interrupted");
		end();
	}

	@Override
	public void pidWrite(double output) 
	{
		rotateToAngleRate = output;
		SmartDashboard.putNumber("Turn Output", output);
		SmartDashboard.putBoolean("ONTARGET", OnTarget());
	}

	public double getSetpoint()
	{
		return targetAngle;
	}

	public double fixYaw(double angle)
	{
		if(angle > 180)
			return angle - 360;
		if(angle < -180)
			return angle + 360;
		return angle;
	}
}
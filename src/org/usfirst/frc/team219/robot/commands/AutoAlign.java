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

	private PIDController turnController;
	private final double kP = 0.06;
	private final double kI = 0.0;
	private final double kD = 0.0;
	private final double kF = 0;
	private final double kTolerance = 5;

	public AutoAlign(double angleToTurn) 
	{
		requires(Robot.drivetrain);
		this.angleToTurn = angleToTurn;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.drivetrain.resetEncoders();
		targetAngle = Robot.imu.getYaw() + angleToTurn;
		targetAngle = fixYaw(targetAngle);

		turnController = new PIDController(kP, kI, kD, kF, Robot.imu, this);
		turnController.setInputRange(-180f, 180f);
//		turnController.setOutputRange(-0.5, 0.5);
		turnController.setContinuous(true);
		turnController.setSetpoint(targetAngle);
		turnController.setAbsoluteTolerance(kTolerance);
		turnController.enable();
		SmartDashboard.putNumber("Target Turn", targetAngle);
		SmartDashboard.putString("Turn Running?", "Yes");

	}

	// Called repeatedly when this Command is scheduled to run 
	protected void execute()
	{
		Robot.drivetrain.tankDrive(rotateToAngleRate, -rotateToAngleRate);
		SmartDashboard.putNumber("Robot Yaw", Robot.imu.getYaw());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
		return Math.abs(Robot.imu.getYaw() - targetAngle) <= kTolerance;
	}
	// Called once after isFinished returns true
	protected void end() 
	{
		SmartDashboard.putString("Turn Running?", "No");
		turnController.disable();

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
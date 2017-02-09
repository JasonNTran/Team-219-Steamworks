package org.usfirst.frc.team219.robot.commands;

import java.util.Date;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//left = +;
/**
 *
 */
public class AutonDrive extends Command implements PIDOutput {

	private double speed;
	private double inchesToDrive;
	private double startDistance;
	private long endTime;
	private boolean timedDrive;

	private PIDController turnController;
	private double rotateToAngleRate;
	private double targetAngle;
	private static final double kP = 0.00125;
	private static final double kI = 0.0;
	private static final double kD = 0.0;
	private static final double kF = 0.0;


	public AutonDrive(double speed, int driveTime) {
		requires(Robot.drivetrain);
		Robot.drivetrain.resetEncoders();
		this.speed = speed;
		timedDrive = true;
		setTimeout(driveTime);
	}

	public AutonDrive(double speed, double inchesToDrive) {
		requires(Robot.drivetrain);
		Robot.drivetrain.resetEncoders();
		this.speed = speed;
		this.inchesToDrive = inchesToDrive;
		timedDrive = false;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if(!timedDrive) {
			startDistance = Robot.drivetrain.getDistance();
		}
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
		//Robot.drivetrain.motorBR.
	}

	// Called repeatedly when this Command is scheduled to run 
	protected void execute() {
		int direction = timedDrive || inchesToDrive > 0 ? 1: -1;
		Robot.drivetrain.tankDrive(direction *(speed + 0.045) + rotateToAngleRate,  direction * (speed) -rotateToAngleRate);
		SmartDashboard.putNumber("Auton Drive Yaw", Robot.imu.getYaw());
		SmartDashboard.putNumber("Set", inchesToDrive);
		SmartDashboard.putNumber("Actual?", Robot.drivetrain.getDistance());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
		//		if(timedDrive) {
		//			return isTimedOut();
		//		}
		//		else if(inchesToDrive > 0) {
		//			return inchesToDrive <= Robot.drivetrain.getDistance();
		//		}
		//		else if(inchesToDrive < 0) {
		//			return inchesToDrive >= Robot.drivetrain.getDistance();
		//		}
		if(Math.abs(Robot.drivetrain.getDistance())>=inchesToDrive) {
			return true;
		}
		else
			return false;

	}

	// Called once after isFinished returns true
	protected void end() {
		turnController.disable();
		Robot.drivetrain.tankDrive(0,0);
		Robot.drivetrain.resetEncoders();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
		SmartDashboard.putNumber("Auton Drive Output", output);
	}
}

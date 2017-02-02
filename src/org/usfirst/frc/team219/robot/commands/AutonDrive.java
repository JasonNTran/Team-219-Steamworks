package org.usfirst.frc.team219.robot.commands;

import java.util.Date;

import org.usfirst.frc.team219.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonDrive extends Command implements PIDOutput {

	private double speed;
	private int timeToDrive;
	private double inchesToDrive;
	private double startDistance;
	private long endTime;
	private boolean timedDrive;

	private PIDController turnController;
	private double rotateToAngleRate;
	private double targetAngle;
	private static final double kP = 0.0;
	private static final double kI = 0.0;
	private static final double kD = 0.0;
	private static final double kF = 0.0;


	public AutonDrive(double speed) {
		requires(Robot.drivetrain);
		this.speed = speed;
	}

	public AutonDrive(double speed, int driveTime) {
		requires(Robot.drivetrain);
		this.speed = speed;
		timeToDrive = driveTime;
		timedDrive = true;
	}

	public AutonDrive(double speed, double inchesToDrive) {
		requires(Robot.drivetrain);
		this.speed = speed;
		this.inchesToDrive = inchesToDrive;
		timedDrive = false;
	}


	// Called just before this Command runs the first time
	protected void initialize() {
		if(timedDrive) {
			endTime = (new Date()).getTime() + timeToDrive;
		}
		else {
			startDistance = Robot.drivetrain.getDistance();
			timedDrive = false;
		}

		targetAngle = Robot.imu.getYaw();
		turnController = new PIDController(kP, kI, kD, kF, Robot.imu, this);
		turnController.setInputRange(-180f, 180f);
		turnController.setOutputRange(-0.1, 0.1);
		turnController.setPercentTolerance(.01);
		turnController.setContinuous(true);
		turnController.setSetpoint(targetAngle);
		turnController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int direction = timedDrive || inchesToDrive > 0 ? 1: -1;
		Robot.drivetrain.tankDrive(direction * speed + rotateToAngleRate,  direction * speed - rotateToAngleRate);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(timedDrive) {
			return new Date().getTime() > endTime;
		}
		else if(inchesToDrive > 0) {
			return inchesToDrive >= Robot.drivetrain.getDistance() - inchesToDrive;
		}
		else if(inchesToDrive < 0) {
			return inchesToDrive <= Robot.drivetrain.getDistance() - inchesToDrive;
		}
		else
			return true;

	}

	// Called once after isFinished returns true
	protected void end() {
		turnController.disable();
		Robot.drivetrain.tankDrive(0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	@Override
	public void pidWrite(double output) {
		synchronized (this) {
			rotateToAngleRate = output;
		}
	}
}

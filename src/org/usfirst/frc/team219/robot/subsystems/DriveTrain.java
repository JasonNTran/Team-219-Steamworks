package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;
import org.usfirst.frc.team219.robot.commands.OpDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Drive Train subsystem for Team 219's 2017 robot.
 */
public class DriveTrain extends Subsystem implements PIDSource{

	private static final boolean driveByTime = false;
	private CANTalon motorBL, motorFL, motorBR, motorFR, talon6, talon7, talon8, talon5;
	private PIDSourceType pidSourceType = PIDSourceType.kDisplacement;

	public DriveTrain() {
		motorBL = new CANTalon(RobotMap.MOTORBL_PORT);
		motorFL = new CANTalon(RobotMap.MOTORFL_PORT);
		motorBR = new CANTalon(RobotMap.MOTORBR_PORT);
		motorFR = new CANTalon(RobotMap.MOTORFR_PORT);
		talon5 = new CANTalon(5);
		talon6 = new CANTalon(6);
		talon7 = new CANTalon(7);
		talon8 = new CANTalon(8);
		motorFR.changeControlMode(TalonControlMode.PercentVbus);
		motorBR.changeControlMode(TalonControlMode.PercentVbus);
		motorBL.changeControlMode(TalonControlMode.PercentVbus);
		motorFL.changeControlMode(TalonControlMode.PercentVbus);
	}
	public void initDefaultCommand() {        //setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new OpDrive());
	}
	/**
	 * Assigns speed values for the left and right motors of tank drive. Also puts the speed of those motors on smart dashboard 
	 * @param rightSpeed - The speed of the right motors of the robot.
	 * @param leftSpeed - The speed of the left motors of the robot.
	 */
	public void tankDrive(double rightSpeed, double leftSpeed) {
		motorFR.set(rightSpeed);
		motorBR.set(rightSpeed);
		motorFL.set(-leftSpeed);
		motorBL.set(-leftSpeed);
		//    	
		SmartDashboard.putNumber("Right Motor Speed", motorFR.getEncVelocity()/4096);
		SmartDashboard.putNumber("Left Motor Speed", motorFL.getEncVelocity()/4096);
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		pidSourceType = pidSource;

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceType;
	}

	@Override
	public double pidGet() {
		return pidSourceType.equals(PIDSourceType.kDisplacement) ? getDistance() : getSpeed();
	}

	public double getDistance() {
		return motorBL.getEncPosition();
	}

	public double getSpeed() {
		if(motorBL.getEncVelocity() < 0) {
			return (32768.0 + (32768.0 + motorBL.getEncVelocity()))/4096.0;
		}
		else if(motorBL.get()>.7)	{
			return (32768 * 2 + motorBL.getEncVelocity())/4096.0;
		}
		return motorBL.getEncVelocity()/4096.0;

	}
}


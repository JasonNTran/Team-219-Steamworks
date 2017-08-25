package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the subsystem for the shooter of Team 219's 2017 robot. It uses PID to make sure the motor is cunning at the correct speed..
 */
public class Shooter extends Subsystem implements PIDSource 
{	
	// Initialize your subsystem here
	public CANTalon shooterMotor;
	private PIDSourceType pidSource = PIDSourceType.kRate;
	//# userLibs=${user.home}/wpilib/user/java/lib

	public Shooter() 
	{
		setShooterMotor(new CANTalon(RobotMap.SHOOTERMOTOR_PORT)); 

	}

	public void initDefaultCommand() {
	}

	/**
	 * Returns the rotations per deci-second, assuming the encoder is reading the negative direction as the proper one
	 */
	public double getRotationRate() 
	{
		/*
		 * Takes into account the 15 bit limit of the encoder, which causes its count to start from the reverse when the value reaches 2^15.
		 * As such, it goes -32766...-32767...-32768...32768...32767...
		 * At high speeds 16 MPdS produces values similar to 1 RPdS. hence the second if-statement that differs between the two using voltage lvs
		 */
//		if(getShooterMotor().getEncVelocity() > 0) 
//		{
//			return (-32768.0 + (-32768.0 + (getShooterMotor().getEncVelocity())))/4096.0;
//		}
//		else if(getShooterMotor().get() < -.9)	
//		{
//			return (-32768 * 2 - (getShooterMotor().getEncVelocity()))/4096.0;
//		}
		return (getShooterMotor().getEncVelocity())/4096.0;
	}
	
	/**
	 * Stops the shooter motor by setting the speed to 0
	 */
	public void stopShooter()
	{
		getShooterMotor().set(0);
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) 
	{
		this.pidSource = pidSource;
	}

	/**
	 * Returns the PID from the shooter.
	 */
	@Override
	public PIDSourceType getPIDSourceType() 
	{
		return pidSource;
	}

	/**
	 * Returns the value that will serve as the input for the PID.
	 */
	@Override
	public double pidGet() 
	{
		return getRotationRate();
	}
	
	/**
	 * Sets the speed of the shooter's motor. The speed is typically the output of the PID.
	 * @param speed The speed that is sent to the shooter motor.
	 */
	public void setMotorSpeed(double speed)
	{
		getShooterMotor().set(speed);
	}

	public CANTalon getShooterMotor() {
		return shooterMotor;
	}

	public void setShooterMotor(CANTalon shooterMotor) {
		this.shooterMotor = shooterMotor;
	}
}

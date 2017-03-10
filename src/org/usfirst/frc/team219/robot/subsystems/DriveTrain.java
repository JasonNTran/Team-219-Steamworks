package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;
import org.usfirst.frc.team219.robot.commands.TeleopDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Drive Train subsystem for Team 219's 2017 robot.
 */
public class DriveTrain extends Subsystem implements PIDSource{

	private CANTalon motorBL, motorFL, motorBR, motorFR;
	private PIDSourceType pidSourceType = PIDSourceType.kRate;
	private final double circumfrenceINCH = 6 * Math.PI;


	public DriveTrain() 
	{
		motorBL = new CANTalon(RobotMap.MOTORBL_PORT);
		motorFL = new CANTalon(RobotMap.MOTORFL_PORT);
		motorBR = new CANTalon(RobotMap.MOTORBR_PORT);
		motorFR = new CANTalon(RobotMap.MOTORFR_PORT);
	}
	/**
	 * Assigns speed values for the left and right motors of tank drive. Also puts the speed of those motors on smart dashboard 
	 * @param rightSpeed - The speed of the right motors of the robot.
	 * @param leftSpeed - The speed of the left motors of the robot.
	 */

	@Override
	public void setPIDSourceType(PIDSourceType pidSource)
	{
		pidSourceType = pidSource;

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceType;
	}

	@Override
	public double pidGet() {
		return  getDistance();
	}

	public double getDistance() 
	{
		return (Math.abs(motorFR.getEncPosition()/4096.0))*circumfrenceINCH;
	}


	public void resetEncoders()
	{
		motorFL.setPosition(0);
		motorFR.setPosition(0);
	}


	public void initDefaultCommand()
	{
		setDefaultCommand(new TeleopDrive());
	}

	/**
	 * Assigns speed values for the left and right motors of tank drive. Also puts the speed of those motors on smart dashboard 
	 * @param rightSpeed - The speed of the right motors of the robot.
	 * @param leftSpeed - The speed of the left motors of the robot.
	 */
	public void tankDrive(double rightSpeed, double leftSpeed) 
	{
		motorFR.set(rightSpeed);
		motorBR.set(rightSpeed);
		motorFL.set(-leftSpeed);
		motorBL.set(-leftSpeed);
		
		//Put encoder info on screen
//		SmartDashboard.putNumber("Right Motor Velocity", motorFR.getEncVelocity()/4096.0);
//		SmartDashboard.putNumber("Left Motor Velocity", motorFL.getEncVelocity()/4096.0);
//		SmartDashboard.putNumber("Right Motor Volts", motorFR.get());
//		SmartDashboard.putNumber("Left Motor Volts", motorFL.get());
	}
	public void setInvertedStatis(boolean statis)
	{
		motorFR.setInverted(statis);
		motorBR.setInverted(statis);
		motorFL.setInverted(statis);
		motorBL.setInverted(statis);
	}
}



package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;
import org.usfirst.frc.team219.robot.commands.ToggleCollector;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The harvester subsystem of Team 219's 2017 robot.
 */
public class Harvester extends Subsystem {

	public CANTalon collectorMotor;

	public Harvester()
	{
		collectorMotor = new CANTalon(RobotMap.COLLECTORMOTOR_PORT);
	}
	public void initDefaultCommand() {
	}

	/**
	 * Turns on the collector motor at a speed of 1.
	 * @param collectSpeed The speed that is sent the collector motor
	 */
	public void startRoller(double collectSpeed) 
	{
		collectorMotor.set(-collectSpeed);		
	}
	
	public void startRollerReverse(double collectSpeed) 
	{
		collectorMotor.set(collectSpeed);	
	}
	
	/**
	 * Turns off the collector motor by setting the speed to 0.
	 */
	public void stopRoller()
	{
		collectorMotor.set(0);
	}

}


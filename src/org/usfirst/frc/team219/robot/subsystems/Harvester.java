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

	private CANTalon collectorMotor;
	private CANTalon collectorMotor2;
	private CANTalon mixer;

	public Harvester()
	{
		collectorMotor = new CANTalon(RobotMap.COLLECTORMOTOR_PORT);
		collectorMotor2 = new CANTalon(RobotMap.COLLECTORMOTOR_PORT2);
		mixer=new CANTalon(RobotMap.Mixer);
	}
	public void initDefaultCommand() {
	}

	/**
	 * Turns on the collector motor at a speed of 1.
	 * @param collectSpeed The speed that is sent the collector motor
	 */
	public void startRoller(double collectSpeed) 
	{
		collectorMotor.set(collectSpeed);
		collectorMotor2.set(collectSpeed);

		SmartDashboard.putString("Roller", "On");			
	}
	
	public void startRollerReverse(double collectSpeed) 
	{
		collectorMotor.set(-collectSpeed);
		collectorMotor2.set(-collectSpeed);

		SmartDashboard.putString("Roller", "On");			
	}
	//BAD!!!!!!!!!!!!!!!
	public void mixerGo()
	{
		mixer.set(1);
	}
	public void mixerStop()
	{
		mixer.set(0);
	}
	public void mixerReverse()
	{
		mixer.set(-1);
	}
	
	/**
	 * Turns off the collector motor by setting the speed to 0.
	 */
	public void stopRoller() {
		collectorMotor.set(0);
		collectorMotor2.set(0);

		SmartDashboard.putString("Roller", "Off");
	}

}


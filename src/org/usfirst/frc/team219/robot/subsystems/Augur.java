package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Augur extends Subsystem 
{

	
	private CANTalon conveyorTalon;
	
	public Augur()
	{
		conveyorTalon = new CANTalon(RobotMap.AUGURMOTOR_PORT);
		
	}

	/**
	 * Sets the augur to move the balls in the direction of the shooter
	 */
	public void forward()
	{
		conveyorTalon.set(.27);
	}
	
	
	/**
	 * Sets the augur to move in reverse direction. Meant to deal with blockages. 
	 */
	public void reverse()
	{
		conveyorTalon.set(-.35);
	}
	
	/**
	 * Stops the augur by setting the motor speed to 0.
	 */
	public void stop()
	{
		conveyorTalon.set(0);
	}
	public void initDefaultCommand() 
	{
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}


package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Augur extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon conveyorTalon;
	
	public Augur()
	{
		conveyorTalon = new CANTalon(RobotMap.CONVEYORMOTOR_PORT);
	}
	public void forwardGo()
	{
		conveyorTalon.set(.1);
	}
	public void reverseGo()
	{
		conveyorTalon.set(-.1);
	}
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


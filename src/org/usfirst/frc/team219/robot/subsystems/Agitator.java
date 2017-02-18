package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Agitator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public CANTalon mixer;
	
	public Agitator()
	{
		mixer = new CANTalon(RobotMap.Mixer);
		mixer.setEncPosition(0);
		
	}
	
	public void mixerGo()
	{
		mixer.set(.6);
	}
	
	public void chooseDirection()
	{
		if(mixer.getEncPosition()/4096.0 > .01)
		{
			
			mixerReverse();
		}
		else if(mixer.getEncPosition()/4096.0 < -.01)
		{
		
			mixerGo();
		}
	}
	
	public void mixerStop()
	{
		mixer.set(0);
	}
	
	public void mixerReverse()
	{
		mixer.set(-.6);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}


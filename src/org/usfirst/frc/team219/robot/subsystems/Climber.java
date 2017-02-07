package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;
import org.usfirst.frc.team219.robot.commands.ClimbUp;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the climber subsystem for Team 219's 2017 robot.
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon climberMotor;
	
	public Climber() {
		climberMotor = new CANTalon(RobotMap.CLIMBERMOTOR_PORT);
		//RobotMap.CLIMBERMOTOR_PORT
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new ClimbUp());
    }
    /**
     * Starts the climber motor and also puts info onto smart dashboard.
     */
    public void startClimbing()
    {
    	climberMotor.set(.5);
    	SmartDashboard.putString("Climber", "Climbing");
    }
    /**
     * Sets the climber motor to go in reverse and also puts info onto smart dashboard.
     */
    public void comeDown() 
    {
    	climberMotor.set(-0.5);
    	SmartDashboard.putString("Climber", "Descending");
    }
    /**
     * Stops the climber motor completely.
     */
	public void stopMotor() {
		climberMotor.set(0);
    	SmartDashboard.putString("Climber", "Stopped");
	}
}


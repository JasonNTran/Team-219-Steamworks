package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * This is the subsystem for the shooter of Team 219's 2017 robot. It uses PID to make sure the motor is cunning at the correct speed..
 */
public class Shooter extends PIDSubsystem {

    // Initialize your subsystem here
	private CANTalon shooterMotor;
    public Shooter() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("shooter", 1,0,0);
    	getPIDController().setContinuous();
    	setSetpoint(5);
    	enable();
    	shooterMotor = new CANTalon(RobotMap.SHOOTERMOTOR_PORT);  	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return shooterMotor.getEncPosition()/4096;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	shooterMotor.set(output);
    }
    
    public void stopShooter()
    {
    	shooterMotor.set(0);
    }
}

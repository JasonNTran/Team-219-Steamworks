package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * The subsystem for the conveyor belt of Team 219's 2017 robot.
 * <br>
 * THIS SUBSYSTEM IS CURRENTLY INCOMPLETE
 */
public class BallConveyor extends PIDSubsystem {

    // Initialize your subsystem here
	private CANTalon conveyorMotor;

    public BallConveyor() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("Conveyer", 1, 0, 0);
    	conveyorMotor = new CANTalon(RobotMap.CONVEYORMOTOR_PORT);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.0;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}

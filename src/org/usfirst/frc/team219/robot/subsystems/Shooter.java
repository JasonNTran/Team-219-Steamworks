package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the subsystem for the shooter of Team 219's 2017 robot. It uses PID to make sure the motor is cunning at the correct speed..
 */
public class Shooter extends PIDSubsystem {

    // Initialize your subsystem here
	private CANTalon shooterMotor;
	private CANTalon tempMotor;
	final double circumfrence =.1524*Math.PI;
    public Shooter() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super( .2,0,0);
    	
    	enable();
    	getPIDController().setContinuous();
    	
    	setSetpoint(.5);
    	setInputRange(-.5,.5);
    	setPercentTolerance(5);
    	setOutputRange(-.5,.8);
    
    	
    	shooterMotor = new CANTalon(RobotMap.SHOOTERMOTOR_PORT);  	
    	tempMotor = new CANTalon(RobotMap.MOTORFR_PORT);  	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return -shooterMotor.getEncVelocity()/4096.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	
//    		if(onTarget() == true)
//    		{
//    			output = 0;
//    		
//    		}
    	
    	shooterMotor.set(shooterMotor.get()+output);
    	tempMotor.set(tempMotor.get()+output);
    	//shooterMotor.get()+
    	SmartDashboard.putNumber("ENC Velocity", shooterMotor.getEncVelocity());
    	SmartDashboard.putNumber("Voltage Perentage", shooterMotor.get());
    	SmartDashboard.putBoolean("OnTarget?", onTarget());
    	SmartDashboard.putNumber("SetPoint", getSetpoint());
    	SmartDashboard.putNumber("Progress to Setpoint", getPosition());
    	SmartDashboard.putNumber("PID Input", returnPIDInput());
    	SmartDashboard.putNumber("PID Output", output);
        SmartDashboard.putNumber("Meters Gone",(shooterMotor.getEncPosition()/4096.0)*circumfrence);
    	//output
    	//shooterMotor.get()+
    }
    
    public void stopShooter()
    {
    	//shooterMotor.set(0);
    }
}

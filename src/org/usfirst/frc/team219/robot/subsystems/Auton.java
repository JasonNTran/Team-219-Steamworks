package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.Robot;
import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Auton extends PIDSubsystem {

    // Initialize your subsystem here

	
	final double circumfrence =.1524*Math.PI;
	private AHRS imu= new AHRS(SerialPort.Port.kMXP);
    public Auton() 
    {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super( .3,.05,1);
    	
    	enable();
    	getPIDController().setContinuous();
    	SmartDashboard.putNumber("Initial Yaw", imu.getYaw());
//    	SmartDashboard.putNumber("Angle", imu.getAngle());
    	setSetpoint(imu.getYaw());
//    	setInputRange(-.5,.5);
//    	setPercentTolerance(5);
//    	setOutputRange(-.5,.8);
    
    
    }
    public void DriveStraight()
    {
//    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return imu.getYaw();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	//int directionFactor = driveByTime || (inchesToDrive>=0) ? 1 : -1;
  //  Robot.drivetrain.tankDrive( .3 + output,  .3 - output);
     //Robot.drivetrain.tankDrive( .3 ,  .3 );
     SmartDashboard.putNumber("PID Stuffy Input", returnPIDInput());
     //SmartDashboard.putNumber("PID Stuffy Output", );
     SmartDashboard.putNumber("PID Stuffy Output", output);
    }
	public float getImuYaw() {
		return imu.getYaw();
	}
	
}

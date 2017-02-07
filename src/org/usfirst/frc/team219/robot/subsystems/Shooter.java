package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the subsystem for the shooter of Team 219's 2017 robot. It uses PID to make sure the motor is cunning at the correct speed..
 */
public class Shooter extends Subsystem implements PIDSource {

	// Initialize your subsystem here
	public CANTalon shooterMotor;
	private PIDSourceType pidSource = PIDSourceType.kRate;
	
	public Shooter() {
		// Use these to get going:
		// setSetpoint() -  Sets where the PID controller should move the system
		//                  to
		// enable() - Enables the PID controller.
//		super( 0.01,0,0.025);
//		
//		setSetpoint(-10);
//		setInputRange(-20.0,20.0);
//		setPercentTolerance(.1);
//		setOutputRange(-1,1);
//
//		getPIDController().setContinuous();
//		enable();
		shooterMotor = new CANTalon(6); 
		//shooterMotor.set(-.6);
		
		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public double getRPM() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;

//		if(shooterMotor.getEncVelocity()<0) {
//			return (32768.0+(32768.0+(shooterMotor.getEncVelocity())))/4096.0;
//		}
//		else if(shooterMotor.get()>.7)	{
//			return (32768*2+(shooterMotor.getEncVelocity()))/4096.0;
//		}
//		return (shooterMotor.getEncVelocity())/4096.0;
	
		if(shooterMotor.getEncVelocity()>0) {
			return (-32768.0+(-32768.0+(shooterMotor.getEncVelocity())))/4096.0;
		}
		else if(shooterMotor.get()<-.7)	{
			return (-32768*2+(shooterMotor.getEncVelocity()))/4096.0;
		}
		return (shooterMotor.getEncVelocity())/4096.0;
	}
//
//	}
//	public void usePIDOutput(double output) {
//		// Use output to drive your system, like a motor
//		// e.g. yourMotor.set(output);
//
//		//    		if(onTarget() == true)
//		//    		{
//		//    			output = 0;
//		//    		}
//		//shooterMotor.get()+output
//		shooterMotor.set(shooterMotor.get()+output);
//		SmartDashboard.putString("Is this thing on??","??????????");
//		SmartDashboard.putNumber("Test Num",output);
//		SmartDashboard.putNumber("ENC Velocity", shooterMotor.getEncVelocity());
////		SmartDashboard.putNumber("ENC Position", shooterMotor.getEncPosition()/4096.0);
////		SmartDashboard.putNumber("Voltage Perentage", shooterMotor.get());
////		SmartDashboard.putBoolean("OnTarget?", onTarget());
////		SmartDashboard.putNumber("SetPoint", getSetpoint());
////		SmartDashboard.putNumber("Progress to Setpoint", getPosition());
//		SmartDashboard.putNumber("PID Input", returnPIDInput());
//		SmartDashboard.putNumber("PID Graph", returnPIDInput());
//		SmartDashboard.putNumber("PID Output", output);
//		
//	}

	public void stopShooter()
	{
	//
		shooterMotor.set(0);
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSource = pidSource;
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return pidSource;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getRPM();
	}
	
	public void setMotorSpeed(double speed)
	{
		shooterMotor.set(speed);
	}
}

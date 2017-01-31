package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the subsystem for the shooter of Team 219's 2017 robot. It uses PID to make sure the motor is cunning at the correct speed..
 */
public class Shooter extends PIDSubsystem {

	// Initialize your subsystem here
	public CANTalon shooterMotor;
	double low;
	double high;
	boolean reachedTarget;
	//private CANTalon tempMotor;
	//final double circumfrence =.1524*Math.PI;
	public Shooter() {
		// Use these to get going:
		// setSetpoint() -  Sets where the PID controller should move the system
		//                  to
		// enable() - Enables the PID controller.
		super( 0.01,0,0.025);

		enable();
		getPIDController().setContinuous();
		high= 0.0;

		reachedTarget=false;
		setSetpoint(10);
		low=getSetpoint();
		setInputRange(-20.0,20.0);
		setPercentTolerance(.1);
		setOutputRange(-1,1);

		//RobotMap.SHOOTERMOTOR_PORT
		shooterMotor = new CANTalon(RobotMap.SHOOTERMOTOR_PORT);  	
//		LiveWindow.addActuator("Shooter", "PIDSubstem Shooter", getPIDController());
		SmartDashboard.putData("PID Control", getPIDController());
		//tempMotor = new CANTalon(11);  	
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public double returnPIDInput() {
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
//	public LiveWindowSendable input()
//	{
//		return returnPIDInput();
//		
//	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);

		//    		if(onTarget() == true)
		//    		{
		//    			output = 0;
		//    		
		//    		}
		//shooterMotor.get()+output

		//over 70% and + 2^17
//		if(returnPIDInput()>14)
//		{
//			reachedTarget=true;
//		}
		shooterMotor.set(shooterMotor.get()+output);
		//tempMotor.set(tempMotor.get()+output);
		//shooterMotor.get()+

		//		if(returnPIDInput()<low&&reachedTarget)
		//		{
		//			low=returnPIDInput();
		//		}
		//		if(returnPIDInput()>high&&returnPIDInput())
		//		{
		//			high=returnPIDInput();
		//		}
		//NetworkTable.initialize();
		SmartDashboard.putNumber("ENC Velocity", shooterMotor.getEncVelocity());
		SmartDashboard.putNumber("ENC Position", shooterMotor.getEncPosition()/4096.0);
		SmartDashboard.putNumber("Voltage Perentage", shooterMotor.get());
		SmartDashboard.putBoolean("OnTarget?", onTarget());
		SmartDashboard.putNumber("SetPoint", getSetpoint());
		SmartDashboard.putNumber("Progress to Setpoint", getPosition());
		SmartDashboard.putNumber("PID Input", returnPIDInput());
		SmartDashboard.putNumber("PID INPUT2", returnPIDInput());
		SmartDashboard.putNumber("PID Output", output);
		SmartDashboard.putNumber("PID Low", low);
		SmartDashboard.putNumber("PID High", high);
		//LiveWindow.
		// SmartDashboard.putNumber("Meters Gone",(shooterMotor.getEncPosition()/4096.0)*circumfrence);
		//output
		//shooterMotor.get()+
	}

	public void stopShooter()
	{
		//shooterMotor.set(0);
	}
}

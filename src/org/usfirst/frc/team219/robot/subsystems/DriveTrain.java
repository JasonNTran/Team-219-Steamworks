package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;
import org.usfirst.frc.team219.robot.commands.TeleOpDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Drive Train subsystem for Team 219's 2017 robot.
 */
public class DriveTrain extends Subsystem 
{

	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon motorBL;
	public CANTalon motorFL;
	public CANTalon motorBR;
	public CANTalon motorFR;
	private boolean autonStatis = false;
	public DriveTrain() 
	{
		motorBL = new CANTalon(RobotMap.MOTORBL_PORT);
		motorFL = new CANTalon(RobotMap.MOTORFL_PORT);
		motorBR = new CANTalon(RobotMap.MOTORBR_PORT);
		motorFR = new CANTalon(RobotMap.MOTORFR_PORT);
		motorFR.changeControlMode(TalonControlMode.PercentVbus);
		motorBR.changeControlMode(TalonControlMode.PercentVbus);
		motorBL.changeControlMode(TalonControlMode.PercentVbus);
		motorFL.changeControlMode(TalonControlMode.PercentVbus);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new TeleOpDrive());
    }
    
    //Used for disabling TeleopDrive in Auton 
    public void setAutonStatis(boolean stat)
    {
    	autonStatis = stat;
    }
    
    public boolean getAutonStatis(){
    	return autonStatis;
    }
    /**
     * Assigns speed values for the left and right motors of tank drive. Also puts the speed of those motors on smart dashboard 
     * @param rightSpeed - The speed of the right motors of the robot.
     * @param leftSpeed - The speed of the left motors of the robot.
     */
    public void tankDrive(double rightSpeed, double leftSpeed) 
    {
    	motorFR.set(rightSpeed);
    	motorBR.set(rightSpeed);
    	motorFL.set(-leftSpeed);
    	motorBL.set(-leftSpeed);
    	//Put encoder info on screen
    	SmartDashboard.putNumber("Right Motor Speed", motorFR.getEncVelocity()/4096);
    	SmartDashboard.putNumber("Left Motor Speed", motorFL.getEncVelocity()/4096);
    }
}


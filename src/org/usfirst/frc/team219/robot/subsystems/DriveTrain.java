package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;
import org.usfirst.frc.team219.robot.commands.OpDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Drive Train subsystem for Team 219's 2017 robot.
 */
public class DriveTrain extends Subsystem {

    private static final boolean driveByTime = false;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon motorBL, motorFL, motorBR, motorFR;

	
	public DriveTrain() {
		motorBL = new CANTalon(RobotMap.MOTORBL_PORT);
		motorFL = new CANTalon(RobotMap.MOTORFL_PORT);
		motorBR = new CANTalon(RobotMap.MOTORBR_PORT);
		motorFR = new CANTalon(RobotMap.MOTORFR_PORT);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new OpDrive());
    }
    /**
     * Assigns speed values for the left and right motors of tank drive. Also puts the speed of those motors on smart dashboard 
     * @param rightSpeed - The speed of the right motors of the robot.
     * @param leftSpeed - The speed of the left motors of the robot.
     */
    public void tankDrive(double rightSpeed, double leftSpeed) {
    	motorFR.set(rightSpeed);
    	motorBR.set(rightSpeed);
    	motorFL.set(leftSpeed);
    	motorBL.set(leftSpeed);
    	
    	SmartDashboard.putNumber("Right Motor Speed", motorFR.getEncVelocity()/4096);
    	SmartDashboard.putNumber("Left Motor Speed", motorFL.getEncVelocity()/4096);

    }
}


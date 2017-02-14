
package org.usfirst.frc.team219.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team219.robot.commands.AutoAlign;
import org.usfirst.frc.team219.robot.commands.AutonDrive;
import org.usfirst.frc.team219.robot.commands.ToggleShooter;
import org.usfirst.frc.team219.robot.subsystems.*;

import com.kauailabs.navx.frc.AHRS;

//import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{
	public static OI oi;
	public static DriveTrain drivetrain;
	public static Harvester harvester;
	public static Climber climber;
	public static Shooter shooter;
	public static Augur Augur;
	public static AHRS imu;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	int num;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		drivetrain = new DriveTrain();
		harvester = new Harvester();
		climber = new Climber();
		shooter = new Shooter();
		harvester = new Harvester();
		Augur=new Augur();
		num=0;
		oi = new OI();
		SmartDashboard.putData("Auto mode", chooser);
		System.out.println("Reached");
		//SmartDashboard.putData("Auto mode", auton.getImuYaw());
		try 
		{
			/* Communicate w/navX MXP via the MXP SPI Bus.                                     */
			/* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
			/* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
			imu = new AHRS(SerialPort.Port.kMXP,AHRS.SerialDataType.kProcessedData, (byte)50);
			//            SmartDashboard.putString("Working?", "true");
		} 
		catch (RuntimeException ex ) 
		{
			DriverStation.reportError("`Error instantiating navX MXP:  " + ex.getMessage(), true);
			//            SmartDashboard.putString("Working", "False");
		}
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() 
	{

		autonomousCommand = chooser.getSelected();
		//  SmartDashboard.putNumber("Initial Angle from AutonInit", ahrs.getAngle());
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
		// SmartDashboard.putNumber("AutonPeriodic Angle", ahrs.getAngle());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		//shooter.shooterMotor.set(.2);
		//SmartDashboard.putData("PID Control", shooter.getPIDController());
		//shooter.disable();
		//drivetrain.setAutonStatis(false);
		// SmartDashboard.putNumber("TeleopInit Angle", ahrs.getAngle());
		//		else
		//		{
		//			SmartDashboard.putString("Disabled?", "No!");
		//		}
		//		
		//		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Counter",num++);
		//SmartDashboard.putNumber("sETPoint", AutoAlign.getDistance()));
		//SmartDashboard.putNumber("Testing getting values", SmartDashboard.getNumber("PegAngle", 0));
	}		/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
		LiveWindow.run();
	}
}

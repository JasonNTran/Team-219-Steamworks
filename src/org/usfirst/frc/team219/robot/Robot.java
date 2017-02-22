
package org.usfirst.frc.team219.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;



import org.usfirst.frc.team219.robot.commands.AutoAlign;
import org.usfirst.frc.team219.robot.commands.AutonDrive;
import org.usfirst.frc.team219.robot.commands.GearLeft;
import org.usfirst.frc.team219.robot.commands.GearMiddle;
import org.usfirst.frc.team219.robot.commands.GearRight;
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
	public static Agitator agitator;
	public static CameraServer camera;
	Command autonomousCommand;
	public SendableChooser autoChooser;
	
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
		Augur = new Augur();
		agitator = new Agitator();
		camera=CameraServer.getInstance();
		//camera.setQuality(50);
		camera.setSize(50);
		camera.startAutomaticCapture("cam0");
		oi = new OI();
		autoChooser = new SendableChooser<CommandGroup>();
		//autoChooser.addObject("MiddleGear Selector", new GearMiddle( SmartDashboard.getNumber("gearDistanceToMove", 0),true));
		autoChooser.addObject("LeftGear Selector", new GearLeft());
		autoChooser.addObject("RightGear Selector", new GearRight());
		System.out.println("Reached");
		try 
		{
			imu = new AHRS(SerialPort.Port.kMXP,AHRS.SerialDataType.kProcessedData, (byte)50);
			imu.reset();
		} 
		catch (RuntimeException ex ) 
		{
			DriverStation.reportError("`Error instantiating navX MXP:  " + ex.getMessage(), true);
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

		autonomousCommand = (Command) autoChooser.getSelected();
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
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		SmartDashboard.putBoolean("Bumpers?", true);
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Robot Yaw", Robot.imu.getYaw());
		SmartDashboard.putNumber("Robot ngleaw", Robot.imu.getAngle());
		SmartDashboard.putNumber("Actual2Inches", Robot.drivetrain.getDistance());
		SmartDashboard.putNumber("Mixer Enc Pos", Robot.agitator.mixer.getEncPosition()/4096.0);
	}		/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
		LiveWindow.run();
	}
}

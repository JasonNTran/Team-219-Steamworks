
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
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

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
	public static CameraServer cameraGearSide,HarvesterSide;
	Command autonomousCommand;
	public SendableChooser<Command> autoChooser;
	int currSession;
	int sessionfront;
	int sessionback;
	Image frame;
	
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
		
		cameraGearSide=CameraServer.getInstance();
//		cameraGearSide.setQuality(50);
//		cameraGearSide.setSize(50);
		cameraGearSide.startAutomaticCapture("cam1");
		
//		HarvesterSide=CameraServer.getInstance();
////		HarvesterSide.setQuality(50);
////		HarvesterSide.setSize(50);
//		HarvesterSide.startAutomaticCapture("GearCamera");
		
		oi = new OI();
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Nothing", null);
		autoChooser.addObject("MiddleGear Selector", new GearMiddle());
		autoChooser.addObject("LeftGear Selector", new GearLeft());
		autoChooser.addObject("RightGear Selector", new GearRight());
		SmartDashboard.putData("Auto Modes", autoChooser);
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
  
		autonomousCommand = autoChooser.getSelected();
//		autonomousCommand= new GearMiddle();
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
		SmartDashboard.putNumber("Robot Yaw", Robot.imu.getYaw());
		SmartDashboard.putNumber("Robot Angle", Robot.imu.getAngle());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

//		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//
//		sessionfront = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//		        
//		sessionback = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//
//		currSession = sessionfront;
//
//		NIVision.IMAQdxConfigureGrab(currSession);
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
//		if(oi.buttonLB.get()){
//	        if(currSession == sessionfront){
//	       		  NIVision.IMAQdxStopAcquisition(currSession);
//	 		  currSession = sessionback;
//		          NIVision.IMAQdxConfigureGrab(currSession);
//	 	} else if(currSession == sessionback){
//	      		  NIVision.IMAQdxStopAcquisition(currSession);
//	       		  currSession = sessionfront;
//	       		  NIVision.IMAQdxConfigureGrab(currSession);
//	        }
//	       
//	}
//		 NIVision.IMAQdxGrab(currSession, frame, 1);
	       // CameraServer.getInstance().setImage(frame);
	
	}		/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
		LiveWindow.run();
	}
}

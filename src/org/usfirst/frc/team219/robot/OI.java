
package org.usfirst.frc.team219.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team219.robot.commands.*;
import org.usfirst.frc.team219.robot.subsystems.Climber;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);  
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public Joystick mainDriver;
	private Joystick secondDriver;
	private Joystick BackupCo;
	public Joystick LogicTech;

	private Button buttonA;
	private Button buttonB;	
	private Button buttonX;
	private Button buttonA2;
	private Button buttonB2;	
	private Button buttonX2;
	private Button buttonY;
	private Button buttonY2;
	Button buttonLB;
	private Button buttonRB;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button buttonBack_2;
	private Button buttonForward_2;
	
	private Button B8;
	private Button B4;
	private Button B2;
	
	
	private JoystickButton first,second,third,fourth,fifth;

	public OI() 
	{
		mainDriver = new Joystick(0);
		buttonA = new JoystickButton(mainDriver, 1);
		buttonB = new JoystickButton(mainDriver, 2);
		buttonX = new JoystickButton(mainDriver, 3);
		buttonY = new JoystickButton(mainDriver, 4);
		buttonLB = new JoystickButton(mainDriver, 5);
		buttonRB = new JoystickButton(mainDriver, 6);
		
		secondDriver = new Joystick(1);
		first = new JoystickButton(secondDriver, 6); //A-6
		second = new JoystickButton(secondDriver, 2); //B
		third = new JoystickButton(secondDriver, 1); //X-1
		fourth=new JoystickButton(secondDriver,3);
		fifth = new JoystickButton(secondDriver, 4); //Y
		
		
		BackupCo = new Joystick(2);
		buttonA2 = new JoystickButton(BackupCo , 1);
		buttonB2 = new JoystickButton(BackupCo , 2);
		buttonX2 = new JoystickButton(BackupCo , 3);
		buttonY2= new JoystickButton(BackupCo,4);
		
		LogicTech = new Joystick(3);
		B8 = new JoystickButton(LogicTech, 8);
		B4= new JoystickButton(LogicTech, 4);
		B2= new JoystickButton(LogicTech, 2);
//		//Main Controller
//		buttonA.toggleWhenPressed(new ToggleCollector(0.85));
//		buttonY.toggleWhenPressed(new ClimbUp());
//		buttonX.toggleWhenPressed(new GearRight());
//		
//		//2nd Controller
//		buttonBack_2.whileHeld(new ClimberReverse());
//		button2.whileHeld(new ReverseAugurToggle());
//		button3.whileHeld(new Augurs_System());
//		button4.toggleWhenPressed(new ToggleShooter());
		buttonB.toggleWhenPressed(new CollectorReverse());
		buttonA.toggleWhenPressed(new ToggleCollector());
		
		buttonX.toggleWhenPressed(new ClimbUp());
		//buttonY.toggleWhenPressed(new ClimberReverse());
		
		first.toggleWhenPressed(new ToggleShooter(14.25)); // A
		second.whileHeld(new Augurs_System()); // B

		fourth.whileHeld(new Reverse_Agitator_Augur());
		fifth.toggleWhenPressed(new ToggleShooter(14.25));
		
		buttonA2.whileHeld(new ToggleShooter(14.25)); // A
		buttonX2.whileHeld(new Augurs_System()); // X 
		//yellow.whileHeld(new Augurs_System());
		buttonB2.whileHeld(new ReverseAugurToggle()); // B
		
		B8.whileHeld(new ToggleShooter(14.25));
		B4.whileHeld(new Augurs_System());
		B2.whileHeld(new ReverseAugurToggle());
		
		
	//	buttonY2.toggleWhenPressed(new ToggleShooter(10));
		
	}
	/**
	 * Getter for the x-axis of the left joystick
	 * @return The x-axis on the left joystick which is used for the speed of the left motors on tank drive
	 */
	public double getLeftSpeed() 
	{
		if(Math.abs(mainDriver.getRawAxis(1)) >= .2)
		{
//			if(mainDriver.getRawAxis(1)> 0)
//			{
//				return (mainDriver.getRawAxis(1)-.10)/.90;
//			}
//			else
//			{
//				return (mainDriver.getRawAxis(1)+.10)/.90;
//			}
			return (mainDriver.getRawAxis(1));
		}
		
	
		return 0.0;
	}
	/**
	 * Getter for the x-axis of the right joystick
	 * @return The x-axis on the right joystick which is used for the speed of the right motors on tank drive
	 */
	public double getRightSpeed() 
	{
		if(Math.abs(mainDriver.getRawAxis(5)) >= .2)
		{
//			if(mainDriver.getRawAxis(5)> 0)
//			{
//				return (mainDriver.getRawAxis(5)-.10)/.90;
//			}
//			else
//			{
//				return (mainDriver.getRawAxis(5)+.10)/.90;
//			}
			return (mainDriver.getRawAxis(5));
		}
		return -0.0;
	}
}


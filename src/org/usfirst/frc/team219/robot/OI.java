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

	private Button buttonA;
	private Button buttonB;	
	private Button buttonX;
	private Button buttonY;
	private Button buttonLB;
	private Button buttonRB;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button buttonBack_2;
	private Button buttonForward_2;

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
		button1= new JoystickButton(secondDriver,1);
		button2 = new JoystickButton(secondDriver, 2);
		button3 = new JoystickButton(secondDriver, 3);
		button4 = new JoystickButton(secondDriver, 4);
		buttonBack_2 = new JoystickButton(secondDriver,9);
		buttonForward_2 = new JoystickButton(secondDriver,10);
		
		//Main Controller
		buttonA.toggleWhenPressed(new ToggleCollector(0.85));
		buttonY.toggleWhenPressed(new ClimbUp());
		buttonX.toggleWhenPressed(new GearRight());
		buttonB.toggleWhenPressed(new CollectorReverse(0.85));
		
		//2nd Controller
		buttonBack_2.whileHeld(new ClimberReverse());
		button2.whileHeld(new ReverseAugurToggle());
		button3.whileHeld(new Augurs_System());
		button4.toggleWhenPressed(new ToggleShooter());
		
		
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


package org.usfirst.frc.team219.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team219.robot.commands.*;

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
	private Joystick mainDriver;
	private Joystick secondDriver;
	
	private Button buttonA;
	private Button buttonB;
	private Button buttonx;
	
	public OI() {
		mainDriver = new Joystick(0);
		secondDriver = new Joystick(2);
		buttonA = new JoystickButton(mainDriver, 1);
		buttonB = new JoystickButton(mainDriver, 2);
		buttonx = new JoystickButton(mainDriver, 3);
		
		buttonA.toggleWhenPressed(new ToggleCollector());
		buttonB.whileHeld(new ClimbUp());
		buttonx.whenPressed(new AutonDrive(.3, 672.0));
	}
	
	/**
	 * Getter for the x-axis of the left joystick
	 * @return The x-axis on the left joystick which is used for the speed of the left motors on tank drive
	 */
	public double getLeftSpeed() {
		return mainDriver.getRawAxis(1);
	}
	/**
	 * Getter for the x-axis of the right joystick
	 * @return The x-axis on the right joystick which is used for the speed of the right motors on tank drive
	 */
	public double getRightSpeed() {
		return mainDriver.getRawAxis(5);
	}
	}
//}

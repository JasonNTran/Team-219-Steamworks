package org.usfirst.frc.team219.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	//Ports for the Drive Train subsystem
	public static int MOTORBL_PORT = 1; //
	public static int MOTORFL_PORT = 2; //encoder
	public static int MOTORBR_PORT = 7; //7//3
	public static int MOTORFR_PORT = 6; //6//4 encoder

	//Ports for the Collector subsystem
	public static int COLLECTORMOTOR_PORT = 9;//9
	public static int COLLECTORMOTOR_PORT2=8;//8
	//Ports for the Climber subsystem
	public static int CLIMBERMOTOR_PORT = 4;

	//Ports for the Shooter subsystem
	public static int SHOOTERMOTOR_PORT = 3;//3

	//Ports for the Augur subsystem
	public static int AUGURMOTOR_PORT = 10;
	
	public static int Mixer=5;

}

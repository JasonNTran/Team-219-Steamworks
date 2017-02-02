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
	
	//drive train ports
	public static int MOTORBL_PORT = 3;
	public static int MOTORFL_PORT = 4;
	public static int MOTORBR_PORT = 1;
	public static int MOTORFR_PORT = 2;
	
	//collector ports
	public static int COLLECTORMOTOR_PORT = 0;
	
	//climber ports
	public static int CLIMBERMOTOR_PORT = 0;
	
	//shooter ports
	public static int SHOOTERMOTOR_PORT = 6;
	
	//conveyor ports
	public static int CONVEYORMOTOR_PORT = 5;
	public static int COLLECTORMOTOR_PORT2=6;
}

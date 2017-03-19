package org.usfirst.frc.team219.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Delay extends Command {

	double stopTime = 0;
	Timer time;
    public Delay(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	stopTime = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 time = new Timer();
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return time.get() > stopTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team293.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends IterativeRobot {
	private DifferentialDrive m_myRobot;
	/*
	 * change motor controllers if needed, the new motor controller type will come up underlined in red
	 * Just hover over the red underlined word and click import 
	 * sorry Christian this is mostly for the newer programmers/anyone else who wants to use this
	 * If there are two ycables one for each group just set the yCable boolean to true and connect the ycables to 0 and 1 pwm
	 * 
	 */
	private VictorSP motorgroup1Left = new VictorSP(0);  
	private VictorSP motorgroup1Right = new VictorSP(1); 
	private VictorSP motorgroup2Left = new VictorSP(2);
	private VictorSP motorgroup2Right = new VictorSP(3);
	private Joystick Joy1 = new Joystick(0);
	private Joystick Joy2 = new Joystick(1);
	boolean yCable = false;  //set to true if you are controlling each group off one cable, cables should be channels 0 and 1
	boolean JoystickThrottleControlWanted = false; //This might not work well but set this to true to control the motor speed with joystick throttles
	double speedgroup1 = 1.0;
	double speedgroup2 = 1.0;
	@Override
	public void robotInit() {
		if (yCable==true) {
		m_myRobot = new DifferentialDrive(motorgroup1Left, motorgroup1Right);
		}
	}

	
	@Override
	public void teleopPeriodic() {
		if (yCable ==true && JoystickThrottleControlWanted == true) {
		m_myRobot.tankDrive(Joy1.getThrottle(), Joy2.getThrottle());
		}
		else if (yCable == true) {
			m_myRobot.tankDrive(speedgroup1, speedgroup2);
		}
		else {
			 motorgroup1Left.set(speedgroup1);  
			 motorgroup1Right.set(speedgroup1);
			 motorgroup2Left.set(speedgroup2); 
			 motorgroup2Right.set(speedgroup2); 
		}
	}
}

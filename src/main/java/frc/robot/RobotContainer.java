/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.EjectHatchCommand;
import frc.robot.commands.HoldHatchCommand;
import frc.robot.commands.JoystickArmCommand;
import frc.robot.commands.JoystickDriveCommand;
import frc.robot.commands.JoystickElevatorCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchIntake;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  Joystick joystick = new Joystick(0);
  // The robot's subsystems and commands are defined here...
  Drivetrain drivetrain = new Drivetrain();
  Elevator elevator = new Elevator();
  HatchIntake hatchIntake = new HatchIntake();
  BallIntake ballIntake = new BallIntake();
  Arm arm = new Arm();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    drivetrain.setDefaultCommand(new JoystickDriveCommand(drivetrain, joystick::getX, joystick::getY));
    elevator.setDefaultCommand(new JoystickElevatorCommand(elevator, () -> joystick.getRawAxis(5)));
    hatchIntake.setDefaultCommand(new HoldHatchCommand(hatchIntake));
    ballIntake.setDefaultCommand(new RunCommand(ballIntake::stop, ballIntake));
    arm.setDefaultCommand(new JoystickArmCommand(arm, () -> joystick.getRawAxis(4)));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton intakeHatchButton = new JoystickButton(joystick, 0);
    JoystickButton ejectHatchButton = new JoystickButton(joystick, 1);
    JoystickButton intakeBallButton = new JoystickButton(joystick, 2);
    JoystickButton ejectBallButton = new JoystickButton(joystick, 3);

    intakeHatchButton.whileHeld(
      () -> {
        hatchIntake.openGrabber();
        hatchIntake.retractEjectors();
      },
      hatchIntake
    );
    ejectHatchButton.whileHeld(new EjectHatchCommand(hatchIntake).perpetually());
    
    intakeBallButton.whileHeld(
      () -> {
        ballIntake.intake();
        if (ballIntake.hasBall()) {
          joystick.setRumble(RumbleType.kRightRumble, 0.5);
        } else {
          joystick.setRumble(RumbleType.kRightRumble, 0);
        }
      }, 
      ballIntake
    );

    intakeBallButton.whenReleased(
      () -> {
        joystick.setRumble(RumbleType.kRightRumble, 0);
      }
    );

    ejectBallButton.whileHeld(ballIntake::outtake, ballIntake);
  }
}

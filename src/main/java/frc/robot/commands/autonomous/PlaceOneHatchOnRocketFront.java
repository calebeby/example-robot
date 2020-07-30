package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveForwardCommand;
import frc.robot.commands.DriveTurnCommand;
import frc.robot.commands.EjectHatchCommand;
import frc.robot.commands.ElevatorPositionCommand;
import frc.robot.commands.JoystickArmCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchIntake;

public class PlaceOneHatchOnRocketFront extends SequentialCommandGroup {
    PlaceOneHatchOnRocketFront(Drivetrain drivetrain, HatchIntake hatchIntake, Elevator elevator, Arm arm) {
        addCommands(
            parallel(
                sequence(
                    new DriveForwardCommand(drivetrain, 0.5).withTimeout(0.65),
                    new DriveTurnCommand(drivetrain, 0.7).withTimeout(1.2),
                    new DriveForwardCommand(drivetrain, 0.5).withTimeout(0.85)
                ),
                new ElevatorPositionCommand(elevator, 0.75),
                new JoystickArmCommand(arm, () -> 0.35).withTimeout(0.15)
            ),
            new EjectHatchCommand(hatchIntake),
            new DriveForwardCommand(drivetrain, -0.5).withTimeout(0.25)
        );
    }
}
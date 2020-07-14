package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.HatchIntake;

public class EjectHatchCommand extends SequentialCommandGroup {
    public EjectHatchCommand(HatchIntake hatchIntake) {
        addRequirements(hatchIntake);
        addCommands(
            new RunCommand(
                () -> {
                    hatchIntake.openGrabber();
                    hatchIntake.retractEjectors();
                },
                hatchIntake
            ).withTimeout(0.15),
            new RunCommand(
                () -> {
                    hatchIntake.openGrabber();
                    hatchIntake.eject();
                },
                hatchIntake
            ).withTimeout(0.15)
        );
    }
}
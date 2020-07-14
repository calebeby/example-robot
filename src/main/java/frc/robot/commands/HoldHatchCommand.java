package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HatchIntake;

public class HoldHatchCommand extends CommandBase {
    private HatchIntake hatchIntake;

    public HoldHatchCommand(HatchIntake hatchIntake) {
        addRequirements(hatchIntake);
        this.hatchIntake = hatchIntake;
    }

    @Override
    public void execute() {
        hatchIntake.retractEjectors();
        hatchIntake.closeGrabber();
    }
}
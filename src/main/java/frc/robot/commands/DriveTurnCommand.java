package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveTurnCommand extends CommandBase {
    private Drivetrain drivetrain;
    private double speed;

    public DriveTurnCommand(Drivetrain drivetrain, double speed) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        addRequirements(drivetrain);

    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(speed, 0);
    }
}
package frc.robot.commands;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveForwardCommand extends CommandBase {
    private Drivetrain drivetrain;
    private double speed;

    public DriveForwardCommand(Drivetrain drivetrain, double speed) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        addRequirements(drivetrain);

    }

    @Override
    public void execute() {
        drivetrain.drive(new Translation2d(speed, 0), 0, false);
    }
}
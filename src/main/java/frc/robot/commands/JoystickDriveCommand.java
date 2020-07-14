package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class JoystickDriveCommand extends CommandBase {
    private Drivetrain drivetrain;
    private DoubleSupplier rotationSpeed;
    private DoubleSupplier forwardSpeed;

    public JoystickDriveCommand(Drivetrain drivetrain, DoubleSupplier rotationSpeed, DoubleSupplier forwardSpeed) {
        this.rotationSpeed = rotationSpeed;
        this.forwardSpeed = forwardSpeed;
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(forwardSpeed.getAsDouble(), rotationSpeed.getAsDouble());
    }
}
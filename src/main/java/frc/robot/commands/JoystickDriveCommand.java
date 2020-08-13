package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class JoystickDriveCommand extends CommandBase {
    private Drivetrain drivetrain;
    private DoubleSupplier rotationAxis;
    private DoubleSupplier forwardAxis;
    private DoubleSupplier strafeAxis;

    public JoystickDriveCommand(Drivetrain drivetrain, DoubleSupplier forwardAxis, DoubleSupplier strafeAxis, DoubleSupplier rotationAxis) {
        this.forwardAxis = forwardAxis;
        this.strafeAxis = strafeAxis;
        this.rotationAxis = rotationAxis;
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.drive(new Translation2d(strafeAxis.getAsDouble(), forwardAxis.getAsDouble()), rotationAxis.getAsDouble(), true);
    }
}
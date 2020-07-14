package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class JoystickDriveCommand extends CommandBase {
    private Drivetrain drivetrain;
    private Joystick joystick;

    public JoystickDriveCommand(Drivetrain drivetrain, Joystick joystick) {
        addRequirements(drivetrain);
        this.joystick = joystick;
        this.drivetrain = drivetrain;
    }

    @Override
    public void execute() {
        double forwardSpeed = joystick.getY();
        double rotationSpeed = joystick.getX();
        drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
    }
}
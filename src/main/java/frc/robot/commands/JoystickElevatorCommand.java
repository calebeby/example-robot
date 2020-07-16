package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class JoystickElevatorCommand extends CommandBase {
    private Elevator elevator;
	private Joystick joystick;
    private DoubleSupplier speedSupplier;

    public JoystickElevatorCommand(Elevator elevator, DoubleSupplier speedSupplier) {
        this.speedSupplier = speedSupplier;
        addRequirements(elevator);
        this.elevator = elevator;
    }
    
    @Override
    public void execute() {
        elevator.setSpeed(speedSupplier.getAsDouble());
    }
}
package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class JoystickElevatorCommand extends CommandBase {
    private Elevator elevator;
	private Joystick joystick;

    public JoystickElevatorCommand(Elevator elevator, Joystick joystick) {
        this.elevator = elevator;
		this.joystick = joystick;
    }
    
    @Override
    public void execute() {
        elevator.setSpeed(joystick.getRawAxis(4));
    }
}
package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class ElevatorPositionCommand extends CommandBase {
    private Elevator elevator;
    private double targetPosition;
    private PIDController pidController = new PIDController(0.0001, 0, 0);

    public ElevatorPositionCommand(Elevator elevator, double targetPosition) {
        addRequirements(elevator);
        this.elevator = elevator;
        this.targetPosition = targetPosition;
    }

    @Override
    public boolean isFinished() {
        return Math.abs(elevator.getPosition() - targetPosition) < 0.5;
    }

    @Override
    public void execute() {
        double power = pidController.calculate(elevator.getPosition(), targetPosition);

        elevator.setSpeed(power);
    }
}
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class JoystickArmCommand extends CommandBase {
    private Arm arm;
    private DoubleSupplier speed;

    public JoystickArmCommand(Arm arm, DoubleSupplier speed) {
        this.speed = speed;
        this.arm = arm;
        
        addRequirements(arm);
    }
    
    @Override
    public void execute() {
        arm.setSpeed(speed.getAsDouble());
    }
}
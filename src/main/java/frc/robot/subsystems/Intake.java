package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private DoubleSolenoid piston = new DoubleSolenoid(0, 1);
    public Intake() {}

    public void extend() {
        piston.set(Value.kForward);
    }

    public void retract() {
        piston.set(Value.kReverse);
    }
}
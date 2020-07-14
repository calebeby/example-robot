package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HatchIntake extends SubsystemBase {
    private DoubleSolenoid ejectorPistons = new DoubleSolenoid(0, 1);
    private DoubleSolenoid grabberPistons = new DoubleSolenoid(2, 3);

    public void eject() {
        ejectorPistons.set(Value.kForward);
    }

    public void retractEjectors() {
        ejectorPistons.set(Value.kReverse);
    }

    public void openGrabber() {
        grabberPistons.set(Value.kForward);
    }

    public void closeGrabber() {
        grabberPistons.set(Value.kReverse);
    }
}
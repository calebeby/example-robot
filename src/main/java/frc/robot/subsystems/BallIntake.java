package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {
    private SpeedController intakeMotor = new VictorSP(5);

    public void intake() {
        intakeMotor.set(1);
    }

    public void outtake() {
        intakeMotor.set(-1);
    }

    public void stop() {
        intakeMotor.set(0);
    }
}
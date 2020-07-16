package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {
    private SpeedController intakeMotor = new VictorSP(5);
    private AnalogInput ballSensor = new AnalogInput(0);

    public void intake() {
        intakeMotor.set(1);
    }

    public void outtake() {
        intakeMotor.set(-1);
    }

    public void stop() {
        if (hasBall()) {
            intakeMotor.set(0.1);
        } else {
            intakeMotor.set(0);
        }
    }

    public boolean hasBall() {
        return ballSensor.getVoltage() < 2.0;
    }
}
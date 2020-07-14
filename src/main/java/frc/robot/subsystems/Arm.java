package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private SpeedController motor1 = new VictorSP(6);
    private SpeedController motor2 = new VictorSP(7);
    private double speed = 0;

    /**
     * Sets the speed (-1 to 1) of the arm.
     * Negative is down, positive is up
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void periodic() {
        motor1.set(speed);
        motor2.set(speed);

        speed = 0;
    }
}
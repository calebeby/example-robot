package frc.robot.subsystems;

import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    private SpeedController motor1 = new VictorSP(2);
    private SpeedController motor2 = new VictorSP(3);
    private double speed = 0;
    private CANCoder encoder = new CANCoder(1);
    private NetworkTableEntry ntEncoderPosition = NetworkTableInstance.getDefault().getEntry("/subsystems/elevator/encoder_position");
    private NetworkTableEntry ntEncoderVelocity = NetworkTableInstance.getDefault().getEntry("/subsystems/elevator/encoder_velocity");

    public void resetEncoder() {
        encoder.setPosition(0);
    }

    /**
     * Sets the speed (-1 to 1) of the elevator.
     * Negative is down, positive is up
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getPosition() {
        return encoder.getAbsolutePosition();
    }

    public double getVelocity() {
        return encoder.getVelocity();
    }

    @Override
    public void periodic() {
        motor1.set(speed);
        motor2.set(speed);

        ntEncoderPosition.setDouble(getPosition());
        ntEncoderVelocity.setDouble(getVelocity());

        speed = 0;
    }
}

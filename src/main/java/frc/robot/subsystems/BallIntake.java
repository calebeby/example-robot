package frc.robot.subsystems;

import org.frcteam2910.common.util.MovingAverage;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {
    private SpeedController intakeMotor = new VictorSP(5);
    private AnalogInput ballSensor = new AnalogInput(0);
    private NetworkTableEntry ntEncoderPosition = NetworkTableInstance.getDefault().getEntry("/subsystems/ball_intake/has_ball");
    private MovingAverage ballSensorAvg = new MovingAverage(10);

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
        return ballSensorAvg.get() < 2.0;
    }

    @Override
    public void periodic() {
        ballSensorAvg.add(ballSensor.getVoltage());
        ntEncoderPosition.setBoolean(hasBall());
    }
}
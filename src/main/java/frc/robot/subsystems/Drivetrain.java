package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import org.frcteam2910.common.drivers.Gyroscope;
import org.frcteam2910.common.drivers.SwerveModule;
import org.frcteam2910.common.math.Vector2;
import org.frcteam2910.common.robot.drivers.Mk2SwerveModuleBuilder;
import org.frcteam2910.common.robot.drivers.NavX;

public class Drivetrain extends SubsystemBase {
    private double TRACKWIDTH = 30;
    private double WHEELBASE = 34;
    
    private final SwerveModule frontLeftModule = new Mk2SwerveModuleBuilder(new Vector2(TRACKWIDTH / 2.0, WHEELBASE / 2.0))
        .angleEncoder(new AnalogInput(0), 0)
        .angleMotor(new TalonFX(0))
        .driveMotor(new TalonFX(1))
        .build();

    private final SwerveModule frontRightModule = new Mk2SwerveModuleBuilder(new Vector2(-TRACKWIDTH / 2.0, WHEELBASE / 2.0))
        .angleEncoder(new AnalogInput(0), 0)
        .angleMotor(new TalonFX(2))
        .driveMotor(new TalonFX(3))
        .build();

    private final SwerveModule backLeftModule = new Mk2SwerveModuleBuilder(new Vector2(TRACKWIDTH / 2.0, -WHEELBASE / 2.0))
        .angleEncoder(new AnalogInput(0), 0)
        .angleMotor(new TalonFX(4))
        .driveMotor(new TalonFX(5))
        .build();

    private final SwerveModule backRightModule = new Mk2SwerveModuleBuilder(new Vector2(-TRACKWIDTH / 2.0, -WHEELBASE / 2.0))
        .angleEncoder(new AnalogInput(0), 0)
        .angleMotor(new TalonFX(6))
        .driveMotor(new TalonFX(7))
        .build();
    
    
    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        new Translation2d(TRACKWIDTH / 2.0, WHEELBASE / 2.0),
        new Translation2d(TRACKWIDTH / 2.0, -WHEELBASE / 2.0),
        new Translation2d(-TRACKWIDTH / 2.0, WHEELBASE / 2.0),
        new Translation2d(-TRACKWIDTH / 2.0, -WHEELBASE / 2.0));
    
    private final Gyroscope gyroscope = new NavX(SPI.Port.kMXP);

    public void resetGyro() {
        gyroscope.setAdjustmentAngle(gyroscope.getUnadjustedAngle());
    }
    
    public Drivetrain() {}

    @Override
    public void periodic() {
        frontLeftModule.updateSensors();
        frontRightModule.updateSensors();
        backLeftModule.updateSensors();
        backRightModule.updateSensors();

        frontLeftModule.updateState(TimedRobot.kDefaultPeriod);
        frontRightModule.updateState(TimedRobot.kDefaultPeriod);
        backLeftModule.updateState(TimedRobot.kDefaultPeriod);
        backRightModule.updateState(TimedRobot.kDefaultPeriod);
    }

    public void drive(Translation2d translation, double rotation, boolean fieldOriented) {
        rotation *= 2.0 / Math.hypot(WHEELBASE, TRACKWIDTH);
        ChassisSpeeds speeds;
        if (fieldOriented) {
            speeds = ChassisSpeeds.fromFieldRelativeSpeeds(translation.getX(), translation.getY(), rotation,
                    Rotation2d.fromDegrees(gyroscope.getAngle().toDegrees()));
        } else {
            speeds = new ChassisSpeeds(translation.getX(), translation.getY(), rotation);
        }

        SwerveModuleState[] states = kinematics.toSwerveModuleStates(speeds);
        frontLeftModule.setTargetVelocity(states[0].speedMetersPerSecond, states[0].angle.getRadians());
        frontRightModule.setTargetVelocity(states[1].speedMetersPerSecond, states[1].angle.getRadians());
        backLeftModule.setTargetVelocity(states[2].speedMetersPerSecond, states[2].angle.getRadians());
        backRightModule.setTargetVelocity(states[3].speedMetersPerSecond, states[3].angle.getRadians());
    }

}
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private SpeedController leftMotor = new Spark(0);
    private SpeedController rightMotor = new Spark(1);
    private Joystick joystick;
  
    private DifferentialDrive drivetrain = new DifferentialDrive(leftMotor, rightMotor);
    
    public Drivetrain(Joystick joystick) {
        this.joystick = joystick;
    }

    public void arcadeDrive() {
        double forwardSpeed = joystick.getRawAxis(0);
        double rotationSpeed = joystick.getRawAxis(1);
        drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
    }

    @Override
    public void periodic() {

    }
}
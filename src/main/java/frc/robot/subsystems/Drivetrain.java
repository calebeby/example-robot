package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private SpeedController leftMotor = new Spark(0);
    private SpeedController rightMotor = new Spark(1);
  
    private DifferentialDrive drivetrain = new DifferentialDrive(leftMotor, rightMotor);
    
    public Drivetrain() {}

    public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
        drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
    }

    @Override
    public void periodic() {}
}
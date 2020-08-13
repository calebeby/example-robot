package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import org.frcteam2910.common.util.InterpolatingDouble;
import org.frcteam2910.common.util.InterpolatingTreeMap;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final TalonFX topMotor = new TalonFX(9);
    private final TalonFX bottomMotor = new TalonFX(10);

    private final InterpolatingTreeMap<InterpolatingDouble, InterpolatingDouble> topTreeMap =
        new InterpolatingTreeMap<InterpolatingDouble, InterpolatingDouble>(100);

    public Shooter() {
        topTreeMap.put(new InterpolatingDouble(2.0), new InterpolatingDouble(0.5));
        topTreeMap.put(new InterpolatingDouble(5.0), new InterpolatingDouble(0.6));
        topTreeMap.put(new InterpolatingDouble(10.0), new InterpolatingDouble(0.8));
        topTreeMap.put(new InterpolatingDouble(20.0), new InterpolatingDouble(0.9));    
        topTreeMap.put(new InterpolatingDouble(25.0), new InterpolatingDouble(1.0));
    }

    @Override
    public void periodic() {
        final double distance = 12.5;

        topTreeMap.getInterpolated(new InterpolatingDouble(distance));
    }
}
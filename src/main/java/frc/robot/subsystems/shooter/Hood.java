package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Hood {

    private final TalonFX motor;
    private final MotionMagicVoltage request = new MotionMagicVoltage(0);

    public Hood(int canID) {
        motor = new TalonFX(canID);

        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        config.Slot0 = new Slot0Configs();
        config.Slot0.kP = 10.0;
        config.Slot0.kI = 0.0;
        config.Slot0.kD = 0.0;

        config.MotionMagic.MotionMagicCruiseVelocity = 5.0;
        config.MotionMagic.MotionMagicAcceleration = 10.0;

        motor.getConfigurator().apply(config);
    }

    public void setAngle(double degrees) {
        double rotations = degrees / 360.0;
        motor.setControl(request.withPosition(rotations));
    }

    public double getAngle() {
        return motor.getPosition().getValueAsDouble() * 360.0;
    }

    public void stop() {
        motor.stopMotor();
    }
}
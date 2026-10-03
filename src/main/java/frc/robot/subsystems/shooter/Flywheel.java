package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Flywheel {

    private final TalonFX motor;
    private final VelocityVoltage velocityRequest = new VelocityVoltage(0);

    public Flywheel(int canID) {
        motor = new TalonFX(canID);

        var config = new com.ctre.phoenix6.configs.TalonFXConfiguration();
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        config.Slot0 = new Slot0Configs();
        config.Slot0.kP = 0.1;
        config.Slot0.kI = 0.0;
        config.Slot0.kD = 0.0;

        motor.getConfigurator().apply(config);
    }

    public void setVelocity(double rpm) {
        double rotationsPerSecond = rpm / 60.0;
        motor.setControl(velocityRequest.withVelocity(rotationsPerSecond));
    }

    public double getVelocity() {
        return motor.getVelocity().getValueAsDouble() * 60.0;
    }

    public void stop() {
        motor.stopMotor();
    }
}

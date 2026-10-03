package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Feeder {

    private final TalonFX motor;

    public Feeder(int canID) {
        motor = new TalonFX(canID);
        motor.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setVolts(double volts) {
        motor.setVoltage(volts);
    }

    public void stop() {
        motor.stopMotor();
    }
}
package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class LifterIOTalonFX implements LifterIO {
    private final TalonFX motor;

    public LifterIOTalonFX(int canId) {
        motor = new TalonFX(canId);
        
        TalonFXConfiguration config = new TalonFXConfiguration();
        
        // 1. 設定電流限制：防止馬達卡死時燒毀（供給電流 30A）
        CurrentLimitsConfigs currentLimits = new CurrentLimitsConfigs();
        currentLimits.SupplyCurrentLimit = 30.0;
        currentLimits.SupplyCurrentLimitEnable = true;
        config.CurrentLimits = currentLimits;
        
        // 2. 設定中立模式為煞車（Brake）：維持位置，防止手臂隨意滑落
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        motor.getConfigurator().apply(config);
    }

    @Override
    public void updateInputs(LifterIOInputs inputs) {
        inputs.lifterPositionRad = motor.getPosition().getValueAsDouble();
        inputs.lifterVelocityRadPerSec = motor.getVelocity().getValueAsDouble();
        inputs.lifterAppliedVolts = motor.getMotorVoltage().getValueAsDouble();
        inputs.lifterCurrentAmps = new double[] { motor.getSupplyCurrent().getValueAsDouble() };
    }

    @Override
    public void periodic() {}

    @Override
    public void setZeroPosition() {
        motor.setPosition(0.0);
    }

    @Override
    public void setControl(MotionMagicVoltage control) {
        motor.setControl(control);
    }

    @Override
    public double getPosition() {
        return motor.getPosition().getValueAsDouble();
    }
}

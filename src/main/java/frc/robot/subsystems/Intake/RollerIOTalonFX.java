package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class RollerIOTalonFX implements RollerIO {
    private final TalonFX motor;

    public RollerIOTalonFX(int canId) {
        motor = new TalonFX(canId);

        TalonFXConfiguration config = new TalonFXConfiguration();
        
        // 1. 設定電流限制：防止吸取物件或卡住時燒毀馬達（供給電流 30A）
        CurrentLimitsConfigs currentLimits = new CurrentLimitsConfigs();
        currentLimits.SupplyCurrentLimit = 30.0;
        currentLimits.SupplyCurrentLimitEnable = true;
        config.CurrentLimits = currentLimits;
        
        // 2. 設定中立模式為滑行（Coast）：停止時讓滾輪自然滑行，減少機構衝擊
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        motor.getConfigurator().apply(config);
    }

    @Override
    public void updateInputs(RollerIOInputs inputs) {
        inputs.rollerVelocityRadPerSec = motor.getVelocity().getValueAsDouble();
        inputs.rollerAppliedVolts = motor.getMotorVoltage().getValueAsDouble();
        inputs.rollerCurrentAmps = new double[] { motor.getSupplyCurrent().getValueAsDouble() };
    }

    @Override
    public void periodic() {}

    @Override
    public void setVolts(double volts) {
        motor.setControl(new com.ctre.phoenix6.controls.VoltageOut(volts));
    }
}

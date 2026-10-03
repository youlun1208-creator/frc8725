package frc.robot.subsystems.Conveyer;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ConveyerIOTalonFX implements ConveyerIO {
    private final TalonFX motor;

    // 建構子：傳入馬達的 CAN ID
    public ConveyerIOTalonFX(int canId) {
        motor = new TalonFX(canId);

        TalonFXConfiguration config = new TalonFXConfiguration();
        
        // 1. 還原你原本的硬體設定：Brake 模式與順時針正轉
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        
        // 2. 安全防護：加上 30A 電流限制防卡死燒毀
        CurrentLimitsConfigs currentLimits = new CurrentLimitsConfigs();
        currentLimits.SupplyCurrentLimit = 30.0;
        currentLimits.SupplyCurrentLimitEnable = true;
        config.CurrentLimits = currentLimits;

        motor.getConfigurator().apply(config);
    }

    @Override
    public void updateInputs(ConveyerIOInputs inputs) {
        inputs.conveyerVelocityRadPerSec = motor.getVelocity().getValueAsDouble();
        inputs.conveyerAppliedVolts = motor.getMotorVoltage().getValueAsDouble();
        inputs.conveyerCurrentAmps = new double[] { motor.getSupplyCurrent().getValueAsDouble() };
    }

    @Override
    public void setVolts(double volts) {
        motor.setControl(new com.ctre.phoenix6.controls.VoltageOut(volts));
    }
}

package frc.robot.subsystems.Intake;

// 引入 CTRE Phoenix 6 的控制類型
import com.ctre.phoenix6.controls.MotionMagicVoltage;

public interface LifterIO {
    public static class LifterIOInputs {
        public double lifterPositionRad = 0.0;
        public double lifterVelocityRadPerSec = 0.0;
        public double lifterAppliedVolts = 0.0;
        public double[] lifterCurrentAmps = new double[] {};
    }

    public default void updateInputs(LifterIOInputs inputs) {}

    // 新增以下四個方法來配合 Intake.java 的呼叫
    public default void periodic() {}

    public default void setZeroPosition() {}

    public default void setControl(MotionMagicVoltage control) {}

    public default double getPosition() {
        return 0.0;
    }
}

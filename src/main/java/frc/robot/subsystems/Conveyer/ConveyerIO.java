package frc.robot.subsystems.Conveyer;

public interface ConveyerIO {
    public static class ConveyerIOInputs {
        public double conveyerVelocityRadPerSec = 0.0;
        public double conveyerAppliedVolts = 0.0;
        public double[] conveyerCurrentAmps = new double[] {};
    }

    public default void updateInputs(ConveyerIOInputs inputs) {}

    public default void setVolts(double volts) {}
}

package frc.robot.subsystems.Intake;

public interface RollerIO {
    public static class RollerIOInputs {
        public double rollerVelocityRadPerSec = 0.0;
        public double rollerAppliedVolts = 0.0;
        public double[] rollerCurrentAmps = new double[] {};
    }

    public default void updateInputs(RollerIOInputs inputs) {}


    public default void periodic() {}

    public default void setVolts(double volts) {}
}

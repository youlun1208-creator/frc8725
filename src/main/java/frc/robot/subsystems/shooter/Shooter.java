package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    public enum FlywheelState {
        Off(0.0),
        Rest(100.0),
        Auto(3150.0),
        Home(3500.0),
        SlowShoot(2000.0);

        public final double speed;

        FlywheelState(double speed) {
            this.speed = speed;
        }
    }

    public enum HoodState {
        Default(0.0),
        AutoAim(0.0),
        Home(25.0),
        Return(0.0);

        public final double angle;

        HoodState(double angle) {
            this.angle = angle;
        }
    }

    public enum FeederState {
        Off(0.0),
        Push(5.0),
        SlowPush(0.0);

        public final double volts;

        FeederState(double volts) {
            this.volts = volts;
        }
    }

    private final Flywheel flywheel;
    private final Hood hood;
    private final Feeder feeder;
    private final ShootCalculator shootCalculator;

    private FlywheelState flywheelState = FlywheelState.Off;
    private HoodState hoodState = HoodState.Default;
    private FeederState feederState = FeederState.Off;

    private double hubDistance;

    public Shooter() {
        flywheel = new Flywheel(
                ShooterConstants.FLYWHEEL_MOTOR_ID);

        hood = new Hood(
                ShooterConstants.HOOD_MOTOR_ID);

        feeder = new Feeder(
                ShooterConstants.FEEDER_MOTOR_ID);

        shootCalculator = new ShootCalculator();
    }

    public void setStates(
            FlywheelState flywheelState,
            HoodState hoodState,
            FeederState feederState) {

        this.flywheelState = flywheelState;
        this.hoodState = hoodState;
        this.feederState = feederState;
    }

    public void setHubDistance(double distance) {
        if (Double.isFinite(distance) && distance >= 0.0) {
            hubDistance = distance;
        }
    }

    public double getDesiredPosition() {
        if (hoodState == HoodState.AutoAim) {
            return shootCalculator.getHoodAngle(hubDistance);
        }

        return hoodState.angle;
    }

    public double getDesiredVelocity() {
        if (flywheelState == FlywheelState.Auto) {
            return shootCalculator.getFlywheelVelocity(hubDistance);
        }

        return flywheelState.speed;
    }

    public boolean flywheelAtSetpoint() {
        return Math.abs(
                flywheel.getVelocity() - getDesiredVelocity())
                < ShooterConstants.FLYWHEEL_TOLERANCE;
    }

    public boolean hoodAtSetpoint() {
        return Math.abs(
                hood.getAngle() - getDesiredPosition())
                < ShooterConstants.HOOD_TOLERANCE;
    }

    public boolean isReadyToShoot() {
        return flywheelAtSetpoint() && hoodAtSetpoint();
    }

    @Override
    public void periodic() {
        flywheel.setVelocity(getDesiredVelocity());
        hood.setAngle(getDesiredPosition());
        feeder.setVolts(feederState.volts);
    }

    public void stop() {
        flywheel.stop();
        hood.stop();
        feeder.stop();
    }
}

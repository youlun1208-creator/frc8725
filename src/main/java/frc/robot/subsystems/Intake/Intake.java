package frc.robot.subsystems.Intake;


import com.ctre.phoenix6.controls.MotionMagicVoltage;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private static Intake INTAKE;

    private static final double kAtSetpointTolerance = 0.01;
    private static final double kLifterTiltDegrees = 17.0;
    private static final double kMetersPerRotation = 1.0;

    private final LifterIO lifter;
    private final RollerIO roller;
    private final MotionMagicVoltage request = new MotionMagicVoltage(0.0);

    private boolean isZeroed = false;

    private LifterState lifterState = LifterState.Up;
    private RollerState rollerState = RollerState.Off;

    private double operateLifterAngle = LifterState.Up.angle;
    private double operateRollerVolts = 0.0;

    public enum LifterState {
        Up(0.05),
        Down(0.29),
        Zero(0.03),
        Slide(0.29),
        OperateControl(0.0);

        public final double angle;

        LifterState(double angle) {
            this.angle = angle;
        }
    }

    public enum RollerState {
        Off(0.0),
        Rest(1.0),
        SlowIn(5.0),
        In(7.0),
        OperateControl(0.0);

        public final double volts;

        RollerState(double volts) {
            this.volts = volts;
        }
    }

    public Intake(LifterIO lifterIO, RollerIO rollerIO) {
        this.lifter = lifterIO;
        this.roller = rollerIO;
        INTAKE = this;
        this.setZeroPosition();
    }

    public static Intake getInstance() {
        if (INTAKE == null) {
            throw new IllegalStateException(
                    "Intake has not been constructed yet");
        }
        return INTAKE;
    }

    public void setZeroPosition() {
        this.lifter.setZeroPosition();
        this.isZeroed = true;
    }

    public void setStates(LifterState lifterState, RollerState rollerState) {
        this.lifterState = lifterState;
        this.rollerState = rollerState;
    }

    public void setOperateLifterAngle(double angleRotations) {
        this.operateLifterAngle = angleRotations;
    }

    public void setOperateRollerVolts(double volts) {
        this.operateRollerVolts = volts;
    }

    @Override
    public void periodic() {
        if (!this.isZeroed) return;

        this.lifter.periodic();
        this.roller.periodic();

        this.lifter.setControl(
                this.request.withPosition(this.getTargetLifterAngle()));
        this.roller.setVolts(this.getTargetRollerVolts());
    }

    public double getTargetLifterAngle() {
        if (this.lifterState == LifterState.OperateControl) {
            return this.operateLifterAngle;
        }
        return this.lifterState.angle;
    }

    public double getTargetRollerVolts() {
        if (this.rollerState == RollerState.OperateControl) {
            return this.operateRollerVolts;
        }
        return this.rollerState.volts;
    }

    public RollerState getRollerState() {
        return this.rollerState;
    }

    public LifterState getLifterState() {
        return this.lifterState;
    }

    public boolean atSetpoint() {
        return Math.abs(this.lifter.getPosition() - this.getTargetLifterAngle())
                < kAtSetpointTolerance;
    }

    public Pose3d getSimulationPose() {
        double length = this.lifter.getPosition() * kMetersPerRotation;
        double tilt = Units.degreesToRadians(kLifterTiltDegrees);

        return new Pose3d(
                length * Math.cos(tilt),
                0.0,
                -length * Math.sin(tilt),
                Rotation3d.kZero);
                
    }
}
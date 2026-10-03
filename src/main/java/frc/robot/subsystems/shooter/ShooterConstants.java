package frc.robot.subsystems.shooter;

public final class ShooterConstants {

    // 修正：更改為不與 Intake (10, 11) 衝突的 CAN ID
    public static final int FLYWHEEL_MOTOR_ID = 7;
    public static final int HOOD_MOTOR_ID = 8;
    public static final int FEEDER_MOTOR_ID = 6; 

    public static final double FLYWHEEL_TOLERANCE = 100.0;
    public static final double HOOD_TOLERANCE = 2.0;

    public static final double HOOD_MIN_ANGLE = 0.0;
    public static final double HOOD_MAX_ANGLE = 25.0;

    public static final double FEEDER_PUSH_VOLTS = 5.0;

    private ShooterConstants() {
    }
}

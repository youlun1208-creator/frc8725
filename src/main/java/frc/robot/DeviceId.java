package frc.robot;

public final class DeviceId {
    public static final class DriveMotor {
        public static final int FRONT_LEFT = 1;
        public static final int BACK_LEFT = 2;
        public static final int FRONT_RIGHT = 3;
        public static final int BACK_RIGHT = 4;
    }
    public static final class ConveyorMotor {
        public static final int CONVEYOR = 5;
    }

    public static final class IntakeMotor {
        public static final int ROLLER_MAIN = 6;
        public static final int ROLLER_FOLLOWER = 7;
        public static final int LIFTER = 8;
    }

    public static final class ShooterMotor {
        public static final int FEEDER = 9;
        public static final int HOOD = 10;
        public static final int FLYWHEEL_MAIN = 11;
        public static final int FLYWHEEL_FOLLOWER = 12;
    }

    private DeviceId() {}
}
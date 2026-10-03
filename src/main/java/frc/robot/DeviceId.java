package frc.robot;

public final class DeviceId {
    public static final class DriveMotor {
        public static final int FRONT_LEFT = 1;
        public static final int BACK_LEFT = 2;
        public static final int FRONT_RIGHT = 3;
        public static final int BACK_RIGHT = 4;
    }

    // 加上 static，並確保名稱正確
    public static final class Conveyer {
        public static final int CONVEYER = 5;
    }

    public static final class Feeder {
        public static final int FEEDER = 6; // 修正名稱
    }

    public static final class Flywheel {
        public static final int FLYWHEEL = 7; // 修正名稱
    }

    public static final class Hood {
        public static final int HOOD = 8; // 修正名稱
    }

    public static final class Shooter {
        public static final int SHOOTER = 9; // 修正名稱
    }

    public static final class Intake {
        public static final int LIFTER = 10;
        public static final int ROLLER = 11;
    }

    private DeviceId() {}
}

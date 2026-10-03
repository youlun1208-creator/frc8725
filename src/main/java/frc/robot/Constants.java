package frc.robot;

public final class Constants {

    public static final class OperatorConstants {
        public static final int DRIVER_CONTROLLER_PORT = 0;
    }

    public static final class Drive {
        public static final double MAX_SPEED = 0.5;
        public static final double MAX_TURN_SPEED = 0.7;
        public static final double DEADBAND = 0.05;

        public static final boolean FRONT_LEFT_INVERTED = false;
        public static final boolean BACK_LEFT_INVERTED = false;
        public static final boolean FRONT_RIGHT_INVERTED = true;
        public static final boolean BACK_RIGHT_INVERTED = true;
    }

    public static final class Conveyer {
        public static final double MAX_DRIVE_SPEED = 0.5;
    }

    public static final class Intake {
        // Lifter (單位: 馬達圈數)
        public static final double LIFTER_UP = 0.05;
        public static final double LIFTER_DOWN = 0.29;
        public static final double LIFTER_ZERO = 0.03;
        public static final double LIFTER_SLIDE = 0.29;
        public static final double SLIDE_AMPLITUDE = 0.05;
        public static final double SLIDE_FREQUENCY = 4.0;

        public static final double LIFTER_KP = 60.0;
        public static final double LIFTER_KD = 0.5;
        public static final double LIFTER_KS = 0.1;
        public static final double LIFTER_CRUISE_VELOCITY = 2.0;
        public static final double LIFTER_ACCELERATION = 8.0;
        public static final double LIFTER_CURRENT_LIMIT = 40.0;
        public static final boolean LIFTER_INVERTED = false;

        // Roller (單位: 伏特)
        public static final double ROLLER_OFF = 0.0;
        public static final double ROLLER_REST = 1.0;
        public static final double ROLLER_SLOW_IN = 5.0;
        public static final double ROLLER_IN = 7.0;
        public static final double ROLLER_CURRENT_LIMIT = 60.0;
        public static final boolean ROLLER_INVERTED = true;
    }

    private Constants() {
    }
}

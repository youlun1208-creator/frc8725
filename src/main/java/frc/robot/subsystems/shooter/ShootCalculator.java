package frc.robot.subsystems.shooter;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ShootCalculator {

    private final NavigableMap<Double, Double> hoodTable = new TreeMap<>();
    private final NavigableMap<Double, Double> flywheelTable = new TreeMap<>();

    public ShootCalculator() {
        hoodTable.put(1.0, 10.0);
        hoodTable.put(2.0, 15.0);
        hoodTable.put(3.0, 20.0);
        hoodTable.put(4.0, 25.0);

        flywheelTable.put(1.0, 2000.0);
        flywheelTable.put(2.0, 2500.0);
        flywheelTable.put(3.0, 3000.0);
        flywheelTable.put(4.0, 3500.0);
    }

    public double getHoodAngle(double distance) {
        return interpolate(hoodTable, distance);
    }

    public double getFlywheelVelocity(double distance) {
        return interpolate(flywheelTable, distance);
    }

    public double[] getHoodAngleAndFlywheelVelocity(double distance) {
        return new double[] {
            getHoodAngle(distance),
            getFlywheelVelocity(distance)
        };
    }

    private double interpolate(
            NavigableMap<Double, Double> table,
            double distance) {

        if (!Double.isFinite(distance)) {
            return 0.0;
        }

        Map.Entry<Double, Double> lower = table.floorEntry(distance);
        Map.Entry<Double, Double> upper = table.ceilingEntry(distance);

        if (lower == null) {
            return table.firstEntry().getValue();
        }

        if (upper == null) {
            return table.lastEntry().getValue();
        }

        if (lower.getKey().equals(upper.getKey())) {
            return lower.getValue();
        }

        double x1 = lower.getKey();
        double y1 = lower.getValue();
        double x2 = upper.getKey();
        double y2 = upper.getValue();

        return y1 + (distance - x1) * (y2 - y1) / (x2 - x1);
    }
}
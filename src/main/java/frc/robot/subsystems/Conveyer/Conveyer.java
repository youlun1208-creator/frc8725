package frc.robot.subsystems.Conveyer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyer extends SubsystemBase {
    private final ConveyerIO io;
    private final ConveyerIO.ConveyerIOInputs inputs = new ConveyerIO.ConveyerIOInputs();

    // 建構子：透過介面將硬體注入進來
    public Conveyer(ConveyerIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        // 定期更新馬達感測器數據
        io.updateInputs(inputs);
    }

    public void move(double volts) {
        io.setVolts(volts);
    }

    public void stop() {
        io.setVolts(0.0);
    }
}

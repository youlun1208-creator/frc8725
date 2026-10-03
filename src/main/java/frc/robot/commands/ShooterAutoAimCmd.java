package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.Shooter;

public class ShooterAutoAimCmd extends Command {
    private final Shooter shooter;

    // 建構子：傳入 Shooter 子系統
    public ShooterAutoAimCmd(Shooter shooter) {
        this.shooter = shooter;
        
        // 宣告使用 shooter 子系統，防止其他指令同時控制
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        // 指令開始時，將飛輪切換為自動模式 (Auto)，Hood 切換為自動瞄準 (AutoAim)
        // 這裡暫時維持 Feeder 關閉 (Off)，等瞄準好再手動或自動送球
        shooter.setStates(
            Shooter.FlywheelState.Auto, 
            Shooter.HoodState.AutoAim, 
            Shooter.FeederState.Off
        );
    }

    @Override
    public void execute() {

        shooter.setHubDistance(2.5);
    }

    @Override
    public void end(boolean interrupted) {

        shooter.stop();
    }

    @Override
    public boolean isFinished() {

        return false;
    }
}

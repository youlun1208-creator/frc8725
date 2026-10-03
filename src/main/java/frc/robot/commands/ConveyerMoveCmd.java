package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Conveyer.Conveyer;

public class ConveyerMoveCmd extends Command {
    private final Conveyer conveyer;
    private final double volts;

    // 建構子：傳入 Conveyer 子系統與目標電壓（正值送球，負值反轉退球）
    public ConveyerMoveCmd(Conveyer conveyer, double volts) {
        this.conveyer = conveyer;
        this.volts = volts;
        
        // 宣告使用 conveyer 子系統，防止多個指令同時控制
        addRequirements(conveyer);
    }

    @Override
    public void initialize() {
        // 指令開始時，讓輸送帶馬達以設定的電壓轉動
        conveyer.move(volts);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        // 指令結束（放開按鈕）時，關閉輸送帶馬達
        conveyer.stop();
    }

    @Override
    public boolean isFinished() {
        // 常駐型指令（按著才運轉），回傳 false 直到手把按鈕放開被中斷
        return false;
    }
}

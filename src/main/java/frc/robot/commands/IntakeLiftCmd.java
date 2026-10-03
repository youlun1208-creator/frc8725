package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Intake;

public class IntakeLiftCmd extends Command {
    private final Intake intake;
    private final Intake.LifterState targetState;

    // 建構子：傳入 Intake 子系統與想要切換到的目標手臂狀態（例如 LifterState.Down 或 LifterState.Up）
    public IntakeLiftCmd(Intake intake, Intake.LifterState targetState) {
        this.intake = intake;
        this.targetState = targetState;
        
        addRequirements(intake);
    }

    @Override
    public void initialize() {

        intake.setStates(targetState, intake.getRollerState());
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {

        return intake.atSetpoint();
    }
}

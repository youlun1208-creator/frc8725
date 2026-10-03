package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Intake;

public class IntakeRollCmd extends Command {
    private final Intake intake;
    private final double volts;

    public IntakeRollCmd(Intake intake, double volts) {
        this.intake = intake;
        this.volts = volts;
        addRequirements(intake);
    }

    @Override
    public void initialize() {

        intake.setStates(intake.getLifterState(), Intake.RollerState.OperateControl);

        intake.setOperateRollerVolts(volts);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {

        intake.setStates(intake.getLifterState(), Intake.RollerState.Off);
        intake.setOperateRollerVolts(0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

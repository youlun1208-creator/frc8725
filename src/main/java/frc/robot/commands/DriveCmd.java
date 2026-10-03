package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Drive;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCmd extends Command {
    private final DriveSubsystem driveSubsystem;
    private final XboxController controller;

    public DriveCmd(
        DriveSubsystem driveSubsystem,
        XboxController controller
    ) {
        this.driveSubsystem = driveSubsystem;
        this.controller = controller;
        this.addRequirements(this.driveSubsystem);
    }

    @Override
    public void execute() {
        double driveSpeed = -MathUtil.applyDeadband(
            this.controller.getLeftY(),
            Drive.DEADBAND
        ) * Drive.MAX_SPEED;

        double turnSpeed = MathUtil.applyDeadband(
            this.controller.getRightX(),
            Drive.DEADBAND
        ) * Drive.MAX_TURN_SPEED;

        double leftSpeed = MathUtil.clamp(
            driveSpeed + turnSpeed, -1.0, 1.0
        );
        double rightSpeed = MathUtil.clamp(
            driveSpeed - turnSpeed, -1.0, 1.0
        );

        this.driveSubsystem.drive(leftSpeed, rightSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ConveyerMoveCmd;
import frc.robot.commands.DriveCmd;
import frc.robot.commands.IntakeLiftCmd;
import frc.robot.commands.IntakeRollCmd;
import frc.robot.commands.ShooterAutoAimCmd; // 引入射球器自動瞄準指令
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Conveyer.Conveyer;
import frc.robot.subsystems.Conveyer.ConveyerIOTalonFX;
import frc.robot.subsystems.Intake.Intake;
import frc.robot.subsystems.Intake.LifterIOTalonFX;
import frc.robot.subsystems.Intake.RollerIOTalonFX;
import frc.robot.subsystems.shooter.Shooter;

public class RobotContainer {
    private final GamepadJoystick joystick =
        new GamepadJoystick(GamepadJoystick.CONTROLLER_PORT);

    private final DriveSubsystem driveSubsystem =
        new DriveSubsystem();

    private final Intake intake = new Intake(
        new LifterIOTalonFX(10),
        new RollerIOTalonFX(11)
    );

    private final Conveyer conveyer = new Conveyer(
        new ConveyerIOTalonFX(DeviceId.Conveyer.CONVEYER)
    );

    private final Shooter shooter = new Shooter();

    public RobotContainer() {
        this.driveSubsystem.setDefaultCommand(
            new DriveCmd(this.driveSubsystem, this.joystick)
        );

        // 1. 滾輪控制：按住 1 號按鈕（A）時，以 7V 吸球
        new JoystickButton(this.joystick, 1)
            .whileTrue(new IntakeRollCmd(this.intake, 7.0));

        // 2. 手臂控制：按下 2 號按鈕（B）時，將手臂放下（Down）
        new JoystickButton(this.joystick, 2)
            .onTrue(new IntakeLiftCmd(this.intake, Intake.LifterState.Down));

        // 3. 手臂控制：按下 3 號按鈕（X）時，將手臂收回（Up）
        new JoystickButton(this.joystick, 3)
            .onTrue(new IntakeLiftCmd(this.intake, Intake.LifterState.Up));

        // 4. 輸送帶控制：按住 4 號按鈕（Y）時，輸送帶轉動送球（7V），放開即停止
        new JoystickButton(this.joystick, 4)
            .whileTrue(new ConveyerMoveCmd(this.conveyer, 7.0));

        // 5. 射球器控制：按住 5 號按鈕（LB）時，啟動飛輪並自動瞄準，放開即停止
        new JoystickButton(this.joystick, 5)
            .whileTrue(new ShooterAutoAimCmd(this.shooter));

        // 6. 射球器預設動作：若沒有按鈕觸發，設定預設指令為完全停止
        this.shooter.setDefaultCommand(
            Commands.runOnce(this.shooter::stop, this.shooter)
        );
    }

    public Command getAutonomousCommand() {
        return null;
    }
}

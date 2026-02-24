package frc.robot.commands.swervedrive.auto.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BufferSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAutoTimer extends Command {
  private final BufferSubsystem buffer;
  private final ShooterSubsystem shooter;
  private final Timer timer;
  private double secondsEnabled;

  public ShootAutoTimer(BufferSubsystem intake, ShooterSubsystem shooter, double secondsEnabled) {
    this.buffer = intake;
    this.shooter = shooter;
    this.secondsEnabled = secondsEnabled;

    this.timer = new Timer();
    addRequirements(this.buffer, this.shooter);
  }

  @Override
  public void initialize() {
    this.timer.reset();
    this.timer.start();
  }

  @Override
  public void execute() {

    this.shooter.velocityOut(73);

    boolean isBufferEnable = (Math.abs(73 - this.shooter.getVelocity())) <= Math.abs(5);

    if (isBufferEnable) {
      this.buffer.percentOut(-0.6);
    }
  }

  @Override
  public boolean isFinished() {
    return this.timer.hasElapsed(this.secondsEnabled);
  }

  @Override
  public void end(boolean isInterrupted) {
    this.buffer.stop();
    this.shooter.stop();
    this.timer.stop();
  }
}
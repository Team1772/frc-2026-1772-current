package frc.robot.subsystems;




import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BufferSubsystem extends SubsystemBase {
private SparkMax motorVortex;
public Servo servoBuffer;

  public BufferSubsystem() {
    this.motorVortex = new SparkMax(12, MotorType.kBrushless);
    
     servoBuffer = new Servo(1);

    SparkMaxConfig config = new SparkMaxConfig();
    config
        .smartCurrentLimit(50)
        .idleMode(IdleMode.kCoast)
        .inverted(true);

    // Persist parameters to retain configuration in the event of a power cycle
    motorVortex.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    // this.motorVortex.follow(this.motorLeftFront);

    // this.drive = new DifferentialDrive(this.motorRightFront, this.motorLeftFront);

  }

    public void percentOut(double speed) {
      motorVortex.set(speed);

      servoBuffer.set(-1.0);
    }

    public void percentOutServo(double speedServo) {
      servoBuffer.set(speedServo);
    }
    public void stopServo() {
      servoBuffer.set(0.5);
    }

    
    public void stop() {
      motorVortex.stopMotor();

      servoBuffer.set(0.5);
    }

    @Override
    public void periodic()
    {
    SmartDashboard.putNumber("corrente:", motorVortex.getOutputCurrent());
    }
}
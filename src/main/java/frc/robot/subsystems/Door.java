import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

//public class Door extends SubsystemBase{
  //  CCSparkMax door = new CCSparkMax ("door", "d", Constants.MotorConstants.DOOR, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.DOOR_REVERSE);

//}


public class Door extends SubsystemBase {
    private final CCSparkMax door;
    private final PIDController pidController;

    // Encoder target values for open and closed positions
    private static final double DOOR_CLOSED_POSITION = 0.0;
    private static final double DOOR_OPEN_POSITION = 120.0;

    public Door() {
        // Initialize motor and PID controller
        door = new CCSparkMax("door", "d", Constants.MotorConstants.DOOR, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.DOOR_REVERSE);
        pidController = new PIDController(0.1, 0, 0); // Set PID , adjust as needed
        pidController.setTolerance(1.0); //tolerance
    }

    public void openDoor() {
        moveToPosition(DOOR_OPEN_POSITION);
    }

    public void closeDoor() {
        moveToPosition(DOOR_CLOSED_POSITION);
    }

    private void moveToPosition(double targetPosition) {
        // Calculate motor power based on target position and current position
        double power = pidController.calculate(door.getPosition(), targetPosition);
        door.set(power);

        // Stop the motor when it's close enough to the target
        if (pidController.atSetpoint()) {
            door.stopMotor();
        }
    }

    @Override
    public void periodic() {
        // Periodically called to update motor power while moving to target position
    }
}
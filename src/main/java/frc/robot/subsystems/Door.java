import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class Door extends SubsystemBase {
    private final CCSparkMax door;
    private final PIDController pidController;

    // Encoder target values for open and closed positions
    

    public Door() {
        // Initialize motor and PID controller
        door = new CCSparkMax("door", "d", Constants.MotorConstants.DOOR, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.DOOR_REVERSE);
        pidController = new PIDController(0.1, 0, 0); // Set PID , adjust as needed
        pidController.setTolerance(1.0); //tolerance
    }

    public void openDoor() {
        moveToPosition(Constants.MechanismPositions.DOOR_UP_POSITION);
    }

    public void closeDoor() {
        moveToPosition(Constants.MechanismPositions.DOOR_DOWN_POSITION);
    }

    public void moveToPosition(double targetPosition) {
        // Calculate motor power based on target position and current position
        
        door.set(pidSet(targetPosition));

        // Stop the motor when it's close enough to the target
        if (pidController.atSetpoint()) {
            door.stopMotor();
        }
    }
    public double pidSet(double targetPosition){
        double power = pidController.calculate(door.getPosition(), targetPosition);
        return power;
    }

    public Command doorUp(){
        return this.run(()->openDoor());
    }
    public Command doorDown(){
        return this.run(()->closeDoor());
    }

    @Override
    public void periodic() {
        // Periodically called to update motor power while moving to target position
    }
}
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Door extends SubsystemBase{
    CCSparkMax door = new CCSparkMax ("door", "d", Constants.MotorConstants.DOOR, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.DOOR_REVERSE);

}

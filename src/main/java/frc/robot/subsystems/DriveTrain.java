package frc.robot.subsystems;




import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
    private  CCSparkMax frontLeft = new CCSparkMax("FrontLeft", "FL", Constants.MotorConstants.FRONT_LEFT,
                        MotorType.kBrushless,
                        IdleMode.kBrake, false);
    private   CCSparkMax frontRight = new CCSparkMax("FrontRight", "FR", Constants.MotorConstants.FRONT_RIGHT,
                        MotorType.kBrushless,
                        IdleMode.kBrake, false);
    private  CCSparkMax backLeft = new CCSparkMax("BackLeft", "BL", Constants.MotorConstants.BACK_LEFT,
                        MotorType.kBrushless,
                        IdleMode.kBrake, false);
    private  CCSparkMax backRight = new CCSparkMax("BackRight", "BR", Constants.MotorConstants.BACK_RIGHT,
                        MotorType.kBrushless,
                        IdleMode.kBrake, false);
                   
    public  DifferentialDrive diffDrive = new DifferentialDrive(
        (double output) -> {
            frontLeft.set(output);
            backLeft.set(output);
            },
        (double output) -> {
            frontRight.set(output);
            backRight.set(output);
        });
    
    public void teleDrive(double moveSpeed, double turnSpeed){
        diffDrive.arcadeDrive(moveSpeed*0.75, turnSpeed*0.75);
     }

    public void driveStraight(double speed){
        frontLeft.set(speed);
        frontRight.set(speed);
        backLeft.set(speed);
        backRight.set(speed);
    }
    public void turnDirection(boolean direction, double speed){
        if (direction == true) {
            frontLeft.set(speed * -1);
            frontRight.set(speed);
            backLeft.set(speed * -1);
            backRight.set(speed);
        }
        else {
            frontLeft.set(speed);
            frontRight.set(speed * -1);
            backLeft.set(speed);
            backRight.set(speed * -1);
        }
    }
    
    public Command basicDrive (double speed){
        return this.run(()-> driveStraight(speed));
    }
    public Command basicTurn (boolean direction, double speed){
        return this.run(()-> turnDirection(direction, speed));
    }
    public Command controlledDrive(double movespeed, double turnSpeed){
        return run(()-> teleDrive(movespeed, turnSpeed));
    }
    public Command driveForTime(double movespeed, double turnSpeed, double time){
        return run(()-> teleDrive(movespeed, turnSpeed)).withTimeout(time);
    }

}
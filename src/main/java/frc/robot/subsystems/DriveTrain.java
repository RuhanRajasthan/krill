package frc.robot.subsystems;
 

import java.util.function.DoubleSupplier;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase{
    
    CCSparkMax frontLeft = new CCSparkMax ("front left", "fl", Constants.MotorConstants.FRONT_LEFT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.FRONT_LEFT_REVERSE);
    CCSparkMax frontRight = new CCSparkMax ("front right", "fr", Constants.MotorConstants.FRONT_RIGHT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.FRONT_RIGHT_REVERSE);
    CCSparkMax backLeft = new CCSparkMax ("back left", "bl", Constants.MotorConstants.BACK_LEFT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.BACK_LEFT_REVERSE);
    CCSparkMax backRight = new CCSparkMax ("back right", "br", Constants.MotorConstants.BACK_RIGHT, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.BACK_RIGHT_REVERSE);
    DifferentialDrive frontDifferentialDrive = new DifferentialDrive (frontLeft, frontRight);
    DifferentialDrive backDifferentialDrive = new DifferentialDrive(backLeft, backRight);
    public void difDrive (double moveSpeed, double turnSpeed){
        if (turnSpeed>0.75||turnSpeed<-0.75){
        frontDifferentialDrive.arcadeDrive(0.5*moveSpeed, 0.5*turnSpeed);
        backDifferentialDrive.arcadeDrive(0.5*moveSpeed, 0.5*turnSpeed);
        }
        else {
            frontDifferentialDrive.arcadeDrive(0.5*moveSpeed, 0);
        backDifferentialDrive.arcadeDrive(0.5*moveSpeed, 0);

        }
    }
    public Command drive (double moveSpeed, double turnSpeed){
        return this.run (()-> difDrive(moveSpeed, turnSpeed));
    }
}


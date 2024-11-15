package frc.robot.subsystems;

import frc.helpers.CCSparkMax;

public class Intake extends SubsystemBase{
    CCSparkMax intakeMotor = new CCSparkMax("Intake Motor", "IM", 1, MotorType.kBrushless, IdleMode.kBrake, false);
    CCSparkMax outtakeMotor = new CCSparkMax("Outtake Motor", "OM", 2, MotorType.kBrushless, IdleMode.kBrake, true);
}

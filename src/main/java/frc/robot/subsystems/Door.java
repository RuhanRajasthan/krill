package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.helpers.CCSparkMax;
import frc.robot.Constants;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class Door extends SubsystemBase {
    public CCSparkMax  door = new CCSparkMax("door", "d", Constants.MotorConstants.DOOR, MotorType.kBrushless, IdleMode.kBrake, Constants.MotorConstants.DOOR_REVERSE);
    private  PIDController pidController;
    private boolean isUp;

    // Encoder target values for open and closed positions
    

    public Door() {
        // Initialize motor and PID controller
        isUp = true;
        pidController = new PIDController(0.1, 0, 0); // Set PID , adjust as needed
        pidController.setTolerance(1.0); //tolerance
    }

    public void openDoor() {
        door.set(0.25);
    }

    public void closeDoor() {
        door.set(-0.25);
    }

    public void moveToPosition(double targetPosition) {
        // Calculate motor power based on target position and current position
        
        door.set(pidSet(targetPosition));

        // Stop the motor when it's close enough to the target
        if (pidController.atSetpoint()) {
            door.set(0.0);
        }
    }

    public boolean atGoal(double goal){
        if (pidController.atSetpoint()||(door.getPosition()>goal-0.25&&door.getPosition()<goal+0.25)){
        // if (door.getPosition()>goal-0.25 && door.getPosition()<goal+0.25){
            return true;
        }
        else
            return false;
    }
    public double pidSet(double targetPosition){
        double power = pidController.calculate(door.getPosition(), targetPosition);
        return power;
    }

    public void manualDoorUp(){
        door.set(0.2); //maybe this is negative idk
        if (door.getPosition()>0){
            door.set(0);
        }
    }
    public void manualDoorDown(){
        door.set(-0.2); //maybe this is position idk
        if (door.getPosition()>0){
            door.set(0);
        }
    }

    public Command doorUp(){
        isUp = true;
        //added the until because maybe the thing is not running for as long
        return this.runEnd(()->openDoor(), ()-> halt()).withTimeout(0.5);
        // return this.run(()-> openDoor());
    }
    public Command doorDown(){
        isUp = false;
        return this.runEnd(()->closeDoor(), ()-> halt()).withTimeout(0.5);
        // return this.run(()->closeDoor());
    }

    public Command manualUp(){
        return this.startEnd(()-> manualDoorUp(),()->halt());
    }

    public Command maunalDownForTime(double time){
        return this.runEnd(()-> manualDoorDown(),()-> halt()).withTimeout(time);
    }

    public Command manualDown(){
        return this.startEnd(()-> manualDoorDown(),()->halt());
    }

    public boolean isUp(){
        return isUp;
    }

    public Command halt (){
        return new InstantCommand(()->{door.set(0);}, this);
    }

    @Override
    public void periodic() {
        // Periodically called to update motor power while moving to target position
    }
}
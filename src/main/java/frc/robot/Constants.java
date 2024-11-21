// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kMechanismsControllerPort = 1;
  }
  
  public static class MotorConstants {
    public static final int FRONT_LEFT = 0;
    public static boolean FRONT_LEFT_REVERSE = true;
    
    public static final int FRONT_RIGHT = 1;
    public static boolean FRONT_RIGHT_REVERSE = true;
    public static final int BACK_LEFT = 2;
    public static boolean BACK_LEFT_REVERSE =false;
    public static final int BACK_RIGHT = 3;
    public static boolean BACK_RIGHT_REVERSE = false;
    public static final int DOOR = 4;
    public static boolean DOOR_REVERSE = false;
    public static double DOOR_ENCODER = 0.0;
    public static final int intake1 = 5;
    public static final int intake2 = 6;
    public static final int screw = 7;
    public static final int indexer = 8;
    public static final int shoot1 = 9;
    public static final int shoot2 = 10;
  }

  public static class MechanismPositions{
    public static final double DOOR_UP_POSITION = 0.0;
    public static final double DOOR_DOWN_POSITION = (150.0/7.0 *(1/3)); //assuming it would be about 120 degrees
  }

  public static class ConversionConstants {
    public static final double WHEEL_CIRCUMFRENCE = Units.inchesToMeters(4*Math.PI);
    // 150/7 rotations of the turn motor to one rotation of the wheel
        // how much of a rotation the wheel turns for one rotation of the turn motor
        public static final double TURN_MOTOR_ROTATIONS_TO_WHEEL_ROTATIONS = 7.0 / 150.0;

        // How many radians the wheel pivots for one full rotation of the turn motor
        public static final double TURN_MOTOR_ROTATIONS_TO_WHEEL_ROTATIONS_RADIANS = Units
                .rotationsToRadians(TURN_MOTOR_ROTATIONS_TO_WHEEL_ROTATIONS);
        public static final double TURN_MOTOR_RADIANS_PER_SECOND = TURN_MOTOR_ROTATIONS_TO_WHEEL_ROTATIONS_RADIANS
                / 60.0;

        // 6.75 rotations of the drive motor to one spin of the wheel
        public static final double DRIVE_MOTOR_ROTATIONS_TO_WHEEL_ROTATIONS = 1.0 / 6.75;
        // horizontal distance travelled by one motor rotation
        public static final double HORIZONTAL_DISTANCE_TRAVELLED_PER_MOTOR_REVOLUTION = WHEEL_CIRCUMFRENCE
                * DRIVE_MOTOR_ROTATIONS_TO_WHEEL_ROTATIONS;
        public static final double DRIVE_MOTOR_METERS_PER_SECOND_CONVERSION_FACTOR = HORIZONTAL_DISTANCE_TRAVELLED_PER_MOTOR_REVOLUTION
                / 60.0;

  }
}
  

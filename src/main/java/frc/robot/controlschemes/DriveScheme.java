package frc.robot.controlschemes;

import frc.helpers.ControlScheme;
import javax.naming.ldap.Control;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveTrain;
public class DriveScheme implements ControlScheme{
    private static CommandXboxController controller;
    public static void configure(DriveTrain driveTrain, int port){
        controller = new CommandXboxController(port);
        // SlewRateLimiter xLimit = new SlewRateLimiter(0.5);
        // SlewRateLimiter yLimit = new SlewRateLimiter(0.5);
        driveTrain.setDefaultCommand(
            new RunCommand(()-> driveTrain.teleDrive((controller.getRightX()), controller.getLeftY()), driveTrain));
    }
}
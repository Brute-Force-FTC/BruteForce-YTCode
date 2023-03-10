package org.firstinspires.ftc.robot.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.Func;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import org.firstinspires.ftc.robot.utilities.Robot;


@TeleOp
public class DriverMode extends LinearOpMode {
    //create variables for the joysticks
    double rsy1 = 0;
    double rsx1 = 0;
    double lsy1 = 0;
    double lsx1 = 0;
    double rsy2 = 0;
    double rsx2 = 0;
    double lsy2 = 0;
    double lsx2 = 0;

    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);
        //wait till start is pressed on the driver hub
        waiForStart();

        while (opModeIsActive()) {
            double rsy1 = this.gamepad1.right_stick_y;
            double rsx1 = this.gamepad1.right_stick_x;
            double lsy1 = this.gamepad1.left_stick_y;
            double lsx1 = this.gamepad1.left_stick_x;
            double rsy2 = this.gamepad2.right_stick_y;
            double rsx2 = this.gamepad2.right_stick_x;
            double lsy2 = this.gamepad2.left_stick_y;
            double lsx2 = this.gamepad2.left_stick_x;

            //when the joystick is up, it is at -1, and when it is down, it is at +1
            //when the joystick is right, it is at +1, and when it is left, it is at -1
            if (rsy1 > 0) {
                //robot to move backward
                robot.moveVertical(rsy1);
            } else if (rsy1 < 0) {
                //robot to move forward
                robot.moveVertical(rsy1);
            } else if (rsx1 > 0) {
                //strafe right
                robot.moveHorizontal(rsx1);
            } else if (rsx1 < 0) {
                //strafe left
                robot.moveHorizontal(rsx1);
            } else if (lsx1 > 0) {
                //turn right
                robot.rotate(lsx1);
            } else if (lsx1 < 0) {
                //turn left
                robot.rotate(lsx1);
            } else {
                //stop all movement
                robot.stopMovement();
            }

            telemetry.addData("Right Stick Y 1", rsy1);
            telemetry.addData("Left Stick Y 1", lsy1);
            telemetry.addData("Left Stick X 1", lsx1);
            telemetry.addData("Right Stick X 1", rsx1);
            telemetry.addData("Right Stick Y 2", rsy2);
            telemetry.addData("Left Stick Y 2", lsy2);
            telemetry.addData("Left Stick X 2", lsx2);
            telemetry.addData("Right Stick X 2", rsx2);
            telemetry.addData("Power of frontLeft", robot.frontLeft.getPower());
            telemetry.addData("Power of backLeft", robot.backLeft.getPower());
            telemetry.addData("Power of frontRight", robot.frontRight.getPower());
            telemetry.addData("Power of backRight", robot.backRight.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();


        }

    }

}
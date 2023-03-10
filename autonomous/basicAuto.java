package org.firstinspires.ftc.robot.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robot.utilities.Robot;

@Autonomous(name="basicAuto", group="group1")
public class basicAuto extends LinearOpMode {

    @Override

    public void runOpMode() {

        Robot robot = new Robot(hardwareMap);

        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Starting Program");
        telmetry.update();

        waitForStart();

        //move forward 10 inches
        robot.encoderDrive("f", 10, 5, opModeIsActive(), telemetry);

        //turn right 5 inches
        robot.encoderDrive("rr", 5, 5, opModeIsActive(), telemetry);

        //move backward 2 inches
        robot.encoderDrive("b", 2, 5, opModeIsActive(), telemetry);

        //strafe left 25 inches
        robot.encoderDrive("l", 25, 5, opModeIsActive(), telemetry);


    }
 }

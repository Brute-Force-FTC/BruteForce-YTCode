package org.firstinspires.ftc.robot.utilities;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Robot { 
    private Blinker controlHub;
    private Blinker expansionHub;
    public BNO055IMU imu;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    static final double COUNTS_PER_MOTOR_REV = 1000; 
    static final double DRIVE_GEAR_REDUCTION = 0.5;
    static final double WHEEL_DIAMETER_INCHES = 3.75;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    public static final double DRIVE_SPEED = 0.5;
    public static final double STRAFE_SPEED = 1;
    public static final double TURN_SPEED = 0.75;

    public Robot(HardwareMap hardwareMap) {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        controlHub = hardwareMap.get(Blinker.class, "Control Hub");
        expansionHub = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
    }

    public void moveVertical (double n) {
        frontLeft.setPower(n);
        frontRight.setPower(-n);
        backLeft.setPower(n);
        backRight.setPower(-n);
    }

    public void moveHorizontal (double n) {
        n = n * 0.5;
        frontLeft.setPower(-n);
        frontRight.setPower(-n);
        backLeft.setPower(n);
        backRight.setPower(n);
    }

    public void rotate (double n) {
        frontLeft.setPower(-n);
        frontRight.setPower(-n);
        backLeft.setPower(-n);
        backRight.setPower(-n);
    }

    public void stopMovement() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void encoderDrive(String config, double Inches, double timeoutS, boolean opModeIsActive, Telemetry telemetry) {
        if (opModeIsActive) {

            double frontLeftInches = 0;
            double frontRightInches = 0;
            double backLeftInches = 0;
            double backRightInches = 0;
            double speed = 0;

            if (config == "f") {
                fronLeftInches = -Inches;
                frontRightInches = Inches;
                backLeftInches = -Inches;
                backRightInches = Inches;
                speed = DRIVE_SPEED;
            } else if (config == "b") {
                fronLeftInches = Inches;
                frontRightInches = -Inches;
                backLeftInches = Inches;
                backRightInches = -Inches;
                speed = DRIVE_SPEED;
            } else if (config == "r") {
                fronLeftInches = -Inches;
                frontRightInches = -Inches;
                backLeftInches = Inches;
                backRightInches = Inches;
                speed = STRAFE_SPEED;
            } else if (config == "l") {
                fronLeftInches = Inches;
                frontRightInches = Inches;
                backLeftInches = -Inches;
                backRightInches = -Inches;
                speed = STRAFE_SPEED;
            } else if (config == "rl") {
                fronLeftInches = Inches;
                frontRightInches = Inches;
                backLeftInches = Inches;
                backRightInches = Inches;
                speed = TURN_SPEED;
            } else if (config == "rr") {
                fronLeftInches = -Inches;
                frontRightInches = -Inches;
                backLeftInches = -Inches;
                backRightInches = -Inches;
                speed = TURN_SPEED;
            }

            int newFrontLeftTarget = frontLeft.getCurrentPosition() + (int)(frontLeftInches * COUNTS_PER_INCH);
            int newFrontRightTarget = frontRight.getCurrentPosition() + (int)(frontRightInches * COUNTS_PER_INCH);
            int newBackLeftTarget = backLeft.getCurrentPosition() + (int)(backLeftInches * COUNTS_PER_INCH);
            int newBackRightTarget = backRight.getCurrentPosition() + (int)(backRightInches * COUNTS_PER_INCH);

            frontLeft.setTargetPosition(newFrontLeftTarget);
            frontRight.setTargetPosition(newFrontRightTarget);
            backLeft.setTargetPosition(newBackLeftTarget);
            backRight.setTargetPosition(newBackRightTarget);

            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeft.setPower(speed);
            frontRight.setPower(speed);
            backLeft.setPower(speed);
            backRight.setPower(speed);

            while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {

                telemetry.addData("COUNTS_PER_INCH =", COUNTS_PER_INCH);
                telemetry.addData("Current Position", "Running at %7d :%7d :%7d :%7d", frontLeft.getCurrentPosition(), frontRight.getCurrentPosition(), backLeft.getCurrentPosition(), backRight.getCurrentPosition())
                telemetry.update();
            }

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
 

}
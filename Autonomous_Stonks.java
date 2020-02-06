package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class Autonomous_Stonks extends LinearOpMode {
    private DcMotor frontLeft; //Pin 2
    private DcMotor frontRight; //Pin 3
    private DcMotor backLeft; // Pin 1
    private DcMotor backRight; // Pin 0

    private Servo intakeLeft; //Servo pin 0
    private Servo intakeRight; //Servo pin 1


    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        intakeLeft = hardwareMap.get(Servo.class, "intakeLeft");
        intakeRight = hardwareMap.get(Servo.class, "intakeRight");

        telemetry.addData("Status", "Waiting");
        telemetry.update();

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        mode("stop");
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mode("run_with_encoders");
        waitForStart();
        intakeLeft.setPosition(0.35);
        intakeRight.setPosition(0.65);
        sleep(2000);

//        setPosition();
        mode("run_to_position");

        //Debugging
        telemetry.addData("Front Left", frontLeft.getCurrentPosition());
        telemetry.addData("Front Right", frontRight.getCurrentPosition());
        telemetry.addData("Back Right", backRight.getCurrentPosition());
        telemetry.addData("Back Left", backLeft.getCurrentPosition());
        telemetry.update();
//        setMotorPower(0);

        sleep(2000);
    }


    private void setMotorPower(double power) {
        power /= 2;
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
    }
    private void setPosition(int positionInTicks) {
        //frontLeft and backRight runs in reverse
        frontLeft.setTargetPosition(-positionInTicks);
        frontRight.setTargetPosition(-positionInTicks);
        backLeft.setTargetPosition(-positionInTicks);
        backRight.setTargetPosition(-positionInTicks);

        setMotorPower(0.05);
        while (opModeIsActive() && frontLeft.getCurrentPosition() > -positionInTicks) {
            telemetry.addData("Front Left", frontLeft.getCurrentPosition());
            telemetry.addData("Front Right", frontRight.getCurrentPosition());
            telemetry.addData("Back Right", backRight.getCurrentPosition());
            telemetry.addData("Back Left", backLeft.getCurrentPosition());
            telemetry.update();
        }
        setMotorPower(0);
    }

    private void mode(String mode) {
        switch (mode) {
            case "stop":
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                break;
            case "run_with_encoders":
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                break;
            case "run_without_encoders":
                frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                break;
            case "run_to_position":
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
}

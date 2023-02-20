package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;

@TeleOp(name = "New_Test_TeleOp")
public class newTestTeleOp extends LinearOpMode{
    double lefty;
    double leftx;
    double righty;
    double rightx;

    public Servo finger;
    public Servo arm;
    public Servo outtake;
    public DcMotorEx intakeSlide;
    public DcMotorEx outtakeSlide;
    public Servo turret;

    int intakeSlidePosition = 0;
    int outtakeSlidePosition = 0;
    double turretPosition = 0;
    double outtakePosition = 0;
    double fingerPosition = 0;
    double armPosition = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);

        intakeSlide = hardwareMap.get(DcMotorEx.class,"intakeSlide");
        outtakeSlide = hardwareMap.get(DcMotorEx.class,"outtakeSlide");
        finger = hardwareMap.get(Servo.class,"finger");
        arm = hardwareMap.get(Servo.class, "arm");
        outtake = hardwareMap.get(Servo.class, "outtake");
        turret = hardwareMap.get(Servo.class, "turret");

        wheels.initialize();
        wheels.telemetry = telemetry;
        wheels.parent = this;
        wheels.leftErrorAdjustment = 0.72;
        wheels.rightErrorAdjustment = 0.72;

        intakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setPosition(0.5);

        waitForStart();
        while(opModeIsActive()){
        lefty = gamepad1.left_stick_y;
        leftx = gamepad1.left_stick_x;
        righty = gamepad1.right_stick_y;
        rightx = -gamepad1.right_stick_x;
        boolean b = gamepad1.b;
        boolean x = gamepad1.x;
        boolean a = gamepad1.a;
        boolean y = gamepad1.y;
        boolean dpadUp = gamepad1.dpad_up;
        boolean dpadDown = gamepad1.dpad_down;
        boolean dpadRight = gamepad1.dpad_right;
        boolean dpadLeft = gamepad1.dpad_left;
        boolean b2 = gamepad2.b;
        boolean x2 = gamepad2.x;
        boolean a2 = gamepad2.a;
        boolean y2 = gamepad2.y;
        boolean dpadUp2 = gamepad2.dpad_up;
        boolean dpadDown2 = gamepad2.dpad_down;
        boolean dpadRight2 = gamepad2.dpad_right;
        boolean dpadLeft2 = gamepad1.dpad_left;
        boolean rightBumper1 = gamepad1.right_bumper;
        boolean leftBumper1 = gamepad1.left_bumper;
        boolean rightBumper2 = gamepad2.right_bumper;
        boolean leftBumper2 = gamepad2.left_bumper;

        if(b){
            intakeSlidePosition += 50;
            intakeSlide.setPower(0.8);
            sleep(1000);
//            intakeSlide.setTargetPosition(intakeSlidePosition);
//            intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if(x){
            intakeSlidePosition -= 50;
            intakeSlide.setPower(-0.8);
//            intakeSlide.setTargetPosition(intakeSlidePosition);
//            intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if(y){
            outtakeSlidePosition += 50;
            outtakeSlide.setPower(0.8);
            outtakeSlide.setTargetPosition(outtakeSlidePosition);
            outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if(a){
            outtakeSlidePosition -= 50;
            outtakeSlide.setPower(0.8);
            outtakeSlide.setTargetPosition(outtakeSlidePosition);
            outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else {
            intakeSlide.setPower(0.0);
            outtakeSlide.setPower(0.0);
        }
        if(dpadUp){
            turretPosition += 0.01;
            turret.setPosition(turretPosition);
        }
        if(dpadDown){
            turretPosition -= 0.01;
            turret.setPosition(turretPosition);
        }
        if(dpadRight){
            outtakePosition += 0.01;
            outtake.setPosition(outtakePosition);
        }
        if(dpadLeft){
            outtakePosition -= 0.01;
            outtake.setPosition(outtakePosition);
        }
        if(rightBumper1){
            fingerPosition += 0.01;
            finger.setPosition(0.56);
        }
        if(leftBumper1){
            fingerPosition -= 0.01;
            finger.setPosition(0.45);
        }
        if(b2){
            armPosition += 0.001;
            arm.setPosition(armPosition);
        }
        if(x2){
            armPosition -= 0.001;
            arm.setPosition(armPosition);
        }

        wheels.move(lefty,righty,leftx,rightx);

        telemetry.addData("intake slide position: ", intakeSlide.getCurrentPosition());
        telemetry.addData("outtake slide position: ", outtakeSlide.getCurrentPosition());
        telemetry.addData("turret position: ", turret.getPosition());
        telemetry.addData("outtake position: ", outtake.getPosition());
        telemetry.addData("finger position: ", finger.getPosition());
        telemetry.addData("arm position: ", arm.getPosition());
        telemetry.update();

    }
}}

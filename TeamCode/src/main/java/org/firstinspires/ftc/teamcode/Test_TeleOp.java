package org.firstinspires.ftc.teamcode;

import android.util.Range;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@TeleOp(name = "Test_TeleOp")
public class Test_TeleOp extends LinearOpMode {
    double lefty;
    double leftx;
    double righty;
    double rightx;

    public DcMotorEx slide1;
    public DcMotorEx slide2;
    public DcMotorEx arm;
    public Servo outtakeArm;
    public Servo finger1;
    public Servo finger2;
    public Servo wrist1;
    public Servo wrist2;



    private static void logGamepad(Telemetry telemetry, Gamepad gamepad, String prefix) {
        telemetry.addData(prefix + "Synthetic",
                gamepad.getGamepadId() == Gamepad.ID_UNASSOCIATED);
        for (Field field : gamepad.getClass().getFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;

            try {
                telemetry.addData(prefix + field.getName(), field.get(gamepad));
            } catch (IllegalAccessException e) {
                // ignore for now
            }
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        finger1 = hardwareMap.get(Servo.class, "finger1");
        finger2 = hardwareMap.get(Servo.class, "finger2");
        wrist1 = hardwareMap.get(Servo.class, "wrist1");
        wrist2 = hardwareMap.get(Servo.class, "wrist2");
        outtakeArm= hardwareMap.get(Servo.class, "outtakeArm");

        wrist2.setDirection(Servo.Direction.REVERSE);
        //finger1.setDirection(Servo.Direction.REVERSE);
        finger2.setDirection(Servo.Direction.REVERSE);

        slide1 = hardwareMap.get(DcMotorEx.class, "slide1");
        slide2 = hardwareMap.get(DcMotorEx.class, "slide2");
        arm = hardwareMap.get(DcMotorEx.class, "arm");

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




        waitForStart();
        while (opModeIsActive()){
            int armposition = 0;
            boolean b = gamepad1.b;
            boolean x = gamepad1.x;
            boolean a = gamepad1.a;
            boolean y = gamepad1.y;
            boolean dpadUp = gamepad1.dpad_up;
            boolean dpadDown = gamepad1.dpad_down;
            boolean dpadRight = gamepad1.dpad_right;
            boolean b2 = gamepad2.b;
            boolean x2 = gamepad2.x;
            boolean a2 = gamepad2.a;
            boolean y2 = gamepad2.y;
            boolean dpadUp2 = gamepad2.dpad_up;
            boolean dpadDown2 = gamepad2.dpad_down;
            boolean dpadRight2 = gamepad2.dpad_right;
            wrist1.setPosition(0.29);
            wrist2.setPosition(0.29);


            if(b){
                //outtakeArm.setPosition(outtakeArm.getPosition() + 0.1);
                slide1.setPower(0.8);
                slide2.setPower(0.8);
                int position = slide1.getCurrentPosition();
                int position2 = slide2.getCurrentPosition();
                slide1.setTargetPosition(position + 250);
                slide2.setTargetPosition(position2 + 250);
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }
            if(x){
                slide1.setPower(0.8);
                slide2.setPower(0.8);
                int position = slide1.getCurrentPosition();
                int position2 = slide2.getCurrentPosition();
                slide1.setTargetPosition(position - 250);
                slide2.setTargetPosition(position2 - 250);
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if(a){
                slide1.setPower(0.8);
                slide2.setPower(0.8);
                slide1.setTargetPosition(0);
                slide2.setTargetPosition(0);
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }
            if(y){
                slide1.setPower(0.8);
                slide2.setPower(0.8);
                slide1.setTargetPosition(1250);
                slide2.setTargetPosition(1250);
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if(dpadUp){
                slide1.setPower(0.9);
                slide2.setPower(0.9);
                slide1.setTargetPosition(2100);
                slide2.setTargetPosition(2100);
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(1000);

            }
            if(dpadDown){
                slide1.setPower(0.9);
                slide2.setPower(0.9);
                slide1.setTargetPosition(0);
                slide2.setTargetPosition(0);
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(2000);

            }
            if(dpadRight){
                slide1.setPower(0.9);
                slide2.setPower(0.9);
                slide1.setTargetPosition(1250);
                slide2.setTargetPosition(1250);
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(1000);
            }
            if(b2){
                //outtakeArm.setPosition(0.425);
                //sleep(100);
                armposition += 100;
                arm.setPower(0.4);
                //int position = arm.getCurrentPosition();
                arm.setTargetPosition(armposition);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(200);

            }
            if(x2){
                //outtakeArm.setPosition(1.0);
                //sleep(100);
                armposition -= 100;
                arm.setPower(0.4);
                int position = arm.getCurrentPosition();
                arm.setTargetPosition(armposition);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(200);
            }
            if(a2){
                finger1.setPosition(0.15);
                finger2.setPosition(0.15);


            }
            if(y2){
                finger1.setPosition(0.29);
                finger2.setPosition(0.29);
            }
            if(dpadUp2){
                wrist1.setPosition(0.29);
                wrist2.setPosition(0.29);
            }
            if(dpadDown2){
                wrist1.setPosition(wrist1.getPosition()-0.01);
                wrist2.setPosition(wrist2.getPosition()-0.01);
            }
            if(dpadRight2){
                wrist1.setPosition(wrist1.getPosition()+0.01);
                wrist2.setPosition(wrist2.getPosition()+0.01);

            }
            else {
                arm.setPower(0);
                slide1.setPower(0);
                slide2.setPower(0);
            }
            telemetry.addData("slide 1 current positon:", slide1.getCurrentPosition());
            telemetry.addData("slide 2 current positon:", slide2.getCurrentPosition());
            telemetry.addData("outtake arm position: ", outtakeArm.getPosition());
            telemetry.addData("finger1 position: ", finger1.getPosition());
            telemetry.addData("finger2 position: ", finger2.getPosition());
            telemetry.addData("wrist1 position: ", wrist1.getPosition());
            telemetry.addData("wrist2 position: ", wrist2.getPosition());
            telemetry.addData("arm position: ", arm.getCurrentPosition());

            telemetry.update();
        }
    }
}

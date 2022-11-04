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
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@TeleOp(name = "Test_TeleOp")
public class Test_TeleOp extends LinearOpMode {
    double lefty;
    double leftx;
    double righty;
    double rightx;

    double armGround;
    double armDropping;

    double wristGround;

    public DcMotorEx slide1;
    public DcMotorEx slide2;
    public Servo arm1;
    public Servo arm2;
    public Servo outtakeArm;
    public Servo finger1;
    public Servo finger2;
    public Servo wrist1;
    public Servo wrist2;

    private double outtakeArmPosition = 0.3;

    private double finger1Position = 0.0;
    private double finger2Position = 0.0;

    private double wrist1postion = 0.3;
    private double wrist2postion = 0.3;

    private double arm1Position = 0.0;
    private double arm2Position = 0.0;


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
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);
        finger1 = hardwareMap.get(Servo.class, "finger1");
        finger2 = hardwareMap.get(Servo.class, "finger2");
        wrist1 = hardwareMap.get(Servo.class, "wrist1");
        wrist2 = hardwareMap.get(Servo.class, "wrist2");
        outtakeArm= hardwareMap.get(Servo.class, "outtakeArm");

        wheels.initialize();
        wheels.telemetry = telemetry;
        wheels.parent = this;
        wheels.leftErrorAdjustment = 0.72;
        wheels.rightErrorAdjustment = 0.72;
        wrist2.setDirection(Servo.Direction.REVERSE);
        //finger1.setDirection(Servo.Direction.REVERSE);
        finger2.setDirection(Servo.Direction.REVERSE);


        slide1 = hardwareMap.get(DcMotorEx.class, "slide1");
        slide2 = hardwareMap.get(DcMotorEx.class, "slide2");
        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");

        arm2.setDirection(Servo.Direction.REVERSE);


        slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm1.setPosition(0);
        arm2.setPosition(0);

        wrist1.setPosition(0.42);
        wrist2.setPosition(0.42);

        outtakeArm.setPosition(0.08);


        waitForStart();
        while (opModeIsActive()){
            int armposition = 0;
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
            boolean b2 = gamepad2.b;
            boolean x2 = gamepad2.x;
            boolean a2 = gamepad2.a;
            boolean y2 = gamepad2.y;
            boolean dpadUp2 = gamepad2.dpad_up;
            boolean dpadDown2 = gamepad2.dpad_down;
            boolean dpadRight2 = gamepad2.dpad_right;
            boolean dpadLeft2 = gamepad1.dpad_left;
            //wrist1.setPosition(0.29);
            //wrist2.setPosition(0.29);


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
//              slide1.setPower(0.9);
                slide2.setPower(0.9);
//                slide1.setTargetPosition(2100);
//                slide2.setTargetPosition(2100);
//                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(1000);
                slide2.setPower(0.0);

            }
            if(dpadDown){
                //slide1.setPower(0.9);
                slide2.setPower(-0.9);
//                slide1.setTargetPosition(0);
//                slide2.setTargetPosition(0);
//                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(1000);
                slide2.setPower(0.0);

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
                arm1Position += 0.01;
                arm2Position += 0.01;
                arm1.setPosition(arm1Position);
                arm2.setPosition(arm2Position);
                sleep(200);
//                outtakeArm.setPosition(0);
//                sleep(200);
            }
            if(x2){
//                outtakeArm.setPosition(0.5);
//                sleep(200);
                arm1Position -= 0.01;
                arm2Position -= 0.01;
                arm1.setPosition(arm1Position);
                arm2.setPosition(arm2Position);
                sleep(200);

            }
            if(a2){
                finger1Position += 0.08;
                finger2Position += 0.08;
                finger1.setPosition(0.2);
                finger2.setPosition(0.2);
                sleep(200);


            }
            if(y2){
                finger1Position -= 0.08;
                finger2Position -= 0.08;
                finger1.setPosition(0.6);
                finger2.setPosition(0.4);
                sleep(200);
            }
            if(dpadRight2){
                outtakeArmPosition += 0.1;
                outtakeArm.setPosition(outtakeArmPosition);
                sleep(200);


            }
            if(dpadLeft2){
                outtakeArmPosition -= 0.1;
                outtakeArm.setPosition(outtakeArmPosition);
                sleep(200);
            }
            if(dpadDown2){
                wrist1postion -= 0.05;
                wrist2postion -= 0.05;
                wrist1.setPosition(wrist1postion);
                wrist2.setPosition(wrist2postion);
                sleep(200);
//                arm1.setPosition(0.5);
//                arm2.setPosition(0.5);
//                wrist1.setPosition(0.66);
//                wrist2.setPosition(0.66);
//                sleep(1000);
//                finger1.setPosition(0.16);
//                finger2.setPosition(0.16);
//                sleep(200);
//                arm1.setPosition(0);
//                arm2.setPosition(0);
//                wrist1.setPosition(0.45);
//                wrist2.setPosition(0.45);
            }
            if(dpadUp2){
                wrist1postion += 0.05;
                wrist2postion += 0.05;
                wrist1.setPosition(wrist1postion);
                wrist2.setPosition(wrist2postion);
                sleep(200);
//                outtakeArmPosition -= 0.1;
//                outtakeArm.setPosition(outtakeArmPosition);
//                sleep(1000);

            }
            else {
                slide1.setPower(0);
                slide2.setPower(0);
            }
            wheels.move(lefty,righty,leftx,rightx);
            telemetry.addData("slide 1 current positon:", slide1.getCurrentPosition());
            telemetry.addData("slide 2 current positon:", slide2.getCurrentPosition());
            telemetry.addData("outtake arm position: ", outtakeArm.getPosition());
            telemetry.addData("finger1 position: ", finger1.getPosition());
            telemetry.addData("finger2 position: ", finger2.getPosition());
            telemetry.addData("wrist1 position: ", wrist1.getPosition());
            telemetry.addData("wrist2 position: ", wrist2.getPosition());
            telemetry.addData("arm1 position: ", arm1.getPosition());
            telemetry.addData("arm2 position: ", arm2.getPosition());

            telemetry.update();
        }
    }
}

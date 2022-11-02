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
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;


@TeleOp(name = "PowerPlay_TELEOP")
public class PowerPlay_TELEOP extends LinearOpMode {
    double lefty;
    double leftx;
    double righty;
    double rightx;

    double armGround;
    double armDropping;

    double wristGround;
    double wristDropping;


    public DcMotorEx slide1;
    public DcMotorEx slide2;

    public Servo arm1;
    public Servo arm2;
    public Servo outtakeArm;
    public Servo finger1;
    public Servo finger2;
    public Servo wrist1;
    public Servo wrist2;


    @Override
    public void runOpMode() throws InterruptedException {
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);
        finger1 = hardwareMap.get(Servo.class, "finger1");
        finger2 = hardwareMap.get(Servo.class, "finger2");
        wrist1 = hardwareMap.get(Servo.class, "wrist1");
        wrist2 = hardwareMap.get(Servo.class, "wrist2");
        outtakeArm= hardwareMap.get(Servo.class, "outtakeArm");
        arm1 = hardwareMap.get(Servo.class,"arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");

        wheels.initialize();
        wheels.telemetry = telemetry;
        wheels.parent = this;
        wheels.leftErrorAdjustment = 0.72;
        wheels.rightErrorAdjustment = 0.72;

        wrist2.setDirection(Servo.Direction.REVERSE);
        finger2.setDirection(Servo.Direction.REVERSE);
        arm2.setDirection(Servo.Direction.REVERSE);

        slide1 = hardwareMap.get(DcMotorEx.class, "slide1");
        slide2 = hardwareMap.get(DcMotorEx.class, "slide2");

        slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm1.setPosition(0.0);
        arm2.setPosition(0.0);

        wrist1.setPosition(0.50);
        wrist2.setPosition(0.50);

        outtakeArm.setPosition(0);
        arm1.setPosition(0.44);
        arm2.setPosition(0.44);

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
            boolean b2 = gamepad2.b;
            boolean x2 = gamepad2.x;
            boolean a2 = gamepad2.a;
            boolean y2 = gamepad2.y;
            boolean dpadUp2 = gamepad2.dpad_up;
            boolean dpadDown2 = gamepad2.dpad_down;
            boolean dpadRight2 = gamepad2.dpad_right;
            boolean dpadLeft2 = gamepad2.dpad_left;


            if(y2){
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
            }
            else if(x2){
                finger1.setPosition(0.50);
                finger2.setPosition(0.38);
            }
            if(a2){
                outtakeArm.setPosition(0);
            }
            if(b2){
                outtakeArm.setPosition(0.55);
            }
            if(dpadDown2){
                arm1.setPosition(0.0);
                arm2.setPosition(0.0);

            }
            if(dpadRight2){
//
                arm1.setPosition(0.5);
                arm2.setPosition(0.5);
                wrist1.setPosition(0.66);
                wrist2.setPosition(0.66);
                sleep(1000);
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
                sleep(200);
                arm1.setPosition(0.44);
                arm2.setPosition(0.44);
                wrist1.setPosition(0.45);
                wrist2.setPosition(0.45);
                raiseSlide(1250);
            }
            if(dpadUp2){
                arm1.setPosition(0.5);
                arm2.setPosition(0.5);
                wrist1.setPosition(0.66);
                wrist2.setPosition(0.66);
                sleep(1000);
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
                sleep(200);
                arm1.setPosition(0.44);
                arm2.setPosition(0.44);
                wrist1.setPosition(0.45);
                wrist2.setPosition(0.45);
                raiseSlide(2350);
            }
            if(dpadLeft2){
                raiseSlide(0);
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
    private void raiseSlide(int index){
        slide1.setPower(0.8);
        slide2.setPower(0.8);
        slide1.setTargetPosition(index);
        slide2.setTargetPosition(index);
        slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}

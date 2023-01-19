package org.firstinspires.ftc.teamcode;

import android.util.Range;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;



@TeleOp(name = "PowerPlay_TELEOP")
public class PowerPlay_TELEOP extends LinearOpMode {
    double lefty;
    double leftx;
    double righty;
    double lefty2;
    double rightx;
    double distance;

    double armGround = 0;
    double armDropping = 0.38;

    double wristGround = 0.6;
    double wristDropping = 0.6;

    public DcMotorEx slide1;

    public Servo arm1;
    public Servo arm2;
    public Servo outtakeArm;
    public Servo finger1;
    public Servo finger2;
    public Servo wrist1;
    public Servo wrist2;
    public DistanceSensor distanceSensor;


    @Override
    public void runOpMode() throws InterruptedException {
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);
        finger1 = hardwareMap.get(Servo.class, "fingerRight");
        finger2 = hardwareMap.get(Servo.class, "fingerLeft");
        wrist1 = hardwareMap.get(Servo.class, "wristRight");
        wrist2 = hardwareMap.get(Servo.class, "wristLeft");
        outtakeArm= hardwareMap.get(Servo.class, "outtakeArm");
        arm1 = hardwareMap.get(Servo.class,"armRight");
        arm2 = hardwareMap.get(Servo.class, "armLeft");
        distanceSensor = hardwareMap.get(DistanceSensor.class,"distance");


        wheels.initialize();
        wheels.telemetry = telemetry;
        wheels.parent = this;
        wheels.leftErrorAdjustment = 0.72;
        wheels.rightErrorAdjustment = 0.72;

        wrist2.setDirection(Servo.Direction.REVERSE);
        finger2.setDirection(Servo.Direction.REVERSE);
        arm2.setDirection(Servo.Direction.REVERSE);
//        outtakeArm.setDirection(Servo.Direction.REVERSE);

        //init position for outtake arm
        outtakeArm.setPosition(0.5);

        //

        slide1 = hardwareMap.get(DcMotorEx.class, "slideRight");

//        slide2 = hardwareMap.get(DcMotorEx.class, "slideLeft");

//        slide2.setDirection(DcMotorSimple.Direction.REVERSE);

        slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        slide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        slide1.setTargetPosition(0);

        slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        wrist1.setPosition(wristDropping);
        wrist2.setPosition(wristDropping);


        arm1.setPosition(armDropping);
        arm2.setPosition(armDropping);



        waitForStart();
        while(opModeIsActive()){
            distance = distanceSensor.getDistance(DistanceUnit.INCH);
            lefty = gamepad1.left_stick_y;
            leftx = gamepad1.left_stick_x;
            righty = gamepad1.right_stick_y;
            rightx = -gamepad1.right_stick_x;
            lefty2 = gamepad2.left_stick_y;
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
            boolean leftBumper2 = gamepad2.left_bumper;
            boolean rightBumper2 = gamepad2.right_bumper;

            if(a){
                wheels.leftErrorAdjustment = 0.5;
                wheels.rightErrorAdjustment = 0.5;
            }
            if(y){
                wheels.leftErrorAdjustment = 0.72;
                wheels.rightErrorAdjustment = 0.72;
            }
            if(y2){
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
            }
            else if(x2){
                finger1.setPosition(0.56);
                finger2.setPosition(0.41);
            }
            if(a2){
                outtakeArm.setPosition(0.5);
            }
            if(b2){
                outtakeArm.setPosition(1);
            }
            if(leftBumper2){
                arm1.setPosition(armGround);
                arm2.setPosition(armGround);
                wrist1.setPosition(wristGround);
                wrist2.setPosition(wristGround);

            }
            if(rightBumper2){
                arm1.setPosition(armDropping);
                arm2.setPosition(armDropping);
                wrist1.setPosition(wristDropping);
                wrist2.setPosition(wristDropping);
                sleep(1000);
            }
            if(dpadDown2){
                arm1.setPosition(armGround);
                arm2.setPosition(armGround);
                outtakeArm.setPosition(0.5);

                distance = distanceSensor.getDistance(DistanceUnit.INCH);
                raiseSlide(0);
                slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                while (distance > 0.6){
                    distance = distanceSensor.getDistance(DistanceUnit.INCH);
                    slide1.setDirection(DcMotorSimple.Direction.REVERSE);
                    slide1.setPower(0.8);
                    if (distance<=0.6){
                        slide1.setPower(0);
                        break;
                    }
                }
                slide1.setPower(0);
                slide1.setDirection(DcMotorSimple.Direction.FORWARD);

                //slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }
            if(dpadRight2){
                slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                distance = distanceSensor.getDistance(DistanceUnit.INCH);
                arm1.setPosition(armDropping);
                arm2.setPosition(armDropping);
                wrist1.setPosition(wristDropping);
                wrist2.setPosition(wristDropping);
                sleep(1000);
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
                sleep(200);
                raiseSlide(1700);

                /*
                slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                while (distance < 10.2){
                    distance = distanceSensor.getDistance(DistanceUnit.INCH);
                    slide1.setPower(1);
                    if (distance>=10.2){
                        slide1.setPower(0);
                        break;
                    }
                }
                slide1.setPower(0);
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                distance = distanceSensor.getDistance(DistanceUnit.INCH);*/

            }
            if(dpadUp2){
                slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                distance = distanceSensor.getDistance(DistanceUnit.INCH);
                arm1.setPosition(armDropping);
                arm2.setPosition(armDropping);
                wrist1.setPosition(wristDropping);
                wrist2.setPosition(wristDropping);
                sleep(1000);
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
                sleep(200);
                slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                while (distance < 7.7){
                    distance = distanceSensor.getDistance(DistanceUnit.INCH);
                    slide1.setPower(1);
                    if (distance>=7.7){
                        slide1.setPower(0);
                        break;
                    }
                }
                slide1.setPower(0);
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                distance = distanceSensor.getDistance(DistanceUnit.INCH);
            }
            if(dpadLeft2){
                arm1.setPosition(armDropping);
                arm2.setPosition(armDropping);
                wrist1.setPosition(wristDropping);
                wrist2.setPosition(wristDropping);
                sleep(1000);
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
                sleep(200);
            }

            lefty2 = gamepad2.left_stick_y;
            if(lefty2 != 0){
                slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                slide1.setPower(lefty2);
            } else if (lefty2 == 0){
                slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            wheels.move(lefty,righty,leftx,rightx);
            distance = distanceSensor.getDistance(DistanceUnit.INCH);
            telemetry.addData("distance: ", distance);
            telemetry.addData("slide 1 current positon:", slide1.getCurrentPosition());
//            telemetry.addData("slide 2 current positon:", slide2.getCurrentPosition());
            telemetry.addData("slide 1 current positon:", slide1.getCurrentPosition());
            telemetry.addData("outtake arm position: ", outtakeArm.getPosition());
            telemetry.addData("finger1 position: ", finger1.getPosition());
            telemetry.addData("finger2 position: ", finger2.getPosition());
            telemetry.addData("wrist1 position: ", wrist1.getPosition());
            telemetry.addData("wrist2 position: ", wrist2.getPosition());
            telemetry.addData("arm1 position: ", arm1.getPosition());
            telemetry.addData("arm2 position: ", arm2.getPosition());
            telemetry.addData("lefty2: ", lefty2);


            telemetry.update();


        }



    }
    private void raiseSlide(int index){
        slide1.setPower(1);
//        slide2.setPower(0.8);
        slide1.setTargetPosition(index);
//        slide2.setTargetPosition(index);
        slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}

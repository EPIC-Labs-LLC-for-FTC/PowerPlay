package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Slider;

@TeleOp(name = "New_PowerPlay_TELEOP")
public class New_Powerplay_TeleOp extends LinearOpMode {
    double lefty;
    double leftx;
    double righty;
    double rightx;

    public DistanceSensor intake;
    public TouchSensor intakeHome;
    public Servo finger;
    public Servo arm;
    public Servo outtake;
    public DcMotorEx intakeSlide;
    public DcMotorEx outtakeSlide;
    public Servo turret;
    //intake sensor values
    double intakeDistance = 0.0;
    //finger values
    double fingerClose = 0.56;
    double fingerOpen = 0.45;
    //outtake alues
    double outtakeDropping = 0.0;
    double outtakeRecieving = 0.0;
    //turret values
    double straightBack = 0.31;
    double left = 0.0;
    double right = 1.0;
    //arm values
    double armRecieving = 0.0;
    double armDropping = 0.0;
    //outtake slide values
    int topJunction = 0;
    int mediumJunction = 0;
    int outtakeSlideHome = 0;

    int intakeSlidePosition = 0;
    int outtakeSlidePosition = 0;
    double turretPosition = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);

        intakeHome = hardwareMap.get(TouchSensor.class,"intakeHome");
        intake = hardwareMap.get(DistanceSensor.class,"intake");
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

        waitForStart();
        lefty = gamepad1.left_stick_y;
        leftx = gamepad1.left_stick_x;
        righty = gamepad1.right_stick_y;
        rightx = -gamepad1.right_stick_x;
        float rightTrigger = gamepad1.right_trigger;
        float leftTrigger = gamepad1.left_trigger;
        boolean right_bumper = gamepad1.right_bumper;
        boolean left_bumper = gamepad1.left_bumper;
        boolean x = gamepad1.x;
        boolean a = gamepad1.a;
        boolean b = gamepad1.b;
        boolean b2 = gamepad2.b;
        boolean x2 = gamepad2.x;
        boolean a2 = gamepad2.a;
        boolean y2 = gamepad2.y;
        boolean dpadUp2 = gamepad2.dpad_up;
        boolean dpadRight2 = gamepad2.dpad_right;
        boolean dpadLeft2 = gamepad1.dpad_left;
        boolean rightBumper2 = gamepad2.right_bumper;
        boolean leftBumper2 = gamepad2.left_bumper;
        float right_trigger2 = gamepad2.right_trigger;
        float left_trigger2 = gamepad2.left_trigger;

        if(rightTrigger > 0){
            intakeSlidePosition += 50;
            intakeSlide.setPower(0.8);
            intakeSlide.setTargetPosition(intakeSlidePosition);
            intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(leftTrigger > 0){
            intakeSlidePosition -= 50;
            intakeSlide.setPower(0.8);
            intakeSlide.setTargetPosition(intakeSlidePosition);
            intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(right_bumper){
            intakeDistance = intake.getDistance(DistanceUnit.INCH);
            while(intakeDistance > 2.1){
                intakeDistance = intake.getDistance(DistanceUnit.INCH);
                intakeSlide.setPower(0.8);
                intakeDistance = intake.getDistance(DistanceUnit.INCH);
                if(intakeDistance < 2.2){
                    intakeSlide.setPower(0);
                }
            }
            arm.setPosition(armRecieving);
        }
        if(left_bumper){
            while(!intakeHome.isPressed()){
                intakeSlide.setPower(0.8);
                if(intakeHome.isPressed()){
                    intakeSlide.setPower(0);
                }
            }

            arm.setPosition(armDropping);
        }
        if(x){
            if(finger.getPosition() < (fingerClose + 0.1) && finger.getPosition() >(fingerClose - 0.1)){
                finger.setPosition(fingerOpen);
            }
            if(finger.getPosition() < (fingerOpen + 0.1) && finger.getPosition() >(fingerOpen - 0.1)){
                finger.setPosition(fingerClose);
            }
        }
        if(a){
            arm.setPosition(armRecieving);
        }
        if(b){
            arm.setPosition(armDropping);
        }
        if(dpadUp2){
            outtakeSlide.setPower(0.8);
            outtakeSlide.setTargetPosition(topJunction);
            outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(dpadLeft2){
            outtakeSlide.setPower(0.8);
            outtakeSlide.setTargetPosition(mediumJunction);
            outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(dpadRight2){
            outtakeSlide.setPower(0.8);
            outtakeSlide.setTargetPosition(outtakeSlideHome);
            outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            outtake.setPosition(outtakeRecieving);
        }
        if(a2){
            if(outtake.getPosition() < (outtakeRecieving + 0.1) && outtake.getPosition() >(outtakeRecieving - 0.1)){
                outtake.setPosition(outtakeDropping);
            }
            if(outtake.getPosition() < (outtakeDropping + 0.1) && outtake.getPosition() >(outtakeDropping - 0.1)){
                outtake.setPosition(outtakeRecieving);
            }
        }
        if(y2){
            turret.setPosition(straightBack);
        }
        if(x2){
            turret.setPosition(left);
        }
        if(b2){
            turret.setPosition(right);
        }
        if(rightBumper2){
            outtakeSlidePosition += 50;
            outtakeSlide.setPower(0.8);
            outtakeSlide.setTargetPosition(intakeSlidePosition);
            outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(leftBumper2){
            outtakeSlidePosition -= 50;
            outtakeSlide.setPower(0.8);
            outtakeSlide.setTargetPosition(intakeSlidePosition);
            outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(rightTrigger > 0){
            turretPosition = turret.getPosition() + 0.1;
            turret.setPosition(turretPosition);
        }
        if(leftTrigger > 0){
            turretPosition = turret.getPosition() - 0.1;
            turret.setPosition(turretPosition);
        }
        wheels.move(lefty,righty,leftx,rightx);

    }
}

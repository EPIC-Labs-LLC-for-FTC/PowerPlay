package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Claw2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Outtake2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Turret2023;

@TeleOp(name = "New_PowerPlay_TELEOP")
public class New_Powerplay_TeleOp extends LinearOpMode {
    double lefty;
    double leftx;
    double righty;
    double rightx;
    public Claw2023 finger;
    public Arm2023 arm;
    public Outtake2023 outtake;
    public DcMotorEx intakeSlide;
    public DcMotorEx outtakeSlide;
    public Turret2023 turret;
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

        intakeSlide = hardwareMap.get(DcMotorEx.class,"intakeSlide");
        outtakeSlide = hardwareMap.get(DcMotorEx.class,"outtakeSlide");
        finger = new Claw2023(hardwareMap);
        finger.telemetry = telemetry;
        finger.parent = this;
        arm = new Arm2023(hardwareMap);
        arm.telemetry = telemetry;
        arm.parent = this;
        outtake = new Outtake2023(hardwareMap);
        outtake.telemetry = telemetry;
        outtake.parent = this;
        turret = new Turret2023(hardwareMap);
        turret.telemetry = telemetry;
        turret.parent = this;

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
        while(opModeIsActive()){
        lefty = gamepad1.left_stick_y;
        leftx = gamepad1.left_stick_x;
        righty = gamepad1.right_stick_y;
        rightx = -gamepad1.right_stick_x;
        float rightTrigger = gamepad1.right_trigger;
        float leftTrigger = gamepad1.left_trigger;
        boolean right_bumper = gamepad1.right_bumper;
        boolean left_bumper = gamepad1.left_bumper;
        boolean x = gamepad1.x;
        boolean y = gamepad1.y;
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
            intakeSlidePosition += 5;
            intakeSlide.setPower(0.8);
            intakeSlide.setTargetPosition(intakeSlidePosition);
            intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(leftTrigger > 0){
            if(intakeSlide.getCurrentPosition() >=5){
            intakeSlidePosition -= 5;
            intakeSlide.setPower(0.8);
            intakeSlide.setTargetPosition(intakeSlidePosition);
            intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }}
        if(right_bumper){
            intakeSlide.setPower(0.8);
            intakeSlide.setTargetPosition(-1400);
            intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.ground();
        }
        if(left_bumper){
            intakeSlide.setPower(0.8);
            intakeSlide.setTargetPosition(50);
            intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.ground();
            arm.dropping();
        }
        if(x){
            finger.release();
        }
        if(y){
            finger.grab();
        }
        if(a){
            arm.ground();
        }
        if(b){
            arm.dropping();
        }
        if(dpadUp2){
            outtake.recieve();
        }
        if(dpadLeft2){
            outtake.recieve();
        }
        if(dpadRight2){
            outtake.recieve();
        }
        if(a2){
            int outtakePosition = (int) outtake.getPosition();
            switch (outtakePosition){
                case 0:
                    outtake.drop(); break;
                case 1:
                    outtake.recieve(); break;
            }
        }
        if(y2){
            turret.home();
        }
        if(x2){
            turret.left();
        }
        if(b2){
            turret.right();
        }
        if(rightBumper2){
            outtakeSlidePosition += 5;
            outtakeSlide.setPower(0.8);
            outtakeSlide.setTargetPosition(outtakeSlidePosition);
            outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(leftBumper2){
            outtakeSlidePosition -= 5;
            outtakeSlide.setPower(0.8);
            outtakeSlide.setTargetPosition(outtakeSlidePosition);
            outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(right_trigger2 > 0){
            turret.incrementer=true;
            turret.turretIncrease();
        }
        if(left_trigger2 > 0){
            turret.incrementer=true;
            turret.turretDecrease();
        }
        wheels.move(lefty,righty,leftx,rightx);
        telemetry.addData("outtake ", outtake.getPosition());
        telemetry.update();
    }
}}

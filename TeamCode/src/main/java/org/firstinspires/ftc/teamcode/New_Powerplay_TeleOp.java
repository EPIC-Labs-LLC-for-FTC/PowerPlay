package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.IntakeSlide2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Claw2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Outtake2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.OuttakeSlide2023;
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
    public Turret2023 turret;
    public IntakeSlide2023 slide;
    public OuttakeSlide2023 outtakeSlide;
    double intakeSlidePosition = 0;
    int outtakeSlidePosition = 0;
    boolean isDropped = false;
    @Override
    public void runOpMode() throws InterruptedException {
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);

        slide = new IntakeSlide2023(hardwareMap);
        slide.parent = this;
        slide.telemetry = telemetry;
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

        outtakeSlide = new OuttakeSlide2023(hardwareMap);
        outtakeSlide.parent = this;
        outtakeSlide.telemetry = this.telemetry;

        wheels.initialize();
        wheels.telemetry = telemetry;
        wheels.parent = this;
        wheels.leftErrorAdjustment = 0.72;
        wheels.rightErrorAdjustment = 0.72;


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

//        intakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        outtakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        intakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        outtakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        intakeSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        outtakeSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.in();
        arm.doInitialize();
        outtake.recieve();
        turret.setPosition(0.15);
        waitForStart();
        while (opModeIsActive()) {
            lefty = gamepad1.left_stick_y;
            leftx = gamepad1.left_stick_x;
            righty = gamepad1.right_stick_y;
            rightx = -gamepad1.right_stick_x;
            rightTrigger = gamepad1.right_trigger;
            leftTrigger = gamepad1.left_trigger;
            right_bumper = gamepad1.right_bumper;
            left_bumper = gamepad1.left_bumper;
            x = gamepad1.x;
            y = gamepad1.y;
            a = gamepad1.a;
            b = gamepad1.b;
            b2 = gamepad2.b;
            x2 = gamepad2.x;
            a2 = gamepad2.a;
            y2 = gamepad2.y;
            dpadUp2 = gamepad2.dpad_up;
            dpadRight2 = gamepad2.dpad_right;
            dpadLeft2 = gamepad2.dpad_left;
            rightBumper2 = gamepad2.right_bumper;
            leftBumper2 = gamepad2.left_bumper;
            right_trigger2 = gamepad2.right_trigger;
            left_trigger2 = gamepad2.left_trigger;

            if (rightTrigger > 0) {
                slide.intakeIncrease();
                sleep(500);
            }
            else if (leftTrigger > 0) {
                slide.intakeDecrease();
                sleep(500);
            }
            else if (right_bumper) {
                slide.out();
                arm.doGround();
            }
            else if (left_bumper) {
                slide.in();
                arm.doDropping();
            }
            else if (x) {
                finger.grab();
            }
            else if (y) {
                finger.release();
            }
            else if (a) {
                arm.doLevel4();
            }
            else if (b) {
                arm.doDropping();
            }
            if (dpadUp2) {
                finger.grab();
                sleep(500);
                slide.in();
                arm.doDropping();
                sleep(900);
                finger.release();
                sleep(1200);
                arm.doInitialize();
                sleep(500);
                outtakeSlide.set(1900);
            }
            else if (dpadLeft2) {
                finger.grab();
                sleep(500);
                slide.in();
                arm.doDropping();
                sleep(900);
                finger.release();
                sleep(900);
                arm.doInitialize();
                outtakeSlide.set(700);
            }
            else if (dpadRight2) {
                outtakeSlide.set(0);
                outtake.recieve();
            }
            else if (a2) {
                if (isDropped) {
                    outtake.recieve();
                    isDropped = false;
                } else {
                    outtake.drop();
                    isDropped = true;
                }
                sleep(500);
            }
            else if (y2) {
                turret.home();
            }
            else if (x2) {
                turret.right();
            }
            else if (b2) {
                turret.left();
            }
            else if (rightBumper2) {
                outtakeSlidePosition += 5;
                outtakeSlide.set(outtakeSlidePosition);
            }
            else if (leftBumper2) {
                outtakeSlidePosition -= 5;
                outtakeSlide.set(outtakeSlidePosition);
            }
            else if (right_trigger2 > 0) {
                turret.incrementer = true;
                turret.turretDecrease();
            }
            else if (left_trigger2 > 0) {
                turret.incrementer = true;
                turret.turretIncrease();
            }
            wheels.move(lefty, righty, leftx, rightx);
            telemetry.addData("turret ", turret.turret.getPosition());
            telemetry.addData("outtake slide ", OuttakeSlide2023.outtakeSlide.getCurrentPosition());

            telemetry.update();
            //for tall junction, slide value : 1480, turret value: 0.75
        }
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Claw2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Outtake2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.OuttakeSlide2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Turret2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.IntakeSlide2023;
@Disabled
@TeleOp(name = "new_Test_Auton")
public class new_Test_Auton extends LinearOpMode {
    //    OpenCvCamera webcam;
//    Scanner scanner;
    public Claw2023 finger;
    public Arm2023 arm;
    public Outtake2023 outtake;
    public Turret2023 turret;
    public IntakeSlide2023 slide;
    public OuttakeSlide2023 outtakeSlide;
    //    public DistanceSensor backDistance;
//    public DistanceSensor rightDistance;
    @Override
    public void runOpMode() throws InterruptedException {
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);
        wheels.parent = this;
        wheels.IsAutonomous = true;
        wheels.telemetry = this.telemetry;
        wheels.initialize();
        double intakepos = 0;
        double armpos = 0;
        outtakeSlide = new OuttakeSlide2023(hardwareMap);
        outtakeSlide.parent = this;
        outtakeSlide.telemetry = this.telemetry;
        slide = new IntakeSlide2023(hardwareMap);
        slide.parent = this;
        slide.telemetry = this.telemetry;
        finger = new Claw2023(hardwareMap);
        finger.parent = this;
        finger.telemetry = this.telemetry;
        arm = new Arm2023(hardwareMap);
        arm.parent = this;
        arm.telemetry = this.telemetry;
        arm.doInitialize();
        outtake = new Outtake2023(hardwareMap);
        outtake.parent = this;
        outtake.telemetry = this.telemetry;
        turret = new Turret2023(hardwareMap);
        turret.parent = this;
        turret.telemetry = this.telemetry;
        //arm.doDropping();
        slide.in();
        turret.leftAuton();
        outtake.recieve();
        waitForStart();
        boolean x1 = gamepad1.x;
        boolean y1 = gamepad1.y;
        boolean a1 = gamepad1.a;
        boolean b1 = gamepad1.b;
        boolean dpadup1 = gamepad1.dpad_up;
        boolean dpaddown1 = gamepad1.dpad_down;
        boolean dpadright1 = gamepad1.dpad_right;
        boolean dpadleft1 = gamepad1.dpad_left;
        boolean rightBumper1 = gamepad1.right_bumper;
        boolean leftBumper1 = gamepad1.left_bumper;
        boolean x2 = gamepad2.x;
        boolean y2 = gamepad2.y;
        boolean a2 = gamepad2.a;
        boolean b2 = gamepad2.b;
        boolean dpadup2 = gamepad2.dpad_up;
        boolean dpaddown2 = gamepad2.dpad_down;
        boolean dpadright2 = gamepad2.dpad_right;
        boolean dpadleft2 = gamepad2.dpad_left;
        boolean rightBumper2 = gamepad2.right_bumper;
        boolean leftBumper2 = gamepad2.left_bumper;
        while(opModeIsActive()){

            x1 = gamepad1.x;
            y1 = gamepad1.y;
            a1 = gamepad1.a;
            b1 = gamepad1.b;
            dpadup1 = gamepad1.dpad_up;
            dpaddown1 = gamepad1.dpad_down;
            dpadright1 = gamepad1.dpad_right;
            dpadleft1 = gamepad1.dpad_left;
            rightBumper1 = gamepad1.right_bumper;
            leftBumper1 = gamepad1.left_bumper;
            x2 = gamepad2.x;
            y2 = gamepad2.y;
            a2 = gamepad2.a;
            b2 = gamepad2.b;
            dpadup2 = gamepad2.dpad_up;
            dpaddown2 = gamepad2.dpad_down;
            dpadright2 = gamepad2.dpad_right;
            dpadleft2 = gamepad2.dpad_left;
            rightBumper2 = gamepad2.right_bumper;
            leftBumper2 = gamepad2.left_bumper;


            if(x1){
                outtakeSlide.set(2800);
                sleep(2000);

            }
            else if(b1){
                arm.doTransition();
                sleep(500);
                slide.outAuton();
                sleep(1000);
                arm.doLevel5();
                sleep(1000);
                finger.grab();
                sleep(500);
                arm.doTransition();
                sleep(500);
                slide.intakeSet(-0.5);
                sleep(750);
                arm.doGround();
                sleep(500);
                finger.release();
                sleep(500);
                finger.grab();
            }
            else if(y1){
                finger.grab();
            }
            else if(a1){
                finger.release();
            }
            if(dpadup1){
                outtake.drop();
                sleep(1000);
                outtake.recieve();
                outtakeSlide.set(0);
                sleep(2000);
            }
            else if(dpaddown1){
                arm.doTransition();
                sleep(1000);
                slide.in();
                sleep(1000);
                arm.doDropping();
                sleep(500);
            }
            if(a2){
                slide.outAuton();
                sleep(500);
                arm.doTransition();
                sleep(1000);
                arm.doLevel4();
                sleep(500);

            }
            if(x2){
                slide.outAuton();
                sleep(500);
                arm.doTransition();
                sleep(1000);
                arm.doLevel3();
                sleep(500);
            }
            if(b2){
                slide.outAuton();
                sleep(500);
                arm.doTransition();
                sleep(1000);
                arm.doLevel2();
                sleep(500);
            }
            if(y2){
                slide.outAuton();
                sleep(500);
                arm.doTransition();
                sleep(1000);
                arm.doGround();
                sleep(500);
            }
            telemetry.addData("armpos",arm.getPosition());
            telemetry.addData("intakeslide1pos", slide.intakeSlide1.getPosition());
            telemetry.addData("intakeslide2pos", slide.intakeSlide2.getPosition());
            telemetry.addData("outtake slide pos", outtakeSlide.getPosition());
            telemetry.addData("outtake slide target pos", outtakeSlide.getTargetPosition());
            telemetry.update();
        }



    }
    public void extend(){

        arm.doTransition();
        slide.out();
        outtakeSlide.set(2700);
        finger.release();
        sleep(2000);
    }
    public void retract(){

        arm.doTransition();
        sleep(500);
        outtakeSlide.set(0);
        slide.in();
        sleep(1500);
        arm.doInitialize();
        sleep(500);
        arm.doDropping();
        finger.release();
    }

}


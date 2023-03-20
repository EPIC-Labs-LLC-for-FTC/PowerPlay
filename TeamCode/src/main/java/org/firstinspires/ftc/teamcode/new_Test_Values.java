package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Claw2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Outtake2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Turret2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.IntakeSlide2023;

@TeleOp(name = "new_Test_Values")
public class new_Test_Values extends LinearOpMode {
    //    OpenCvCamera webcam;
//    Scanner scanner;
    public Claw2023 finger;
    public Arm2023 arm;
    public Outtake2023 outtake;
    public Turret2023 turret;
    public IntakeSlide2023 slide;
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
        slide = new IntakeSlide2023(hardwareMap);
        slide.parent = this;
        slide.telemetry = this.telemetry;
        finger = new Claw2023(hardwareMap);
        finger.parent = this;
        finger.telemetry = this.telemetry;
        arm = new Arm2023(hardwareMap);
        arm.parent = this;
        arm.telemetry = this.telemetry;
        outtake = new Outtake2023(hardwareMap);
        outtake.parent = this;
        outtake.telemetry = this.telemetry;
        turret = new Turret2023(hardwareMap);
        turret.parent = this;
        turret.telemetry = this.telemetry;
        arm.doDropping();
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
        while(opModeIsActive()){

            x1 = gamepad1.x;
            y1 = gamepad1.y;
            a1 = gamepad1.a;
            b1 = gamepad1.b;
            dpadup1 = gamepad1.dpad_up;
            dpaddown1 = gamepad1.dpad_down;
            if(x1){
                intakepos+=0.01;
                slide.intakeSet(intakepos);
                sleep(1000);
            }
            else if(b1){
                intakepos-=0.01;
                slide.intakeSet(intakepos);
                sleep(1000);
            }
            else if(y1){
                armpos+=0.01;
                arm.armset(armpos);
                sleep(1000);
            }
            else if(a1){
                armpos-=0.01;
                arm.armset(armpos);
                sleep(1000);
            }
            if(dpadup1){
                finger.release();
                sleep((1000));
            }
            else if(dpaddown1){
                finger.grab();
                sleep(1000);
            }
            telemetry.addData("armpos",arm.getPosition());
            telemetry.addData("intakeslide1pos", slide.intakeSlide1.getPosition());
            telemetry.addData("intakeslide2pos", slide.intakeSlide2.getPosition());
            telemetry.update();
        }



    }

}


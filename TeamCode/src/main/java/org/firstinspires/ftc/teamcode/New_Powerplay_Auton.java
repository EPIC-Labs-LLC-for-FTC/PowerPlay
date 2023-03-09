package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Claw2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Outtake2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Scanner;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Slide2023;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Turret2023;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "new_Powerplay_Auton")
public class New_Powerplay_Auton extends LinearOpMode {
    OpenCvCamera webcam;
    Scanner scanner;
    public Claw2023 finger;
    public Arm2023 arm;
    public Outtake2023 outtake;
    public Turret2023 turret;
    public Slide2023 slide;
    public DistanceSensor backDistance;
    public DistanceSensor rightDistance;
    @Override
    public void runOpMode() throws InterruptedException {
        scanner = new Scanner(hardwareMap);
        scanner.telemetry = this.telemetry;
        scanner.parent = this;
        scanner.initialize();
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);
        wheels.parent = this;
        wheels.IsAutonomous = true;
        wheels.telemetry = this.telemetry;
        wheels.initialize();

        backDistance = hardwareMap.get(DistanceSensor.class, "backDistance");
        rightDistance = hardwareMap.get(DistanceSensor.class, "rightDistance");
        slide = new Slide2023(hardwareMap);
        finger = new Claw2023(hardwareMap);
        arm = new Arm2023(hardwareMap);
        outtake = new Outtake2023(hardwareMap);
        turret = new Turret2023(hardwareMap);
        int parkingSpot = 0;
        parkingSpot = scanner.getParkingSpot();
        scanner.releaseCamera();
        waitForStart();
            wheels.encoderDrive(0.8,   -30, 30, 30, -30, 3);

            turret.left();
            sleep(500);
            //preload
            slide.outtakeSet(20);
            outtake.drop();
            sleep(1500);
            outtake.recieve();
            slide.outtakeSet(0);
            sleep(1000);
            //first cycle
            arm.level5();
            slide.intakeSet(35);
            sleep(1000);
            finger.grab();
            sleep(600);
            arm.dropping();
            slide.intakeSet(0);
            sleep(1000);
            slide.outtakeSet(20);
            outtake.drop();
            sleep(1500);
            outtake.recieve();
            slide.outtakeSet(0);
            sleep(1000);
            //second cycle
            arm.level4();
            slide.intakeSet(35);
            sleep(1000);
            finger.grab();
            sleep(600);
            arm.dropping();
            slide.intakeSet(0);
            sleep(1000);
            slide.outtakeSet(20);
            outtake.drop();
            sleep(1500);
            outtake.recieve();
            slide.outtakeSet(0);
            sleep(1000);
            //third cycle
            arm.level3();
            slide.intakeSet(35);
            sleep(1000);
            finger.grab();
            sleep(600);
            arm.dropping();
            slide.intakeSet(0);
            sleep(1000);
            slide.outtakeSet(20);
            outtake.drop();
            sleep(1500);
            outtake.recieve();
            slide.outtakeSet(0);
            sleep(1000);
            //fourth cycle
            arm.level2();
            slide.intakeSet(35);
            sleep(1000);
            finger.grab();
            sleep(600);
            arm.dropping();
            slide.intakeSet(0);
            sleep(1000);
            slide.outtakeSet(20);
            outtake.drop();
            sleep(1500);
            outtake.recieve();
            slide.outtakeSet(0);
            sleep(1000);
            //last cycle
            arm.ground();
            slide.intakeSet(35);
            sleep(1000);
            finger.grab();
            sleep(600);
            arm.dropping();
            slide.intakeSet(0);
            sleep(1000);


        }

       //arm.level5();
        //outtakeSlide.setPower(0.8);
        //outtakeSlide.setTargetPosition(-1300);
        //outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //finger.grab();
    }


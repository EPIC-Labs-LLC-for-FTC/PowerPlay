package org.firstinspires.ftc.teamcode.drive.opmode;

import android.transition.Slide;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;




import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.RobotObjects.EPIC.ScanSleeve;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Claw;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Slide_Control;

@Autonomous (name="EXP_RR_Auto_Blue_Right")
public class EXP_RR_Auto_Blue_Right extends LinearOpMode {

    ScanSleeve scanner;


    @Override
    public void runOpMode() {
        double liftPower = 0.6;
        int armPosition = 0;
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        DcMotorEx arm = hardwareMap.get(DcMotorEx.class, "Arm");
        Servo grab = hardwareMap.get(Servo.class, "Grab");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setTargetPosition(0);
        grab.setPosition(0);

        Slide_Control slideControl = new Slide_Control(hardwareMap);
        Claw armClaw = new Claw(hardwareMap);

        slideControl.initialize();
        armClaw.initialize();

        scanner = new ScanSleeve(hardwareMap);
        scanner.telemetry = this.telemetry;
        scanner.parent = this;
        scanner.initialize();

        int parkingSpot = 0;
        parkingSpot = scanner.getParkingSpot();

        scanner.releaseCamera();


        waitForStart();
        TrajectorySequence turnAndStrafe = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(5)
                .turn(Math.toRadians(100))
                .waitSeconds(0.5)
                .strafeRight(40)
                .waitSeconds(0.5)
                .build();


        TrajectorySequence parking1 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(20)
                .waitSeconds(0.5)
                .back(20)
                .build();

        TrajectorySequence parking2 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(20)
                .waitSeconds(0.5)
                .build();

        TrajectorySequence parking3 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(20)
                .waitSeconds(0.5)
                .forward(20)
                .build();


        waitForStart();

        if(isStopRequested()) return;


        drive.followTrajectorySequence(turnAndStrafe);

        sleep(500);

        int i = 0;
        while(i<5){
            armClaw.specificLift(-1);
            sleep(500);
            i++;
        }

        armClaw.stop();
        sleep(100);
        armClaw.open();
        sleep(500);
        armClaw.close();
        sleep(100);

        if (parkingSpot == 1){
            drive.followTrajectorySequence(parking1);
        } else if (parkingSpot == 2) {
            drive.followTrajectorySequence(parking2);
        } else {
            drive.followTrajectorySequence(parking3);
        }

        scanner.releaseCamera();


    }
}

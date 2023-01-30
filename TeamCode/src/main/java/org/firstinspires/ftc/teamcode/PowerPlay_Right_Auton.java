package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Slider;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Scanner;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "Powerplay Right Auton")

public class PowerPlay_Right_Auton extends LinearOpMode {

    OpenCvCamera webcam;
    Scanner scanner;
    public Slider slide1;

    public Servo arm1;
    public Servo arm2;
    public Servo outtakeArm;
    public Servo finger1;
    public Servo finger2;
    public Servo wrist1;
    public Servo wrist2;


    public void runOpMode() throws InterruptedException {

        scanner = new Scanner(hardwareMap);
        scanner.telemetry = this.telemetry;
        scanner.parent = this;
        scanner.initialize();
        slide1 = new Slider(hardwareMap);
        slide1.parent = this;
        slide1.telemetry = telemetry;
        slide1.initialize();
        int parkingSpot = 0;
        parkingSpot = scanner.getParkingSpot();
        scanner.releaseCamera();


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                .strafeRight(61)
                .back(4)
                .build();
        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(new Pose2d(-0.7, -61, Math.toRadians(0)))
                .forward(3.3)
                .strafeLeft(11.5)
                .back(19.75)
                .build();
        TrajectorySequence traj3 = drive.trajectorySequenceBuilder(new Pose2d(-17, -49.5, Math.toRadians(0)))
                .forward(18.5)
                .strafeRight(12)
                .build();
        TrajectorySequence parkingSpot1 = drive.trajectorySequenceBuilder(new Pose2d(-0.7, -61, Math.toRadians(0)))
                .strafeLeft(13.15)
                .back(22)
                .build();
        TrajectorySequence parkingSpot2 = drive.trajectorySequenceBuilder(new Pose2d(-0.7, -61, Math.toRadians(0)))
                .strafeLeft(13.15)
                .build();
        TrajectorySequence parkingSpot3 = drive.trajectorySequenceBuilder(new Pose2d(-0.7, -61, Math.toRadians(0)))
                .strafeLeft(13.15)
                .forward(22)
                .build();




        waitForStart();
        drive.followTrajectorySequence(traj1);
        slide1.dropCone(0.21,0.25);
        drive.followTrajectorySequence(traj2);
        slide1.pickUpCone();
        drive.followTrajectorySequence(traj3);
        sleep(1000);
        slide1.dropCone(0.439,0.64);
        if(parkingSpot == 1){
            drive.followTrajectorySequence(parkingSpot3);
        }
        if(parkingSpot == 2){
            drive.followTrajectorySequence(parkingSpot2);
        }
        if(parkingSpot == 3){
            drive.followTrajectorySequence(parkingSpot1);
        }


}}

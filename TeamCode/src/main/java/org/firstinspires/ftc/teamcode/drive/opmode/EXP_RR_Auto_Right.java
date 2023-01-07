package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.RobotObjects.EPIC.ScanSleeve;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Claw;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Slide_Control;

@Autonomous (name="EXP_RR_Auto_Right")
public class EXP_RR_Auto_Right extends LinearOpMode {

    ScanSleeve scanner;
    Claw arm = new Claw((hardwareMap));

    public void raiseArm(){
        arm.specificLift(-0.5);
        sleep(5400);
        arm.stop();
    }


    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Slide_Control slideControl = new Slide_Control(hardwareMap);
        Claw armClaw = new Claw(hardwareMap);

        slideControl.initialize();
        armClaw.initialize();
        arm.initialize();

        scanner = new ScanSleeve(hardwareMap);
        scanner.telemetry = this.telemetry;
        scanner.parent = this;
        scanner.initialize();

        int parkingSpot = 1;
        parkingSpot = scanner.getParkingSpot();

        scanner.releaseCamera();


        waitForStart();
        TrajectorySequence turnAndStrafe = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(8)
                .turn(Math.toRadians(106))
                .waitSeconds(0.01)
                .strafeRight(70)
                .waitSeconds(0.01)
                .strafeLeft(14)
                .waitSeconds(0.01)
                .build();

        TrajectorySequence moveForward = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(8)
                .build();

        TrajectorySequence moveBackward = drive.trajectorySequenceBuilder(new Pose2d())
                .back(9)
                .build();


        TrajectorySequence parking1 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(50)
                .waitSeconds(0.01)
                .strafeLeft(30)
                .forward(28)
                .build();

        TrajectorySequence parking2 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(50)
                .waitSeconds(0.01)
                .strafeLeft(30)
                .waitSeconds(0.01)
                .build();

        TrajectorySequence parking3 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(25)
                .waitSeconds(0.01)
                .back(28)
                .build();


        waitForStart();

        if(isStopRequested()) return;

        sleep(1);

        drive.followTrajectorySequence(turnAndStrafe);

        sleep(1);

        drive.followTrajectorySequence(moveForward);

        sleep(2500);
        armClaw.open();
        sleep(500);
        armClaw.close();
        sleep(1);

        drive.followTrajectorySequence(moveBackward);
        sleep(1);

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

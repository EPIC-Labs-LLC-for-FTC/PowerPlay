package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Claw;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Slide_Control;
import org.firstinspires.ftc.teamcode.drive.opmode.RobotObjects.EPIC.ScanSleeve;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous (name="EXP_RR_Auto_Left")
public class EXP_RR_Auto_Left extends LinearOpMode {

    ScanSleeve scanner;


    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Slide_Control slideControl = new Slide_Control(hardwareMap);
        Claw armClaw = new Claw(hardwareMap);

        slideControl.initialize();
        armClaw.initialize();

//        scanner = new ScanSleeve(hardwareMap);
//        scanner.telemetry = this.telemetry;
//        scanner.parent = this;
//        scanner.initialize();
//
//        int parkingSpot = 0;
//        parkingSpot = scanner.getParkingSpot();
//
//        scanner.releaseCamera();


        waitForStart();
        TrajectorySequence strafe = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(54)
                .build();

       TrajectorySequence strafeMore = drive.trajectorySequenceBuilder(new Pose2d())
               .strafeRight(20)
               .build();

//         TrajectorySequence moveBackward = drive.trajectorySequenceBuilder(new Pose2d())
//                 .back(9)
//                 .build();


        TrajectorySequence parking1 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(25)
                .waitSeconds(0.5)
                .back(28)
                .build();

        TrajectorySequence parking2 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(47)
                .waitSeconds(0.5)
                .strafeRight(30)
                .waitSeconds(0.5)
                .build();

        TrajectorySequence parking3 = drive.trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(46)
                .waitSeconds(0.5)
                .strafeRight(25)
                .waitSeconds(0.5)
                .forward(28)
                .build();


        waitForStart();

        if(isStopRequested()) return;

        armClaw.specificLift(0.4);
        sleep(1000);
        armClaw.stop();
        drive.followTrajectorySequence(strafe);
        armClaw.open();
        sleep(500);
        armClaw.close();
        drive.followTrajectorySequence(strafeMore);
        armClaw.specificLift(-0.4);
        slideControl.specificLift(0.5);
        sleep(2000);
        armClaw.stop();
        slideControl.stop();

        if(isStopRequested()) return;



//        if (parkingSpot == 1){
//            drive.followTrajectorySequence(parking1);
//        } else if (parkingSpot == 2) {
//            drive.followTrajectorySequence(parking2);
//        } else {
//            drive.followTrajectorySequence(parking3);
//        }


//        scanner.releaseCamera();


    }
}

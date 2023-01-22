package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "RoadrunnerTest")

public class RoadrunnerTest extends LinearOpMode {

    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                .forward(30)
                .splineTo(new Vector2d(54,3.8),Math.toRadians(45))
                .setReversed(true)
                .splineTo(new Vector2d(51,-19.5),Math.toRadians(270 ))
                .build();

        TrajectorySequence cycle =drive.trajectorySequenceBuilder(new Pose2d(30,-6,Math.toRadians(0)))
                .setReversed(true)
                .splineTo(new Vector2d(52, -12.2),Math.toRadians(0))
                .setReversed(false)
                .splineTo(new Vector2d(30,-6),Math.toRadians(145))
                .build();

        waitForStart();
        drive.followTrajectorySequence(traj1);







    }

}

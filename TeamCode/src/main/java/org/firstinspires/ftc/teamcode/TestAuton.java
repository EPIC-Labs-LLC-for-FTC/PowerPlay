package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "TestAuton")

public class TestAuton extends LinearOpMode {

    public DcMotorEx slide1;
    public DcMotorEx slide2;
    public Servo arm1;
    public Servo arm2;
    public Servo outtakeArm;
    public Servo finger1;
    public Servo finger2;
    public Servo wrist1;
    public Servo wrist2;
    @Override
    public void runOpMode() throws InterruptedException {
        finger1 = hardwareMap.get(Servo.class, "finger1");
        finger2 = hardwareMap.get(Servo.class, "finger2");
        wrist1 = hardwareMap.get(Servo.class, "wrist1");
        wrist2 = hardwareMap.get(Servo.class, "wrist2");
        outtakeArm= hardwareMap.get(Servo.class, "outtakeArm");
        arm1 = hardwareMap.get(Servo.class,"arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");
        slide1 = hardwareMap.get(DcMotorEx.class, "slide1");
        slide2 = hardwareMap.get(DcMotorEx.class, "slide2");
        slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeArm.setPosition(0);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        waitForStart();
        drive.setMotorPowers(0.8,0.7,0.8,0.8);

        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                .forward(5.8)
                .strafeLeft(34)




//                .splineTo(new Vector2d(-30,6),Math.toRadians(-35))
                .build();
        TrajectorySequence raiseSlide = drive.trajectorySequenceBuilder((new Pose2d(0,0,Math.toRadians(0))))
                .turn(1)
                .back(4)
                .addDisplacementMarker(() -> {
                    outtakeArm.setPosition(0.55);
                })
                .addDisplacementMarker(() -> {
                    raiseSlide(2450);
                })
                .forward(1)

                .build();

        TrajectorySequence cycle =drive.trajectorySequenceBuilder(new Pose2d(-30,6,Math.toRadians(-35)))
                .setReversed(true)
                .splineTo(new Vector2d(-60, 12.2),Math.toRadians(-180))
                .setReversed(false)
                .splineTo(new Vector2d(-30,6),Math.toRadians(-35))
                .build();


        drive.followTrajectorySequence(traj1);
        drive.followTrajectorySequence(raiseSlide);

//        for (int i = 0; i < 5; i++) {
//            drive.followTrajectorySequence(cycle);
//        }





    }
    private void raiseSlide(int index){
        slide1.setPower(0.8);
        slide2.setPower(0.8);
        slide1.setTargetPosition(index);
        slide2.setTargetPosition(index);
        slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}

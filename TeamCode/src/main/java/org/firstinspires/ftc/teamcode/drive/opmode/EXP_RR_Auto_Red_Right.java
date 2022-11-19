package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.atomic.AtomicInteger;

@Autonomous (name="EXP_RR_Auto_Red_Right")
public class EXP_RR_Auto_Red_Right extends LinearOpMode {
    @Override


        public void runOpMode() {
        double liftPower = 0.75;
        AtomicInteger armPosition = new AtomicInteger();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        DcMotorEx arm = hardwareMap.get(DcMotorEx.class, "Arm");
        Servo grab = hardwareMap.get(Servo.class, "Grab");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setTargetPosition(0);
        grab.setPosition(0);

        waitForStart();
        TrajectorySequence EXP_Auto_Red_Right = drive.trajectorySequenceBuilder(new Pose2d())
                .addDisplacementMarker(() -> {
                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    arm.setTargetPosition(465);
                    while (arm.getCurrentPosition() < arm.getTargetPosition()) {
                        arm.setTargetPosition(465);
                        arm.setPower(0.6);
                    }
                })
                .waitSeconds(0.5)
                .strafeRight(25)
                .addDisplacementMarker(() -> { {
                    grab.setPosition(0.5);
                }
                })
                .waitSeconds(0.5)
                .strafeRight(25)
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(EXP_Auto_Red_Right);
//            while (arm.getCurrentPosition() < armPosition) {
//                armPosition = 205;
//                arm.setTargetPosition(armPosition);
//                arm.setPower(liftPower);
//                sleep(400);
//                telemetry.addData("current pos: ", arm.getCurrentPosition());
//                telemetry.addData("target pos: ", arm.getTargetPosition());
//                telemetry.update();
//            }

    }
}

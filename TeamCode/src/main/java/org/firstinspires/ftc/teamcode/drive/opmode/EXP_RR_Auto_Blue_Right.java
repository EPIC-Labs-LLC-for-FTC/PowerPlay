package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous (name="EXP_RR_Auto_Blue_Right")
public class EXP_RR_Auto_Blue_Right extends LinearOpMode {
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

        waitForStart();
        TrajectorySequence EXP_Auto_Blue_Right = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(15)
                .turn(Math.toRadians(110))
                .strafeRight(42)
                .strafeLeft(7)
                .forward(27)
                .turn(Math.toRadians(115))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(EXP_Auto_Blue_Right);
//        while (arm.getCurrentPosition() < armPosition) {
//            armPosition = 205;
//            arm.setTargetPosition(armPosition);
//            arm.setPower(liftPower);
//            sleep(300);
//        }
    }
}

package org.firstinspires.ftc.teamcode.drive.opmode;

import android.transition.Slide;

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
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Claw;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Slide_Control;

@Autonomous (name="EXP_RR_Auto_Blue_Right")
public class EXP_RR_Auto_Blue_Right extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        
        Slide_Control slideControl = new Slide_Control(hardwareMap);
        slideControl.initialize();

        waitForStart();
        TrajectorySequence EXP_Auto_Blue_Right = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(36)
                .turn(Math.toRadians(-60))
                .forward(10)
                .addDisplacementMarker(46,() -> {
                    slideControl.lift(2);
                })
                .turn(Math.toRadians(-90))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        slideControl.retract();
        drive.followTrajectorySequence(EXP_Auto_Blue_Right);

    }
}

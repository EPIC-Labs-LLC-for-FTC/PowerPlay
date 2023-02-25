package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Claw;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Slide_Control;


@TeleOp(name="EXP_Test")

public class EXP_Test extends LinearOpMode {
    //Configuration used: 4wheelConfig
//    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
//        FtcDashboard dashboard = FtcDashboard.getInstance();
//        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        Claw armClaw = new Claw(hardwareMap);
        Slide_Control slider = new Slide_Control(hardwareMap);
        slider.initialize();
        armClaw.initialize();
        int tar = 0;
        waitForStart();
        while(opModeIsActive()){
            boolean x = gamepad1.x;
            boolean y = gamepad1.y;
            boolean a = gamepad1.a;
            boolean b = gamepad1.b;

            if(x){
                slider.specificLift(0.3);
            } else if (y) {
                slider.specificLift(-0.3);
            } else if (a){
                slider.lift(3);
                tar = 3;
            } else if (b){
                slider.lift(4);
                tar = 4;
            }

            armClaw.specificLift(0.4);
            sleep(1000);
            armClaw.stop();
            armClaw.open();
            sleep(500);
            armClaw.close();
            armClaw.specificLift(-0.4);
            slider.specificLift(0.3);
            sleep(1500);
            slider.specificLift(-0.6);
            sleep(1500);
            armClaw.stop();
            slider.stop();

            telemetry.addData("Left Distance: ", armClaw.getDistance(armClaw.leftDistance));
            telemetry.addData("Right Distance: ", armClaw.getDistance(armClaw.rightDistance));
            telemetry.addData("Front2 Distance: ", armClaw.getDistance(armClaw.front2Distance));
            telemetry.addData("Front Distance: ", armClaw.getDistance(armClaw.frontDistance));
            telemetry.addData("Target: ", tar);
            telemetry.addData("Slider Target: ", slider.sliderPosition());
            telemetry.update();

        }



    }

}
package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Claw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftSlider;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.NewClaw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.TiltSlider;

@TeleOp(name="Test New Components", group="Research")

//@Disabled

public class TestNewComponents extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    LazySusan lazy = null;
    NewClaw claw = null;
    LiftSlider slider = null;
    TiltSlider pankit = null;
    int sliderPosition = 4300;
    int panPosition = 0;
    boolean x2;
    boolean b2;
    boolean a2;
    boolean y2;
    boolean dpadDown = false;
    boolean dpadLeft = false;
    boolean dpadUp = false;
    boolean dpadRight = false;
    boolean leftbumber2 = false;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        lazy = new LazySusan(hardwareMap);
        lazy.parent = this;
        lazy.telemetry = telemetry;
        lazy.initialize();

        claw = new NewClaw(hardwareMap);
        claw.parent = this;
        claw.telemetry = telemetry;
        claw.initialize(0);

        slider = new LiftSlider(hardwareMap);
        slider.parent = this;
        slider.telemetry = telemetry;
        slider.initialize2();



        pankit = new TiltSlider(hardwareMap);
        pankit.parent = this;
        pankit.telemetry = telemetry;
        pankit.initialize();
        slider.setPower(0.3);
        sleep(2000);
        slider.setPower(0);
        telemetry.addData("position", lazy.getPosition());
        telemetry.update();
        waitForStart();
        double position = 0.0;
        while(opModeIsActive()){
            leftbumber2 = gamepad2.left_bumper;
            x2 = gamepad2.x;
            y2 = gamepad2.y;
            b2 = gamepad2.b;
            a2 = gamepad2.a;
            dpadDown = gamepad2.dpad_down;
            dpadLeft = gamepad2.dpad_left;
            dpadUp = gamepad2.dpad_up;
            dpadRight = gamepad2.dpad_right;
            if(dpadLeft){
                position = 0.25;

                lazy.rotate(position);
            }
            else if (dpadRight){
                position = 0.85;
                lazy.rotate(position);
            }
            else if(dpadUp){
//                position =0.5;
//                lazy.rotate(position);
                panPosition-=100;
                pankit.extendTicks(0.6,panPosition);
            }
            else if(leftbumber2){

                panPosition+=100;
                pankit.extendTicks(0.6,panPosition);
            }
            else if(dpadDown){
                position = 0;
                lazy.rotate(position);
            }
            else if(x2){
                position -= 0.1;
                lazy.rotate(position);
            }
            else if (y2){
                //position += 0.05;
                pankit.extendTicks(0.6,200);
            }
            else if(b2){
                //position +=0.1;

                claw.release();
            }
            else if(a2){
                //position -=0.01;
                slider.extendTicks(0.4,sliderPosition);
            }
//            lazy.rotate(0.15);
//            sleep(1000);
//            telemetry.addData("position", lazy.getPosition());
//            telemetry.update();
            telemetry.addData("position", lazy.getPosition());
            telemetry.update();
            sleep(1000);
        }

    }
}

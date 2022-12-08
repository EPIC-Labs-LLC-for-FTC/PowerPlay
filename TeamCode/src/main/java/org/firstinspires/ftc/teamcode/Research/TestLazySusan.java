package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;

@TeleOp(name="Test LazySu", group="Research")

//@Disabled

public class TestLazySusan extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    LazySusan lazy = null;

    boolean x2;
    boolean b2;
    boolean a2;
    boolean y2;
    boolean dpadDown = false;
    boolean dpadLeft = false;
    boolean dpadUp = false;
    boolean dpadRight = false;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        lazy = new LazySusan(hardwareMap);
        lazy.parent = this;
        lazy.telemetry = telemetry;
        lazy.initialize();


        telemetry.addData("position", lazy.getPosition());
        telemetry.update();
        waitForStart();
        double position = 0.27;
        while(opModeIsActive()){

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
            }
            else if (dpadRight){
                position = 0.75;
            }
            else if(dpadUp){
                position =0.5;
            }
            else if(dpadDown){
                position = 0;
            }
            else if(x2){
                position -= 0.1;
            }
            else if (y2){
                position += 0.05;
            }
            else if(b2){
                position +=0.1;
            }
            else if(a2){
                position -=0.01;
            }
//            lazy.rotate(0.15);
//            sleep(1000);
//            telemetry.addData("position", lazy.getPosition());
//            telemetry.update();
            lazy.rotate(position);
            telemetry.addData("position", lazy.getPosition());
            telemetry.update();
            sleep(1000);
        }

    }
}

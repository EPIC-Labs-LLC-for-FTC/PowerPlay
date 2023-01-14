package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftArm;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftSlider;

@TeleOp(name="Test Lift Slider", group="Research")
//@Disabled
public class TestLiftSlider extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    LiftSlider liftArm = null;

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

        liftArm = new LiftSlider(hardwareMap);
        liftArm.parent = this;
        liftArm.telemetry = telemetry;
        liftArm.initialize2();
        boolean isPosition = true;

        telemetry.addData("position", liftArm.getCurrentPosition());
        telemetry.update();
        waitForStart();

        int position = 4300;
        while(opModeIsActive()){
            isPosition = true;
            x2 = gamepad2.x;
            y2 = gamepad2.y;
            b2 = gamepad2.b;
            a2 = gamepad2.a;
            dpadDown = gamepad2.dpad_down;
            dpadLeft = gamepad2.dpad_left;
            dpadUp = gamepad2.dpad_up;
            dpadRight = gamepad2.dpad_right;
            if(dpadLeft){
                position = 2;
                isPosition = false;
            }
            else if(dpadUp){
                position =3;
                isPosition = false;
            }
            else if (dpadRight){
                position = 4;
                isPosition = false;
            }
            else if(dpadDown){
                position = 1;
                isPosition = false;
            }
            else if(x2){
                position += 50;

            }
            else if (y2){
                position += 100;
            }
            else if(b2){
                position -=25;
            }
            else if(a2){
                position -=10;
            }
            if(!isPosition) {
                liftArm.liftEncoder(0.3, position);
            }
            else
            {
                liftArm.extendTicks(0.3,position);
            }
            telemetry.addData("position", liftArm.getCurrentPosition());
            telemetry.update();
            sleep(1000);

        }

    }
}

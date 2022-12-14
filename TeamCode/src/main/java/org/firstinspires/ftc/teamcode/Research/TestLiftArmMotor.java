package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftArm;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftArmMotor;

@TeleOp(name="Test Motor Lift Arm", group="Research")
//@Disabled
public class TestLiftArmMotor extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    LiftArmMotor liftArm = null;

    boolean x2;
    boolean b2;
    boolean a2;
    boolean y2;
    boolean dpadDown = false;
    boolean dpadLeft = false;
    boolean dpadUp = false;
    boolean dpadRight = false;
    int level = -1;
    double power = 0.6;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        liftArm = new LiftArmMotor(hardwareMap);
        liftArm.parent = this;
        liftArm.telemetry = telemetry;
        liftArm.initialize2();


        telemetry.addData("position", liftArm.getCurrentPosition());
        telemetry.update();
        waitForStart();

        int position = 45;
        boolean isLevel = false;
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
                level = 1;
                isLevel = true;
            }
            else if (dpadRight){
                level = 3;
                isLevel = true;
            }
            else if(dpadUp){
                level = 2;
                isLevel = true;
            }
            else if(dpadDown){
                level = 0;
                isLevel = true;
            }
            else if(x2){
                position -= 50;
            }
            else if (y2){
                position += 100;
            }
            else if(b2){
                position +=500;
            }
            else if(a2){
                position -=100;
            }

            if(isLevel)
                liftArm.liftEncoder(power,level);
            else
                liftArm.extendTicks(power,position);
            telemetry.addData("position", liftArm.getCurrentPosition());
            telemetry.update();
            sleep(1000);

        }

    }
}

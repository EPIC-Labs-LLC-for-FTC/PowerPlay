package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="FinalTeleop")

@Disabled
public class Finalteleop extends LinearOpMode {
    private DcMotor Frontright;
    private DcMotor Frontleft;
    private DcMotor Backright;
    private DcMotor Backleft;
    private DcMotor MiddleLeft;
    private DcMotor MiddleRight;

    public void runOpMode()  throws InterruptedException {


        Frontright=hardwareMap.get(DcMotor.class,"Frontright");
        Frontleft=hardwareMap.get(DcMotor.class,"Frontleft");
        Backright=hardwareMap.get(DcMotor.class,"Backright");
        Backleft=hardwareMap.get(DcMotor.class,"Backleft");
        MiddleRight=hardwareMap.get(DcMotor.class,"Middleright");
        MiddleLeft=hardwareMap.get(DcMotor.class, "Middleleft");

        Frontright.setDirection(DcMotorSimple.Direction.REVERSE);
        Backright.setDirection(DcMotorSimple.Direction.REVERSE);
        MiddleRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            //double reset = 0;
            double lefty = gamepad1.left_stick_y;
            double leftx = gamepad1.left_stick_x;
            double righty = gamepad1.right_stick_y;
            double rightx = gamepad1.right_stick_x;
            boolean y =  gamepad1.y;


            Frontright.setPower(lefty - rightx - leftx);
            Frontleft.setPower(lefty + rightx + leftx);
            Backright.setPower(lefty - rightx + leftx);
            Backleft.setPower(lefty + rightx - leftx);
            MiddleRight.setPower(lefty - rightx);
            MiddleLeft.setPower(lefty + rightx);


            if (y==true) {
                MiddleRight.setPower(lefty);
                MiddleLeft.setPower(lefty);
            }
                else{
                    
                }
            }


            extend_collpse();
        }


    public void  extend_collpse() {

        boolean dpadright = gamepad1.dpad_right;
        boolean dpadleft = gamepad1.dpad_left;

        if (dpadright == true) {
            Frontleft.setPower(1);
            Backleft.setPower(-1);

        }

        if (dpadleft==true) {
            Frontleft.setPower(-1);
            Backleft.setPower(1);
        }

    }

}

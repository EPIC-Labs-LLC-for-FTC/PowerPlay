package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;



@TeleOp(name = "EXP_TeleOp")
public class EXP_TeleOp extends LinearOpMode {
    //Configuration used: EPIC4Wheel
    Mecanum_Wheels wheels;
    double lefty = 0.0;
    double lefty2 = 0.0;
    double leftx = 0.0;
    double righty = 0.0;
    double rightx = 0.0;
    double speed = 1.0;

    double pos = 0.0;
    double liftPower = 0.6;
    double breakPower = 0.1;
    int armPosition = 0;
    boolean slow = false;

    private static void logGamepad(Telemetry telemetry, Gamepad gamepad, String prefix) {
        telemetry.addData(prefix + "Synthetic",
                gamepad.getGamepadId() == Gamepad.ID_UNASSOCIATED);
        for (Field field : gamepad.getClass().getFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;

            try {
                telemetry.addData(prefix + field.getName(), field.get(gamepad));
            } catch (IllegalAccessException e) {
            }
        }
    }

    private void liftArm(int level){

    }

    @Override
    public void runOpMode() throws InterruptedException {

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        // Hardware Mapping

        DcMotorEx frontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
        DcMotorEx frontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        DcMotorEx backRight = hardwareMap.get(DcMotorEx.class, "BackRight");
        DcMotorEx backLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");
        Servo grab = hardwareMap.get(Servo.class, "Grab");
        DcMotorEx arm = hardwareMap.get(DcMotorEx.class, "Arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setTargetPosition(0);
        arm.setPower(breakPower);


        grab.setPosition(0);

        waitForStart();

        //claw.initiateLift();
        while (opModeIsActive()) {

            lefty = -gamepad1.left_stick_y;
            leftx =-gamepad1.left_stick_x;
            righty = gamepad1.right_stick_y;
            rightx = -gamepad1.right_stick_x;



            //liftPower = gamepad2.right_stick_y;

            lefty2 = -gamepad2.left_stick_y;

            boolean b = gamepad1.b;
            boolean x = gamepad1.x;
            boolean y = gamepad1.y;
            boolean a = gamepad1.a;

            boolean a2 = gamepad2.a;
            boolean b2 = gamepad2.b;
            boolean y2 = gamepad2.y;
            boolean x2 = gamepad2.x;
            boolean dpad_down2 = gamepad2.dpad_down;
            boolean dpad_left2 = gamepad2.dpad_left;
            boolean dpad_up2 = gamepad2.dpad_up;
            boolean dpad_right2 = gamepad2.dpad_right;
            if(a) {
                if (slow == false) {
                        speed = 0.6;
                        slow = true;
                        sleep(250);
                    }
                } else if (slow == true) {
                        speed = 1;
                        slow = false;
                        sleep(250);
                }


                    if (Math.abs(lefty) > Math.abs(leftx)) {
                        frontRight.setPower(lefty*speed);
                        frontLeft.setPower(-lefty * speed);
                        backLeft.setPower(-lefty * speed);
                        backRight.setPower(lefty*speed);
                    } else {
                        frontRight.setPower(leftx*speed);
                        frontLeft.setPower(leftx * speed);
                        backLeft.setPower(-leftx * speed);
                        backRight.setPower(-leftx*speed);

                        if (rightx > 0) {
                            frontRight.setPower(rightx*speed);
                            frontLeft.setPower(rightx*speed);
                            backLeft.setPower(rightx*speed);
                            backRight.setPower(rightx*speed);
                        } else {
                            frontRight.setPower(rightx*speed);
                            frontLeft.setPower(rightx*speed);
                            backLeft.setPower(rightx*speed);
                            backRight.setPower(rightx*speed);
                        }
                    }
//                if (liftPower != 0){
//                arm.setPower(liftPower);
//            } else {
//                    arm.setPower(0.02);
//                }

if(x2) {
    grab.setPosition(0.5);

}
else if(y2) {
    grab.setPosition(0);
}
if(a2) {
    arm.setPower(liftPower);
    armPosition += 10;
    arm.setTargetPosition(armPosition);

}
else if(b2){
    arm.setPower(liftPower);
    armPosition -=10;
    arm.setTargetPosition(armPosition);
}
else {
    arm.setPower(breakPower);
    arm.setTargetPosition(armPosition);

}







         //   telemetry.addData("lefty", "%.2f", lefty);
            // telemetry.addData("lefty2", "%.2f", lefty2);
            //telemetry.addData("leftx", "%.2f", leftx);

            // telemetry.addData("rightx", "%.2f", gamepad1.right_stick_x);
            // telemetry.addData("righty", "%.2f", gamepad1.right_stick_y);

             telemetry.addData("armPosition: ", arm.getCurrentPosition());
            telemetry.addData("motor position: ", armPosition);


            telemetry.update();

            telemetry.update();
        }
    }
}


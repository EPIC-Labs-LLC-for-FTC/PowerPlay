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
    double drift = 1.0;

    double pos = 0.0;
    double liftPower = 0.6;
    double breakPower = 0.2;
    double armPosition = 0.0;

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

            lefty = gamepad1.left_stick_y;
            leftx = gamepad1.left_stick_x;
            righty = gamepad1.right_stick_y;
            rightx = gamepad1.right_stick_x;
            if (gamepad1.right_bumper) {
                if (drift < 1) {
                    drift = drift + 0.01;
                }
            } else if (gamepad1.left_bumper) {
                if (drift > 0) {
                    drift = drift - 0.01;
                }
            }

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

                    if (Math.abs(lefty) > Math.abs(leftx)) {
                        frontRight.setPower(lefty);
                        frontLeft.setPower(-lefty * drift);
                        backLeft.setPower(-lefty * drift);
                        backRight.setPower(lefty);
                    } else {
                        frontRight.setPower(leftx);
                        frontLeft.setPower(leftx * drift);
                        backLeft.setPower(-leftx * drift);
                        backRight.setPower(-leftx);

                        if (rightx > 0) {
                            frontRight.setPower(rightx);
                            frontLeft.setPower(rightx);
                            backLeft.setPower(rightx);
                            backRight.setPower(rightx);
                        } else {
                            frontRight.setPower(rightx);
                            frontLeft.setPower(rightx);
                            backLeft.setPower(rightx);
                            backRight.setPower(rightx);
                        }
                    }
//                if (liftPower != 0){
//                arm.setPower(liftPower);
//            } else {
//                    arm.setPower(0.02);
//                }
if(a2) {
    arm.setPower(liftPower);
    armPosition += 100;
    arm.setTargetPosition((int)armPosition);

}
else if(b2){
    arm.setPower(liftPower);
    armPosition -=10;
    arm.setTargetPosition((int)armPosition);
}
else {
    arm.setPower(breakPower);
}







         //   telemetry.addData("lefty", "%.2f", lefty);
            // telemetry.addData("lefty2", "%.2f", lefty2);
            //telemetry.addData("leftx", "%.2f", leftx);

            // telemetry.addData("rightx", "%.2f", gamepad1.right_stick_x);
            // telemetry.addData("righty", "%.2f", gamepad1.right_stick_y);

             telemetry.addData("armPosition: ", "%.2f", arm.getCurrentPosition());
            telemetry.addData("motor position: ", "%.2f", armPosition);


            telemetry.update();

            telemetry.update();
        }
    }
}


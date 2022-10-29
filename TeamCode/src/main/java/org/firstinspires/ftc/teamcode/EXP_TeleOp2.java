package org.firstinspires.ftc.teamcode;

/* This program makes it easier to control the robot by letting the left stick control
forward and backward movement and letting the right stick x control the rotating of
the robot just like an Rc Car.
*/


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.I2cAddr;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


@TeleOp(name = "EXP_TeleOp2")
public class EXP_TeleOp2 extends LinearOpMode {
    //Configuration used: EPIC4Wheel
    Mecanum_Wheels wheels;
    double lefty = 0.0;
    double lefty2 = 0.0;
    double leftx = 0.0;
    double righty = 0.0;
    double rightx = 0.0;
    double drift = 1.0;
//    public DcMotorEx frontright;
//    public DcMotorEx frontleft;
//    public DcMotorEx backright;
//    public DcMotorEx backleft;


//    double liftPower = 0.0;
//    double rotateArm = 0.0;
//    double powerfactor = 0.6;

    private static void logGamepad(Telemetry telemetry, Gamepad gamepad, String prefix) {
        telemetry.addData(prefix + "Synthetic",
                gamepad.getGamepadId() == Gamepad.ID_UNASSOCIATED);
        for (Field field : gamepad.getClass().getFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;

            try {
                telemetry.addData(prefix + field.getName(), field.get(gamepad));
            } catch (IllegalAccessException e) {
                // ignore for now
            }
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        // Hardware Mapping

        DcMotorEx frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        DcMotorEx frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        DcMotorEx backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        DcMotorEx backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        ColorSensor colorSensor = new ColorSensor() {
            @Override
            public int red() {
                return 0;
            }

            @Override
            public int green() {
                return 0;
            }

            @Override
            public int blue() {
                return 0;
            }

            @Override
            public int alpha() {
                return 0;
            }

            @Override
            public int argb() {
                return 0;
            }

            @Override
            public void enableLed(boolean enable) {

            }

            @Override
            public void setI2cAddress(I2cAddr newAddress) {

            }

            @Override
            public I2cAddr getI2cAddress() {
                return null;
            }

            @Override
            public Manufacturer getManufacturer() {
                return null;
            }

            @Override
            public String getDeviceName() {
                return null;
            }

            @Override
            public String getConnectionInfo() {
                return null;
            }

            @Override
            public int getVersion() {
                return 0;
            }

            @Override
            public void resetDeviceConfigurationForOpMode() {

            }

            @Override
            public void close() {

            }
        };



        waitForStart();

        //claw.initiateLift();
        while (opModeIsActive()) {


//            //telemetry.addData("Finger 1 position", claw.clawFinger1.getPosition());
//            //telemetry.addData("Finger 2 position", claw.clawFinger2.getPosition());
//            //telemetry.update();
//            //mecanumWheels.initialize();
//
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



//            liftPower = gamepad2.right_stick_y;
//            rotateArm = gamepad2.left_stick_y;

            lefty2 = -gamepad2.left_stick_y;
//            boolean dpad_left = gamepad1.dpad_left;
//            boolean dpad_right = gamepad1.dpad_right;
            boolean b = gamepad1.b;
            boolean x = gamepad1.x;
            boolean y = gamepad1.y;
            boolean a = gamepad1.a;
//
            boolean a2 = gamepad2.a;
            boolean b2 = gamepad2.b;
            boolean y2 = gamepad2.y;
            boolean x2 = gamepad2.x;
            boolean dpad_down2 = gamepad2.dpad_down;
            boolean dpad_left2 = gamepad2.dpad_left;
            boolean dpad_up2 = gamepad2.dpad_up;
            boolean dpad_right2 = gamepad2.dpad_right;
//            //if(!dpad_left && !dpad_right)
//            //else
//            if(dpad_left) {
////                wheels.Collapse();
//
//            }
//            else if(dpad_right) {
////                wheels.Expand();
//                spinner.setPower(0);
//            }
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




//           if (rightx > 0)) {
//                frontRight.setPower(0);
//                frontLeft.setPower(-rightx);
//                backLeft.setPower(-rightx);
//                backRight.setPower(0);
//            } else {
//                frontRight.setPower(rightx);
//                frontLeft.setPower(0);
//                backLeft.setPower(0);
//                backRight.setPower(rightx);
//            }

//            boolean leftBumper = gamepad2.left_bumper;
//            boolean rightBumper = gamepad2.right_bumper;

//            claw.rotate(leftBumper, rightBumper);
//
//            if(y2) {
//                claw.grab();
//            }
//                else{
//                    claw.release();
//            }
//
////            wheels.leftMotorY(-lefty);
////            wheels.rightMotorY(-righty);
////            wheels.rightMotorX(rightx);
////            wheels.leftMotorX(leftx);




            

            telemetry.addData("lefty", "%.2f", lefty);
            telemetry.addData("lefty2", "%.2f", lefty2);
            telemetry.addData("leftx", "%.2f", leftx);

            telemetry.addData("rightx", "%.2f", gamepad1.right_stick_x);
            telemetry.addData("righty", "%.2f", gamepad1.right_stick_y);


            telemetry.addData("Drift: ", drift);


            telemetry.update();



            //logGamepad(telemetry, gamepad1, "gamepad1");
            //logGamepad(telemetry, gamepad2, "gamepad2");
            telemetry.update();
        }
    }
}


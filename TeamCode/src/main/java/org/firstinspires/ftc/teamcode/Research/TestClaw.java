package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Test Claw", group="Research")
//@Disabled
public class TestClaw extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Servo servo;
    double servoPosition = 0;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        servo = hardwareMap.get(Servo.class, "claw");
        servo.setPosition(servoPosition);



        waitForStart();
        runtime.reset();



        while(opModeIsActive()){
            servoPosition =0.0;
            servo.setPosition(servoPosition);
            telemetry.addData("servoPosition", servoPosition);
            telemetry.update();
            sleep(3000);

            servoPosition =0.4;
            servo.setPosition(servoPosition);
            telemetry.addData("servoPosition", servoPosition);
            telemetry.update();
            sleep(3000);


        }
        telemetry.addData("servoPosition", servoPosition);
        telemetry.update();
    }
}

package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Test Claw", group="Research")
public class TestClaw extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Servo servo;
    double position = 0;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        servo = hardwareMap.get(Servo.class, "claw");
        servo.setPosition(position);
        waitForStart();
        runtime.reset();



        while(opModeIsActive()){
            position =0.4;
            servo.setPosition(position);
            telemetry.addData("position", position);
            telemetry.update();
            sleep(3000);

            position =0;
            servo.setPosition(position);
            telemetry.addData("position", position);
            telemetry.update();
            sleep(3000);
        }
        telemetry.addData("position", position);
        telemetry.update();
    }
}

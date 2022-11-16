package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Test LazySu", group="Research")
public class TestLazySusan extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Servo servo;
    double position = 0;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        servo = hardwareMap.get(Servo.class, "lazySusan");
        servo.setPosition(position);
        waitForStart();
        runtime.reset();


        int direction = 1;
        while(opModeIsActive()){
            position +=0.05*direction;
            servo.setPosition(position);
            telemetry.addData("position", position);
            telemetry.update();
            sleep(100);
            if(position>=0.8)
                direction = -1;
            else if(position<=0)
                direction = 1;
//            position =0;
//            servo.setPosition(position);
//            telemetry.addData("position", position);
//            telemetry.update();
//            sleep(3000);
        }
        telemetry.addData("position", position);
        telemetry.update();
    }
}

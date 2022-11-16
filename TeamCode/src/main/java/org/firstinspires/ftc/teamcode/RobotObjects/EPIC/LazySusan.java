package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LazySusan {
    public LinearOpMode parent;

    private ElapsedTime runtime = new ElapsedTime();
    private Servo servo;
    double dPosition = 0;
    int direction = 1;
    double maxPosition = 0.8;

    public Telemetry telemetry;

    public LazySusan(HardwareMap hardwareMap) {


        servo = hardwareMap.get(Servo.class, "claw");
    }

    public void initialize() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        servo.setPosition(dPosition);
    }

    public void rotate(double position){
        if(position > 0.8)
            position = 0.8;
        if(position <0)
            position = 0;
        dPosition = position;
        servo.setPosition(position);
    }
    public void rotateRight(double position){
        direction = -1;


    }
}

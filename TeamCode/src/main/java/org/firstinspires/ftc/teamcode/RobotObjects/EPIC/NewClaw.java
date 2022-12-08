package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class NewClaw {
    public LinearOpMode parent;

    private ElapsedTime runtime = new ElapsedTime();
    private Servo servo;
    double servoPosition = 0;
    int direction = 1;
    double maxPosition = 0.8;

    public Telemetry telemetry;

    public NewClaw(HardwareMap hardwareMap) {


        servo = hardwareMap.get(Servo.class, "claw");
    }

    public void initialize(double position) {

        telemetry.addData("ClawStatus", "Initialized");
        telemetry.update();
        servo.setPosition(position);
        telemetry.addData("clawPosition", servoPosition);
        telemetry.addData("clawServoPosition", servo.getPosition());
        telemetry.update();
    }
    
    public void grab (){
        servoPosition =0.0;
        servo.setPosition(servoPosition);
        telemetry.addData("clawPosition", servoPosition);
        telemetry.addData("clawServoPosition", servo.getPosition());
        telemetry.update();
    }

    public void release (){
        servoPosition =0.4;
        servo.setPosition(servoPosition);
        telemetry.addData("clawPosition", servoPosition);
        telemetry.addData("clawServoPosition", servo.getPosition());
        telemetry.update();
    }
}

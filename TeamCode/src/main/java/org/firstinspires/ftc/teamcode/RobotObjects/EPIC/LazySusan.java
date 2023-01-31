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
    public double zeroAdjustment = 0.0;
    double maxPosition = 0.8;

    public Telemetry telemetry;

    public LazySusan(HardwareMap hardwareMap) {


        servo = hardwareMap.get(Servo.class, "lazySusan");
    }

    public void initialize() {

        telemetry.addData("lazySusanStatus", "Initialized");
        telemetry.update();
        //servo.setDirection(Servo.Direction.REVERSE);
        servo.setPosition(zeroAdjustment);
        //servo.setDirection(Servo.Direction.FORWARD);
        telemetry.addData("lazyPosistion", zeroAdjustment);
        telemetry.addData("lazyPosistion", servo.getPosition());
        telemetry.update();

    }

    public void initialize2() {

        telemetry.addData("lazySusanStatus", "Initialized");
        telemetry.update();
        //servo.setDirection(Servo.Direction.REVERSE);
        servo.setPosition(0.75);
        //servo.setDirection(Servo.Direction.FORWARD);
        telemetry.addData("lazyPosistion", zeroAdjustment);
        telemetry.addData("lazyPosistion", servo.getPosition());
        telemetry.update();

    }

    public double getPosition(){
        return servo.getPosition();
    }
    public void rotate(double position){
        position -= zeroAdjustment;
        if(position > 0.8)
            position = 0.8;
        if(position < 0)
            position = 0;
        dPosition = position;
        servo.setPosition(position);
        telemetry.addData("lazyPosistion", position);
        telemetry.addData("lazyServoPosistion", servo.getPosition());
        telemetry.update();
    }
    public void rotateRight(double position){
        direction = -1;


    }
}

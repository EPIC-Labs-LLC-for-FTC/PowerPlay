package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftArm {
    public LinearOpMode parent;

    private ElapsedTime runtime = new ElapsedTime();
    private Servo servo;
    double dPosition = 0;
    int direction = 1;
    public double zeroAdjustment = 0.0;
    double maxPosition = 0.8;

    public Telemetry telemetry;

    public LiftArm(HardwareMap hardwareMap) {


        servo = hardwareMap.get(Servo.class, "liftArm");
    }

    public void initialize() {

        telemetry.addData("lift Arm Status", "Initialized");
        telemetry.update();
        //servo.setDirection(Servo.Direction.REVERSE);
        servo.setPosition(zeroAdjustment);
        //servo.setDirection(Servo.Direction.FORWARD);
        telemetry.addData("liftArmPosistion", zeroAdjustment);
        telemetry.addData("liftArmPosistion", servo.getPosition());
        telemetry.update();

    }

    public void initialize2() {

        telemetry.addData("lift Arm Status", "InitialiSzed");
        telemetry.update();
        //servo.setDirection(Servo.Direction.REVERSE);
        servo.setPosition(0.27);
        //servo.setDirection(Servo.Direction.FORWARD);
        telemetry.addData("liftArmPosistion", zeroAdjustment);
        telemetry.addData("liftArmPosistion", servo.getPosition());
        telemetry.update();

    }

    public double getPosition(){
        return servo.getPosition();
    }
    public void lift(double position){
        position -= zeroAdjustment;
        if(position > 0.8)
            position = 0.8;
        if(position <0)
            position = 0;
        dPosition = position;
        servo.setPosition(position);
        telemetry.addData("liftArmPosistion", position);
        telemetry.addData("liftArmServoPosistion", servo.getPosition());
        telemetry.update();
    }
    public void rotateRight(double position){
        direction = -1;


    }
}

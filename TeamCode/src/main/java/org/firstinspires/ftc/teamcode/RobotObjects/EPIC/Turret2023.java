package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Turret2023 {
    //Configuration used: 6wheelConfig
    public ServoImplEx turret;
    //public DcMotorEx liftMotor;
    public Telemetry telemetry;
    public LinearOpMode parent;
    public double front = 0.4;
    public double left  = 0;
    public double right  = 1;
    public double turretPosition = 0;
    public boolean incrementer = false;


    public Turret2023(HardwareMap hardwareMap) {
        turret = hardwareMap.get(ServoImplEx.class,"turret");
        turret.setDirection(Servo.Direction.FORWARD);

    }
    public void setPosition(double position){
        turret.setPosition(position);
    }
    public void home()
    {
        turret.setPosition(front);
        telemetry.addData("Finger Claw 1:%d", turret.getPosition());

        telemetry.update();
    }
    public void right() {
        turret.setPosition(right);
        telemetry.addData("Finger Claw 1:%d", turret.getPosition());
        telemetry.update();
        //parent.sleep(5000);
    }
    public void left() {
        turret.setPosition(left);
        telemetry.addData("Finger Claw 1:%d", turret.getPosition());
        telemetry.update();
        //parent.sleep(5000);
    }
    public void turretIncrease(){
        if(incrementer) {
            turretPosition = turret.getPosition() + 0.05;
            turret.setPosition(turretPosition);
            parent.sleep(1000);
            incrementer=false;
        }
    }
    public void turretDecrease(){
        if(incrementer) {
            turretPosition = turret.getPosition() - 0.05;
            turret.setPosition(turretPosition);
            parent.sleep(1000);
            incrementer=false;
        }
    }
}

package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TiltSlider {
    public DcMotorEx arm;
    double power = 0.3;
    public LinearOpMode parent;
    int currentPosition=0;
    public Telemetry telemetry;
    public TiltSlider(HardwareMap hardwareMap){
        arm = hardwareMap.get(DcMotorEx.class,"pankit");

    }
    //initialize for TeleOp
    public void initialize() {
        arm.setDirection(DcMotorSimple.Direction.FORWARD);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setTargetPosition(0);
        currentPosition = 0;

    }

    public void initialize2() {
        //arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        //arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        int currentPosition = arm.getCurrentPosition();
//        arm.setTargetPosition(1000);
//        arm.setPower(0.4);
//        parent.sleep(2000);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //arm.setDirection(DcMotorSimple.Direction.FORWARD);
        //arm.setTargetPosition(0);
        arm.setTargetPosition(0);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setPower(0);

    }

    public void lift(double power, int milliseconds, int direction) {
        if(direction==-1){
        //    arm.setDirection(DcMotorSimple.Direction.REVERSE);
            currentPosition += 70 * direction;

        }
        else
        {
            currentPosition += 100 * direction;

            //    arm.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        arm.setTargetPosition(currentPosition);
        arm.setPower(power);
        parent.sleep(milliseconds);
    }

    public double getCurrentPosition(){
        return arm.getCurrentPosition();
    }

    public void extendTicks(double power, int encoderTicks) {
        int targetPosition = encoderTicks;
        arm.setPower(power);
        arm.setTargetPosition(targetPosition);
        currentPosition = targetPosition;


        telemetry.addData("extenArmPosition", arm.getCurrentPosition());
        telemetry.addData("extendArmtargetPosition", targetPosition);

        telemetry.update();
    }

    public void liftEncoder(double power, int level) {
        int targetPosition = 0;
        //arm.setDirection(direction);
        arm.setPower(power);
        if(level==1)
            targetPosition = 15;
        else if(level==2)
            targetPosition = 30;
        else if(level==3)
            targetPosition = 40;
        else if(level==4) {
//            targetPosition = 700;
//            arm.setTargetPosition(targetPosition);
//            currentPosition = targetPosition;

            //parent.sleep(1000);
            targetPosition = 400;
        }
        arm.setTargetPosition(targetPosition);
        currentPosition = targetPosition;
        //parent.sleep(500);

        telemetry.addData("extendArmPosition", arm.getCurrentPosition());
        telemetry.addData("extendArmtargetPosition", targetPosition);

        telemetry.update();
        //parent.sleep(2000);
        //arm.setPower(0);
    }

    public void setPower(double power){
        arm.setPower(power);
    }
}

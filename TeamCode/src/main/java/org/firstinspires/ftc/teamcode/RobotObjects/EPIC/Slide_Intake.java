//package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//@Disabled
//
//public class Slide_Intake {
//    public Servo finger1;
//    public Servo finger2;
//    public Servo wrist1;
//    public Servo wrist2;
//    public Servo outtakeArm;
//    public DcMotorEx arm1;
//    public DcMotorEx arm2;
//    public DcMotorEx slide1;
//    public DcMotorEx slide2;
//
//    //finger values
//    private double fingeropen;
//    private double fingerclose;
//    //slide values with placeholders to be initialized
//    private int smallJunction;
//    private int middleJunction;
//    private int tallJunction;
//    private int recievingCone;
//    //arm values
//    private int level5;
//    private int level4;
//    private int level3;
//    private int level2;
//    private int ground;
//    private int droppingCone;
//    //Outtake
//    private double recievingOutake = 0;
//    private double droppingOutake = 1;
//    //wrist values
//    private double recievingWrist = 0;
//    private double droppingWrist = 1;
//
//    public LinearOpMode parent;
//    public Telemetry telemetry;
//
//    public Slide_Intake(HardwareMap hardwareMap){
//        finger1 = hardwareMap.get(Servo.class, "finger1");
//        finger2 = hardwareMap.get(Servo.class, "finger2");
//        wrist1 = hardwareMap.get(Servo.class, "wrist1");
//        wrist2 = hardwareMap.get(Servo.class, "wrist2");
//        outtakeArm= hardwareMap.get(Servo.class, "outtakeArm");
//
//        finger2.setDirection(Servo.Direction.REVERSE);
//        wrist2.setDirection(Servo.Direction.REVERSE);
//        arm1.setDirection(DcMotorSimple.Direction.REVERSE);
//        slide1.setDirection(DcMotorSimple.Direction.REVERSE);
//
//
//        finger1.setPosition(fingeropen);
//        finger2.setPosition(fingeropen);
//    }
//    public void initializeClaw() {
//        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        arm1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
////        arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////        arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//
//        finger1.setPosition(fingeropen);
//        finger2.setPosition(fingeropen);
//        slide1.setTargetPosition(recievingCone);
//        slide2.setTargetPosition(recievingCone);
//
//
//    }
//    public void cycling(int cycle) {
//        //0th cycle with the preload cone
//        if(cycle == 0){
//            //temporal
//            slide1.setTargetPosition(tallJunction);
//            slide2.setTargetPosition(tallJunction);
//            //displacement to drop
//            outtakeArm.setPosition(droppingOutake);
//
//            parent.sleep(500);
//            //displacment to pick
//            outtakeArm.setPosition(recievingOutake);
//            //temporal
//            slide1.setTargetPosition(recievingCone);
//            slide2.setTargetPosition(recievingCone);
//
//        }
//        //first cycle
//        if(cycle == 1){
//            //setting arm to the highest level cone
//            arm1.setTargetPosition(level5);
//            arm2.setTargetPosition(level5);
//            //setting wrist position to the reciving position
//            wrist1.setPosition(recievingWrist);
//            wrist2.setPosition(recievingWrist);
//            //closing fingers on the cone
//            finger1.setPosition(fingerclose);
//            finger2.setPosition(fingerclose);
//            //moving the cone up, out of the stack, and into the dropping position
//            wrist1.setPosition(droppingWrist);
//            wrist2.setPosition(droppingWrist);
//            //moving the arm back, so that it will insert the cone onto the outtake arm
//            arm1.setTargetPosition(droppingCone);
//            arm2.setTargetPosition(droppingCone);
//            //opening the fingers so that it will let go of the cone
//            finger1.setPosition(fingeropen);
//            finger2.setPosition(fingeropen);
//            //moving the wrist back to receiving position, getting out of the way of the outtake arm
//            wrist1.setPosition(recievingWrist);
//            wrist2.setPosition(recievingWrist);
//            //sending slide to tallest junction
//            slide1.setTargetPosition(tallJunction);
//            slide2.setTargetPosition(tallJunction);
//            //turn outtake arm to drop cone onto junction, and then setting it back
//            outtakeArm.setPosition(droppingOutake);
//            parent.sleep(500);
//            outtakeArm.setPosition(recievingOutake);
//            //setting slide back to receiving position
//            slide1.setTargetPosition(recievingCone);
//            slide2.setTargetPosition(recievingCone);
//            //setting arm to the next level
//            arm1.setTargetPosition(level4);
//            arm2.setTargetPosition(level4);
//        }
//        if(cycle == 2){
//            //closing fingers on the cone
//            finger1.setPosition(fingerclose);
//            finger2.setPosition(fingerclose);
//            //moving the cone up, out of the stack, and into the dropping position
//            wrist1.setPosition(droppingWrist);
//            wrist2.setPosition(droppingWrist);
//            //moving the arm back, so that it will insert the cone onto the outtake arm
//            arm1.setTargetPosition(droppingCone);
//            arm2.setTargetPosition(droppingCone);
//            //opening the fingers so that it will let go of the cone
//            finger1.setPosition(fingeropen);
//            finger2.setPosition(fingeropen);
//            //moving the wrist back to receiving position, getting out of the way of the outtake arm
//            wrist1.setPosition(recievingWrist);
//            wrist2.setPosition(recievingWrist);
//            //sending slide to tallest junction
//            slide1.setTargetPosition(tallJunction);
//            slide2.setTargetPosition(tallJunction);
//            //turn outtake arm to drop cone onto junction, and then setting it back
//            outtakeArm.setPosition(droppingOutake);
//            parent.sleep(500);
//            outtakeArm.setPosition(recievingOutake);
//            //setting slide back to receiving position
//            slide1.setTargetPosition(recievingCone);
//            slide2.setTargetPosition(recievingCone);
//            //setting arm to the next level
//            arm1.setTargetPosition(level3);
//            arm2.setTargetPosition(level3);
//        }
//        if(cycle == 3){
//            //closing fingers on the cone
//            finger1.setPosition(fingerclose);
//            finger2.setPosition(fingerclose);
//            //moving the cone up, out of the stack, and into the dropping position
//            wrist1.setPosition(droppingWrist);
//            wrist2.setPosition(droppingWrist);
//            //moving the arm back, so that it will insert the cone onto the outtake arm
//            arm1.setTargetPosition(droppingCone);
//            arm2.setTargetPosition(droppingCone);
//            //opening the fingers so that it will let go of the cone
//            finger1.setPosition(fingeropen);
//            finger2.setPosition(fingeropen);
//            //moving the wrist back to receiving position, getting out of the way of the outtake arm
//            wrist1.setPosition(recievingWrist);
//            wrist2.setPosition(recievingWrist);
//            //sending slide to tallest junction
//            slide1.setTargetPosition(tallJunction);
//            slide2.setTargetPosition(tallJunction);
//            //turn outtake arm to drop cone onto junction, and then setting it back
//            outtakeArm.setPosition(droppingOutake);
//            parent.sleep(500);
//            outtakeArm.setPosition(recievingOutake);
//            //setting slide back to receiving position
//            slide1.setTargetPosition(recievingCone);
//            slide2.setTargetPosition(recievingCone);
//            //setting arm to the next level
//            arm1.setTargetPosition(level2);
//            arm2.setTargetPosition(level2);
//        }
//        if(cycle == 4){
//            //closing fingers on the cone
//            finger1.setPosition(fingerclose);
//            finger2.setPosition(fingerclose);
//            //moving the cone up, out of the stack, and into the dropping position
//            wrist1.setPosition(droppingWrist);
//            wrist2.setPosition(droppingWrist);
//            //moving the arm back, so that it will insert the cone onto the outtake arm
//            arm1.setTargetPosition(droppingCone);
//            arm2.setTargetPosition(droppingCone);
//            //opening the fingers so that it will let go of the cone
//            finger1.setPosition(fingeropen);
//            finger2.setPosition(fingeropen);
//            //moving the wrist back to receiving position, getting out of the way of the outtake arm
//            wrist1.setPosition(recievingWrist);
//            wrist2.setPosition(recievingWrist);
//            //sending slide to tallest junction
//            slide1.setTargetPosition(tallJunction);
//            slide2.setTargetPosition(tallJunction);
//            //turn outtake arm to drop cone onto junction, and then setting it back
//            outtakeArm.setPosition(droppingOutake);
//            parent.sleep(500);
//            outtakeArm.setPosition(recievingOutake);
//            //setting slide back to receiving position
//            slide1.setTargetPosition(recievingCone);
//            slide2.setTargetPosition(recievingCone);
//            //setting arm to the next level
//            arm1.setTargetPosition(ground);
//            arm2.setTargetPosition(ground);
//        }
//
//
//
//
//
//
//    }
//}

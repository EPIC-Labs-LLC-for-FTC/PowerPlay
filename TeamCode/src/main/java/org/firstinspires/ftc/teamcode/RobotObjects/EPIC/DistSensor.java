package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.HashMap;
import java.util.Map;

public class DistSensor {

    public LinearOpMode parent;

    public Telemetry telemetry;
    private Map<String, Double> distances = new HashMap<String, Double>();

    private DistanceSensor rDist;
    private DistanceSensor lDist;
    private DistanceSensor bDist;
    private DistanceSensor bDist2;

    public DistSensor (HardwareMap hardwareMap){
        rDist = hardwareMap.get(DistanceSensor.class,"rDist");
        rDist.resetDeviceConfigurationForOpMode();
        lDist = hardwareMap.get(DistanceSensor.class,"lDist");
        lDist.resetDeviceConfigurationForOpMode();
        bDist = hardwareMap.get(DistanceSensor.class,"bDist");
        bDist.resetDeviceConfigurationForOpMode();
        bDist2 = hardwareMap.get(DistanceSensor.class,"bDist2");
        bDist2.resetDeviceConfigurationForOpMode();
    }

    public void initialize(){

        distances.put("Right",0.0);
        distances.put("Left",0.0);
        distances.put("Back",0.0);
        distances.put("Back2",0.0);
        distances.put("Front",-1.0); //Change once front is installed

    }

    public Map<String,Double> getDistances(DistanceUnit unit){
        double rightAve = 0.0;
        double backAve = 0.0;
        double back2Ave = 0.0;
        double leftAve = 0.0;
        for(int i = 0; i<4;i++){
            rightAve += rDist.getDistance(unit);
            backAve += bDist.getDistance(unit);
            back2Ave += bDist.getDistance(unit);
            leftAve += lDist.getDistance(unit);
        }
        rightAve = rightAve/4;
        leftAve = leftAve/4;
        backAve = backAve/4;
        back2Ave = back2Ave/4;
        distances.put("Right",rightAve);
        distances.put("Left",leftAve);
        distances.put("Back",backAve);
        distances.put("Back2",back2Ave);
        telemetry.addData("Right Position in " + unit.name() , rightAve);
        telemetry.addData("Left Position in " + unit.name() , leftAve);
        telemetry.addData("Back Position in " + unit.name() , backAve);
        telemetry.addData("Back2 Position in " + unit.name() , back2Ave);
        //telemetry.update();
        return distances;
    }

    public double getRight(DistanceUnit unit){
        double rightAve = 0.0;
        for(int i = 0; i<4;i++){
            rightAve += rDist.getDistance(unit);
        }
        rightAve = rightAve/4;
        return rightAve;
    }

    public double getLeft(DistanceUnit unit){
        double leftAve = 0.0;
        for(int i = 0; i<4;i++){
            leftAve += lDist.getDistance(unit);
        }
        leftAve = leftAve/4;
        return leftAve;
    }


    public double getBack(DistanceUnit unit){
        double backAve = 0.0;
        for(int i = 0; i<4;i++){
            backAve += bDist.getDistance(unit);
        }
        backAve = backAve/4;
        return backAve;
    }


    public double getBack2(DistanceUnit unit){
        double back2Ave = 0.0;
        for(int i = 0; i<4;i++){
            back2Ave += bDist2.getDistance(unit);
        }
        back2Ave = back2Ave/4;
        return back2Ave;
    }
}

package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.HashMap;
import java.util.Map;

public class FieldSetup {
    public String AllianceColor = ""; //Blue or Red
    public String Side = ""; //Left or Right
    private DistSensor ds = null;
    private DistanceUnit unit = DistanceUnit.INCH;
    private Map<String,JunctionType> junctions = new HashMap<>();

    public FieldSetup(String allianceColor, String side, DistSensor ds, DistanceUnit unit){
        AllianceColor = allianceColor;
        Side = side;
        this.ds = ds;

        JunctionType j = new JunctionType();
        j.name = "x4";
        j.points = 5;
        junctions.put("x4",j);

        j = new JunctionType();
        j.name = "x2";
        j.points = 5;
        junctions.put("x2",j);

        j = new JunctionType();
        j.name = "w4";
        j.points = 4;
        junctions.put("w4",j);

        j = new JunctionType();
        j.name = "w1";
        j.points = 3;
        junctions.put("w1",j);

        j = new JunctionType();
        j.name = "w2";
        j.points = 3;
        junctions.put("w2",j);

    }

    public boolean checkPos(String junctionName){
        boolean retval = true;
        Map<String,Double> distances = ds.getDistances(unit);
        double bDist = distances.get("Back");
        double rDist = distances.get("Right");
        double lDist = distances.get("Left");
        if(Side.equals("Right"))
            {
                if(junctionName.equals("x2"))
                {
                    if(bDist>49 && lDist < 8) {
                        retval= false;
                    }
                }
                else if(junctionName.equals("w2")){
                    if(bDist>30 && lDist < 8) {
                        retval = false;
                    }
                }
                else if(junctionName.equals("wx0")){
                    if(bDist<2.5) {
                        retval = false;
                    }
                }
            }
        return retval;
    }



    public static class JunctionType{
        public int points = 2;
        public String type = "";
        public String name = "";

    }
}

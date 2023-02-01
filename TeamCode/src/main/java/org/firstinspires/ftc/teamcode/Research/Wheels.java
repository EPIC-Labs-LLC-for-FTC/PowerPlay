package org.firstinspires.ftc.teamcode.Research;

public class Wheels {
    public String direction = "";
    public double frontLeftInches = 0.0;
    public double frontRightInches = 0.0;
    public double backLeftInches = 0.0;
    public double backRightInches = 0.0;

    public boolean isRotation = false;
    public double arcValue = 0.0;
    public double timeInSeconds = 0.0;
    public Wheels() {

    }
    public Wheels(String direction, double frontLeftInches, double frontRightInches, double backLeftInches, double backRightInches, double timeInSeconds){
        this.direction = direction;
        this.frontRightInches = frontRightInches;
        this.frontLeftInches = frontLeftInches;
        this.backRightInches = backRightInches;
        this.backLeftInches = backLeftInches;
        this.timeInSeconds = timeInSeconds;
    }

    public Wheels getReverse(){
        if(this.direction.equals("forward"))
            return this.getBackward();
        else if(this.direction.equals("backward"))
            return this.getForward();
        else if(this.direction.equals("left"))
            return this.getRight();
        else if(this.direction.equals("right"))
            return this.getLeft();
        else
            return null;
    }

    private Wheels getLeft(){
        Wheels wheels = new Wheels();
        wheels.direction = "left";
        wheels.frontLeftInches = -frontLeftInches;
        wheels.frontRightInches = frontRightInches;
        wheels.backRightInches = -backRightInches;
        wheels.backLeftInches = backLeftInches;
        wheels.timeInSeconds = timeInSeconds;
        return wheels;
    }
    private Wheels getRight(){
        Wheels wheels = new Wheels();
        wheels.direction = "right";
        wheels.frontLeftInches = frontLeftInches;
        wheels.frontRightInches = -frontRightInches;
        wheels.backRightInches = backRightInches;
        wheels.backLeftInches = -backLeftInches;
        wheels.timeInSeconds = timeInSeconds;
        return wheels;

    }
    private Wheels getForward(){
        Wheels wheels = new Wheels();
        wheels.direction = "forward";
        wheels.frontLeftInches = frontLeftInches;
        wheels.frontRightInches = frontRightInches;
        wheels.backRightInches = backRightInches;
        wheels.backLeftInches = backLeftInches;
        wheels.timeInSeconds = timeInSeconds;
        return wheels;

    }
    private Wheels getBackward(){
        Wheels wheels = new Wheels();
        wheels.direction = "backward";
        wheels.frontLeftInches = -frontLeftInches;
        wheels.frontRightInches = -frontRightInches;
        wheels.backRightInches = -backRightInches;
        wheels.backLeftInches = -backLeftInches;
        wheels.timeInSeconds = timeInSeconds;
        return wheels;

    }

}

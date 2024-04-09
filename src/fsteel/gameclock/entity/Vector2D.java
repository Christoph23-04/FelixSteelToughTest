package fsteel.gameclock.entity;

public class Vector2D {

    public static Vector2D calculateVectorBetween(double xPos1, double yPos1, double xPos2, double yPos2){
        double xDiff = xPos2 - xPos1;
        double yDiff = yPos2 - yPos1;
        return new Vector2D(xDiff, yDiff);
    }

    public static final float STANDARD_VECTOR_LENGTH = 1f;

    private double xDir;
    private double yDir;
    private double currentAmount;

    public Vector2D(){
        this(0, 0);
    }

    public Vector2D(int xDir, int yDir){
        this((double)xDir,yDir);
    }

    public Vector2D(double xDir, double yDir){
         setDirection(xDir, yDir);
    }

    private void calculateAmount(){
        currentAmount =  Math.abs(xDir) + Math.abs(yDir);
    }

    public void setDirection(double xDir, double yDir){
        this.xDir = xDir;
        this.yDir = yDir;
        calculateAmount();
    }

    public double getXDir(){
        return xDir;
    }

    public double getXDirToAmount(double amount){
        if(currentAmount < 0.0001){
            return 0;
        }
        return getXDir() * amount/currentAmount;
    }

    public double getYDir(){
        return yDir;
    }

    public double getYDirToAmount(double amount){
        if(currentAmount < 0.0001){
            return 0;
        }
        return getYDir() * amount/currentAmount;
    }

    public double getVectorAmount(){
        return currentAmount;
    }
}

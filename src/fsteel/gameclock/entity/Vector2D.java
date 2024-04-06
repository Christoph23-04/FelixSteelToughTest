package fsteel.gameclock.entity;

public class Vector2D {

    public static Vector2D calculateVectorBetween(float xPos1, float yPos1, float xPos2, float yPos2){
        float xDiff = xPos2 - xPos1;
        float yDiff = yPos2 - yPos1;
        return new Vector2D(xDiff, yDiff);
    }

    public static final float STANDARD_VECTOR_LENGTH = 1f;

    private float xDir;
    private float yDir;
    private float currentAmount;

    public Vector2D(){
        this(0, 0);
    }

    public Vector2D(int xDir, int yDir){
        this((float) xDir,(float) yDir);
    }

    public Vector2D(float xDir, float yDir){
        this.xDir = xDir;
        this.yDir = yDir;
        currentAmount = calculateAmount();
    }

    private float calculateAmount(){
        return Math.abs(xDir) + Math.abs(yDir);
    }

    public void setDirection(float xDir, float yDir){
        this.xDir = xDir;
        this.yDir = yDir;
        calculateAmount();
    }

    public float getXDir(){
        return xDir;
    }

    public float getXDirToAmount(float amount){
        if(currentAmount < 0.00001){
            return 0;
        }
        return getXDir() * amount/currentAmount;
    }

    public float getYDir(){
        return yDir;
    }

    public float getYDirToAmount(float amount){
        if(currentAmount < 0.00001){
            return 0;
        }
        return getYDir() * amount/currentAmount;
    }

    public float getVectorAmount(){
        return currentAmount;
    }
}

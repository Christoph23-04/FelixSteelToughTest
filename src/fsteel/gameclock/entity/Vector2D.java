package fsteel.gameclock.entity;

public class Vector2D {

    public static Vector2D calculateVectorBetween(double xPos1, double yPos1, double xPos2, double yPos2){
        double xDiff = xPos2 - xPos1;
        double yDiff = yPos2 - yPos1;
        return new Vector2D(xDiff, yDiff);
    }

    public static final float STANDARD_VECTOR_LENGTH = 1f;

    public static final int VECTOR_DIRECTION_UP = 0;
    public static final int VECTOR_DIRECTION_DOWN = 1;
    public static final int VECTOR_DIRECTION_RIGHT = 2;
    public static final int VECTOR_DIRECTION_LEFT = 3;

    private double xDir;
    private double yDir;
    private double amount;

    public Vector2D(){
        this(0, 1);
    }

    public Vector2D(int xDir, int yDir){
        this((double)xDir,yDir);
    }

    public Vector2D(Vector2D v){
        this(v.getXDir(), v.getYDir());
    }

    public Vector2D(double xDir, double yDir){
         setDir(xDir, yDir);
    }

    public void scaleVector(double newLength){
        if(amount == 0){
            setDir(newLength/2, newLength/2);
        }
        setDir(xDir*(newLength/amount), yDir*(newLength/amount));
    }

    public void setDir(double xDir, double yDir){
        this.xDir = xDir;
        this.yDir = yDir;
        amount = Math.abs(xDir) + Math.abs(yDir);
    }

    public void addVector(Vector2D vector){
        setDir(xDir + vector.xDir, yDir + vector.getYDir());
    }

    public double getXDir(){
        return xDir;
    }

    public double getXDirToAmount(double amount){
        if(this.amount < 0.0001){
            return 0;
        }
        return getXDir() * (amount / this.amount);
    }

    public double getYDir(){
        return yDir;
    }

    public double getYDirToAmount(double amount){
        if(this.amount < 0.0001){
            return 0;
        }
        return getYDir() * (amount / this.amount);
    }

    public double getVectorAmount(){
        return amount;
    }

    /**
     * @return the direction (UP, DOWN, RIGHT, LEFT) which the vector is aligned to
     */
    public int toVectorDirection() {
        if (Math.abs(xDir) > Math.abs(yDir)) {
            if(xDir > 0){
                return VECTOR_DIRECTION_RIGHT;
            }
            return VECTOR_DIRECTION_LEFT;
        }
        if(yDir > 0){
            return VECTOR_DIRECTION_DOWN;
        }
        return VECTOR_DIRECTION_UP;
    }
}

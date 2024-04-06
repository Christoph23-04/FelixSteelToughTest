package fsteel.gameclock.entity.hitBox;

import fsteel.gameclock.entity.ScreenPoint;
import fsteel.gameclock.entity.Vector2D;

import java.awt.*;

public class HitBox implements ScreenPoint {

    public static HitBox createSquareHitbox(int width, int height, HitBoxObject hbo){
        Point[] points = new Point[4];
        points[0] = new Point(0,0);
        points[1] = new Point(width, 0);
        points[2] = new Point(width, height);
        points[3] = new Point(0, height);
        return new HitBox(points, hbo);
    }
    private Point centroid;
    private Polygon bounds;
    private HitBoxObject hboObject;

    public HitBox(Point[] boundPoints, HitBoxObject hitBoxObject){
        this.hboObject = hitBoxObject;
        setBounds(boundPoints);
    }


    public void setBounds(Point[] bounds){
        Polygon p = new Polygon();
        for(int i = 0; i < bounds.length; i++){
            p.addPoint(bounds[i].x, bounds[i].y);
        }
        setBounds(p);
    }

    public void setBounds(Polygon newBounds){
        if(newBounds == null){
            return;
        }
        this.bounds = newBounds;
        calculateCentroid();
    }

    private void calculateCentroid(){
        int xPos = 0;
        int yPos = 0;
        for(int x : bounds.xpoints){
            xPos = xPos + x;
        }
        xPos = xPos / bounds.xpoints.length;
        for(int y : bounds.ypoints){
            yPos = yPos + y;
        }
        yPos = yPos/ bounds.xpoints.length;
        centroid = new Point(xPos, yPos);
    }

    public Vector2D calculateVectorToCenter(HitBox foreignHitBox){
        int x1 = foreignHitBox.getCentroidXOnScreen();
        int y1 = foreignHitBox.getCentroidYOnScreen();
        int x2 = this.getCentroidXOnScreen();
        int y2 = this.getCentroidYOnScreen();
        return Vector2D.calculateVectorBetween(x1,y1,x2,y2);
    }

    public boolean isHit(HitBox hb){
        for(int i = 0; i < hb.getBoundsResolution(); i++){
            if(isHit(hb.bounds.xpoints[i] + hb.getXPosOnScreen(), hb.bounds.ypoints[i] + hb.getYPosOnScreen())){
                return true;
            }
        }
        return false;
    }

    public boolean isHit(int xPosOnScreen, int yPosOnScreen){
        int relXPos = xPosOnScreen - this.getXPosOnScreen();
        int relYPos = yPosOnScreen - this.getYPosOnScreen();
        if(bounds.contains(relXPos, relYPos)){
            return true;
        }
       return false;
    }

    public int getCentroidX(){
        return centroid.x;
    }

    public int getCentroidXOnScreen(){
        return getCentroidX() + getXPosOnScreen();
    }

    public int getCentroidY(){
        return centroid.y;
    }

    public int getCentroidYOnScreen(){
        return getCentroidY() + getYPosOnScreen();
    }

    public int getBoundsResolution(){
        return bounds.npoints;
    }

    public Point[] getBounds(){
        Point[] points = new Point[bounds.npoints];
        for(int i = 0; i < points.length; i++){
            points[i] = new Point(bounds.xpoints[i], bounds.ypoints[i]);
        }
        return points;
    }

    @Override
    public int getXPosOnScreen() {
        return hboObject.getXPosOnScreen();
    }

    @Override
    public int getYPosOnScreen() {
        return hboObject.getYPosOnScreen();
    }
}

package fsteel.gameclock.entity;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HitBox extends Polygon {

    public static HitBox createSquareHitbox(int width, int height){
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        m.put(0, 0);
        m.put(0, width);
        m.put(height, width);
        m.put(height, 0);
        return new HitBox(m);
    }

    public HitBox(Map<Integer, Integer> boundPoints){
        for(int i : boundPoints.keySet()){
            super.addPoint(i, boundPoints.get(i));
        }
    }

    /**
     * Please create a new HitBox instead, this method is overwritten
     */
    @Override
    @Deprecated
    public void addPoint(int xPos, int yPos){}
}

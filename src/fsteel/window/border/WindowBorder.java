package fsteel.window.border;

import fsteel.gameclock.entity.MoveAbleEntity;
import fsteel.gameclock.entity.TouchAbleEntity;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.HitBox;
import fsteel.gameclock.entity.hitBox.Touch;
import fsteel.gameclock.entity.hitBox.TouchCheckProcess;
import fsteel.gameclock.entity.hitBox.TouchEmitter;
import fsteel.main.GameSettings;

import javax.swing.border.Border;
import java.awt.*;

public class WindowBorder extends Rectangle{

    public static final int SOUTH_BORDER = 0;
    public static final int NORTH_BORDER = 1;
    public static final int WEST_BORDER = 2;
    public static final int EAST_BORDER = 3;

    private VerticalBorderElement verticalBorderElement;
    private HorizontalBorderElement horizontalBorderElement;

    public WindowBorder(){
        super(GameSettings.CONSTANT_FOR_NORMAL_SCREEN_WIDTH, GameSettings.CONSTANT_FOR_NORMAL_SCREEN_HEIGHT);
        verticalBorderElement = new VerticalBorderElement(super.width);
        horizontalBorderElement = new HorizontalBorderElement(super.height);
        TouchCheckProcess.addTouchEmitter(verticalBorderElement);
        TouchCheckProcess.addTouchEmitter(horizontalBorderElement);
    }

    private static class HorizontalBorderElement implements TouchEmitter {

        private int screenHeight;

        private HorizontalBorderElement(int screenHeight){
            this.screenHeight = screenHeight;
        }

        @Override
        public int isTouching(TouchAbleEntity tae) {
            if(tae.getHitBox().getMaxYOnScreen() >= screenHeight){
                return SOUTH_BORDER;
            }
            if(tae.getHitBox().getMinYOnScreen() <= 0){
                return NORTH_BORDER;
            }
            return TouchEmitter.NOT_TOUCHED;
        }

        @Override
        public Touch getTouchFor(TouchAbleEntity tae, int touchCode) {
            double touchForce = calculateHorizontalTouchForce(tae);
            if(touchCode == SOUTH_BORDER){
                Vector2D touchVector = new Vector2D(0,-1);
                return new Touch(touchVector, touchForce, Touch.COLLISION_WITH_BORDER, this);
            }
            if(touchCode == NORTH_BORDER){
                Vector2D touchVector = new Vector2D(0,1);
                return new Touch(touchVector, touchForce, Touch.COLLISION_WITH_BORDER, this);
            }
            return null;
        }

        //TODO write a physical entity
        private double calculateHorizontalTouchForce(TouchAbleEntity tae){
            return Touch.FORCE_DEFAULT;
        }
    }

    private static class VerticalBorderElement implements TouchEmitter{
        private int screenWidth;

        private VerticalBorderElement(int screenWidth){
            this.screenWidth = screenWidth;
        }

        @Override
        public int isTouching(TouchAbleEntity tae) {
            if(tae.getHitBox().getMaxXOnScreen() >= screenWidth){
                return EAST_BORDER;
            }
            if(tae.getHitBox().getMinXOnScreen() <= 0){
                return WEST_BORDER;
            }
            return TouchEmitter.NOT_TOUCHED;
        }

        @Override
        public Touch getTouchFor(TouchAbleEntity tae, int touchCode) {
            double touchForce = calculateVerticalTouchForce(tae);
            if(touchCode == EAST_BORDER){
                Vector2D touchVector = new Vector2D(-1,0);
                return new Touch(touchVector, touchForce, Touch.COLLISION_WITH_BORDER, this);
            }
            if(touchCode == WEST_BORDER){
                Vector2D touchVector = new Vector2D(1, 0);
                return new Touch(touchVector, touchForce, Touch.COLLISION_WITH_BORDER, this);
            }
            return null;
        }

        //TODO write a physical entity
        private double calculateVerticalTouchForce(TouchAbleEntity tae){
            return Touch.FORCE_DEFAULT;
        }
    }
}

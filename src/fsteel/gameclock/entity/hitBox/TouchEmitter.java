package fsteel.gameclock.entity.hitBox;

import fsteel.gameclock.entity.TouchAbleEntity;

public interface TouchEmitter {

    public static final int NOT_TOUCHED = -1;

    /**
     * @return the touch code, -1, if the entity wasn't touched
     */
    public int isTouching(TouchAbleEntity tae);
    public Touch getTouchFor(TouchAbleEntity tae, int touchCode);

}

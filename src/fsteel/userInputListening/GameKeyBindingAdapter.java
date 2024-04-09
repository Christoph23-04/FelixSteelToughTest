package fsteel.userInputListening;

public class GameKeyBindingAdapter extends GameKeyBinding{


    public GameKeyBindingAdapter(String actionName, String keyName) {
        super(actionName, keyName);
    }

    @Override
    protected void onKeyPressed() {}

    @Override
    protected void onKeyReleased() {}
}

package sw19.moring03.paint.util;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.espresso.action.Tapper;

import static android.support.test.espresso.action.ViewActions.actionWithAssertions;

public final class Interaction {

    public static ViewAction touchAt(int x, int y) {
        return touchAt((float) x, (float) y);
    }

    public static ViewAction touchAt(float x, float y) {
        return touchAt(x, y, Tap.SINGLE);
    }

    public static ViewAction touchAt(float x, float y, Tapper tapper) {
        return actionWithAssertions(new GeneralClickAction(tapper,
                new CoordProvider(x, y), Press.FINGER, 0, 0));
    }

}



package sw19.moring03.paint.util;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.Swiper;
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

    public static ViewAction swipe(int start_x, int start_y, int end_x, int end_y) {
        return swipe((float) start_x, (float) start_y, (float) end_x, (float) end_y);
    }

    public static ViewAction swipe(float start_x, float start_y, float end_x, float end_y) {
        return swipe(start_x, start_y, end_x, end_y, Swipe.SLOW);
    }

    public static ViewAction swipe(float start_x, float start_y, float end_x, float end_y, Swiper swiper) {
        return actionWithAssertions(new GeneralSwipeAction(swiper,
                new CoordProvider(start_x, start_y),
                new CoordProvider(end_x, end_y), Press.PINPOINT));
    }

    public static ViewAction changeValueOfStrokeWidthSeekBar(int pathWidth) {
        return actionWithAssertions(new GeneralSwipeAction(Swipe.SLOW,
                new SeekBarCoordProvider(0), new SeekBarCoordProvider(pathWidth), Press.PINPOINT));
    }
}



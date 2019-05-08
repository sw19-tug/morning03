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

    public static ViewAction swipe(int xStart, int yStart, int xEnd, int yEnd) {
        return swipe((float) xStart, (float) yStart, (float) xEnd, (float) yEnd);
    }

    public static ViewAction swipe(float xStart, float yStart, float xEnd, float yEnd) {
        return swipe(xStart, yStart, xEnd, yEnd, Swipe.SLOW);
    }

    public static ViewAction swipe(float xStart, float yStart, float xEnd, float yEnd, Swiper swiper) {
        return actionWithAssertions(new GeneralSwipeAction(swiper,
                new CoordProvider(xStart, yStart),
                new CoordProvider(xEnd, yEnd), Press.PINPOINT));
    }

    public static ViewAction changeValueOfStrokeWidthSeekBar(int pathWidth) {
        return actionWithAssertions(new GeneralSwipeAction(Swipe.SLOW,
                new SeekBarCoordProvider(0), new SeekBarCoordProvider(pathWidth), Press.PINPOINT));
    }
}



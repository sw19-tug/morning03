package sw19.moring03.paint;

import static org.junit.Assert.assertEquals;

import android.graphics.DashPathEffect;
import android.graphics.PathEffect;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LineTypeTouchTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnDashedLine() {
        PathEffect effect = new DashPathEffect(new float[]{20, 25, 20, 25}, 0);

        onView(withId(R.id.lineTypeChooserButton)).perform(click());
        onView(withId(R.id.dashedLine)).perform(click());

        assertEquals(effect.getClass(),launchActivityRule.getActivity().getPathEffect().getClass());
    }

    @Test
    public void clickOnSolidLine() {
        PathEffect effect = new PathEffect();

        onView(withId(R.id.lineTypeChooserButton)).perform(click());
        onView(withId(R.id.solidLine)).perform(click());

        assertEquals(effect.getClass(),launchActivityRule.getActivity().getPathEffect().getClass());
    }

    @Test
    public void clickOnDashedDottedLine() {
        PathEffect effect = new DashPathEffect(new float[]{5, 10, 20, 10}, 0);

        onView(withId(R.id.lineTypeChooserButton)).perform(click());
        onView(withId(R.id.dashedLine)).perform(click());

        assertEquals(effect.getClass(),launchActivityRule.getActivity().getPathEffect().getClass());
    }

    @Test
    public void clickOnDottedLine() {
        PathEffect effect = new DashPathEffect(new float[]{5, 10, 5, 10}, 0);

        onView(withId(R.id.lineTypeChooserButton)).perform(click());
        onView(withId(R.id.dashedLine)).perform(click());

        assertEquals(effect.getClass(),launchActivityRule.getActivity().getPathEffect().getClass());
    }

}

package sw19.moring03.paint;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import sw19.moring03.paint.Views.DrawingView;
import sw19.moring03.paint.tools.Tools;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static sw19.moring03.paint.util.Interaction.changeValueOfStrokeWidthSeekBar;
import static sw19.moring03.paint.util.Interaction.touchAt;

@RunWith(AndroidJUnit4.class)
public class LineTypeTouchTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnDashedLine() {
        onView(withId(R.id.lineTypeChooserButton)).perform(click());
    }

    @Test
    public void clickOnSolidLine() {
        onView(withId(R.id.lineTypeChooserButton)).perform(click());
    }

    @Test
    public void clickOnDashedDottedLine() {
        onView(withId(R.id.lineTypeChooserButton)).perform(click());
    }


    @Test
    public void clickDotedDottedLine() {
        onView(withId(R.id.lineTypeChooserButton)).perform(click());
    }

}

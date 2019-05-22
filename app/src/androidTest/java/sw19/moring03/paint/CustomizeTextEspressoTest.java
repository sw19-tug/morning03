package sw19.moring03.paint;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import sw19.moring03.paint.Views.DrawingView;
import sw19.moring03.paint.tools.Tools;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static sw19.moring03.paint.util.Interaction.changeValueOfStrokeWidthSeekBar;
import static sw19.moring03.paint.util.Interaction.touchAt;

public class CustomizeTextEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    
    @Test
    public void testSimpleTextInsert() {
        onView(withId(R.id.strokeWidthChooserButton)).perform(click());
        onView(withId(R.id.strokeWidth)).perform(changeValueOfStrokeWidthSeekBar(60));
        onView(withId(R.id.strokeWidth)).perform(pressBack());

        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.redSlider)).perform(changeValueOfStrokeWidthSeekBar(255));
        onView(withId(R.id.redSlider)).perform(pressBack());

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawTextButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(450, 450));

        onView(withId(R.id.editText)).perform(typeText("Hello World"));
        onView(withId(R.id.editText)).check(matches(withText("Hello World")));

        DrawingView view = activityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.getObjectsToPaint();
        Tools tool = pointsToDraw.get(pointsToDraw.size()-1);
        assertEquals(tool.getColor(), activityRule.getActivity().getChosenColor());
        assertEquals(tool.getStrokeWidth(), activityRule.getActivity().getStrokeWidth());


    }


}

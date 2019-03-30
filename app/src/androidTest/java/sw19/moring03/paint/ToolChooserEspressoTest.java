package sw19.moring03.paint;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import sw19.moring03.paint.utils.Tool;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ToolChooserEspressoTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {R.id.toolChooserButton, R.id.drawPointButton, Tool.DRAW_POINT, R.id.toolChooserMenu},
                {R.id.toolChooserButton, R.id.drawLineButton, Tool.DRAW_LINE, R.id.toolChooserMenu},
                {R.id.toolChooserButton, R.id.drawPathButton, Tool.DRAW_PATH, R.id.toolChooserMenu},
                {R.id.toolChooserButton, R.id.drawFillButton, Tool.FILL, R.id.toolChooserMenu},
                {R.id.toolChooserButton, R.id.eraserButton, Tool.ERASER, R.id.toolChooserMenu},
                {R.id.toolChooserButton, R.id.drawCircleButton, Tool.DRAW_CIRCLE, R.id.toolChooserMenu},
                {R.id.toolChooserButton, R.id.drawRectangleButton, Tool.DRAW_RECTANGLE, R.id.toolChooserMenu}
        });
    }

    @Parameterized.Parameter
    public @IdRes int menuButtonId;

    @Parameterized.Parameter(1)
    public @IdRes int selectedButtonInMenu;

    @Parameterized.Parameter(2)
    public @IdRes Tool chosenToolOption;

    @Parameterized.Parameter(3)
    public @IdRes int xmlBottomSheet;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMenuButtonVisibility() {
        onView(withId(menuButtonId)).check(matches(isDisplayed()));
    }

    @Test
    public void testToolChooserBottomSheetVisibility() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(xmlBottomSheet)).check(matches(isDisplayed()));
    }
    @Test
    public void testChooserMenuButtons() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(selectedButtonInMenu)).check(matches(isDisplayed()));
    }

    @Test
    public void testToolSelected() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(selectedButtonInMenu)).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), chosenToolOption);
        onView(withId(menuButtonId)).check(matches(isDisplayed()));
    }
}
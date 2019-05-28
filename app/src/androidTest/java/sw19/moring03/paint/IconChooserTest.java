package sw19.moring03.paint;

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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IconChooserTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {R.id.toolChooserButton, R.id.drawIconButton, "smiley face", Tool.DRAW_ICON, R.drawable.emoji1},
        });
    }

    @Parameterized.Parameter
    public @IdRes int menuButtonId;

    @Parameterized.Parameter(1)
    public @IdRes int drawIconButtonId;

    @Parameterized.Parameter(2)
    public @IdRes String selectedOptionInDialog;

    @Parameterized.Parameter(3)
    public @IdRes Tool selectedTool;

    @Parameterized.Parameter(4)
    public @IdRes int selectedIcon;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMenuButtonVisibility() {
        onView(withId(menuButtonId)).check(matches(isDisplayed()));
    }

    @Test
    public void testIconChooserButtonVisibility() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(drawIconButtonId)).check(matches(isDisplayed()));
    }

    @Test
    public void testIconChooserDialogVisibility() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(drawIconButtonId)).perform(click());
        onView(withText(R.string.iconChooserFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void testIconAvailable() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(drawIconButtonId)).perform(click());
        onView(withText(selectedOptionInDialog)).check(matches(isDisplayed()));
    }

    @Test
    public void testIconSelected() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(drawIconButtonId)).perform(click());
        onView(withText(selectedOptionInDialog)).perform(click());
        Tool chosenTool = activityTestRule.getActivity().getChosenTool();
        int chosenIcon = activityTestRule.getActivity().getIconToDraw();

        assertEquals(selectedTool, chosenTool);
        assertEquals(selectedIcon, chosenIcon);
    }


}
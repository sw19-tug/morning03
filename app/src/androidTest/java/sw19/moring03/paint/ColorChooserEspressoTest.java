package sw19.moring03.paint;

import android.support.annotation.IdRes;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ColorChooserEspressoTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {R.id.colorChooserButton, R.id.whiteButton, Color.WHITE, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.yellowButton, Color.YELLOW, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.orangeButton, Color.ORANGE, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.redButton, Color.RED, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.purpleButton, Color.PURPLE, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.magentaButton, Color.MAGENTA, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.turkisButton, Color.TURKIS, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.lightBlueButton, Color.LIGHT_BLUE, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.lightGreenButton, Color.LIGHT_GREEN, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.darkBlueButton, Color.DARK_BLUE, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.darkGreenButton, Color.DARK_GREEN, R.id.colorChooserMenu}
        });
    }

    @Parameterized.Parameter
    public @IdRes int menuButtonId;

    @Parameterized.Parameter(1)
    public @IdRes int selectedButtonInMenu;

    @Parameterized.Parameter(2)
    public @IdRes Color chosenColorOption;

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
    public void testColorSelected() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(selectedButtonInMenu)).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenColor(), chosenColorOption);
        onView(withId(menuButtonId)).check(matches(isDisplayed()));
    }


}
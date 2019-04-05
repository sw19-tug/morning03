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
                {R.id.colorChooserButton, R.id.whiteButton, R.color.white, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.yellowButton, R.color.yellow, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.orangeButton, R.color.orange, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.redButton, R.color.red, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.purpleButton, R.color.purple, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.magentaButton, R.color.magenta, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.turkisButton, R.color.turkis, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.lightBlueButton, R.color.lightBlue, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.lightGreenButton, R.color.lightGreen, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.darkBlueButton, R.color.darkBlue, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.darkGreenButton, R.color.darkGreen, R.id.colorChooserMenu},
                {R.id.colorChooserButton, R.id.blackButton, R.color.black, R.id.colorChooserMenu}
        });
    }

    @Parameterized.Parameter
    public @IdRes int menuButtonId;

    @Parameterized.Parameter(1)
    public @IdRes int selectedButtonInMenu;

    @Parameterized.Parameter(2)
    public @IdRes int chosenColorOption;

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
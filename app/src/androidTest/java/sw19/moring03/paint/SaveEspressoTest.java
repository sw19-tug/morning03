package sw19.moring03.paint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
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
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
public class SaveEspressoTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {R.id.saveButton}

        });
    }

    @Parameterized.Parameter
    public @IdRes int menuButtonId;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSaveButtonExists() {
        onView(withId(menuButtonId)).check(matches(isDisplayed()));

    }

    @Test
    public void testImageSaved() {
        onView(withId(menuButtonId)).perform(click());
        String uri = activityTestRule.getActivity().getLastSavedImageURI();
        try {
            Bitmap image = MediaStore.Images.Media.getBitmap(activityTestRule.getActivity().getContentResolver(), Uri.parse(uri));
            assertNotNull(image);
        } catch (Exception exc) {
            assertTrue(false);
        }
    }
}
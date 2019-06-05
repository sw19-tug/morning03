package sw19.moring03.paint;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class SaveEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSaveButtonExists() {
        //onView(withId(R.id.saveButton)).check(matches(isDisplayed()));
        try {
            onView(withId(R.id.saveButton)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException e) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withId(R.string.saveButton)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void testImageSaved() throws IOException {
        try {
            onView(withId(R.id.saveButton)).perform(click());
        } catch (NoMatchingViewException e) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(withId(R.id.saveButton)).perform(click());
        }


        onView(withId(R.id.saveButton)).perform(click());

        Uri imageURI = Uri.parse(activityTestRule.getActivity().getLastSavedImageURI());

        Bitmap image = MediaStore.Images.Media.getBitmap(activityTestRule.getActivity().getContentResolver(), imageURI);
        assertNotNull(image);

    }
}
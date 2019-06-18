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
public class StickerChooserTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {R.id.toolChooserButton, R.id.drawStickerButton, "happy", Tool.DRAW_STICKER, R.drawable.happy},
                {R.id.toolChooserButton, R.id.drawStickerButton, "sunglasses", Tool.DRAW_STICKER, R.drawable.sunglasses},
                {R.id.toolChooserButton, R.id.drawStickerButton, "thumbs up", Tool.DRAW_STICKER, R.drawable.thumbsup},
                {R.id.toolChooserButton, R.id.drawStickerButton, "dragon", Tool.DRAW_STICKER, R.drawable.dragon},
                {R.id.toolChooserButton, R.id.drawStickerButton, "blossom", Tool.DRAW_STICKER, R.drawable.blossom},
                {R.id.toolChooserButton, R.id.drawStickerButton, "peach", Tool.DRAW_STICKER, R.drawable.peach}
        });
    }

    @Parameterized.Parameter
    public @IdRes int menuButtonId;

    @Parameterized.Parameter(1)
    public @IdRes int drawStickerButtonId;

    @Parameterized.Parameter(2)
    public @IdRes String selectedOptionInDialog;

    @Parameterized.Parameter(3)
    public @IdRes Tool selectedTool;

    @Parameterized.Parameter(4)
    public @IdRes int selectedSticker;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMenuButtonVisibility() {
        onView(withId(menuButtonId)).check(matches(isDisplayed()));
    }

    @Test
    public void testStickerChooserButtonVisibility() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(drawStickerButtonId)).check(matches(isDisplayed()));
    }

    @Test
    public void testStickerChooserDialogVisibility() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(drawStickerButtonId)).perform(click());
        onView(withText(R.string.stickerChooserFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void testStickerAvailable() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(drawStickerButtonId)).perform(click());
        onView(withText(selectedOptionInDialog)).check(matches(isDisplayed()));
    }

    @Test
    public void testStickerSelected() {
        onView(withId(menuButtonId)).perform(click());
        onView(withId(drawStickerButtonId)).perform(click());
        onView(withText(selectedOptionInDialog)).perform(click());
        Tool chosenTool = activityTestRule.getActivity().getChosenTool();
        int chosenSticker = activityTestRule.getActivity().getStickerToDraw();

        assertEquals(selectedTool, chosenTool);
        assertEquals(selectedSticker, chosenSticker);
    }


}
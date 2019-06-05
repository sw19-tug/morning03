package sw19.moring03.paint;

import android.graphics.drawable.Drawable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import sw19.moring03.paint.Views.DrawingView;
import sw19.moring03.paint.tools.Tools;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static sw19.moring03.paint.util.Interaction.changeValueOfStrokeWidthSeekBar;
import static sw19.moring03.paint.util.Interaction.touchAt;

@RunWith(AndroidJUnit4.class)
public class UndoButtonTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testButtonVisibility() {
        onView(withId(R.id.undoButton)).check(doesNotExist());
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPointButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(400, 400));

        onView(withId(R.id.undoButton)).perform(click());
    }

    @Test
    public void testUndoLastObject() {
        onView(withId(R.id.undoButton)).check(doesNotExist());

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPointButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(400, 400));
        onView(withId(R.id.undoButton)).perform(click());

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.getObjectsToPaint();

        assertEquals(pointsToDraw.size(),0);
        onView(withId(R.id.undoButton)).check(doesNotExist());
    }

    @Test
    public void testUndoToolIcon() {
        Drawable expectedIcon = launchActivityRule.getActivity().getResources().getDrawable(R.drawable.ic_line_icon);

        onView(withId(R.id.undoButton)).check(doesNotExist());

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(swipeLeft());
        onView(withId(R.id.undoButton)).perform(click());

        Toolbar toolbar = launchActivityRule.getActivity().findViewById(R.id.toolbar);
        ActionMenuItemView toolChooser  = toolbar.findViewById(R.id.toolChooserButton);
        Drawable currentIcon = toolChooser.getItemData().getIcon();

        assertEquals(expectedIcon.getConstantState(),currentIcon.getConstantState());

        onView(withId(R.id.undoButton)).check(doesNotExist());
    }

    public void testUndoStrokeWidth() {
        String expectedTitle = "35pt";

        onView(withId(R.id.undoButton)).check(doesNotExist());

        onView(withId(R.id.strokeWidthChooserButton)).perform(click());
        onView(withId(R.id.strokeWidth)).perform(changeValueOfStrokeWidthSeekBar(35));

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());

        onView(withId(R.id.drawingView)).perform(swipeLeft());
        onView(withId(R.id.undoButton)).perform(click());

        Toolbar toolbar = launchActivityRule.getActivity().findViewById(R.id.toolbar);
        ActionMenuItemView toolChooser  = toolbar.findViewById(R.id.toolChooserButton);
        String currentTitle = toolChooser.getItemData().getTitle().toString();

        assertEquals(expectedTitle,currentTitle);

        onView(withId(R.id.undoButton)).check(doesNotExist());

    }
}

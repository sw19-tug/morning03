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
import static sw19.moring03.paint.util.Interaction.touchAt;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class RedoButtonTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testButtonVisibility() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPointButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(400, 400));
        onView(withId(R.id.undoButton)).perform(click());

        onView(withId(R.id.redoButton)).perform(click());
    }

    @Test
    public void testRedoLastObject() {
        onView(withId(R.id.redoButton)).check(doesNotExist());

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPointButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(400, 400));
        onView(withId(R.id.undoButton)).perform(click());

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.drawingObjectManager.getObjectsToPaint();

        onView(withId(R.id.redoButton)).perform(click());

        assertEquals(pointsToDraw.size(),0);
        onView(withId(R.id.redoButton)).check(doesNotExist());
    }

    @Test
    public void testRedoToolIcon() {
        Drawable expectedIcon = launchActivityRule.getActivity().getResources().getDrawable(R.drawable.ic_redo_black_24dp);

        onView(withId(R.id.redoButton)).check(doesNotExist());

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(swipeLeft());
        onView(withId(R.id.undoButton)).perform(click());
        Toolbar toolbar = launchActivityRule.getActivity().findViewById(R.id.toolbar);
        ActionMenuItemView toolChooser = toolbar.findViewById(R.id.redoButton);
        Drawable currentIcon = toolChooser.getItemData().getIcon();

        assertEquals(expectedIcon.getConstantState(),currentIcon.getConstantState());

        onView(withId(R.id.redoButton)).perform(click());
        onView(withId(R.id.redoButton)).check(doesNotExist());
    }
}

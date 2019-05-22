package sw19.moring03.paint;

import android.graphics.drawable.Drawable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import sw19.moring03.paint.Views.DrawingView;
import sw19.moring03.paint.tools.Tools;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static sw19.moring03.paint.util.Interaction.touchAt;
import static org.junit.Assert.assertEquals;

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
    public void testUndoToolIcon(){

        onView(withId(R.id.undoButton)).check(doesNotExist());

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(swipeLeft());
        onView(withId(R.id.undoButton)).perform(click());

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        Toolbar toolbar = launchActivityRule.getActivity().findViewById(R.id.toolbar);
        ActionMenuItemView toolchooser  = toolbar.findViewById(R.id.toolChooserButton);
        Drawable test = toolchooser.getItemData().getIcon();

        assertEquals(launchActivityRule.getActivity().getResources().getDrawable(R.drawable.ic_line_icon).getConstantState(),test.getConstantState());

        onView(withId(R.id.undoButton)).check(doesNotExist());
    }
}

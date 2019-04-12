package sw19.moring03.paint.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;


public class PhotoToolTest {
    private Canvas canvas;
    private Paint paint;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        canvas = new Canvas();
        paint = new Paint();

    }



    @After
    public void tearDown() throws Exception {
    }


    private Bitmap getBitmap()
    {
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(activityTestRule.getActivity().getResources(), R.drawable.test_image);
            Bitmap new_photo = bitmap;
            return new_photo;

        } catch (Exception ex) {}
        return null;
    }
}
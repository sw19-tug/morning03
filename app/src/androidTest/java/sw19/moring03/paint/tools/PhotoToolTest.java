package sw19.moring03.paint.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class PhotoToolTest {
    private Canvas canvas;
    private Paint paint;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        canvas = new Canvas();
        paint = new Paint();

    }

    @Test
    public void testValidImage() {
        Bitmap image = getBitmap();
        PhotoTool tool = new PhotoTool(image);
        tool.draw(canvas, paint);
        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testInvalidImage() {
        Bitmap image = null;
        PhotoTool tool = new PhotoTool(image);
        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testInvalidLineOnImage() {
        Bitmap tmp_image = getBitmap();
        PhotoTool photoTool = new PhotoTool(tmp_image);
        photoTool.draw(canvas, paint);
    }


    private Bitmap getBitmap() {
        return BitmapFactory.decodeResource(activityTestRule.getActivity().getResources(), R.drawable.test_image);
    }
}
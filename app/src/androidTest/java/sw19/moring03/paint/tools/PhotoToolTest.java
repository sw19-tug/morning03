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
    public void testDrawOnImage() {
        Bitmap image = getBitmap();
        PhotoTool photoTool = new PhotoTool(image);
        photoTool.draw(canvas, paint);

        LineTool tool = new LineTool(5);
        tool.addPoint(new PointF(10, 10));

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testAddPointsOnImage() {
        List<PointF> expectedPoints = new ArrayList<>();
        expectedPoints.add(new PointF(10, 10));
        expectedPoints.add(new PointF(20, 20));
        expectedPoints.add(new PointF(30, 30));

        PathTool tool = new PathTool(5);
        tool.addPoint(expectedPoints.get(0));
        tool.addPoint(expectedPoints.get(1));
        tool.addPoint(expectedPoints.get(2));

        List<PointF> points = tool.getPoints();
        assertArrayEquals(expectedPoints.toArray(), points.toArray());
    }

    @Test
    public void testDrawShapesOnImage() {
        OvalTool tool = new OvalTool();

        tool.addPoint(new PointF(15, 15));
        tool.addPoint(new PointF(30, 30));
        tool.addPoint(new PointF(60, 60));
        tool.addPoint(new PointF(90, 90));

        Assert.assertTrue(tool.draw(canvas, paint));
    }


    @Test
    public void testInvalidLineOnImage() {
        Bitmap tmp_image = getBitmap();
        PhotoTool photoTool = new PhotoTool(tmp_image);
        photoTool.draw(canvas, paint);
    }

    @After
    public void tearDown(){
    }


    private Bitmap getBitmap() {
        return BitmapFactory.decodeResource(activityTestRule.getActivity().getResources(), R.drawable.test_image);
    }
}
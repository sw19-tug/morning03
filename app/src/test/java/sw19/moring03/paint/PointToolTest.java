package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.PointTool;
import sw19.moring03.paint.utils.PointF;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class PointToolTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void setUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void basicPointDrawTest() {
        PointTool ptool = new PointTool(Color.BLACK, 10);
        ptool.addPoint(new PointF(5, 5));
        ptool.addPoint(new PointF(30, 30));
        assertTrue(ptool.draw(canvas, paint));
    }

    @Test
    public void testDrawMultiplePoints() {
        final int expectedPoints = 1;

        PointTool tool = new PointTool(Color.BLACK, 10);
        tool.addPoint(new PointF(1, 1));
        tool.addPoint(new PointF(2, 2));
        tool.addPoint(new PointF(3, 3));
        tool.addPoint(new PointF(4, 4));

        canvas = Mockito.mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expectedPoints)).drawCircle(Mockito.anyFloat(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }

    @Test
    public void testInvalidOval() {
        PointTool tool = new PointTool(Color.BLACK, 10);

        assertFalse(tool.draw(canvas, paint));
    }
}
package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.FillTool;
import sw19.moring03.paint.utils.PointF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FillTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testNumberOfPoints() {
        FillTool tool = new FillTool(Color.BLACK, null, 10, 10);
        tool.addPoint(new PointF(15, 15));
        tool.addPoint(new PointF(20, 20));

        assertTrue(tool.getPoints().size() == 1);
    }

    @Test
    public void testInvalidFill() {
        FillTool tool = new FillTool(Color.BLACK, null, 10, 10);
        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testFillOfWholeDrawingArea() {
        final int expectedLines = 10;

        int[] frame = new int[100];

        canvas = Mockito.spy(Canvas.class);

        FillTool tool = new FillTool(Color.BLACK, frame, 10, 10);
        tool.addPoint(new PointF(0, 0));
        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expectedLines)).drawLine(
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }

    @Test
    public void testFillRectangleWithSinglePoint() {
        final int expectedPoints = 2;

        int[] frame = new int[] {
                1, 1, 1,
                1, 0, 1,
                1, 1, 1
        };

        FillTool tool = new FillTool(Color.BLACK, frame, 3, 3);
        tool.scanlineFloodFillStack(1, 1, 0);

        assertEquals(expectedPoints, tool.getPoints().size());
    }

    @Test
    public void testFillCircle() {
        final int expectedPoints = 6;

        int[] frame = new int[] {
                0, 1, 1, 1, 0,
                1, 0, 0, 0, 1,
                1, 0, 0, 0, 1,
                1, 0, 0, 0, 1,
                0, 1, 1, 1, 0
        };

        FillTool tool = new FillTool(Color.BLACK, frame, 5, 5);
        tool.scanlineFloodFillStack(2, 2, 0);

        assertEquals(expectedPoints, tool.getPoints().size());
    }

    @Test
    public void testFillHeartShape() {
        final int expectedPoints = 10;

        int[] frame = new int[] {
                0, 1, 0, 1, 0,
                1, 0, 1, 0, 1,
                1, 0, 0, 0, 1,
                1, 0, 0, 0, 1,
                0, 1, 0, 1, 0,
                0, 0, 1, 0, 0
        };

        FillTool tool = new FillTool(Color.BLACK, frame, 5, 6);
        tool.scanlineFloodFillStack(2, 2, 0);

        assertEquals(expectedPoints, tool.getPoints().size());
    }

    @Test
    public void testFillWithSameColor() {
        int[] frame = new int[] {
                0, 1, 1, 1, 0,
                1, 0, 0, 0, 1,
                1, 0, 0, 0, 1,
                1, 0, 0, 0, 1,
                0, 1, 1, 1, 0
        };

        FillTool tool = new FillTool(0, frame, 5, 5);
        tool.addPoint(new PointF(2,2));
        assertTrue(tool.draw(canvas, paint));
    }
}


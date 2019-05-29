package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.tools.RectangleTool;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RectangleTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testRectangleSimple() {
        RectangleTool tool = new RectangleTool(Color.BLACK, 10);
        assertEquals(Color.BLACK, tool.getColor());
        assertEquals(10, tool.getStrokeWidth());

        tool.addPoint(new PointF(15, 15));
        tool.addPoint(new PointF(30, 30));
        tool.addPoint(new PointF(60, 60));
        tool.addPoint(new PointF(90, 90));

        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testInvalidRectangle() {
        RectangleTool tool = new RectangleTool();
        tool.addPoint(new PointF(10, 10));

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testDrawRectangle() {
        int expectedRectangles = 1;

        RectangleTool tool = new RectangleTool();

        tool.addPoint(new PointF(10, 10));
        tool.addPoint(new PointF(20, 20));
        tool.addPoint(new PointF(30, 30));
        tool.addPoint(new PointF(40, 40));

        canvas = Mockito.mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expectedRectangles)).drawRect(Mockito.anyFloat(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }

    @Test
    public void testAddPoints() {
        List<PointF> addedPoints = new ArrayList<>();

        addedPoints.add(new PointF(10, 10));
        addedPoints.add(new PointF(20, 20));
        addedPoints.add(new PointF(30, 30));
        addedPoints.add(new PointF(40, 40));

        RectangleTool tool = new RectangleTool();

        for (int i = 0; i < addedPoints.size(); i++) {
            tool.addPoint(addedPoints.get(i));
        }

        assertEquals(2, tool.getPoints().size());
        assertEquals((int)addedPoints.get(0).x, (int)tool.getPoints().get(0).x);
        assertEquals((int)addedPoints.get(3).x, (int)tool.getPoints().get(1).x);
    }
}
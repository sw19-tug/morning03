package sw19.moring03.paint;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;


import sw19.moring03.paint.tools.PathTool;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PathTest {
    @Test
    public void testSimplePath() {
        List<PointF> points = new ArrayList<>();

        points.add(new PointF(10, 10));
        points.add(new PointF(20, 20));

        PathTool tool = new PathTool();

        assertTrue(tool.draw(points));
    }

    @Test
    public void testInvalidPath() {
        List<PointF> points = new ArrayList<>();
        points.add(new PointF(10, 10));

        PathTool tool = new PathTool();

        assertFalse(tool.draw(points));
    }

    @Test
    public void testDrawPath() {
        final int expectedDrawnLines = 2;

        List<PointF> points = new ArrayList<>();
        points.add(new PointF(10, 10));
        points.add(new PointF(20, 20));
        points.add(new PointF(30, 30));

        PathTool tool = new PathTool();
        tool.canvas = Mockito.mock(Canvas.class, Mockito.CALLS_REAL_METHODS);
        Mockito.doNothing().when(tool.canvas).drawLine(Mockito.anyFloat(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));

        tool.draw(points);

        Mockito.verify(tool.canvas, Mockito.times(expectedDrawnLines)).drawLine(Mockito.anyFloat(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }

    @Test
    public void testInsertTouchPoints() {
        List<PointF> expectedPoints = new ArrayList<>();
        expectedPoints.add(new PointF(10, 10));
        expectedPoints.add(new PointF(20, 20));
        expectedPoints.add(new PointF(30, 30));

        PathTool tool = new PathTool();
        tool.addTouchPoint(expectedPoints.get(0));
        tool.addTouchPoint(expectedPoints.get(1));
        tool.addTouchPoint(expectedPoints.get(2));

        List<PointF> points = tool.getTouchPoints();
        assertArrayEquals(expectedPoints.toArray(), points.toArray());
    }
}

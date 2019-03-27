package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import sw19.moring03.paint.tools.LineTool;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LineTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() throws Exception {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testLineSimple() {
        LineTool tool = new LineTool();

        tool.addPoint(new PointF(15, 15));
        tool.addPoint(new PointF(30, 30));
        tool.addPoint(new PointF(60, 60));
        tool.addPoint(new PointF(90, 90));

        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testDrawLine() {
        int expectedLines = 1;

        LineTool tools = new LineTool();

        tools.addPoint(new PointF(10, 10));
        tools.addPoint(new PointF(20, 20));
        tools.addPoint(new PointF(30, 30));
        tools.addPoint(new PointF(40, 40));

        canvas = Mockito.mock(Canvas.class);

        tools.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expectedLines)).drawLine(Mockito.anyFloat(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }

    @Test
    public void testAddPoints() {
        List<PointF> expectedPoints = new ArrayList<>();

        expectedPoints.add(new PointF(10, 10));
        expectedPoints.add(new PointF(20, 20));
        expectedPoints.add(new PointF(30, 30));
        expectedPoints.add(new PointF(30, 30));

        LineTool tool = new LineTool();

        tool.addPoint(expectedPoints.get(0));
        tool.addPoint(expectedPoints.get(1));
        tool.addPoint(expectedPoints.get(2));
        tool.addPoint(expectedPoints.get(3));

        List<PointF> points = tool.getPoints();
        assertArrayEquals(expectedPoints.toArray(), points.toArray());

    }

}
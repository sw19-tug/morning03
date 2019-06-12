package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.utils.PointF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LineTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() {
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
    public void testInvalidLine() {
        LineTool tool = new LineTool();
        tool.addPoint(new PointF(10, 10));

        assertFalse(tool.draw(canvas, paint));
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

        Mockito.verify(canvas, Mockito.times(expectedLines)).drawPath(Mockito.any(Path.class), Mockito.any(Paint.class));
    }

    @Test
    public void testAddPoints() {
        List<PointF> addedPoints = new ArrayList<>();

        addedPoints.add(new PointF(10, 10));
        addedPoints.add(new PointF(20, 20));
        addedPoints.add(new PointF(30, 30));
        addedPoints.add(new PointF(40, 40));

        LineTool tool = new LineTool();
        for (int i = 0; i < addedPoints.size(); i++) {
            tool.addPoint(addedPoints.get(i));
        }

        assertEquals(2, tool.getPoints().size());
        assertEquals((int)addedPoints.get(0).x, (int)tool.getPoints().get(0).x);
        assertEquals((int)addedPoints.get(3).x, (int)tool.getPoints().get(1).x);
    }
}
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

import sw19.moring03.paint.tools.PathTool;
import sw19.moring03.paint.utils.PointF;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PathTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void setUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testSimplePath() {
        PathTool tool = new PathTool();
        tool.addPoint(new PointF(10, 10));
        tool.addPoint(new PointF(20, 20));

        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testInvalidPath() {
        PathTool tool = new PathTool();
        tool.addPoint(new PointF(10, 10));

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testNoPoint() {
        PathTool tool = new PathTool();

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testDrawPath() {
        PathTool tool = new PathTool();
        tool.addPoint(new PointF(10, 10));
        tool.addPoint(new PointF(20, 20));
        tool.addPoint(new PointF(30, 30));

        canvas = Mockito.mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(1)).drawPath(Mockito.any(Path.class), Mockito.any(Paint.class));
    }

    @Test
    public void testAddPoints() {
        List<PointF> expectedPoints = new ArrayList<>();
        expectedPoints.add(new PointF(10, 10));
        expectedPoints.add(new PointF(20, 20));
        expectedPoints.add(new PointF(30, 30));

        PathTool tool = new PathTool();
        tool.addPoint(expectedPoints.get(0));
        tool.addPoint(expectedPoints.get(1));
        tool.addPoint(expectedPoints.get(2));

        List<PointF> points = tool.getPoints();
        assertArrayEquals(expectedPoints.toArray(), points.toArray());
    }
}

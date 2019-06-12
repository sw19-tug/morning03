package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.utils.PointF;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LineTestWithLineType {
    private Canvas canvas;
    private Paint paint;
    private PathEffect pathEffect;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testPathLineSimple() {
        pathEffect = new DashPathEffect(new float[]{20, 25, 20, 25}, 0);
        paint.setPathEffect(pathEffect);

        LineTool tool = new LineTool();

        tool.addPoint(new PointF(15, 15));
        tool.addPoint(new PointF(30, 30));
        tool.addPoint(new PointF(60, 60));
        tool.addPoint(new PointF(90, 90));

        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testPathInvalidLine() {
        pathEffect = new DashPathEffect(new float[]{5, 10, 5, 10}, 0);
        paint.setPathEffect(pathEffect);

        LineTool tool = new LineTool();
        tool.addPoint(new PointF(10, 10));

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testPathDrawLine() {
        pathEffect = new DashPathEffect(new float[]{5, 10, 20, 10}, 0);
        paint.setPathEffect(pathEffect);

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
}
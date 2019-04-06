package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.FillTool;

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
    public void testSimpleFillTool() {
        FillTool tool = new FillTool();
        tool.addPoint(new PointF(15, 15));
        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testInvalidFill() {
        FillTool tool = new FillTool();
        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testDrawLines() {
        int expectedLines = 2;

        FillTool tool = new FillTool();

        tool.addPoint(new PointF(0, 0));
        tool.addPoint(new PointF(10, 0));
        tool.addPoint(new PointF(0, 1));
        tool.addPoint(new PointF(10, 1));

        canvas = Mockito.mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expectedLines)).drawLine(
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }
}
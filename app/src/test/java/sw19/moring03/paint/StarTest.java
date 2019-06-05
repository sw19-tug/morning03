package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.StarTool;
import sw19.moring03.paint.utils.PointF;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StarTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testStarSimple() {
        StarTool tool = new StarTool(Color.BLACK, 10);
        assertEquals(Color.BLACK, tool.getColor());
        assertEquals(10, tool.getStrokeWidth());

        tool.addPoint(new PointF(15, 15));
        tool.addPoint(new PointF(30, 30));
        tool.addPoint(new PointF(60, 60));

        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testInvalidStar() {
        StarTool tool = new StarTool();
        tool.addPoint(new PointF(10, 10));

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testDrawStar() {
        int expectedLines = 5;

        StarTool tool = new StarTool();

        tool.addPoint(new PointF(10, 10));
        tool.addPoint(new PointF(20, 20));

        canvas = Mockito.mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expectedLines)).drawLine(Mockito.anyFloat(), Mockito.anyFloat(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }

}
package sw19.moring03.paint;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.PointTool;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PointToolTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void setUp() throws Exception {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void basicPointDrawTest() {
        PointTool ptool = new PointTool();
        ptool.addPoint(new PointF(5, 5));
        ptool.addPoint(new PointF(30, 30));

        assertTrue(ptool.draw(canvas, paint));
    }

    @Test
    public void testDrawMultiplePoints() {
        final int expectedPints = 4;

        PointTool tool = new PointTool();
        tool.addPoint(new PointF(1, 1));
        tool.addPoint(new PointF(2, 2));
        tool.addPoint(new PointF(3, 3));
        tool.addPoint(new PointF(4, 4));

        canvas = Mockito.mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expectedPints)).drawLine(Mockito.anyFloat(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }
}
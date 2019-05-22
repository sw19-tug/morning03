package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.TextTool;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TextTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testText() {
        TextTool tool = new TextTool();
        tool.addPoint(new PointF(15, 15));

        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testNoPoint() {
        TextTool tool = new TextTool();

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testOnlyOnePoint() {
        TextTool tool = new TextTool();
        tool.addPoint(new PointF(15, 15));
        tool.addPoint(new PointF(25, 25));
        tool.addPoint(new PointF(35, 35));

        assertTrue(tool.getPoints().size() == 1);
    }

    @Test
    public void testDrawText() {
        int expected = 1;

        TextTool tool = new TextTool();
        tool.addPoint(new PointF(10, 10));

        canvas = Mockito.mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expected)).drawText(Mockito.anyString(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }
}
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

import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class CustomizeTextTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();
    }
    @Test
    public void testDrawTextWithColor() {
        int expected = 1;

        TextTool tool = new TextTool();
        tool.addPoint(new PointF(10, 10));

        canvas = Mockito.mock(Canvas.class);

        paint.setColor(R.color.yellow);
        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expected)).drawText(Mockito.anyString(),
                Mockito.anyFloat(), Mockito.anyFloat(), eq(paint));

    }
    @Test
    public void testDrawTextChangeSize()
    {
        int expected = 1;

        TextTool tool = new TextTool();
        tool.addPoint(new PointF(10, 10));

        canvas = Mockito.mock(Canvas.class);

        paint.setTextSize(50);
        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expected)).drawText(Mockito.anyString(),
                Mockito.anyFloat(), Mockito.anyFloat(), eq(paint));
    }
}
package sw19.moring03.paint;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.TextTool;
import sw19.moring03.paint.utils.PointF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TextTest {
    private Canvas canvas;
    private Paint paint;

    private Context context;
    private Resources res;
    private DisplayMetrics metrics;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();

        context = Mockito.mock(Context.class);
        res = Mockito.mock(Resources.class);
        metrics = Mockito.mock(DisplayMetrics.class);

        when(context.getResources()).thenReturn(res);
        when(res.getDisplayMetrics()).thenReturn(metrics);
    }

    @Test
    public void testText() {
        TextTool tool = new TextTool(Color.BLACK, 10, context);
        tool.addPoint(new PointF(15, 15));

        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testNoPoint() {
        TextTool tool = new TextTool(Color.BLACK, 10, context);

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testOnlyOnePoint() {
        TextTool tool = new TextTool(Color.BLACK, 10, context);
        tool.addPoint(new PointF(15, 15));
        tool.addPoint(new PointF(25, 25));
        tool.addPoint(new PointF(35, 35));

        assertEquals(1, tool.getPoints().size());
    }

    @Test
    public void testDrawText() {
        int expected = 1;

        TextTool tool = new TextTool(Color.BLACK, 10, context);
        tool.addPoint(new PointF(10, 10));

        canvas = mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expected)).drawText(Mockito.anyString(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }

    @Test
    public void testTypeFace() {
        int expected = 1;

        metrics.scaledDensity = 40.5f;

        TextTool tool = new TextTool(Color.BLACK, 10, context);
        tool.addPoint(new PointF(10, 10));
        tool.setFont("crazy.ttf");
        tool.setText("HelloWorld");

        canvas = mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expected)).drawText(eq("HelloWorld"),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any(Paint.class));
    }
}
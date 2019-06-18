package sw19.moring03.paint;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.TextTool;
import sw19.moring03.paint.utils.PointF;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomizeTextTest {
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
    public void testDrawTextWithColor() {
        int expected = 1;

        TextTool tool = new TextTool(Color.BLACK, 10, context);
        tool.addPoint(new PointF(10, 10));

        canvas = Mockito.mock(Canvas.class);

        paint.setColor(R.color.yellow);
        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expected)).drawText(Mockito.anyString(),
                Mockito.anyFloat(), Mockito.anyFloat(), eq(paint));

    }
    @Test
    public void testDrawTextChangeSize() {
        int expected = 1;

        TextTool tool = new TextTool(Color.BLACK, 10, context);
        tool.addPoint(new PointF(10, 10));

        canvas = Mockito.mock(Canvas.class);

        paint.setTextSize(50);
        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expected)).drawText(Mockito.anyString(),
                Mockito.anyFloat(), Mockito.anyFloat(), eq(paint));
    }
    @Test
    public void testDrawTextChangeFont() {
        int expected = 1;

        TextTool tool = new TextTool(Color.BLACK, 10, context);
        tool.addPoint(new PointF(10, 10));


        canvas = Mockito.mock(Canvas.class);
        paint.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/normal.ttf"));
        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expected)).drawText(Mockito.anyString(),
                Mockito.anyFloat(), Mockito.anyFloat(), eq(paint));

    }
}
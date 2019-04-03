package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.EraseTool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EraseTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testSimpleErase() {
        EraseTool tool = new EraseTool();
        tool.addPoint(new PointF(10, 10));
        tool.addPoint(new PointF(20, 20));

        int tmp_color = paint.getColor();

        assertTrue(tool.draw(canvas, paint));
        assertEquals(tmp_color, paint.getColor());
    }

}
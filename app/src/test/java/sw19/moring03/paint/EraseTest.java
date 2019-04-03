package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.tools.EraseTool;
import sw19.moring03.paint.tools.LineTool;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EraseTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() throws Exception {
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
        assertTrue(tmp_color == paint.getColor());
    }

}
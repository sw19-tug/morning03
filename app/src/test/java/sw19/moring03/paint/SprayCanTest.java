package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.SprayCanTool;
import sw19.moring03.paint.utils.PointF;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SprayCanTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void setUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void basicPointDrawTest() {
        SprayCanTool tool = new SprayCanTool(Color.BLACK, 10);
        assertEquals(Color.BLACK, tool.getColor());
        assertEquals(10, tool.getStrokeWidth());

        tool.addPoint(new PointF(5, 5));
        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void falsePointDrawTest() {
        SprayCanTool tool = new SprayCanTool(Color.BLACK, 10);
        assertFalse(tool.draw(canvas, paint));
    }
}
package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.EraseTool;
import sw19.moring03.paint.tools.PathTool;
import sw19.moring03.paint.utils.PointF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        EraseTool tool = new EraseTool(10);
        tool.addPoint(new PointF(10, 10));
        tool.addPoint(new PointF(20, 20));

        int tmpColor = paint.getColor();

        assertTrue(tool.draw(canvas, paint));
        assertEquals(tmpColor, paint.getColor());
    }

    @Test
    public void testInvalidPointCount() {
        EraseTool tool = new EraseTool(10);
        assertEquals(10, tool.getStrokeWidth());

        assertFalse(tool.draw(canvas, paint));
        tool.addPoint(new PointF(10, 10));
        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testAddFirstPointToPath() {
        EraseTool tool = new EraseTool(10);
        tool.path = Mockito.mock(Path.class);
        Mockito.when(tool.path.isEmpty()).thenReturn(true);
        PointF firstPoint = new PointF(0, 0);
        tool.addPoint(firstPoint);
        Mockito.verify(tool.path, Mockito.times(1)).moveTo(firstPoint.x, firstPoint.y);
        assertFalse(tool.draw(canvas, paint));
    }
}
package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.SprayCanTool;

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
        SprayCanTool tool = new SprayCanTool();
        tool.addPoint(new PointF(5, 5));
        assertTrue(tool.draw(canvas, paint));
    }
}
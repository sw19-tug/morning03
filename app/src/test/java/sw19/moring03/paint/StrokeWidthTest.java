package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.tools.PointTool;
import sw19.moring03.paint.tools.PathTool;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StrokeWidthTest {
    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() throws Exception {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testStrokeWidthLine() {
        LineTool tool = new LineTool(Color.BLACK, 5);

        int lineStrokeWidth = tool.getStrokeWidth();
        boolean isInRange = false;

        if (lineStrokeWidth > 1  && lineStrokeWidth < 51) {
            isInRange = true;
        }

        assertTrue(isInRange);
    }

    @Test
    public void testStrokeWidthPath() {
        PathTool tool = new PathTool(Color.BLACK,5);

        int pathStrokeWidth = tool.getStrokeWidth();
        boolean isInRange = false;

        if (pathStrokeWidth > 1  && pathStrokeWidth < 51) {
            isInRange = true;
        }

        assertTrue(isInRange);
    }

    @Test
    public void testStrokeWidthPoint() {
        PointTool tool = new PointTool(Color.BLACK,5);

        int pointStrokeWidth = tool.getStrokeWidth();
        boolean isInRange = false;

        if (pointStrokeWidth > 1  && pointStrokeWidth < 51) {
            isInRange = true;
        }

        assertTrue(isInRange);
    }

    @Test
    public void testStrokeWidthPointFalse() {
        int pointStrokeWidth = 0;
        PointTool tool = new PointTool(Color.BLACK, pointStrokeWidth);

        pointStrokeWidth = tool.getStrokeWidth();
        boolean isInRange = false;

        if (pointStrokeWidth < 1  && pointStrokeWidth > 50) {
            isInRange = true;
        }
        assertFalse(isInRange);
    }

}
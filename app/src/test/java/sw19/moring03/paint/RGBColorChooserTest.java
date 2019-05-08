package sw19.moring03.paint;

import android.graphics.Color;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.tools.PathTool;
import sw19.moring03.paint.tools.PointTool;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RGBColorChooserTest {

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

        int pointStrokeWidth = tool.getStrokeWidth();
        boolean isInRange = false;

        if (pointStrokeWidth > 1  && pointStrokeWidth < 51) {
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

        if (pointStrokeWidth > 1  && pointStrokeWidth < 50) {
            isInRange = true;
        }

        assertFalse(isInRange);
    }

}
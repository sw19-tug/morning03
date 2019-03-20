package sw19.moring03.paint;


import android.graphics.PointF;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.tools.PathTool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PathTest {

    @Test
    public void testSimplePath() {
        List<PointF> points = new ArrayList<>();

        points.add(new PointF(10, 10));
        points.add(new PointF(20, 20));

        PathTool tool = new PathTool();

        assertTrue(tool.draw(points));
    }

    @Test
    public void testInvalidPath() {
        List<PointF> points = new ArrayList<>();
        points.add(new PointF(10, 10));

        PathTool tool = new PathTool();

        assertFalse(tool.draw(points));
    }

    @Test
    public void testDrawPath() {
        final int expectedDrawnLines = 2;
        int drawnLines = 0;

        List<PointF> points = new ArrayList<>();
        points.add(new PointF(10, 10));
        points.add(new PointF(20, 20));
        points.add(new PointF(30, 30));

        PathTool tool = new PathTool();
        tool.draw(points);

        assertEquals(expectedDrawnLines, drawnLines);
    }
}

package sw19.moring03.paint;

import android.content.Context;
import android.graphics.PointF;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.tools.PathTool;

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
}

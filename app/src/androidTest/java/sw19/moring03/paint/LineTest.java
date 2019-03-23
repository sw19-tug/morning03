package sw19.moring03.paint;

import android.graphics.PointF;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.tools.LineTool;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

@RunWith(AndroidJUnit4.class)
public class LineTest {

    @Test
    public void testSimpleLine() {


        List<PointF> linepoints = new ArrayList<>();

        linepoints.add(new PointF(50, 50));
        linepoints.add(new PointF(100, 100));

        linepoints.add(new PointF(10, 10));
        linepoints.add(new PointF(30, 30));

        List<PointF> linepoints2 = new ArrayList<>();

        linepoints2.add(new PointF(15, 15));
        linepoints2.add(new PointF(30, 30));
        linepoints2.add(new PointF(40, 40));


        LineTool tools = new LineTool();

        assertFalse("Good Job max, you failed!", tools.draw(linepoints2));

        assertTrue("Good Job you succeeded!", tools.draw(linepoints));
    }
}
package sw19.moring03.paint;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.ChristmasTreeTool;
import sw19.moring03.paint.utils.PointF;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;

@RunWith(MockitoJUnitRunner.class)
public class ChristmasTreeTest {
    private Canvas canvas;
    private Paint paint;
    private Bitmap tree;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();
        tree = Mockito.mock(Bitmap.class);
    }

    @Test
    public void testTreeSimple() {

        ChristmasTreeTool tool = new ChristmasTreeTool(tree);
        tool.addPoint(new PointF(15, 15));
        tool.addPoint(new PointF(30, 30));
        tool.addPoint(new PointF(60, 60));

        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testInvalidTree() {
        ChristmasTreeTool tool = new ChristmasTreeTool(tree);
        tool.addPoint(new PointF(10, 10));

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testDrawTree() {
        int expectedCalls = 1;

        ChristmasTreeTool tool = new ChristmasTreeTool(tree);

        tool.addPoint(new PointF(10, 10));
        tool.addPoint(new PointF(20, 20));

        canvas = Mockito.mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expectedCalls)).drawBitmap(Mockito.any(Bitmap.class), (Rect)isNull(), Mockito.any(Rect.class), (Paint)isNull());
    }
}
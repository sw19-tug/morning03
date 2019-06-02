package sw19.moring03.paint;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.StickerTool;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StickerTest {

    private Canvas canvas;
    private Paint paint;

    @Before
    public void startUp() {
        canvas = new Canvas();
        paint = new Paint();
    }

    @Test
    public void testStickerSimple() {
        Bitmap image = Mockito.mock(Bitmap.class);
        StickerTool tool = new StickerTool(image);

        tool.addPoint(new PointF(10, 10));

        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testNoPoint() {
        Bitmap image = Mockito.mock(Bitmap.class);
        StickerTool tool = new StickerTool(image);

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testInvalidSticker() {
        StickerTool tool = new StickerTool(null);

        tool.addPoint(new PointF(10, 10));

        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testDrawStickerMultiplePoints() {
        int expectedStickers = 1;

        Bitmap image = Mockito.mock(Bitmap.class);
        StickerTool tool = new StickerTool(image);

        tool.addPoint(new PointF(10, 10));
        tool.addPoint(new PointF(20, 20));
        tool.addPoint(new PointF(30, 30));
        tool.addPoint(new PointF(40, 40));

        canvas = Mockito.mock(Canvas.class);

        tool.draw(canvas, paint);

        Mockito.verify(canvas, Mockito.times(expectedStickers)).drawBitmap(Mockito.any(Bitmap.class),
                Mockito.any(Rect.class), Mockito.any(Rect.class), Mockito.any(Paint.class));
    }
}
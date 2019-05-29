package sw19.moring03.paint;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import sw19.moring03.paint.utils.PointF;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.tools.PhotoTool;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PhotoToolTest {
    private Canvas canvas;
    private Paint paint;

    @Mock
    Bitmap image;

    @Before
    public void setUp() {
        canvas = new Canvas();
        paint = new Paint();
        image = mock(Bitmap.class);
    }

    @Test
    public void testValidImage() {
        PhotoTool tool = new PhotoTool(image);
        tool.draw(canvas, paint);
        assertTrue(tool.draw(canvas, paint));
    }

    @Test
    public void testInvalidImage() {
        PhotoTool tool = new PhotoTool(null);
        assertFalse(tool.draw(canvas, paint));
    }

    @Test
    public void testDrawOnImage() {
        PhotoTool photoTool = new PhotoTool(image);
        photoTool.draw(canvas, paint);

        LineTool tool = new LineTool(Color.BLACK, 5, new PathEffect());
        tool.addPoint(new PointF(10, 10));

        assertFalse(tool.draw(canvas, paint));
    }
}
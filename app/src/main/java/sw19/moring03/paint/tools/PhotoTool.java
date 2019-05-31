package sw19.moring03.paint.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Rect;

import java.util.ArrayList;

public class PhotoTool extends Tools {

    private Bitmap newImage;

    public PhotoTool(Bitmap image) {
        points = new ArrayList<>();
        newImage = image;
        pathEffect = new PathEffect();
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (newImage != null) {
            Rect source = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
            canvas.drawBitmap(newImage, null, source, null);
            return true;
        }
        return false;
    }
}

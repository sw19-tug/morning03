package sw19.moring03.paint.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class PhotoTool extends Tools {

    private Bitmap new_image;

    public PhotoTool(Bitmap image) {
        points = new ArrayList<>();
        new_image = image;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (new_image != null) {
            Rect source = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
            canvas.drawBitmap(new_image, null, source, null);
            return true;
        }

        return false;
    }
}
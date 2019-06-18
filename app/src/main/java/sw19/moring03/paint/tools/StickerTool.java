package sw19.moring03.paint.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import sw19.moring03.paint.utils.PointF;

public class StickerTool extends Tools {

    private Bitmap iconToDraw;

    public StickerTool(Bitmap icon) {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
        iconToDraw = icon;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points.size() != 2 || iconToDraw == null) {
            return false;
        }

        int x = (int)points.get(0).x;
        int y = (int)points.get(0).y;

        Rect dst = new Rect(x - (int)getHalfDistance() , y - (int)getHalfDistance(), x + (int)getHalfDistance(), y + (int)getHalfDistance());

        canvas.drawBitmap(iconToDraw, null, dst, null);

        return true;
    }

    @Override
    public void addPoint(PointF point) {
        if (points.size() == 2) {
            points.remove(1);
        }

        super.addPoint(point);
    }

    private double getHalfDistance() {
        PointF firstPoint = points.get(0);
        PointF secondPoint = points.get(1);

        return Math.sqrt(Math.pow(firstPoint.x - secondPoint.x, 2) + Math.pow(firstPoint.y - secondPoint.y, 2)) / 2;

    }
}

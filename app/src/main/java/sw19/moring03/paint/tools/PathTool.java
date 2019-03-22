package sw19.moring03.paint.tools;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.List;

public class PathTool extends Tools {
    @Override
    public boolean draw(List<PointF> points) {

        if(points != null && points.size() < 2) {
            return false;
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);

        for (int i = 1; i < points.size(); i++) {
            canvas.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y, paint);
        }

        return true;
    }
}

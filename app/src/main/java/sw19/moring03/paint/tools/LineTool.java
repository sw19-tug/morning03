package sw19.moring03.paint.tools;


import android.graphics.Canvas;
import android.graphics.Paint;


public class LineTool extends Tools {


    public boolean draw(Canvas canvas, Paint paint) {

        if (points != null && points.size() != 4) {
            return false;
        }

        for (int i = 1; i < points.size(); i++) {
            canvas.drawLine(points.get(i - 1).x, points.get(i - 1).y, points.get(i).x, points.get(i).y, paint);
        }

    }
}

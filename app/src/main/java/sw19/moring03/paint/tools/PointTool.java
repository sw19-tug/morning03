package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class PointTool extends Tools {
    public PointTool() {points = new ArrayList<>();};

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if(points != null && points.size() < 1)
            return false;

        float xCoord = points.get(0).x;
        float yCoord = points.get(0).y;

        canvas.drawPoint(xCoord, yCoord, paint);
        return true;
    }
}

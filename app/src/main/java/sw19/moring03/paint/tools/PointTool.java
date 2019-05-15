package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class PointTool extends Tools {
    public PointTool() {
        points = new ArrayList<>();
    }


    public int getPointCount() {
        return points.size();
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points != null && points.size() < 1) {
            return false;
        }

        for (int i = 0; i < points.size(); i++) {
            float xCoord = points.get(i).x;
            float yCoord = points.get(i).y;
            canvas.drawPoint(xCoord, yCoord, paint);
        }

        return true;
    }
}

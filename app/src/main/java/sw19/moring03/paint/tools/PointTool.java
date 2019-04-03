package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;

public class PointTool extends Tools {
    public PointTool(int strkW) {
        points = new ArrayList<>();
        strokeWidth = strkW;
    }

    public int getPointCount() {
        return points.size();
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if(points != null && points.size() < 1)
            return false;

        for(int i=0; i< points.size(); i++)
        {
            float xCoord = points.get(i).x;
            float yCoord = points.get(i).y;
            canvas.drawPoint(xCoord, yCoord, paint);
        }
        return true;
    }

    public void addPoint(PointF point) {
        if (getPointCount() > 0) {
            return;
        }

        super.addPoint(point);
    }
}

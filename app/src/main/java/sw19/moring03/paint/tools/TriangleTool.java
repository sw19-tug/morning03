package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;

import sw19.moring03.paint.utils.PointF;

public class TriangleTool extends Tools {

    public TriangleTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
    }

    public TriangleTool(int col, int strkW) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points.size() != 2) {
            return false;
        }

        PointF thirdPoint = calculateThirdPoint();

        Path path = new Path();
        path.moveTo(points.get(0).x, points.get(0).y);
        path.lineTo(points.get(1).x, points.get(1).y);
        path.lineTo(thirdPoint.x, thirdPoint.y);
        path.lineTo(points.get(0).x, points.get(0).y);

        canvas.drawPath(path,paint);
        return true;
    }

    @Override
    public void addPoint(PointF point) {
        if (points.size() == 2) {
            points.remove(1);
        }

        super.addPoint(point);
    }

    private double getDistance() {
        PointF firstPoint = points.get(0);
        PointF secondPoint = points.get(1);

        return Math.sqrt(Math.pow(firstPoint.x - secondPoint.x,2) + Math.pow(firstPoint.y - secondPoint.y,2));
    }

    private PointF calculateThirdPoint() {
        double distance = getDistance();

        float xCoord = (float)(Math.pow(distance,2) / (2 * distance));
        float yCoord = (float)Math.sqrt(Math.pow(distance,2) - Math.pow(xCoord, 2));
        return new PointF(xCoord, yCoord);
    }
}

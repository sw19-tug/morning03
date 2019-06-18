package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import sw19.moring03.paint.utils.PointF;

public class StarTool extends Tools {
    private final float corners = 5.0f;
    private final float goldenratio = 1.618f;

    public StarTool(int col, int strkW) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points.size() != 2) {
            return false;
        }

        float halfDist = (float)getDistance() / 2;

        PointF tmp = new PointF(points.get(0).x + halfDist, points.get(0).y);
        //https://www.mathsisfun.com/geometry/pentagram.html
        float distanceStartToPeakEnd = (float)getDistance() / goldenratio;
        float distanceStartToPeakStart = distanceStartToPeakEnd / goldenratio;
        float peakWidth = distanceStartToPeakStart / goldenratio;
        float peakHeight = (float)Math.sqrt(Math.pow(distanceStartToPeakStart, 2) - Math.pow(peakWidth / 2, 2));

        canvas.save();

        if (points.get(0).x > points.get(1).x) {
            canvas.translate( - (points.get(0).x - points.get(1).x), 0);
        }

        for (int corners = 1; corners <= this.corners; corners++) {
            canvas.translate(halfDist, -peakHeight);
            canvas.rotate(360 / this.corners, points.get(0).x, points.get(0).y);
            canvas.drawLine(points.get(0).x, points.get(0).y, tmp.x + halfDist, tmp.y, paint);
        }
        canvas.restore();

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

        return Math.sqrt(Math.pow(firstPoint.x - secondPoint.x, 2) + Math.pow(firstPoint.y - secondPoint.y, 2));

    }

}

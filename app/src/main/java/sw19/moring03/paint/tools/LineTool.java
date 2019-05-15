package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class LineTool extends Tools {
    public LineTool() {
        points = new ArrayList<>();
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {

        float xStart = points.get(0).x;
        float yStart = points.get(0).y;

        float xEnd = points.get(points.size() - 1).x;
        float yEnd = points.get(points.size() - 1).y;

        canvas.drawLine(xStart, yStart, xEnd, yEnd, paint);

        return true;
    }
}
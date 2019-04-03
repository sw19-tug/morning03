package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class LineTool extends Tools {

    public LineTool() {
        color = Color.BLACK;
        points = new ArrayList<>();
    }

    public LineTool(int col) {
        color = col;
        points = new ArrayList<>();
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {

        float x_start = points.get(0).x;
        float y_start = points.get(0).y;

        float x_end = points.get(points.size() - 1).x;
        float y_end = points.get(points.size() - 1).y;

        canvas.drawLine(x_start, y_start, x_end, y_end, paint);

        return true;
    }
}
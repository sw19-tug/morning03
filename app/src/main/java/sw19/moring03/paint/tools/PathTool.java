package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class PathTool extends Tools {

    public PathTool() {
        color = Color.BLACK;
        points = new ArrayList<>();
        strokeWidth = 5;
    }

    public PathTool(int col, int strkW) {
        points = new ArrayList<>();
        color = col;
        strokeWidth = strkW;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {

        if(points == null || points.size() < 2) {
            return false;
        }

        for(int i = 1; i < points.size(); i++) {
            canvas.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y, paint);
        }

        return true;
    }
}

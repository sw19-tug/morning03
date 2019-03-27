package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class EraseTool extends Tools {

    public EraseTool() {
        points = new ArrayList<>();
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {

        if(points == null || points.size() < 2) {
            return false;
        }

        Paint erasePaint = paint;

        //erasePaint.setColor(backgroundColor);
        for(int i = 1; i < points.size(); i++) {
            canvas.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y, erasePaint);
        }

        return true;
    }
}

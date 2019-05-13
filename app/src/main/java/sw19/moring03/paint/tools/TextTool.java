package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TextTool extends Tools {

    @Override
    public boolean draw(Canvas canvas, Paint paint)
    {
        canvas.drawText("Some text", 300, 300, paint);
        paint.setTextSize(40);
        return true;
    }
}

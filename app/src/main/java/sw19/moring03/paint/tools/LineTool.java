package sw19.moring03.paint.tools;

import android.graphics.PointF;

import java.util.List;

public class LineTool {


    public boolean draw(List<PointF> points)
    {

        if(points.size() != 4)
        {
            return false;
        }
        else
        {
            return true;

        }

    }
}

package sw19.moring03.paint.tools;

import android.graphics.PointF;

import java.util.List;

public class PathTool extends Tools {

    @Override
    public boolean draw(List<PointF> points) {

        if(points != null && points.size() < 2) {
            return false;
        }

        return true;
    }
}

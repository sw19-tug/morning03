package sw19.moring03.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;

import org.junit.Before;
import org.junit.Test;

import sw19.moring03.paint.tools.PathTool;
import sw19.moring03.paint.utils.PointF;

import static org.junit.Assert.assertEquals;

public class PathEffectTest {

    @Test
    public void testDashedPathEffect() {
        PathEffect expectedEffect = new DashPathEffect(new float[]{20, 25, 20, 25}, 0);
        PathTool tool = new PathTool(Color.BLACK, 10, expectedEffect);
        tool.addPoint(new PointF(10, 10));
        tool.addPoint(new PointF(20, 20));

        assertEquals(expectedEffect, tool.getPathEffect());
    }
}

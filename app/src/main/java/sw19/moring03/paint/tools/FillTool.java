package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import sw19.moring03.paint.utils.PointF;

import java.util.ArrayList;
import java.util.Stack;

public class FillTool extends Tools {

    private int[] frameBuffer;
    private int bufferWidth;
    private int bufferHeight;

    public FillTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
    }

    public FillTool(int col, int[] frameBuffer, int bufferWidth, int bufferHeight) {
        points = new ArrayList<>();
        this.frameBuffer = frameBuffer;
        this.bufferWidth = bufferWidth;
        this.bufferHeight = bufferHeight;
        color = col;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points.size() == 1 && frameBuffer != null) {
            PointF start = points.remove(0);
            int scanColor = frameBuffer[(int)start.y * bufferWidth + (int)start.x];
            scanlineFloodFillStack((int)start.x, (int)start.y, scanColor);
            this.frameBuffer = null;
        }

        if (points.isEmpty() || points.size() % 2 != 0) {
            return false;
        }

        for (int i = 1; i < points.size(); i += 2) {
            canvas.drawLine(points.get(i - 1).x, points.get(i - 1).y, points.get(i).x, points.get(i).y, paint);
        }

        return true;
    }

    @Override
    public void addPoint(PointF point) {
        if (points.size() > 0) {
            return;
        }
        super.addPoint(point);
    }

    private int getPixel(int x, int y) {
        return this.frameBuffer[y * bufferWidth + x];
    }

    private void setPixel(int x, int y, int color) {
        this.frameBuffer[y * bufferWidth + x] = color;
    }

    public void scanlineFloodFillStack(int x, int y, int scanColor) {
        // adapted code from source: https://lodev.org/cgtutor/floodfill.html#Scanline_Floodfill_Algorithm_With_Stack
        int x1;
        int y1;
        boolean spanAbove;
        boolean spanBelow;
        Stack<PointF> pointsToCheck = new Stack<>();

        pointsToCheck.push(new PointF(x, y));

        while (!pointsToCheck.empty()) {
            PointF currentScanlinePoint = pointsToCheck.pop();
            x1 = (int)currentScanlinePoint.x;
            y1 = (int)currentScanlinePoint.y;

            while (x1 >= 0 && getPixel(x1, y1) == scanColor) {
                x1--;
            }
            points.add(new PointF(x1, y1));
            x1++;

            spanAbove = false;
            spanBelow = false;
            while (x1 < bufferWidth && getPixel(x1, y1) == scanColor) {
                setPixel(x1, y1, color);
                if (!spanAbove && y1 > 0 && getPixel(x1, (y1 - 1)) == scanColor) {
                    pointsToCheck.push(new PointF(x1, y1 - 1));
                    spanAbove = true;
                } else if (spanAbove && y1 > 0 && getPixel(x1, y1 - 1) != scanColor) {
                    spanAbove = false;
                }

                if (!spanBelow && y1 < bufferHeight - 1 && getPixel(x1, y1 + 1) == scanColor) {
                    pointsToCheck.push(new PointF(x1, y1 + 1));
                    spanBelow = true;
                } else if (spanBelow && y1 < bufferHeight - 1 && getPixel(x1, y1 + 1) != scanColor) {
                    spanBelow = false;
                }
                x1++;
            }
            points.add(new PointF(x1, y1));
        }
    }

}

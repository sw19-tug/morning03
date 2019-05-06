package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import sw19.moring03.paint.utils.PointF;
import android.support.annotation.VisibleForTesting;

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
        if (points.size() == 1) {
            PointF start = points.remove(0);
            int scanColor = frameBuffer[(int)start.y * bufferWidth + (int)start.x];
            scanlineFloodFillStack((int)start.x, (int)start.y, scanColor);
        }

        if (points.size() % 2 != 0 || points.isEmpty()) {
            return false;
        }

        for (int i = 1; i < points.size(); i += 2) {
            canvas.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y, paint);
        }

        return true;
    }

    public void addLinePoints(PointF startPoint, PointF endPoint) {
        super.addPoint(startPoint);
        super.addPoint(endPoint);
    }

    private int getPixel(int x, int y) {
        return this.frameBuffer[y * bufferWidth + x];
    }

    private void setPixel(int x, int y, int color) {
        this.frameBuffer[y * bufferWidth + x] = color;
    }

    @VisibleForTesting
    public void scanlineFloodFill(int x, int y, int scanColor) {
        // adapted code from source: https://lodev.org/cgtutor/floodfill.html#Recursive_Scanline_Floodfill_Algorithm
        if (getPixel(x, y) != scanColor)
            return;

        // scanline right
        int xEnd = x;
        while (xEnd < bufferWidth && getPixel(xEnd, y) == scanColor)
        {
            setPixel(xEnd, y, color);
            xEnd++;
        }
        addLinePoints(new PointF(x, y), new PointF(xEnd, y));

        // scanline left
        xEnd = x - 1;
        while (xEnd >= 0 && getPixel(xEnd, y) == scanColor)
        {
            setPixel(xEnd, y, color);
            xEnd--;
        }
        if (x != xEnd + 1) {
            addLinePoints(new PointF(x, y), new PointF(xEnd, y));
        }

        // add recursive scanline above
        xEnd = x;
        while (y > 0 && xEnd < bufferWidth && getPixel(xEnd, y) == color)
        {
            if (getPixel(xEnd, y - 1) == scanColor) {
                scanlineFloodFill(xEnd, y - 1, scanColor);
            }
            xEnd++;
        }

        xEnd = x - 1;
        while (y > 0 && xEnd >= 0 && getPixel(xEnd, y) == color)
        {
            if (getPixel(xEnd, y - 1) == scanColor) {
                scanlineFloodFill(xEnd, y - 1, scanColor);
            }
            xEnd--;
        }

        // add recursive scanline below
        xEnd = x;
        while (y < bufferHeight - 1 && xEnd < bufferWidth && getPixel(xEnd, y) == color)
        {
            if (getPixel(xEnd, y + 1) == scanColor) {
                scanlineFloodFill(xEnd, y + 1, scanColor);
            }
            xEnd++;
        }

        xEnd = x - 1;
        while (y < bufferHeight - 1 && xEnd >= 0 && getPixel(xEnd, y) == color)
        {
            if (getPixel(xEnd, y + 1) == scanColor) {
                scanlineFloodFill(xEnd, y + 1, scanColor);
            }
            xEnd--;
        }
    }

    public void scanlineFloodFillStack(int x, int y, int scanColor) {
        // adapted code from source: https://lodev.org/cgtutor/floodfill.html#Scanline_Floodfill_Algorithm_With_Stack

        // initializations
        int x1;
        int y1;
        boolean spanAbove;
        boolean spanBelow;
        Stack<PointF> pointsToCheck = new Stack<>();

        // push start point
        pointsToCheck.push(new PointF(x, y));

        while (!pointsToCheck.empty())
        {
            PointF currentScanlinePoint = pointsToCheck.pop();
            x1 = (int)currentScanlinePoint.x;
            y1 = (int)currentScanlinePoint.y;

            // go to beginning of the scanline -> left
            while (x1 >= 0 && getPixel(x1, y1) == scanColor) {
                x1--;
            }
            points.add(new PointF(x1, y1));
            x1++;

            // go to the right on the scanline until you hit border or end of line
            spanAbove = false;
            spanBelow = false;
            while (x1 < bufferWidth && getPixel(x1, y1) == scanColor)
            {
                setPixel(x1, y1, color);

                // if pixel above our current position has the same color we add it to the stack (= next scanline)
                if (!spanAbove && y1 > 0 && getPixel(x1, (y1 - 1)) == scanColor)
                {
                    pointsToCheck.push(new PointF(x1, y1 - 1));
                    spanAbove = true;
                }
                // after we found a scanline above we will stop checking unless this scanline is interrupted (= we encouter a border above)
                else if (spanAbove && y1 > 0 && getPixel(x1, y1 - 1) != scanColor)
                {
                    spanAbove = false;
                }

                // same game for scanline below
                if (!spanBelow && y1 < bufferHeight - 1 && getPixel(x1, y1 + 1) == scanColor)
                {
                    pointsToCheck.push(new PointF(x1, y1 + 1));
                    spanBelow = true;
                }
                else if (spanBelow && y1 < bufferHeight - 1 && getPixel(x1, y1 + 1) != scanColor)
                {
                    spanBelow = false;
                }
                x1++;
            }
            points.add(new PointF(x1, y1));
        }
    }

}

package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;

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
            scanlineFloodFill((int)start.x, (int)start.y, scanColor, color);
        }

        if (points.size() % 2 != 0 || points.isEmpty()) {
            return false;
        }

        for (int i = 1; i < points.size(); i += 2) {
            canvas.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y, paint);
        }

        return true;
    }

    private int getPixel(int x, int y) {
        return this.frameBuffer[y * bufferWidth + x];
    }

    private void setPixel(int x, int y, int color) {
        this.frameBuffer[y * bufferWidth + x] = color;
    }

    private void scanlineFloodFill(int x, int y, int scanColor, int newColor) {
        // adapted code from source: https://lodev.org/cgtutor/floodfill.html#Recursive_Scanline_Floodfill_Algorithm
        if (getPixel(x, y) != scanColor)
            return;

        // scanline right
        int xEnd = x;
        while (xEnd < bufferWidth && getPixel(xEnd, y) == scanColor)
        {
            setPixel(xEnd, y, newColor);
            xEnd++;
        }
        points.add(new PointF(x, y));
        points.add(new PointF(xEnd - 1, y));

        // scanline left
        xEnd = x - 1;
        while (xEnd >= 0 && getPixel(xEnd, y) == scanColor)
        {
            setPixel(xEnd, y, newColor);
            xEnd--;
        }
        points.add(new PointF(x, y));
        points.add(new PointF(xEnd + 1, y));

        // add recursive scanline above
        xEnd = x;
        while (y > 0 && xEnd < bufferWidth && getPixel(xEnd, y) == newColor)
        {
            if (getPixel(xEnd, y - 1) == scanColor) {
                scanlineFloodFill(xEnd, y - 1, scanColor, newColor);
            }
            xEnd++;
        }

        xEnd = x - 1;
        while (y > 0 && xEnd >= 0 && getPixel(xEnd, y) == newColor)
        {
            if (getPixel(xEnd, y - 1) == scanColor) {
                scanlineFloodFill(xEnd, y - 1, scanColor, newColor);
            }
            xEnd--;
        }

        // add recursive scanline below
        xEnd = x;
        while (y < bufferHeight - 1 && xEnd < bufferWidth && getPixel(xEnd, y) == newColor)
        {
            if (getPixel(xEnd, y + 1) == scanColor) {
                scanlineFloodFill(xEnd, y + 1, scanColor, newColor);
            }
            xEnd++;
        }

        xEnd = x - 1;
        while (y < bufferHeight - 1 && xEnd >= 0 && getPixel(xEnd, y) == newColor)
        {
            if (getPixel(xEnd, y + 1) == scanColor) {
                scanlineFloodFill(xEnd, y + 1, scanColor, newColor);
            }
            xEnd--;
        }
    }

}

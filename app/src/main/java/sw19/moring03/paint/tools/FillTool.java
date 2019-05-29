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
    private int scanColor;

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

    private int getPixel(float x, float y) {
        return this.frameBuffer[(int)y * bufferWidth + (int)x];
    }

    private void setPixel(float x, float y, int color) {
        this.frameBuffer[(int)y * bufferWidth + (int)x] = color;
    }

    private boolean isScanlineAbove(PointF point) {
        return point.y > 0 && getPixel(point.x, point.y - 1) == scanColor;
    }

    private boolean isScanlineBelow(PointF point) {
        return point.y < bufferHeight - 1 && getPixel(point.x, point.y + 1) == scanColor;
    }

    public void scanlineFloodFillStack(int x, int y, int scanColor) {
        // adapted code from source: https://lodev.org/cgtutor/floodfill.html#Scanline_Floodfill_Algorithm_With_Stack
        this.scanColor = scanColor;
        boolean checkForScanlineAbove;
        boolean checkForScanlineBelow;

        Stack<PointF> pointsToCheck = new Stack<>();
        pointsToCheck.push(new PointF(x, y));

        while (!pointsToCheck.empty()) {
            PointF currentScanlinePoint = pointsToCheck.pop();

            while (currentScanlinePoint.x >= 0 && getPixel(currentScanlinePoint.x, currentScanlinePoint.y) == scanColor) {
                currentScanlinePoint.x--;
            }
            points.add(new PointF(currentScanlinePoint.x, currentScanlinePoint.y));
            currentScanlinePoint.x++;

            checkForScanlineAbove = true;
            checkForScanlineBelow = true;

            while (currentScanlinePoint.x < bufferWidth && getPixel(currentScanlinePoint.x, currentScanlinePoint.y) == scanColor) {
                setPixel(currentScanlinePoint.x, currentScanlinePoint.y, color);

                if (checkForScanlineAbove && isScanlineAbove(currentScanlinePoint)) {
                    pointsToCheck.push(new PointF(currentScanlinePoint.x, currentScanlinePoint.y - 1));
                    checkForScanlineAbove = false;
                } else if (!checkForScanlineAbove && !isScanlineAbove(currentScanlinePoint)) {
                    checkForScanlineAbove = true;
                }

                if (checkForScanlineBelow && isScanlineBelow(currentScanlinePoint)) {
                    pointsToCheck.push(new PointF(currentScanlinePoint.x, currentScanlinePoint.y + 1));
                    checkForScanlineBelow = false;
                } else if (!checkForScanlineBelow && !isScanlineBelow(currentScanlinePoint)) {
                    checkForScanlineBelow = true;
                }

                currentScanlinePoint.x++;
            }
            points.add(new PointF(currentScanlinePoint.x, currentScanlinePoint.y));
        }
    }

}

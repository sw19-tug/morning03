package sw19.moring03.paint.tools;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;


import java.io.File;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class CameraTool extends Tools {

    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;

    @Override
    public boolean draw(Canvas canvas, Paint paint) {


        final int REQUEST_IMAGE_CAPTURE = 1;

        /*private void dispatchTakePictureIntent() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }*/

        //if(points == null || points.size() < 2) {
        //    return false;
        //}
        //
        //int backUpColor = paint.getColor();
        //
        //paint.setColor(getBackgroundColor());
        //for(int i = 1; i < points.size(); i++) {
        //    canvas.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y, paint);
        //}
        //paint.setColor(backUpColor);
        return true;
    }


}

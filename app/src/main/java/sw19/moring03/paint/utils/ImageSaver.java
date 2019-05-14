package sw19.moring03.paint.utils;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.provider.MediaStore;

public class ImageSaver {
    ContentResolver cr;
    public ImageSaver(ContentResolver cr)
    {
        this.cr = cr;
    }

    public Boolean saveImage(Bitmap bitmap)
    {
        if(bitmap == null)
            return false;

        String filename = "CANVAS" + System.currentTimeMillis() / 1000 + ".jpg";
        try {
            String savedImageURI = MediaStore.Images.Media.insertImage(cr,
                    bitmap,
                    filename,
                    filename);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}

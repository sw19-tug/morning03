package sw19.moring03.paint.utils;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.provider.MediaStore;

public class ImageSaver {

    private ContentResolver cr;
    private String savedImageURI;

    public ImageSaver(ContentResolver cr) {
        this.cr = cr;
        this.savedImageURI = "";
    }

    public String getSavedImageURI() {
        return savedImageURI;
    }

    public Boolean saveImage(Bitmap bitmap) {
        if (bitmap == null) {
            return false;
        }

        String filename = "CANVAS" + System.currentTimeMillis() / 1000 + ".jpg";
        savedImageURI = MediaStore.Images.Media.insertImage(cr, bitmap, filename, filename);
        return true;
    }
}

package sw19.moring03.paint.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.OutputStream;

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

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "CANVAS");
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "CANVAS" + System.currentTimeMillis() / 1000 + ".jpeg");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Paint");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());

        Uri url = null;

        try {
            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            OutputStream imageOut = cr.openOutputStream(url);
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
            } finally {
                imageOut.close();
            }
        } catch (Exception e) {
            if (url != null) {
                cr.delete(url, null, null);
            }
        }

        savedImageURI = url.toString();
        return true;
    }
}

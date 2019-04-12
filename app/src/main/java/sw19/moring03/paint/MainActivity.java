package sw19.moring03.paint;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sw19.moring03.paint.Fragments.ShapeChooserFragment;
import sw19.moring03.paint.Fragments.ToolChooserMenuBottomSheetDialog;
import sw19.moring03.paint.utils.Color;
import sw19.moring03.paint.utils.Tool;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 60;
    private ToolChooserMenuBottomSheetDialog toolChooserMenu;
    private ColorChooserMenuBottomSheetDialog colorChooserMenu;
    private Tool chosenTool = Tool.DRAW_POINT;
    private Color chosenColor = Color.BLACK;
    Bitmap lastCameraPicture = null;

    ContentValues values;
    private Uri file;
    ImageView imageView;
    Bitmap help1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toolChooserButton) {
            toolChooserMenu = ToolChooserMenuBottomSheetDialog.newInstance();
            toolChooserMenu.show(getSupportFragmentManager(), "toolChooserMenu");
            return true;
        }

        if (id == R.id.colorChooserButton) {
            colorChooserMenu = ColorChooserMenuBottomSheetDialog.newInstance();
            colorChooserMenu.show(getSupportFragmentManager(), "colorChooserMenu");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Tool getChosenTool() {
        return chosenTool;
    }

    public void setChosenTool(Tool chosenTool) {
        this.chosenTool = chosenTool;
    }

    public void chooseNewTool(View view) {
        switch (view.getId()) {
            case R.id.drawPointButton:
                setChosenTool(Tool.DRAW_POINT);
                break;
            case R.id.drawLineButton:
                setChosenTool(Tool.DRAW_LINE);
                break;
            case R.id.drawFillButton:
                setChosenTool(Tool.FILL);
                break;
            case R.id.drawShapesButton:
                FragmentManager manager = getSupportFragmentManager();
                ShapeChooserFragment fragment = new ShapeChooserFragment();
                fragment.show(manager,"ShapeChooserFragment");
                break;
            case R.id.eraserButton:
                setChosenTool(Tool.ERASER);
                break;
            case R.id.drawPathButton:
                setChosenTool(Tool.DRAW_PATH);
                break;
            case R.id.cameraButton:
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent, CAMERA_REQUEST);
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePicture.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePicture, CAMERA_REQUEST);
                }
                break;

        }

        toolChooserMenu.dismiss();
    }


    private void captureAndSafeCameraPicture() {
        Intent catchPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (catchPhoto.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            Toast toast = Toast.makeText(getApplicationContext(), "Couldt not take picture!", Toast.LENGTH_SHORT);
            toast.show();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "sw19.moring03.paint.fileprovider",
                        photoFile);
                catchPhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(catchPhoto, CAMERA_REQUEST);
            }
        }
    }


    public Color getChosenColor() {
        return chosenColor;
    }

    public void setChosenColor(Color chosenColor) {
        this.chosenColor = chosenColor;
    }

    public void chooseNewColor(View view) {
        switch (view.getId()) {
            case R.id.whiteButton:
                setChosenColor(Color.WHITE);
                break;
            case R.id.yellowButton:
                setChosenColor(Color.YELLOW);
                break;
            case R.id.orangeButton:
                setChosenColor(Color.ORANGE);
                break;
            case R.id.redButton:
                setChosenColor(Color.RED);
                break;
            case R.id.purpleButton:
                setChosenColor(Color.PURPLE);
                break;
            case R.id.magentaButton:
                setChosenColor(Color.MAGENTA);
                break;
            case R.id.turkisButton:
                setChosenColor(Color.TURKIS);
                break;
            case R.id.lightBlueButton:
                setChosenColor(Color.LIGHT_BLUE);
                break;
            case R.id.lightGreenButton:
                setChosenColor(Color.LIGHT_GREEN);
                break;
            case R.id.darkBlueButton:
                setChosenColor(Color.DARK_BLUE);
                break;
            case R.id.darkGreenButton:
                setChosenColor(Color.DARK_GREEN);
                break;
            case R.id.blackButton:
                setChosenColor(Color.BLACK);
                break;
        }

        colorChooserMenu.dismiss();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST)
        {
            Bitmap cameraPicture = (Bitmap) data.getExtras().get("data");

            if(cameraPicture == null)
                return;

            Matrix cameraMatrix = new Matrix();
            cameraMatrix.postRotate(90);
            Bitmap rotatedCameraPicture = Bitmap.createBitmap(cameraPicture, 0, 0,
                    cameraPicture.getWidth(),
                    cameraPicture.getHeight(),
                    cameraMatrix, true);
            lastCameraPicture = rotatedCameraPicture;
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }




}

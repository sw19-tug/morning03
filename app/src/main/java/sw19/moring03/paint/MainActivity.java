package sw19.moring03.paint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

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
    String lastCameraPicturePath = null;

    ImageView imageView;

    public Bitmap new_photo;
    public static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = findViewById(R.id.imageView);
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

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, PICK_IMAGE);

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
                fragment.show(manager, "ShapeChooserFragment");
                break;
            case R.id.eraserButton:
                setChosenTool(Tool.ERASER);
                break;
            case R.id.drawPathButton:
                setChosenTool(Tool.DRAW_PATH);
                break;
            case R.id.cameraButton:
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePicture.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePicture, CAMERA_REQUEST);
                }
                break;

        }

        toolChooserMenu.dismiss();
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

        if (requestCode == CAMERA_REQUEST) {
            try {
                Bitmap cameraPicture = (Bitmap) data.getExtras().get("data");

                if (cameraPicture == null)
                    return;

                lastCameraPicture = cameraPicture;
                saveLastCameraPicture();
                pickFromGallery(); // function die dir is foto rausholt

            } catch (Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error while handling Camera request!", Toast.LENGTH_SHORT);
                toast.show();
                lastCameraPicturePath = null;
                lastCameraPicture = null;
                e.printStackTrace();
            }

        }

        if (resultCode == Activity.RESULT_OK) {
            try {
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                new_photo = bitmap;
                setChosenTool(Tool.TAKE_PHOTO);
            } catch (Exception ex) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error while trying to import Image!", Toast.LENGTH_SHORT);
                toast.show();
                ex.printStackTrace();
            }
        }
    }

    private void saveLastCameraPicture() {

        if (lastCameraPicture == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "First, take a picture :)", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }


        Random generator = new Random();
        int n = 100000;
        n = generator.nextInt(n);
        String fname = "Paint-" + n + ".jpg";
        try {
            String savedImageURI = MediaStore.Images.Media.insertImage(getContentResolver(),
                    lastCameraPicture,
                    fname,
                    fname);
            lastCameraPicturePath = savedImageURI;


            Toast toast = Toast.makeText(getApplicationContext(), "Image Saved to Gallery", Toast.LENGTH_SHORT);
            toast.show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(), "Could not save Image. Check permissions in app settings.", Toast.LENGTH_LONG);
            toast.show();
            lastCameraPicture = null;
            lastCameraPicturePath = null;
        }
    }


}

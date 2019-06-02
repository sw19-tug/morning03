package sw19.moring03.paint;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.ColorInt;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import sw19.moring03.paint.Fragments.ColorChooserMenuBottomSheetDialog;
import sw19.moring03.paint.Fragments.ShapeChooserFragment;
import sw19.moring03.paint.Fragments.StickerChooserFragment;
import sw19.moring03.paint.Fragments.StrokeWidthChooserMenuBottomSheetDialog;
import sw19.moring03.paint.Fragments.ToolChooserMenuBottomSheetDialog;
import sw19.moring03.paint.Views.DrawingView;
import sw19.moring03.paint.utils.ImageSaver;
import sw19.moring03.paint.utils.Tool;

public class MainActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    private final int saveCanvasToExtStorage = 701;
    public Bitmap newPhoto;

    private static final int CAMERA_REQUEST = 60;
    private ToolChooserMenuBottomSheetDialog toolChooserMenu;
    private ColorChooserMenuBottomSheetDialog colorChooserMenu;
    private StrokeWidthChooserMenuBottomSheetDialog strokeWidthChooserMenu;
    private Tool chosenTool = Tool.DRAW_POINT;
    @ColorInt private int chosenColor;
    private int strokeWidth = 5;
    private int stickerToDraw;
    private Menu menu;
    private String lastSavedImageURI;

    public ColorChooserMenuBottomSheetDialog getColorChooserMenu() {
        return colorChooserMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolChooserMenu = new ToolChooserMenuBottomSheetDialog();
        colorChooserMenu = new ColorChooserMenuBottomSheetDialog();
        strokeWidthChooserMenu = new StrokeWidthChooserMenuBottomSheetDialog();

        chosenColor = getResources().getColor(R.color.black);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        this.menu = menu;

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        setToolIcon();
        setStrokeWidth(strokeWidth);
        DrawingView view = findViewById(R.id.drawingView);
        if (view.isAlreadyDrawn()) {
            menu.findItem(R.id.undoButton).setVisible(true);
        } else {
            menu.findItem(R.id.undoButton).setVisible(false);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case saveCanvasToExtStorage:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("PERMISSION", "permission for FileStorage was granted.");
                    saveCanvas();
                } else {
                    Log.w("PERMISSION", "permission for FileStorage was denied.");
                }
                break;
            default:
                Log.d("PERMISSON", "tried to retrieve an unknown request code.");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.toolChooserButton:
                toolChooserMenu.show(getSupportFragmentManager(), "toolChooserMenu");
                return true;
            case R.id.colorChooserButton:
                colorChooserMenu.show(getSupportFragmentManager(), "colorChooserMenu");
                return true;
            case R.id.strokeWidthChooserButton:
                strokeWidthChooserMenu.show(getSupportFragmentManager(), "strokeWidthChooserMenu");
                return true;
            case R.id.saveButton:
                tryPerformAction(saveCanvasToExtStorage);
                return true;
            case R.id.undoButton:
                ((DrawingView)findViewById(R.id.drawingView)).removeLastElementFromPaintList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveCanvas() {
        DrawingView view = findViewById(R.id.drawingView);
        Bitmap currentBitmap = view.getCurrentBitmap();
        ImageSaver is = new ImageSaver(getContentResolver());

        if (is.saveImage(currentBitmap)) {
            this.lastSavedImageURI = is.getSavedImageURI();
            Toast toastSuccess = Toast.makeText(getApplicationContext(), "Saved current canvas as picture!", Toast.LENGTH_SHORT);
            toastSuccess.show();
        }

        Toast toastFail = Toast.makeText(getApplicationContext(), "Could not save Canvas!", Toast.LENGTH_SHORT);
        toastFail.show();
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        MenuItem menuItem = menu.findItem(R.id.strokeWidthChooserButton);
        menuItem.setTitle(strokeWidth + "pt");
    }

    public Tool getChosenTool() {
        return chosenTool;
    }

    public void setChosenTool(Tool chosenTool) {
        this.chosenTool = chosenTool;
    }

    public int getStickerToDraw() {
        return stickerToDraw;
    }

    public void setStickerToDraw(int stickerToDraw) {
        this.stickerToDraw = stickerToDraw;
    }

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, PICK_IMAGE);
    }

    public void chooseNewTool(View view) {
        FragmentManager manager = getSupportFragmentManager();
        switch (view.getId()) {
            case R.id.drawStickerButton:
                StickerChooserFragment stickerFragment = new StickerChooserFragment();
                stickerFragment.show(manager, "ShapeChooserFragment");
                break;
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
                ShapeChooserFragment fragment = new ShapeChooserFragment();
                fragment.show(manager, "ShapeChooserFragment");
                break;
            case R.id.eraserButton:
                setChosenTool(Tool.ERASER);
                break;
            case R.id.drawPathButton:
                setChosenTool(Tool.DRAW_PATH);
                break;
            case R.id.takePhoto:
                pickFromGallery();
                break;
            case R.id.drawTextButton:
                setChosenTool(Tool.DRAW_TEXT);
                break;
            case R.id.cameraButton:
                setChosenTool(Tool.TAKE_PHOTO);
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePicture.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePicture, CAMERA_REQUEST);
                }
                break;
        }

        toolChooserMenu.dismiss();
        setToolIcon();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            try {
                newPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                setChosenTool(Tool.TAKE_PHOTO);

                findViewById(R.id.drawingView).dispatchTouchEvent(MotionEvent.obtain(
                        SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_DOWN, 0, 0, 0));

            } catch (Exception ex) {
                System.out.println("ERROR: Failed to load Bitmap");
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST) {
            super.onActivityResult(requestCode, resultCode, data);

            Bitmap cameraPicture = (Bitmap) data.getExtras().get("data");
            try {
                newPhoto = cameraPicture;
                setChosenTool(Tool.TAKE_PHOTO);


                findViewById(R.id.drawingView).dispatchTouchEvent(MotionEvent.obtain(
                        SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_DOWN, 0, 0, 0));

            } catch (Exception e) {
                System.out.println("ERROR: Failed to load Bitmap");
            }
        }
    }

    public void setToolIcon() {
        MenuItem menuItem = menu.findItem(R.id.toolChooserButton);

        switch (getChosenTool()) {
            case DRAW_LINE:
                menuItem.setIcon(R.drawable.ic_line_icon);
                break;
            case DRAW_STICKER:
                menuItem.setIcon(R.drawable.ic_sticker_icon);
                break;
            case DRAW_CIRCLE:
                menuItem.setIcon(R.drawable.ic_circle_icon);
                break;
            case DRAW_PATH:
                menuItem.setIcon(R.drawable.ic_path_icon);
                break;
            case DRAW_OVAL:
                menuItem.setIcon(R.drawable.ic_oval_icon);
                break;
            case DRAW_POINT:
                menuItem.setIcon(R.drawable.ic_point_icon);
                break;
            case DRAW_RECTANGLE:
                menuItem.setIcon(R.drawable.ic_rectangle_icon);
                break;
            case FILL:
                menuItem.setIcon(R.drawable.ic_fill_icon);
                break;
            case ERASER:
                menuItem.setIcon(R.drawable.ic_eraser_icon);
                break;
            case DRAW_TEXT:
                menuItem.setIcon(R.drawable.ic_text);
                break;
        }
    }

    public int getChosenColor() {
        return chosenColor;
    }

    public void setChosenColor(int chosenColor) {
        this.chosenColor = getResources().getColor(chosenColor);
    }

    public void setChosenColorInt(int colorInt) {
        this.chosenColor = colorInt;
        setColorToIconTint();
    }

    public void chooseNewColor(View view) {
        switch (view.getId()) {
            case R.id.whiteButton:
                setChosenColor(R.color.white);
                break;
            case R.id.yellowButton:
                setChosenColor(R.color.yellow);
                break;
            case R.id.orangeButton:
                setChosenColor(R.color.orange);
                break;
            case R.id.redButton:
                setChosenColor(R.color.red);
                break;
            case R.id.purpleButton:
                setChosenColor(R.color.purple);
                break;
            case R.id.magentaButton:
                setChosenColor(R.color.magenta);
                break;
            case R.id.turkisButton:
                setChosenColor(R.color.turkis);
                break;
            case R.id.lightBlueButton:
                setChosenColor(R.color.lightBlue);
                break;
            case R.id.lightGreenButton:
                setChosenColor(R.color.lightGreen);
                break;
            case R.id.darkBlueButton:
                setChosenColor(R.color.darkBlue);
                break;
            case R.id.darkGreenButton:
                setChosenColor(R.color.darkGreen);
                break;
            case R.id.blackButton:
                setChosenColor(R.color.black);
                break;
        }

        colorChooserMenu.dismiss();

        int red = Color.red(chosenColor);
        int green = Color.green(chosenColor);
        int blue = Color.blue(chosenColor);
        colorChooserMenu.setChosenColor(chosenColor);
        colorChooserMenu.setRed(red);
        colorChooserMenu.setGreen(green);
        colorChooserMenu.setBlue(blue);

        setColorToIconTint();
    }

    private void setColorToIconTint() {
        MenuItem menuItem = menu.findItem(R.id.colorChooserButton);

        Drawable drawable = menuItem.getIcon();
        drawable = DrawableCompat.wrap(drawable);

        DrawableCompat.setTint(drawable, chosenColor);
        menuItem.setIcon(drawable);
    }

    public String getLastSavedImageURI() {
        return lastSavedImageURI;
    }

    private void tryPerformAction(int requestCode) {
        if (requestCode == saveCanvasToExtStorage) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                saveCanvas();
            } else {
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, requestCode);
            }
        }
    }
}

package sw19.moring03.paint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import sw19.moring03.paint.Fragments.ColorChooserMenuBottomSheetDialog;
import sw19.moring03.paint.Fragments.ShapeChooserFragment;
import sw19.moring03.paint.Fragments.StrokeWidthChooserMenuBottomSheetDialog;
import sw19.moring03.paint.Fragments.ToolChooserMenuBottomSheetDialog;
import sw19.moring03.paint.utils.Tool;


public class MainActivity extends AppCompatActivity {
    private ToolChooserMenuBottomSheetDialog toolChooserMenu;
    private ColorChooserMenuBottomSheetDialog colorChooserMenu;
    private StrokeWidthChooserMenuBottomSheetDialog strokeWidthChooserMenu;
    private Tool chosenTool = Tool.DRAW_POINT;
    private int chosenColor = R.color.black;
    private int strokeWidth = 5;
    private Menu menu;

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        MenuItem menuItem = menu.findItem(R.id.strokeWidthChooserButton);
        menuItem.setTitle(strokeWidth + "pt");
    }

    public Bitmap new_photo;
    public static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolChooserMenu = new ToolChooserMenuBottomSheetDialog();
        colorChooserMenu = new ColorChooserMenuBottomSheetDialog();
        strokeWidthChooserMenu = new StrokeWidthChooserMenuBottomSheetDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toolChooserButton) {
            toolChooserMenu.show(getSupportFragmentManager(), "toolChooserMenu");
            return true;
        }

        if (id == R.id.colorChooserButton) {
            colorChooserMenu.show(getSupportFragmentManager(), "colorChooserMenu");
            return true;
        }

        if (id == R.id.strokeWidthChooserButton) {
            strokeWidthChooserMenu.show(getSupportFragmentManager(), "strokeWidthChooserMenu");
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

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, PICK_IMAGE);

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
            case R.id.takePhoto:
                pickFromGallery();
                break;
            case R.id.drawTextButton:

                break;
        }

        toolChooserMenu.dismiss();
        setToolIcon();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK)
            if(requestCode == PICK_IMAGE) {
                Uri imageUri = data.getData();
                try {
                    new_photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    setChosenTool(Tool.TAKE_PHOTO);
                } catch (Exception ex) {
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
        this.chosenColor = chosenColor;
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
        setColorToIconTint(view);
    }

    private void setColorToIconTint(View view) {
        MenuItem menuItem = menu.findItem(R.id.colorChooserButton);

        Drawable drawable = menuItem.getIcon();

        drawable = DrawableCompat.wrap(drawable);

        DrawableCompat.setTint(drawable, view.getResources().getColor(chosenColor));
        menuItem.setIcon(drawable);
    }
}

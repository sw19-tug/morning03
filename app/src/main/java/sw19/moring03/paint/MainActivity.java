package sw19.moring03.paint;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import sw19.moring03.paint.Fragments.ShapeChooserFragment;
import sw19.moring03.paint.Fragments.ToolChooserMenuBottomSheetDialog;
import sw19.moring03.paint.utils.Tool;

public class MainActivity extends AppCompatActivity {

    private ToolChooserMenuBottomSheetDialog toolChooserMenu;
    private Tool chosenTool = Tool.DRAW_POINT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolIcon();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        return super.onOptionsItemSelected(item);
    }

    public Tool getChosenTool() {
        return chosenTool;
    }

    public void setChosenTool(Tool chosenTool) {
        this.chosenTool = chosenTool;
        setToolIcon();
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
        }

        toolChooserMenu.dismiss();
    }

    private void setToolIcon() {
        ImageView imageView = findViewById(R.id.toolIcon);

        switch(getChosenTool()) {
            case DRAW_LINE:
                imageView.setImageResource(R.drawable.ic_line_icon);
                break;
            case DRAW_CIRCLE:
                imageView.setImageResource(R.drawable.ic_circle_icon);
                break;
            case DRAW_PATH:
                imageView.setImageResource(R.drawable.ic_path_icon);
                break;
            case DRAW_OVAL:
                imageView.setImageResource(R.drawable.ic_oval_icon);
                break;
            case DRAW_POINT:
                imageView.setImageResource(R.drawable.ic_point_icon);
                break;
            case DRAW_RECTANGLE:
                imageView.setImageResource(R.drawable.ic_rectangle_icon);
                break;
            case FILL:
                imageView.setImageResource(R.drawable.ic_fill_icon);
                break;
            case ERASER:
                imageView.setImageResource(R.drawable.ic_eraser_icon);
                break;
        }
    }

}

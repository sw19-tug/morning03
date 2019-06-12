package sw19.moring03.paint.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;
import sw19.moring03.paint.utils.Tool;

import static sw19.moring03.paint.utils.Tool.DRAW_CHRISTMAS_TREE;
import static sw19.moring03.paint.utils.Tool.DRAW_HEART;
import static sw19.moring03.paint.utils.Tool.DRAW_STAR;
import static sw19.moring03.paint.utils.Tool.DRAW_TRIANGLE;

public class ShapeChooserFragment extends DialogFragment {

    final Item[] shapeTypes = {
            new Item("Circle", R.drawable.ic_circle_icon),
            new Item("Rectangle", R.drawable.ic_rectangle_icon),
            new Item("Oval", R.drawable.ic_oval_icon),
            new Item("Triangle", R.drawable.ic_triangle_icon),
            new Item("Heart", R.drawable.ic_heart_icon),
            new Item("Star", R.drawable.ic_star_icon),
            new Item("Tree", R.drawable.ic_tree_icon)
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        ListAdapter adapter = createListAdapter();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.shapeChooserFragment)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (getContext() != null) {
                            MainActivity activity = (MainActivity) getContext();

                            Item item = shapeTypes[which];

                            switch (item.text) {
                                case "Circle":
                                    activity.setChosenTool(Tool.DRAW_CIRCLE);
                                    break;
                                case "Rectangle":
                                    activity.setChosenTool(Tool.DRAW_RECTANGLE);
                                    break;
                                case "Oval":
                                    activity.setChosenTool(Tool.DRAW_OVAL);
                                    break;
                                case "Triangle":
                                    activity.setChosenTool(DRAW_TRIANGLE);
                                    break;
                                case "Heart":
                                    activity.setChosenTool(DRAW_HEART);
                                    break;
                                case "Star":
                                    activity.setChosenTool(DRAW_STAR);
                                    break;
                                case "Tree":
                                    activity.setChosenTool(DRAW_CHRISTMAS_TREE);
                                    break;
                            }

                            activity.setToolIcon();
                        }
                    }
                }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );
        return builder.create();

    }

    private ListAdapter createListAdapter() {
        ListAdapter adapter = new ArrayAdapter<Item>(getContext(), android.R.layout.select_dialog_item,
                android.R.id.text1, shapeTypes) {
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView)view.findViewById(android.R.id.text1);

                tv.setCompoundDrawablesWithIntrinsicBounds(shapeTypes[position].icon, 0, 0, 0);

                int padding = (int)(10 * getResources().getDisplayMetrics().density + 0.5f);
                tv.setCompoundDrawablePadding(padding);


                return view;
            }
        };

        return  adapter;
    }

    static class Item {
        private final String text;
        private final int icon;

        public Item(String text, Integer icon) {
            this.text = text;
            this.icon = icon;
        }
        @Override
        public String toString() {
            return text;
        }
    }

}

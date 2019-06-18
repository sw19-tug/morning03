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

public class StickerChooserFragment extends DialogFragment {

    final Item[] availableStickers = {
            new Item("happy", R.drawable.happy_x24),
            new Item("sunglasses", R.drawable.sunglasses_x24),
            new Item("thumbs up", R.drawable.thumbsup_x24),
            new Item("dragon", R.drawable.dragon_x24),
            new Item("blossom", R.drawable.blossom_x24),
            new Item("peach", R.drawable.peach_x24),
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        ListAdapter adapter = createListAdapter();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.stickerChooserFragment)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (getContext() != null) {
                            MainActivity activity = (MainActivity) getContext();

                            Item item = availableStickers[which];

                            activity.setChosenTool(Tool.DRAW_STICKER);

                            switch (item.icon) {
                                case R.drawable.sunglasses_x24:
                                    activity.setStickerToDraw(R.drawable.sunglasses);
                                    break;
                                case R.drawable.peach_x24:
                                    activity.setStickerToDraw(R.drawable.peach);
                                    break;
                                case R.drawable.happy_x24:
                                    activity.setStickerToDraw(R.drawable.happy);
                                    break;
                                case R.drawable.dragon_x24:
                                    activity.setStickerToDraw(R.drawable.dragon);
                                    break;
                                case R.drawable.thumbsup_x24:
                                    activity.setStickerToDraw(R.drawable.thumbsup);
                                    break;
                                case R.drawable.blossom_x24:
                                    activity.setStickerToDraw(R.drawable.blossom);
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
                android.R.id.text1, availableStickers) {
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView)view.findViewById(android.R.id.text1);

                tv.setCompoundDrawablesWithIntrinsicBounds(availableStickers[position].icon, 0, 0, 0);

                int padding = (int) (10 * getResources().getDisplayMetrics().density + 0.5f);
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

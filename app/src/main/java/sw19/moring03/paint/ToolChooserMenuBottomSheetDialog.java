package sw19.moring03.paint;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ToolChooserMenuBottomSheetDialog extends BottomSheetDialogFragment {
    public static ToolChooserMenuBottomSheetDialog newInstance() {
        return new ToolChooserMenuBottomSheetDialog();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tool_chooser_menu, container, false);
    }
}

package io.github.maasencioh.conecta4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Project: Conecta4
 * Created by: maasencioh
 */
public class Level extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main  = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] levels = getResources().getStringArray(R.array.levels);
        builder.setTitle(R.string.level);
        builder.setSingleChoiceItems(levels, main.game.level,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        main.game.level = which;
                        main.game.restart();
                        main.drawBoard();
                        main.end = false;
                        TextView mainText = (TextView) main.findViewById(R.id.mainText);
                        mainText.setText(R.string.title);
                        mainText.setTextColor(getResources().getColor(R.color.colorGrey));
                        dialog.dismiss();
                    }
                }
        );

        return builder.create();
    }
}

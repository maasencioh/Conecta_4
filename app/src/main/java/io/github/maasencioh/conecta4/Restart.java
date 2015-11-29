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
public class Restart extends DialogFragment {
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        final MainActivity main  = (MainActivity) getActivity();
        TextView mainText = (TextView) main.findViewById(R.id.mainText);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(mainText.getText());
        alertDialogBuilder.setMessage(R.string.restartMessage);
        alertDialogBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
        alertDialogBuilder.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        return alertDialogBuilder.create();
    }
}

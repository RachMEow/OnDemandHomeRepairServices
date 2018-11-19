package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddServiceDialog extends AppCompatDialogFragment {
    private EditText editTextServiceName;
    private EditText editTextRate;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder( getActivity() );

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate( R.layout.layout_add_service_dialog,null);

        builder.setView( view )
                .setTitle("Submit")
                .setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                } );
        editTextServiceName = view.findViewById( R.id.editTextServiceName );
        editTextRate= view.findViewById( R.id.editTextRate );
        return builder.create();
    }
}

package com.example.pharmacies_analysis.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

public class DialNumberAction {

    public void makeCall(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+((TextView) view).getText().toString().trim()));
        view.getContext().startActivity(intent);
    }
}

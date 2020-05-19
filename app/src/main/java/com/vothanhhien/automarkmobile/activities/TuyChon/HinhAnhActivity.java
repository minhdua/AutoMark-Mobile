package com.vothanhhien.automarkmobile.activities.TuyChon;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.utils.Utils;

class HinhAnhActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        init();
    }



    private void init() {
        imageDisplay = findViewById(R.id.imageDisplay);
        Bundle bundle = getIntent().getExtras();
        byte [] image = bundle.getByteArray("Image");
        Bitmap bitmap = Utils.decodeBitmapFromByteArray(image,imageDisplay.getWidth(),imageDisplay.getHeight());
        imageDisplay.setImageBitmap(bitmap);
    }
    private ImageView imageDisplay;
}

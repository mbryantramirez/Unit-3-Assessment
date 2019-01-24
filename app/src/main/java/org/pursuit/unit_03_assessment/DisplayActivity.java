package org.pursuit.unit_03_assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DisplayActivity extends AppCompatActivity {
    private ImageView planetImageView;
    private TextView nameTv;
    private TextView numberTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        int number = extras.getInt("number");
        String image = extras.getString("image");

        planetImageView = findViewById(R.id.planet_imageview);
        nameTv = findViewById(R.id.planet_name_tv);
        numberTv = findViewById(R.id.planet_number_tv);

        nameTv.setText(name);
        numberTv.setText(number);
        Picasso.with(getApplicationContext())
                .load(image)
                .into(planetImageView);
    }
}

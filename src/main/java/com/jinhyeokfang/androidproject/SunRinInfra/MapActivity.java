package com.jinhyeokfang.androidproject.SunRinInfra;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jinhyeokfang.androidproject.sunrininfra.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView textView = (TextView)findViewById(R.id.text_view);

//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CanvasMapView map = new CanvasMapView(getApplicationContext());
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.map_view);
        frameLayout.addView(map);

        CardView mapSearchCardView = (CardView) findViewById(R.id.mapSearchCardView);
        mapSearchCardView.bringToFront();

        Spinner mapSpinner = (Spinner) findViewById(R.id.mapSpinner);
        List<String> mapList = new ArrayList<String>(Arrays.asList("1호관", "2호관", "3호관"));
        ArrayAdapter<String> mapAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mapList);
        mapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapSpinner.setAdapter(mapAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

class CanvasMapView extends View {
    float x=0, y=-500, savedX, savedY;
    boolean isChanged = true;

    public CanvasMapView(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.test_image);

        canvas.drawBitmap(b, x, y, paint);
        isChanged = false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                savedX = event.getX();
                savedY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE :
                float finalX = event.getX();
                float finalY = event.getY();
                if (finalX == x && finalY == y)
                    break;
                else
                    isChanged = true;
                x += finalX - savedX;
                y += finalY - savedY;
                savedX = event.getX();
                savedY = event.getY();
                invalidate();

                break;
        }
        return true;
    }
}
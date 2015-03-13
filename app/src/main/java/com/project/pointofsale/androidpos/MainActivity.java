package com.project.pointofsale.androidpos;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        float width = convertPxToDp(getWidthScreenResolution());
        float height = convertPxToDp(getHeightScreenResolution());
        float companyLayoutHeight = (height * 10)/100;
        setupCompanyLayoutHeight(convertDpToPx(companyLayoutHeight));

        int companyLogoSideSize = convertDpToPx((width*10)/100);
        setupCompanyLogo(companyLogoSideSize);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupCompanyLayoutHeight(int heightInPx){
        LinearLayout companyDetailLayout = (LinearLayout) findViewById(R.id.companydetaillayout);
        companyDetailLayout.getLayoutParams().height = heightInPx;
    }

    private void setupCompanyLogo(int sideSize){
        ImageView companyLogo = (ImageView) findViewById(R.id.companylogo);
        ViewGroup.LayoutParams companyLayoutParams = companyLogo.getLayoutParams();
        companyLayoutParams.height = sideSize;
        companyLayoutParams.width = sideSize;

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) companyLayoutParams;
        int margin = convertDpToPx(10);
        mlp.setMargins(margin, margin, margin, margin);
        companyLogo.setLayoutParams(mlp);

    }

    private int getWidthScreenResolution(){
         return this.getPoint().x;
    }

    private int getHeightScreenResolution(){
        return this.getPoint().y;
    }

    private Point getPoint(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Point point = new Point();
        display.getRealSize(point);

        return point;
    }

    private float convertPxToDp(int sizeInPx){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        float sizeRatio = dm.densityDpi/160;
        float sizeInDp = sizeInPx/sizeRatio;
        return sizeInDp;
    }


    private int convertDpToPx(float sizeInDp){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDp, getResources().getDisplayMetrics());
    }

}

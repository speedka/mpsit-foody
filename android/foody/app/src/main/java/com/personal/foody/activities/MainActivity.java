package com.personal.foody.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.personal.foody.R;

import org.json.JSONObject;

/**
 * The class renders a screen of food specialities.
 * <p>
 * Launcher activity of the project.
 * </p>
 */
public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, View.OnClickListener {

    private final String TAG = MainActivity.class.getSimpleName();

    private static int ROMANIAN = 1;
    private static int ITALIAN = 3;
    private static int CHINESE = 2;
    private static int INDIAN = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_romanian).setOnClickListener(this);
        findViewById(R.id.btn_italian).setOnClickListener(this);
        findViewById(R.id.btn_chinese).setOnClickListener(this);
        findViewById(R.id.btn_indian).setOnClickListener(this);
    }


    private void gotoSubcategoryListActivity(int intent) {
        Intent i = new Intent(this, SubcategoryListActivity.class);
        i.putExtra(SubcategoryListActivity.CATEGORY_INTENT, intent);
        startActivity(i);
    }


    @Override
    public void onResponse(JSONObject response) {
        Log.d(TAG, response.toString());

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_romanian:
                gotoSubcategoryListActivity(ROMANIAN);
                break;
            case R.id.btn_italian:
                gotoSubcategoryListActivity(ITALIAN);
                break;
            case R.id.btn_indian:
                gotoSubcategoryListActivity(INDIAN);
                break;
            case R.id.btn_chinese:
                gotoSubcategoryListActivity(CHINESE);
                break;
        }
    }
}

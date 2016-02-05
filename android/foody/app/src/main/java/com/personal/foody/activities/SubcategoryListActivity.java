package com.personal.foody.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.personal.foody.R;
import com.personal.foody.adapters.SubcategoryListAdapter;
import com.personal.foody.web.ServerRequest;
import com.personal.foody.web.models.SubCategory;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Renders a list of food subcategories received from the server.
 * <p>
 * A subcategory is a generic name of the food to be ordered for example,
 * "pizza" can be a subcategory of italian food and contains more specific pizza names
 * </p>
 */
public class SubcategoryListActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, ListView.OnItemClickListener {

    public static String CATEGORY_INTENT = "category_intent";
    private static String TAG = SubcategoryListActivity.class.getSimpleName();

    public static int RON = 0;
    public static int EURO = 1;

    private SubcategoryListAdapter mAdapter;
    private ProgressDialog mLoadingDialog;
    private SearchView mSearchView;
    private ArrayList<SubCategory> mDishList;
    private View mAppInfo;
    private int mCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        mCategory = getIntent().getIntExtra(CATEGORY_INTENT, -1);
        if (mCategory == -1) {
            Log.e(TAG, "no category intent was passed");
            finish();
            return;
        }

        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setMessage("Loading");

        loadDishList();
        setupToolbar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLoadingDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_food, menu);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_euro:
                mAdapter.setCurrency(EURO);
                break;
            case R.id.action_ron:
                mAdapter.setCurrency(RON);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    /**
     * Issues a server request via Volley to fetch the Subcategory items available.
     * Uses the {@link com.android.volley.Response.Listener} callback implementation to parse the response.
     * @see #onResponse(JSONObject)
     * @see #onErrorResponse(VolleyError)
     */
    private void loadDishList() {
        mLoadingDialog.show();
        ServerRequest.loadSubcategory(this, mCategory, this, this);
    }

    /**
     * Ties the adapter to the listView and the query listener to the searchbar
     * to enable list search. It is called after the data is fetched from
     * the backend.
     * @see #loadDishList();
     */

    private void renderDishList() {
        mAdapter = new SubcategoryListAdapter(this, mDishList);
        ListView lvDishList = (ListView) findViewById(R.id.dish_list);
        lvDishList.setAdapter(mAdapter);
        lvDishList.setOnItemClickListener(this);
        mSearchView.setOnQueryTextListener(mAdapter);
        mAppInfo = findViewById(R.id.app_info);

        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mAppInfo.setVisibility(View.INVISIBLE);
                } else {
                    mAppInfo.setVisibility(View.VISIBLE);
                }
            }
        });

        mAdapter.onQueryTextChange("");
        mAdapter.setCurrency(RON);
        mLoadingDialog.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i = new Intent(this, OfferListActivity.class);
        i.putExtra(OfferListActivity.SUBCATEGORY_INTENT, mDishList.get(position).id);
        i.putExtra(OfferListActivity.CATEGORY_INTENT, mCategory);
        startActivity(i);

    }

    /**
     * @param error
     * Callback to register server communication errors.
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        mLoadingDialog.dismiss();

        Log.e(TAG, "Error message = " + error.getMessage());
        Toast.makeText(this, "Unable to reach the server", Toast.LENGTH_SHORT).show();

    }


    /**
     * @param response
     *
     * Callback to parse server responses. Unpacks the response in a list of {@link SubCategory}
     * using {@link Gson}
     */
    @Override
    public void onResponse(JSONObject response) {
        mLoadingDialog.dismiss();
        try {
            Type listType = new TypeToken<List<SubCategory>>() {
            }.getType();
            mDishList = new Gson().fromJson(response.getString("data"), listType);
            renderDishList();
        } catch (JSONException e) {
            Log.e(TAG, "error parsing the JSON: " + e.getMessage());
        }


    }
}

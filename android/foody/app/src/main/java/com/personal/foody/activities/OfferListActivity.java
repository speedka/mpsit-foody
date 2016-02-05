package com.personal.foody.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.personal.foody.R;
import com.personal.foody.adapters.OfferListAdapter;
import com.personal.foody.web.ServerRequest;
import com.personal.foody.web.models.OfferItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Renders a list of offers available in the chosen {@link com.personal.foody.web.models.SubCategory} .
 * An {@link OfferItem} contains data about a dish such as name, price, selling restaurant and contact info for the restaurant.
 */
public class OfferListActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {


    private static String TAG = OfferListActivity.class.getSimpleName();

    public static String SUBCATEGORY_INTENT = "subcategory_intent";
    public static String CATEGORY_INTENT = "category_intent";

    private ListView mLvOfferList;
    private ArrayList<OfferItem> mOfferList;
    private OfferListAdapter mAdapter;

    private ProgressDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        int subCategory = i.getIntExtra(SUBCATEGORY_INTENT, -1);
        int category = i.getIntExtra(CATEGORY_INTENT, -1);

        if (subCategory == -1 || category == -1) {
            Log.e(TAG, "Subcategory and Category IDs should have been passed in the intent");
            finish();
            return;
        }

        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setMessage("Loading");
        loadOffers(category, subCategory);

        setContentView(R.layout.activity_restaurants);
        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_offers, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_euro:
                mAdapter.setCurrency(SubcategoryListActivity.EURO);
                break;
            case R.id.action_ron:
                mAdapter.setCurrency(SubcategoryListActivity.RON);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Issues a server request via Volley to fetch the offers available.
     * Uses the {@link com.android.volley.Response.Listener} callback implementation to parse the response.
     *
     * @param category    - category number (received via intent)
     * @param subCategory - subCategory number (received from the backend)
     * @see #onResponse(JSONObject)
     * @see #onErrorResponse(VolleyError)
     */

    private void loadOffers(int category, int subCategory) {
        mLoadingDialog.show();
        ServerRequest.loadOffers(this, category, subCategory, this, this);
    }


    /**
     * Ties the adapter to the listView and the query listener to the searchbar
     * to enable list search. It is called after the data is fetched from
     * the backend.
     *
     * @see #loadOffers(int, int) ();
     */

    private void renderList() {
        mLvOfferList = (ListView) findViewById(R.id.lv_offers);
        mAdapter = new OfferListAdapter(this, mOfferList);
        mLvOfferList.setAdapter(mAdapter);

    }

    /**
     * @param error Callback to register server communication errors.
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        mLoadingDialog.dismiss();
        Log.e(TAG, "Error msg = " + error.getMessage());
    }


    /**
     * @param response Callback to parse server responses. Unpacks the response in a list of {@link OfferItem}
     *                 using {@link Gson}
     */

    @Override
    public void onResponse(JSONObject response) {
        mLoadingDialog.dismiss();
        mOfferList = new ArrayList<>();
        try {
            Type listType = new TypeToken<List<OfferItem>>() {
            }.getType();
            mOfferList = new Gson().fromJson(response.getString("data"), listType);
            renderList();
        } catch (JSONException e) {
            Log.e(TAG, "error parsing the JSON: " + e.getMessage());

        }


    }
}

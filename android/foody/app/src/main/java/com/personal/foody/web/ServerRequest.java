package com.personal.foody.web;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by danielastamati on 28/01/16.
 * <p>
 * Class to handle server requests. Uses Volley library for sending and receiving data.
 * </p>
 */
public class ServerRequest {

    private static int MY_SOCKET_TIMEOUT_MS = 10000;
    private static int RETRIES = 3;


    /**
     * @param context
     * @param categoryId       = ex: {@link com.personal.foody.activities.MainActivity#CHINESE}
     * @param responseListener - an implementation of a response listener
     * @param errorListener    - an implementation of an error listener
     *                         <p>
     *                         Loads food subcategories via Volley.
     *                         </p>
     */
    public static void loadSubcategory(Context context, int categoryId, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        String url = "https://mpsit-foody-catalincalotescu-1.c9users.io/get_subcategory?id=" + categoryId;


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, (String) null, responseListener, errorListener);

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(jsonRequest);
    }

    /**
     * @param context
     * @param categoryId       = ex: {@link com.personal.foody.activities.MainActivity#CHINESE}
     * @param subcategoryId    - subcategory chosen by the user
     * @param responseListener - an implementation of a response listener
     * @param errorListener    - an implementation of an error listener
     *                         <p> Loads restaurant offers list for a chosen subcategory </p>
     */
    public static void loadOffers(Context context, int categoryId, int subcategoryId, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {

        String url = "https://mpsit-foody-catalincalotescu-1.c9users.io/get_offers?category_id=%d&subcategory_id=%d";
        url = String.format(url, categoryId, subcategoryId);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, (String) null, responseListener, errorListener);

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(jsonRequest);
    }

}

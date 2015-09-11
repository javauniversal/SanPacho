package com.poocode.sanpacho.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.poocode.sanpacho.Adapters.CostomAdapterLugar;
import com.poocode.sanpacho.Entities.ListLugares;
import com.poocode.sanpacho.R;

import java.util.HashMap;
import java.util.Map;

public class FragmentLugares extends BaseVolleyFragment {

    private ListLugares dataLugares;
    private ListView multiColumnList;

    public FragmentLugares() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lugares, container, false);
        multiColumnList = (ListView) view.findViewById(R.id.card_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupGrid();
    }

    private void setupGrid() {
        String url = String.format("%1$s%2$s", getString(R.string.url_base),"listLugares");
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(final String response) {
                        parseJSON(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        onConnectionFailed(error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        addToQueue(jsonRequest);
    }

    private void parseJSON(String json) {
        if (json != null && json.length() > 0) {
            try {
                Gson gson = new Gson();
                dataLugares = gson.fromJson(json, ListLugares.class);

                CostomAdapterLugar adapter = new CostomAdapterLugar(getActivity(), dataLugares);
                adapter.enableAutoMeasure(250);
                multiColumnList.setAdapter(adapter);

            }catch (IllegalStateException ex) {
                ex.printStackTrace();
            }
        }
    }


}

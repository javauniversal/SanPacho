package com.poocode.sanpacho.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.poocode.sanpacho.Adapters.AppAdapter;
import com.poocode.sanpacho.Entities.ListContactoE;
import com.poocode.sanpacho.R;

import java.util.HashMap;
import java.util.Map;

public class FragmentEmergencia extends BaseVolleyFragment {

    private SwipeMenuListView multiColumnList = null;

    public FragmentEmergencia() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergencia, container, false);
        multiColumnList = (SwipeMenuListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cargarDataView();
    }

    private void cargarDataView() {
        String url = String.format("%1$s%2$s", getString(R.string.url_base),"listaContacto");
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
                Map<String, String>  params = new HashMap<String, String>();
                //params.put("operador", "listCompania");
                return params;
            }
        };
        addToQueue(jsonRequest);
    }

    private boolean parseJSON(String json) {
        try {
            Gson gson = new Gson();
            ListContactoE contactoE = gson.fromJson(json, ListContactoE.class);
            AppAdapter adapter = new AppAdapter(getActivity(), contactoE);
            multiColumnList.setAdapter(adapter);
            return true;
        }catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
        return false;

    }
}

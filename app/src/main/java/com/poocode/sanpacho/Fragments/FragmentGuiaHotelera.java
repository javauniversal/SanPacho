package com.poocode.sanpacho.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.poocode.sanpacho.Activities.ActDesHotRes;
import com.poocode.sanpacho.Adapters.ExpandableListAdapter;
import com.poocode.sanpacho.Adapters.ExpandableListDataPump;
import com.poocode.sanpacho.Entities.ListCategoria;
import com.poocode.sanpacho.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.poocode.sanpacho.Entities.MasterItem.setMasterItemStatic;

public class FragmentGuiaHotelera extends BaseVolleyFragment {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private ListCategoria menu;
    private HashMap<String, List<String>> expandableListDetail;
    private ArrayList<String> expandableListTitle;

    public FragmentGuiaHotelera() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guia_hotelera, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setExpandableListView();
    }

    private void setExpandableListView() {
        String url = String.format("%1$s%2$s", getString(R.string.url_base),"listMenuHoteles");
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(final String response) {
                        // response
                        new AsyncTask<String[], Long, Long>(){
                            @Override
                            protected Long doInBackground(String[]... params) {
                                parseJSON(response);
                                return null;
                            }
                            protected void onPreExecute() {
                                //multiColumnList.setVisibility(View.GONE);
                            }
                            @Override
                            public void onProgressUpdate(Long... value) {

                            }
                            @Override
                            protected void onPostExecute(Long result){
                                //Clic Titulo.
                                expandableListAdapter = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
                                expandableListView.setAdapter(expandableListAdapter);
                                expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                                    @Override
                                    public void onGroupExpand(int groupPosition) {
                                        //Toast.makeText(getActivity(),expandableListTitle.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                //Cerrar lista desplegable.
                                expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                                    @Override
                                    public void onGroupCollapse(int groupPosition) {
                                        //Toast.makeText(getActivity(), expandableListTitle.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                //Clic en el elemento hijo.
                                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                                    @Override
                                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                                        //Toast.makeText(getActivity(), menu.get(groupPosition).getHijos().get(childPosition).getCodigo()+"", Toast.LENGTH_SHORT).show();

                                        setMasterItemStatic(menu.get(groupPosition).getHijos().get(childPosition));
                                        startActivity(new Intent(getActivity(), ActDesHotRes.class));
                                        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                        return false;
                                    }
                                });

                            }
                        }.execute();
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
                Map<String, String>  params = new HashMap<>();

                return params;
            }
        };
        addToQueue(jsonRequest);
    }


    private void parseJSON(String json) {
        if (json != null && json.length() > 0) {
            try {
                Gson gson = new Gson();
                menu = gson.fromJson(json, ListCategoria.class);
                expandableListDetail = ExpandableListDataPump.getData(menu);
                expandableListTitle = new ArrayList<>(expandableListDetail.keySet());

            }catch (IllegalStateException ex) {
                ex.printStackTrace();
            }
        }

    }

}

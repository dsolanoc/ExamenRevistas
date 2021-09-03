package com.example.examenmichelle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pubs extends AppCompatActivity {
    private RequestQueue rq;
    List<Journal2> datos2= new ArrayList<>();
    String id="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubs);
        rq= Volley.newRequestQueue(this);
        id=getIntent().getExtras().getString("issue_id");
        mostrarRevistas();
    }
    public void llamarAdapter()
    {
        Adapter2 adapter = new  Adapter2( this, datos2);
        RecyclerView recyclerView= findViewById(R.id.recyclerView3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void mostrarRevistas(){

        String dir="https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+id;

        JsonArrayRequest requerimiento=new JsonArrayRequest
                (Request.Method.GET,dir,null,
                        new com.android.volley.Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                datos2.clear();
                                for(int f=0;f<response.length();f++)
                                {
                                    try {

                                        JSONObject journalObject=new JSONObject(response.get(f).toString());
                                        datos2.add(new Journal2(  journalObject.get("section").toString(),
                                                journalObject.get("publication_id").toString(),
                                                journalObject.get("title").toString(),
                                                journalObject.get("doi").toString(),
                                                journalObject.get("date_published").toString()));

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                llamarAdapter();
                            }
                        },
                        new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
        rq.add(requerimiento);
    }
}
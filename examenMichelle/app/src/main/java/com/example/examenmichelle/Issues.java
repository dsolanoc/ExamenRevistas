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

public class Issues extends AppCompatActivity {
    private RequestQueue rq;
    List<Journal1> datos1= new ArrayList<>();
    String id="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        rq= Volley.newRequestQueue(this);
        id=getIntent().getExtras().getString("journal_id");
        mostrarRevistas();
    }
    public void llamarAdapter()
    {
        Adapter1 adapter = new  Adapter1( this, datos1);
        RecyclerView recyclerView= findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    public void mostrarRevistas(){
        String dir="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+id;
        System.out.println("RUTA:" +dir);
        JsonArrayRequest requerimiento=new JsonArrayRequest
                (Request.Method.GET,dir,null,
                        new com.android.volley.Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {


                                datos1.clear();
                                for(int f=0;f<response.length();f++)
                                {
                                    try {

                                        JSONObject journalObject=new JSONObject(response.get(f).toString());
                                        datos1.add(new Journal1(  journalObject.get("issue_id").toString(),
                                                journalObject.get("date_published").toString(),
                                                journalObject.get("title").toString(),
                                                journalObject.get("doi").toString(),
                                                journalObject.get("cover").toString()));

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
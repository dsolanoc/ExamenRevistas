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

public class MainActivity extends AppCompatActivity {
    private RequestQueue rq;
    List<Journal> journal0s= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rq= Volley.newRequestQueue(this);
        revistasPrincipales();

    }
    public void llamarAdapter()
    {
        Adapter adapter = new Adapter( this, journal0s);
        System.out.println("Tamaño:" +journal0s.size());
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        Adapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,Issues.class);
//                i.putExtra("journal_id",journal0s.get(recyclerView.getChildAdapterPosition(v)).getJournal_id());
//                startActivity(i);
//            }
//        });
        recyclerView.setAdapter(adapter);
    }
    public void revistasPrincipales(){

        String url="https://revistas.uteq.edu.ec/ws/journals.php";
        JsonArrayRequest requerimiento1=new JsonArrayRequest
                (Request.Method.GET,url,null,
                        new com.android.volley.Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray resp) {
                                journal0s.clear();
                                for(int f=0;f<resp.length();f++)
                                {
                                    try {
                                        JSONObject json=new JSONObject(resp.get(f).toString());
                                        journal0s.add(new Journal(json.get("journal_id").toString(),
                                                json.get("portada").toString(),
                                                json.get("description").toString(),
                                                json.get("name").toString()));

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
        rq.add(requerimiento1);
    }


}
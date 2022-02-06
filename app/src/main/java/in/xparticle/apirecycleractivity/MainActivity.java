package in.xparticle.apirecycleractivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.xparticle.apirecycleractivity.Adapter.MyAdapter;
import in.xparticle.apirecycleractivity.Model.ReModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<ReModel>mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mList=new ArrayList<>();
    recyclerView=findViewById(R.id.recycler);
    myAdapter=new MyAdapter(mList,this);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(myAdapter);
    recyclerView.setHasFixedSize(true);


//        String baseURL="http://myjson.dit.upm.es/api/bins/5w4z";
        String baseURL="https://api.github.com/users";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                baseURL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject person = (JSONObject) response.get(i);

                                int id = person.getInt("id");
                                String url=person.getString("url");
                                String login=person.getString("login");
//                                Log.e("nine", "onResponse: nine"+id + url + login );

                                ReModel reModel=new ReModel();
                                reModel.setId(String.valueOf(id));
                                reModel.setUrl(url);
                                reModel.setLogin(login);
                                mList.add(reModel);
                                Log.e("three", "onResponse: three"+reModel);

                            }
                            myAdapter.notifyDataSetChanged();

                            Log.e("TAG", "onResponse: "+response );


                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("catch", "onResponse: error "+e.getMessage() );
                        }
                    }
                    },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                            // Do something when error occurred

                        Log.e("TAG", "onErrorResponse: error "+error.getMessage() );
                            Toast.makeText(MainActivity.this, "error "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonArrayRequest);
    }
}
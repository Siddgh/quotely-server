package sid.com.quotelyserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import sid.com.quotelyserver.DataModels.RecyclerAdapter;
import sid.com.quotelyserver.DataModels.RecyclerTouchListener;

public class TypeSection extends AppCompatActivity {

    ArrayList<String> typeSections;
    RecyclerView recyclerView;
    String CameFrom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_section);
        CameFrom = getIntent().getExtras().getString("CameFrom");
        typeSections = new ArrayList<>();
        typeSections.add("Movies");
        typeSections.add("Tvshow");
        typeSections.add("Animes");
        typeSections.add("Books");
        typeSections.add("Authors");
        recyclerView = (RecyclerView) findViewById(R.id.rv_recyclerView);
        Constants.setUpRecyclerViews(getBaseContext(),recyclerView,new LinearLayoutManager(getBaseContext()),LinearLayoutManager.VERTICAL);
        RecyclerAdapter adapter = new RecyclerAdapter(typeSections);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void OnClick(View view, int position) {
                TextView tv = view.findViewById(R.id.rv_tv_tmp);
                Intent i = new Intent(getBaseContext(),ContentScreen.class);
                i.putExtra("TypeSection",tv.getText().toString());
                i.putExtra("CameFrom",CameFrom);
                startActivity(i);
            }

            @Override
            public void OnLongClick(View v, int position) {

            }
        }));
    }
}

package sid.com.quotelyserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import sid.com.quotelyserver.DataModels.NotificationsModel;
import sid.com.quotelyserver.DataModels.QuoteInformationModel;
import sid.com.quotelyserver.DataModels.RecyclerQuotesInformationAdapter;
import sid.com.quotelyserver.DataModels.RecyclerTouchListener;

public class ContentScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    View includeTypeSection;
    String type, CameFrom;
    ArrayList<QuoteInformationModel> quoteInformationModels;
    DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference quoteReference = FirebaseDatabase.getInstance().getReference().child("quotes");
    DatabaseReference notificationsReference = rootReference.child("notifications");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_screen);
        includeTypeSection = findViewById(R.id.content_screen);
        recyclerView = includeTypeSection.findViewById(R.id.rv_recyclerView);
        type = getIntent().getExtras().getString("TypeSection");
        CameFrom = getIntent().getExtras().getString("CameFrom");
        quoteInformationModels = new ArrayList<>();
        quoteReference.child(type.toLowerCase()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        quoteInformationModels.add(snapshot.getValue(QuoteInformationModel.class));
                    }
                }
                Constants.setUpRecyclerViews(getBaseContext(), recyclerView, new LinearLayoutManager(getBaseContext()), LinearLayoutManager.VERTICAL);
                RecyclerQuotesInformationAdapter adapter = new RecyclerQuotesInformationAdapter(quoteInformationModels);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void OnClick(View view, int position) {
                TextView tv = view.findViewById(R.id.rv_tv_tmp);
                for (QuoteInformationModel data : quoteInformationModels) {
                    if (tv.getText().toString().toLowerCase().equalsIgnoreCase(data.title + "")) {

                        if (CameFrom.equals("RECENT")) {
                            NotificationsModel notificationsModel = new NotificationsModel("RECENT",
                                    data.title
                                    , "New Quotes added from " + data.title
                                    , (type.equals("Tvshows")) ? "r" + "TvShows" : "r" + type
                                    , data.quotelink
                                    , ""
                                    , data.poster
                                    , false
                                    , true
                                    , false
                                    , ""
                                    , ""
                                    , data.BuyLinks);

                            notificationsReference.child(new Date().getTime() + "").setValue(notificationsModel);
                        } else {
                            Intent i = new Intent(getBaseContext(), IndividualActivity.class);
                            i.putExtra("IndividualActivity", data.quotelink);
                            i.putExtra("CameFrom", CameFrom);
                            startActivity(i);
                        }
                    }
                }
            }

            @Override
            public void OnLongClick(View v, int position) {

            }
        }));
    }
}

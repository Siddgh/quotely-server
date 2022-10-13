package sid.com.quotelyserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import sid.com.quotelyserver.DataModels.NotificationsModel;
import sid.com.quotelyserver.DataModels.QOTDModel;
import sid.com.quotelyserver.DataModels.QuoteDataModel;
import sid.com.quotelyserver.DataModels.QuoteInformationModel;
import sid.com.quotelyserver.DataModels.RecyclerQuotesDataAdapter;
import sid.com.quotelyserver.DataModels.RecyclerTouchListener;

public class IndividualActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View includeTypeSection;
    String title, CameFrom;
    ArrayList<QuoteDataModel> quoteDataModels;
    DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference quoteDataReference = FirebaseDatabase.getInstance().getReference().child("quotedata");
    DatabaseReference qotdReference = rootReference.child("quoteoftheday");
    DatabaseReference famouseQuoteLineReference = rootReference.child("famousquotelines");
    DatabaseReference notificationsReference = rootReference.child("notifications");

    DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference().child("quotes").child("movies");
    DatabaseReference tvRef = FirebaseDatabase.getInstance().getReference().child("quotes").child("tvshow");
    DatabaseReference animeRef = FirebaseDatabase.getInstance().getReference().child("quotes").child("animes");
    DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference().child("quotes").child("books");
    DatabaseReference authorRef = FirebaseDatabase.getInstance().getReference().child("quotes").child("authors");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);
        includeTypeSection = findViewById(R.id.individual_activity);
        recyclerView = includeTypeSection.findViewById(R.id.rv_recyclerView);
        title = getIntent().getExtras().getString("IndividualActivity");
        CameFrom = getIntent().getExtras().getString("CameFrom");
        quoteDataModels = new ArrayList<>();
        quoteDataReference.child(title).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        quoteDataModels.add(snapshot.getValue(QuoteDataModel.class));
                    }
                }
                Constants.setUpRecyclerViews(getBaseContext(), recyclerView, new LinearLayoutManager(getBaseContext()), LinearLayoutManager.VERTICAL);
                RecyclerQuotesDataAdapter adapter = new RecyclerQuotesDataAdapter(quoteDataModels);
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
                String quote = tv.getText().toString().toLowerCase();
                for (final QuoteDataModel data : quoteDataModels) {
                    if (quote.equalsIgnoreCase(data.quote + "")) {
                        QOTDModel pushModel = new QOTDModel(data.from, data.quoteid, data.type);
                        switch (CameFrom) {
                            case "QOTD":
                                qotdReference.setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED QUOTE OF THE DAY", Toast.LENGTH_SHORT).show();
                                break;
                            case "FQ02":
                                famouseQuoteLineReference.child("fql02").setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED FQL02", Toast.LENGTH_SHORT).show();
                                break;
                            case "FQ03":
                                famouseQuoteLineReference.child("fql03").setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED FQL03", Toast.LENGTH_SHORT).show();
                                break;
                            case "FQ04":
                                famouseQuoteLineReference.child("fql04").setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED FQL04", Toast.LENGTH_SHORT).show();
                                break;
                            case "FQ05":
                                famouseQuoteLineReference.child("fql05").setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED FQL05", Toast.LENGTH_SHORT).show();
                                break;
                            case "FQ06":
                                famouseQuoteLineReference.child("fql06").setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED FQL06", Toast.LENGTH_SHORT).show();
                                break;
                            case "FQ07":
                                famouseQuoteLineReference.child("fql07").setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED FQL07", Toast.LENGTH_SHORT).show();
                                break;
                            case "FQ08":
                                famouseQuoteLineReference.child("fql08").setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED FQL08", Toast.LENGTH_SHORT).show();
                                break;
                            case "FQ09":
                                famouseQuoteLineReference.child("fql09").setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED FQL09", Toast.LENGTH_SHORT).show();
                                break;
                            case "FQ10":
                                famouseQuoteLineReference.child("fql10").setValue(pushModel);
                                Toast.makeText(IndividualActivity.this, "UPDATED FQL10", Toast.LENGTH_SHORT).show();
                                break;
                            case "QUOTEPUSHER":
                                switch (data.type) {
                                    case "Movies":
                                        movieRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                        if (snapshot.getValue(QuoteInformationModel.class).title.equals(data.from + "")) {
                                                            NotificationsModel notificationsModel = new NotificationsModel("QUOTEPUSHER",
                                                                    data.from,
                                                                    data.quote,
                                                                    data.type,
                                                                    snapshot.getValue(QuoteInformationModel.class).quotelink,
                                                                    "",
                                                                    snapshot.getValue(QuoteInformationModel.class).poster,
                                                                    true,
                                                                    true,
                                                                    true,
                                                                    data.quoteid,
                                                                    data.category,
                                                                    snapshot.getValue(QuoteInformationModel.class).BuyLinks);
                                                            notificationsReference.child(-1 * new Date().getTime() + "").setValue(notificationsModel);
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                        break;
                                    case "Tvshows":
                                        tvRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                        if (snapshot.getValue(QuoteInformationModel.class).title.equals(data.from + "")) {
                                                            NotificationsModel notificationsModel = new NotificationsModel("QUOTEPUSHER",
                                                                    data.from,
                                                                    data.quote,
                                                                    "TvShows",
                                                                    snapshot.getValue(QuoteInformationModel.class).quotelink,
                                                                    "",
                                                                    snapshot.getValue(QuoteInformationModel.class).poster,
                                                                    true,
                                                                    true,
                                                                    true,
                                                                    data.quoteid,
                                                                    data.category,
                                                                    snapshot.getValue(QuoteInformationModel.class).BuyLinks);
                                                            notificationsReference.child(-1 * new Date().getTime() + "").setValue(notificationsModel);
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                        break;
                                    case "Animes":
                                        animeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                        if (snapshot.getValue(QuoteInformationModel.class).title.equals(data.from + "")) {
                                                            NotificationsModel notificationsModel = new NotificationsModel("QUOTEPUSHER",
                                                                    data.from,
                                                                    data.quote,
                                                                    data.type,
                                                                    snapshot.getValue(QuoteInformationModel.class).quotelink,
                                                                    "",
                                                                    snapshot.getValue(QuoteInformationModel.class).poster,
                                                                    true,
                                                                    true,
                                                                    true,
                                                                    data.quoteid,
                                                                    data.category,
                                                                    snapshot.getValue(QuoteInformationModel.class).BuyLinks);
                                                            notificationsReference.child(-1 * new Date().getTime() + "").setValue(notificationsModel);
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                        break;
                                    case "Books":
                                        bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                        if (snapshot.getValue(QuoteInformationModel.class).title.equals(data.from + "")) {
                                                            NotificationsModel notificationsModel = new NotificationsModel("QUOTEPUSHER",
                                                                    data.from,
                                                                    data.quote,
                                                                    data.type,
                                                                    snapshot.getValue(QuoteInformationModel.class).quotelink,
                                                                    "",
                                                                    snapshot.getValue(QuoteInformationModel.class).poster,
                                                                    true,
                                                                    true,
                                                                    true,
                                                                    data.quoteid,
                                                                    data.category,
                                                                    snapshot.getValue(QuoteInformationModel.class).BuyLinks);
                                                            notificationsReference.child(-1 * new Date().getTime() + "").setValue(notificationsModel);
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                        break;
                                    case "Authors":
                                        authorRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                        if (snapshot.getValue(QuoteInformationModel.class).title.equals(data.from + "")) {
                                                            NotificationsModel notificationsModel = new NotificationsModel("QUOTEPUSHER",
                                                                    data.from,
                                                                    data.quote,
                                                                    data.type,
                                                                    snapshot.getValue(QuoteInformationModel.class).quotelink,
                                                                    "",
                                                                    snapshot.getValue(QuoteInformationModel.class).poster,
                                                                    true,
                                                                    true,
                                                                    true,
                                                                    data.quoteid,
                                                                    data.category,
                                                                    snapshot.getValue(QuoteInformationModel.class).BuyLinks);
                                                            notificationsReference.child(-1 * new Date().getTime() + "").setValue(notificationsModel);
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                        break;
                                }
                                Toast.makeText(IndividualActivity.this, "Quote Data Pushed", Toast.LENGTH_SHORT).show();
                                break;
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

package sid.com.quotelyserver;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sid.com.quotelyserver.DataModels.DataPushModel;
import sid.com.quotelyserver.DataModels.QuoteDataModel;
import sid.com.quotelyserver.DataModels.QuoteInformationModel;
import sid.com.quotelyserver.DataModels.RecentDataModel;

public class Migrate extends AppCompatActivity {

    //TO DO WHEN ADDING QUOTES
    // --> Add your quotes in the dataToPush array list
    // --> Add the entry in recentData array list
    // --> Add the information for the particular type section
    // --> Run the app

    //QUOTE DATA DEFAULT VALUES
    String contributedby = "Quotely";
    int likes = 0;

    //QUOTE INFORMATION DEFAULT VALUES
    String IndividualRatings = "0";
    String LikedBy = "none";

    //----------------------------------------------------------------------------------------------

    /* -------------------- THIS SECTION IS TO BE MODIFIED ---------------------*/


    //SETTING COMMON VALUES
    String title = "Steve Jobs";
    String quotelink = "quotestevejobs";
    String type = "Authors";

    //SETTING UNIQUE QUOTE INFORMATION
    String BuyLinks = "none";
    String poster = "https://res.cloudinary.com/dnzsbsock/image/upload/v1522531211/stevejobs.0_ygcans.jpg";
    String nodeName = "stevejobs";


    /*
    * Swami Vivekananda : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531624/Swami-Vivekananda-ili-96-img-1_fesofh.jpg
    * Pablo Picasso : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531562/pica_647_102516090750_jzs1ji.jpg
    * Elon Musk : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531503/408px-Elon_Musk_2015_c1d3am.jpg
    * Elvis Presley : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531485/595px-Elvis_Presley_promoting_Jailhouse_Rock_kbh71a.jpg
    * Oprah Winfrey : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531461/469px-Oprah_Winfrey__282004_29_zwqzpz.jpg
    * Mahatma Gandhi : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531422/fotonoticia_20160130133049_645_qiinax.jpg
    * Rabindranath Tagore : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531376/site_197_Gujarati_668467_kuq3h9.jpg
    * Muhammad Ali : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531343/13986881888_2eb2970eeb_b_ufhdw3.jpg
    * Mother Teresa : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531239/MotherTeresa_094_niyhol.jpg
    * Steve Jobs : https://res.cloudinary.com/dnzsbsock/image/upload/v1522531211/stevejobs.0_ygcans.jpg
    * */


    //----------------------------------------------------------------------------------------------


    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference recentReference = reference.child("recent");
    DatabaseReference quotedataReference = reference.child("quotedata");
    DatabaseReference quoteinfoReference = reference.child("quotes");

    ArrayList<RecentDataModel> recentData;
    ArrayList<DataPushModel> dataToPush;

    DecimalFormat twodigits = new DecimalFormat("00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_migrate);
        addQuoteData();
        addRecentData();
    }

    public void pushQuoteData(View view) {
        //String category, String contributedby, String from, String quote, String quoteid, String type, int likes
        int count = 1;
        for (int i = 0; i < dataToPush.size(); i++) {
            quotedataReference.child(quotelink).child(quotelink + twodigits.format(count)).setValue(new QuoteDataModel(
                    dataToPush.get(i).category,
                    contributedby,
                    title,
                    dataToPush.get(i).quote,
                    quotelink + twodigits.format(count),
                    type,
                    likes
            ));
            Log.d("YOYO", "Pushed "+ quotelink+twodigits.format(count));
            count++;
        }
        Toast.makeText(this, "Data pushed Successfully", Toast.LENGTH_SHORT).show();
    }

    public void pushQuoteInformation(View view) {

        String typeSectionNode;
        if (type.contains("Tvshows")) {
            typeSectionNode = "tvshow";
        } else {
            typeSectionNode = type.toLowerCase();
        }

        //String BuyLinks, String IndividualRatings,String LikedBy,String poster,String quotelink,String title,String type
        quoteinfoReference.child(typeSectionNode).child(nodeName).setValue(new QuoteInformationModel(
                BuyLinks,
                IndividualRatings,
                LikedBy,
                poster,
                quotelink,
                title,
                type
        ));

        Toast.makeText(this, "Pushed Quote Information Successfully", Toast.LENGTH_SHORT).show();
    }

    public void updateRecentsList(View view) {
        String keyName = "recent";
        int count = 01;
        for (int i = 0; i < recentData.size(); i++) {
            recentReference.child(keyName + twodigits.format(count)).child("name").setValue(recentData.get(i).name);
            recentReference.child(keyName + twodigits.format(count)).child("type").setValue(recentData.get(i).type);
            count++;
        }
        Toast.makeText(this, "Recent List Updated", Toast.LENGTH_SHORT).show();
    }


    public void addRecentData() {
        recentData = new ArrayList<>();
        recentData.add(new RecentDataModel("Mr Robot", "green"));
        recentData.add(new RecentDataModel("Kung Fu Panda", "red"));
        recentData.add(new RecentDataModel("Kung Fu Panda 2", "red"));
        recentData.add(new RecentDataModel("Kung Fu Panda 3", "red"));
        recentData.add(new RecentDataModel("The Theory of Everything", "red"));
        recentData.add(new RecentDataModel("A Brief History of Time by Stephen Hawking", "yellow"));
        recentData.add(new RecentDataModel("The Subtle Art of Not Giving a F*ck: A Counterintuitive Approach to Living a Good Life by Mark Manson", "yellow"));
        recentData.add(new RecentDataModel("Thor", "red"));
        recentData.add(new RecentDataModel("Thor: The Dark World", "red"));
        recentData.add(new RecentDataModel("Thor: Ragnarok", "red"));
        recentData.add(new RecentDataModel("Iron Man Trilogy", "red"));
        recentData.add(new RecentDataModel("The Avengers", "red"));
        recentData.add(new RecentDataModel("Avengers: Age of Ultron", "red"));
        recentData.add(new RecentDataModel("The Man from Nowhere", "red"));
        recentData.add(new RecentDataModel("Monster", "red"));
        recentData.add(new RecentDataModel("Oldboy", "red"));
        recentData.add(new RecentDataModel("Pretty Little Liars", "green"));
        recentData.add(new RecentDataModel("Psycho-Pass", "blue"));
        recentData.add(new RecentDataModel("Revenge", "green"));
        recentData.add(new RecentDataModel("Story CD: Style, Structure, Substance, and the Principles of Screenwriting by Robert McKee", "yellow"));
        recentData.add(new RecentDataModel("Two and a Half Men", "green"));
    }

    public void addQuoteData() {
        dataToPush = new ArrayList<>();

        //PASTE YOUR DATA HERE;
        dataToPush.add(new DataPushModel("Great things in business are never done by one person. They're done by a team of people.","Success Tips"));
        dataToPush.add(new DataPushModel("Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. And the only way to do great work is to love what you do. If you haven't found it yet, keep looking. Don't settle. As with all matters of the heart, you'll know when you find it.","Success Tips"));
        dataToPush.add(new DataPushModel("Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma - which is living with the results of other people's thinking. Don't let the noise of others' opinions drown out your own inner voice. And most important, have the courage to follow your heart and intuition.","Inspirational"));
        dataToPush.add(new DataPushModel("You can't connect the dots looking forward; you can only connect them looking backwards. So you have to trust that the dots will somehow connect in your future. You have to trust in something - your gut, destiny, life, karma, whatever. This approach has never let me down, and it has made all the difference in my life.","Inspirational"));
        dataToPush.add(new DataPushModel("Innovation distinguishes between a leader and a follower.","Inspirational"));
        dataToPush.add(new DataPushModel("Sometimes life hits you in the head with a brick. Don't lose faith.","Success Tips"));
        dataToPush.add(new DataPushModel("Design is not just what it looks like and feels like. Design is how it works.","Technology"));
        dataToPush.add(new DataPushModel("That's been one of my mantras - focus and simplicity. Simple can be harder than complex: You have to work hard to get your thinking clean to make it simple. But it's worth it in the end because once you get there, you can move mountains.","Inspirational"));
        dataToPush.add(new DataPushModel("Remembering that I'll be dead soon is the most important tool I've ever encountered to help me make the big choices in life. Because almost everything - all external expectations, all pride, all fear of embarrassment or failure - these things just fall away in the face of death, leaving only what is truly important.","Inspirational"));
        dataToPush.add(new DataPushModel("For the past 33 years, I have looked in the mirror every morning and asked myself: 'If today were the last day of my life, would I want to do what I am about to do today?' And whenever the answer has been 'No' for too many days in a row, I know I need to change something.","Inspirational"));
        dataToPush.add(new DataPushModel("Stay hungry, stay foolish.","Success Tips"));
        dataToPush.add(new DataPushModel("Sometimes when you innovate, you make mistakes. It is best to admit them quickly, and get on with improving your other innovations.","Success Tips"));
        dataToPush.add(new DataPushModel("Being the richest man in the cemetery doesn't matter to me. Going to bed at night saying we've done something wonderful, that's what matters to me.","Inspirational"));
        dataToPush.add(new DataPushModel("When you're a carpenter making a beautiful chest of drawers, you're not going to use a piece of plywood on the back, even though it faces the wall and nobody will ever see it. You'll know it's there, so you're going to use a beautiful piece of wood on the back.","Random"));
        dataToPush.add(new DataPushModel("Older people sit down and ask, 'What is it?' but the boy asks, 'What can I do with it?'.","Inspirational"));
        dataToPush.add(new DataPushModel("An iPod, a phone, an internet mobile communicator... these are NOT three separate devices! And we are calling it iPhone! Today Apple is going to reinvent the phone. And here it is.","Technology"));
        dataToPush.add(new DataPushModel("I want to put a ding in the universe.","Inspirational"));
        dataToPush.add(new DataPushModel("I learned about serif and san serif typefaces, about varying the amount of space between different letter combinations, about what makes great typography great. It was beautiful, historical, artistically subtle in a way that science can't capture, and I found it fascinating.","Technology"));//dataToPush.add(new DataPushModel("",""));


        Toast.makeText(this, "Data Push Completed", Toast.LENGTH_SHORT).show();
    }
}

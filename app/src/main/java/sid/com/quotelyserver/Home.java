package sid.com.quotelyserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void btnMigrate(View view) {
        startActivity(new Intent(getBaseContext(), Migrate.class));
    }

    public void btnQuoteOfTheWeek(View view) {
        Intent i = new Intent(getBaseContext(), TypeSection.class);
        i.putExtra("CameFrom", "QOTD");
        startActivity(i);
    }

    public void btnFQ(View view) {
        Intent i = new Intent(getBaseContext(), FamousQuotes.class);
        startActivity(i);
    }

    public void btnNotifications(View view) {
        Intent i = new Intent(getBaseContext(), Notifications.class);
        startActivity(i);
    }
}

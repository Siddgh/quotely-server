package sid.com.quotelyserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Notifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
    }

    public void btnNotifyQuotePusher(View view) {
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","QUOTEPUSHER");
        startActivity(i);
    }

    public void btnNotifyQuoteContributions(View view) {

    }

    public void btnNotifyRecentlyAdded(View view) {
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","RECENT");
        startActivity(i);
    }

}

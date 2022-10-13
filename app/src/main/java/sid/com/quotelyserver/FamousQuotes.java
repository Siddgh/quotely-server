package sid.com.quotelyserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FamousQuotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famous_quotes);
    }

    public void btnFQ02(View view){
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","FQ02");
        startActivity(i);
    } public void btnFQ03(View view){
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","FQ03");
        startActivity(i);
    } public void btnFQ04(View view){
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","FQ04");
        startActivity(i);
    } public void btnFQ05(View view){
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","FQ05");
        startActivity(i);
    } public void btnFQ06(View view){
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","FQ06");
        startActivity(i);
    } public void btnFQ07(View view){
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","FQ07");
        startActivity(i);
    } public void btnFQ08(View view){
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","FQ08");
        startActivity(i);
    } public void btnFQ09(View view){
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","FQ09");
        startActivity(i);
    } public void btnFQ10(View view){
        Intent i = new Intent(getBaseContext(),TypeSection.class);
        i.putExtra("CameFrom","FQ10");
        startActivity(i);
    }
}

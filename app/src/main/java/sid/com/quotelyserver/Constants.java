package sid.com.quotelyserver;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by siddheshdighe on 15/03/18.
 */

public class Constants {

    public static void setUpRecyclerViews(Context c, RecyclerView rv, LinearLayoutManager llm, int orientation) {
        if (rv != null) {
            rv.hasFixedSize();
            llm = new LinearLayoutManager(c);
            llm.setOrientation(orientation);
            rv.setLayoutManager(llm);
            rv.setNestedScrollingEnabled(false);
            rv.setItemViewCacheSize(0);
        }
    }

    public static String trimWithoutNumbers(String data) {
        return data = data.replaceAll("\\d", "");
    }
}

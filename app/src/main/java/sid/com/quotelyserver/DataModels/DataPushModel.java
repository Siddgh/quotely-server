package sid.com.quotelyserver.DataModels;

/**
 * Created by siddheshdighe on 11/03/18.
 */

public class DataPushModel {
    public String quote, category;

    public DataPushModel() {
    }

    public DataPushModel(String quote, String category) {
        this.category = category;
        this.quote = quote;
    }
}

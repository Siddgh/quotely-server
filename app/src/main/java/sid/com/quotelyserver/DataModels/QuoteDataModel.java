package sid.com.quotelyserver.DataModels;

/**
 * Created by siddheshdighe on 11/03/18.
 */

public class QuoteDataModel {
    public String category, contributedby, from, quote, quoteid, type;
    public int likes;

    public QuoteDataModel() {
    }

    public QuoteDataModel(String category, String contributedby, String from, String quote, String quoteid, String type, int likes) {
        this.category = category;
        this.contributedby = contributedby;
        this.from = from;
        this.quote = quote;
        this.quoteid = quoteid;
        this.type = type;
        this.likes = likes;
    }

}

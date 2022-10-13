package sid.com.quotelyserver.DataModels;

/**
 * Created by siddheshdighe on 16/03/18.
 */

public class QOTDModel {

    public String from,quoteid,type;

    public QOTDModel() {
    }

    public QOTDModel(String from, String quoteid,String type) {
        this.from = from;
        this.quoteid = quoteid;
        this.type = type;
    }


}


package sid.com.quotelyserver.DataModels;

/**
 * Created by siddheshdighe on 01/04/18.
 */

public class NotificationsModel {

    public String type,from,quote,section,quotelink,username,poster,quoteid,category,buylink;
    public boolean btnOpen,btnMore,btnRate;

    public NotificationsModel() {
    }

    public NotificationsModel(String type, String from, String quote,String section,String quotelink,String username,String poster,Boolean btnOpen,Boolean btnMore,Boolean btnRate,String quoteid,String category,String buylink) {
        this.type = type;
        this.from = from;
        this.quote = quote;
        this.section = section;
        this.quotelink = quotelink;
        this.username = username;
        this.poster = poster;
        this.btnMore = btnMore;
        this.btnOpen = btnOpen;
        this.btnRate = btnRate;
        this.quoteid = quoteid;
        this.category = category;
        this.buylink = buylink;

    }

}


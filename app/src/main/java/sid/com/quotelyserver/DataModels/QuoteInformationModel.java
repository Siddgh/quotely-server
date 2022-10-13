package sid.com.quotelyserver.DataModels;

/**
 * Created by siddheshdighe on 11/03/18.
 */

public class QuoteInformationModel {
    public String BuyLinks, IndividualRatings, LikedBy, poster, quotelink, title, type;
    public QuoteInformationModel(){}
    public QuoteInformationModel(String BuyLinks, String IndividualRatings,String LikedBy,String poster,String quotelink,String title,String type){
        this.BuyLinks = BuyLinks;
        this.IndividualRatings = IndividualRatings;
        this.LikedBy = LikedBy;
        this.poster = poster;
        this.quotelink = quotelink;
        this.title = title;
        this.type = type;
    }
}

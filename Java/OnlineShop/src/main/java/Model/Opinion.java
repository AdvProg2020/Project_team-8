package Model;

public class Opinion {
    enum OpinionSituation{
        CONFIRMING,CONFIRMED,NOT_CONFIRMED
    }
    private User user;
    private Good good;
    private String commentText;
    private boolean wasBoughtByUser;
    private OpinionSituation situation;
}

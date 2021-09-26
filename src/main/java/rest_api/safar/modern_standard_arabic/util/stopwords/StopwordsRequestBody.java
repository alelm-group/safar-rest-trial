package rest_api.safar.modern_standard_arabic.util.stopwords;

public class StopwordsRequestBody {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "StopwordsRequestBody [text=" + text + "]";
    }


}

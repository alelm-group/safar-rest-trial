package rest_api.safar.modern_standard_arabic.util.pattern_detection;

public class PatternDetectionRequestBody {

    private String text;
    private String implementation;

    public PatternDetectionRequestBody() {
        // TODO Auto-generated constructor stub
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    @Override
    public String toString() {
        return "StopwordsRequestBody [text=" + text + ", implementation=" + implementation + "]";
    }
}

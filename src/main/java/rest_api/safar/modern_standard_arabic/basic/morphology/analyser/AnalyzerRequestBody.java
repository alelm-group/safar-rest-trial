package rest_api.safar.modern_standard_arabic.basic.morphology.analyser;


public class AnalyzerRequestBody {
    private String text;
    private String implementation;

    public AnalyzerRequestBody(String text, String implementation) {
        this.text = text;
        this.implementation = implementation;
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
        return "AnalyzerRequestBody [text=" + text + ", implementation=" + implementation + "]";
    }


}

package rest_api.safar.modern_standard_arabic.basic.morphology.stemmer;

public class StemmerRequestBody {
    private String text;
    private String implementation;

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
        return "requestBody [text=" + text + ",implementation=" + implementation + "]";
    }

}

package rest_api.safar.modern_standard_arabic.application.ner;

public class NerReqeuestBody {

    private String text;
    private String implementation;

    @Override
    public String toString() {
        return "nerReqeuestBody [text=" + text + ", implementation=" + implementation + "]";
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


}

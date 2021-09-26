package rest_api.safar.modern_standard_arabic.basic.syntax.posTagger;

public class PosTagRequestBody {

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
        return "PosTaggerRequestBody [text=" + text + ", implementation=" + implementation + "]";
    }


}

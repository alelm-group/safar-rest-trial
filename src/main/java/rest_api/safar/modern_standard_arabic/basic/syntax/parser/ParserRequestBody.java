package rest_api.safar.modern_standard_arabic.basic.syntax.parser;

public class ParserRequestBody {

    private String text;
    private String implementation;

    public ParserRequestBody(String text, String parser) {
        this.text = text;
        this.implementation = parser;
    }

    @Override
    public String toString() {
        return "parserRequestBody [text=" + text + ", parser=" + implementation + "]";
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

    public void setImplementation(String parser) {
        this.implementation = parser;
    }


}

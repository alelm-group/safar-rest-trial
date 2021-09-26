package rest_api.safar.modern_standard_arabic.util.tokenization;

public class TokenizationRequestBody {

    private String text;
    private boolean withUniqueTokens;

    public TokenizationRequestBody() {

    }

    @Override
    public String toString() {
        return "TokenizationRequestBody [text=" + text + ", withUniqueTokens=" + withUniqueTokens + "]";
    }

    public boolean isWithUniqueTokens() {
        return withUniqueTokens;
    }

    public void setWithUniqueTokens(boolean withUniqueTokens) {
        this.withUniqueTokens = withUniqueTokens;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}

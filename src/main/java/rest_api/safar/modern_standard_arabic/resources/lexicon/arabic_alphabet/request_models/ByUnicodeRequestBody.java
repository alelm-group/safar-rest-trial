package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByUnicodeRequestBody {
    private String unicode;

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    @Override
    public String toString() {
        return "GetLetterByUnicodeRequestBody [unicode=" + unicode + "]";
    }


}

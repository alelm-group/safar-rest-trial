package rest_api.safar.modern_standard_arabic.util.transliteration;

public class TransliterationRequestBody {

    private String text;
    private String transliterationFunction;

    public String getTransliterationFunction() {
        return transliterationFunction;
    }

    public void setTransliterationFunction(String transliterationFunction) {
        this.transliterationFunction = transliterationFunction;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "TransliterationRequestBody [text=" + text + ", transliterationFunction=" + transliterationFunction + "]";
    }


}

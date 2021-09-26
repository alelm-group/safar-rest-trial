package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByIsolatedDisplayRequestBody {

    private String isolatedDisplay;


    @Override
    public String toString() {
        return "GetLetterByIsolatedDisplay [isolatedDisplay=" + isolatedDisplay + "]";
    }

    public String getIsolatedDisplay() {
        return isolatedDisplay;
    }

    public void setIsolatedDisplay(String isolatedDisplay) {
        this.isolatedDisplay = isolatedDisplay;
    }

}

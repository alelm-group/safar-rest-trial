package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class GetLetterRequestBody {
    private String letter;

    @Override
    public String toString() {
        return "GetLetterRequestBody [letter=" + letter + "]";
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }


}

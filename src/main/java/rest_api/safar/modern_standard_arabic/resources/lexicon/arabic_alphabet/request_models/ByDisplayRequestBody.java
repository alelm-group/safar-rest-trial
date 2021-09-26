package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models;

public class ByDisplayRequestBody {
    private String display;
    private int position;


    @Override
    public String toString() {
        return "GetLetterByDisplayRequestBody [display=" + display + ", position=" + position + "]";
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}

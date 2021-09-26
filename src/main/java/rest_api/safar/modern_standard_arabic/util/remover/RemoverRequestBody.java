package rest_api.safar.modern_standard_arabic.util.remover;

public class RemoverRequestBody {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "RemoverRequestBody [text=" + text + "]";
    }


}

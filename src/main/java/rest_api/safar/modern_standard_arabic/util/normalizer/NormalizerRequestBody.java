package rest_api.safar.modern_standard_arabic.util.normalizer;

public class NormalizerRequestBody {
    private String text;
    private String formOfNormalization;
    private String otherCharsToDelete;

    @Override
    public String toString() {
        return "NormalizerRequestBody [text=" + text + ", formOfNormalization=" + formOfNormalization
                + ", otherCharsToDelete=" + otherCharsToDelete + "]";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFormOfNormalization() {
        return formOfNormalization;
    }

    public void setFormOfNormalization(String formOfNormalization) {
        this.formOfNormalization = formOfNormalization;
    }

    public String getOtherCharsToDelete() {
        return otherCharsToDelete;
    }

    public void setOtherCharsToDelete(String otherCharsToDelete) {
        this.otherCharsToDelete = otherCharsToDelete;
    }


}

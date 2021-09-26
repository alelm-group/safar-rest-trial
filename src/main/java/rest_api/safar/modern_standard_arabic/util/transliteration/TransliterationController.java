package rest_api.safar.modern_standard_arabic.util.transliteration;

import common.constants.Transliteration;
import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.util.transliteration.impl.SAFARTransliterator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class TransliterationController {

    public static String[] functions = {"ArabicToLatin", "LatinToArabic"};
    private static SAFARTransliterator safarTransliterator;

    @PostMapping("${safar.modern_standard_arabic.util.tranliteration}")
    public Map<String, String> transliterateArabicToLatin(@RequestBody TransliterationRequestBody req,
                                                          HttpServletResponse response) throws IOException {

        Map<String, String> map = new HashMap<String, String>();
        String result = "";

        try {
            if (req.getText().isEmpty()) throw new Exception("Text is empty !!");
            else if (req.getTransliterationFunction().isEmpty()
                    || !ArrayUtils.contains(functions, req.getTransliterationFunction()))
                throw new Exception("TransliterationFunction is empty or not in [ArabicToLatin,LatinToArabic]");


            if (req.getTransliterationFunction().equals("LatinToArabic")) {
                result = safarTransliterator.transliterateLatinToArabic(req.getText(), Transliteration.BUCKWALTER);
            } else {
                result = safarTransliterator.transliterateArabicToLatin(req.getText(), Transliteration.BUCKWALTER);

            }
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty and TransliterationFunction in [ArabicToLatin,LatinToArabic]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        map.put("transliteration", result);

        return map;
    }


    @Bean
    public static SAFARTransliterator getSafarTransliterator() {
        safarTransliterator = new SAFARTransliterator();
        return safarTransliterator;
    }
}

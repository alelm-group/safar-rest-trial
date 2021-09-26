package rest_api.safar.modern_standard_arabic.util.normalizer;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.util.normalization.factory.NormalizerFactory;
import safar.modern_standard_arabic.util.normalization.interfaces.INormalizer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
public class NormalizerRestController {
    private static INormalizer normalizer;

    @PostMapping("${safar.modern_standard_arabic.util.normalization.normalize}")
    public Map<String, String> normalize(@RequestBody NormalizerRequestBody req,
                                         HttpServletResponse response) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        String normalizedText = "";
        try {

            if (req.getText() == null || req.getText() == "") throw new Exception("Text is empty !!");

            if (req.getFormOfNormalization() == null)
                req.setFormOfNormalization("NonArabicNumbersWordsContainingDigitsSingleCharDiacriticsPunctuation");
            if (req.getOtherCharsToDelete() == null) req.setOtherCharsToDelete("");

            normalizedText = normalizer.normalize(req.getText(), req.getFormOfNormalization(), req.getOtherCharsToDelete());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your text is not empty and the formNormalization is valid if you want to configure it !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        map.put("normalizedText", normalizedText);

        return map;

    }

    @Bean
    public INormalizer getNormalizer() {
        normalizer = NormalizerFactory.getSAFARNormalizerImplementation();
        return normalizer;
    }

}

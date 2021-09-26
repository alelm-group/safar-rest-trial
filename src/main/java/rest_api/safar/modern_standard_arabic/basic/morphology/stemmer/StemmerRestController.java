package rest_api.safar.modern_standard_arabic.basic.morphology.stemmer;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.basic.morphology.stemmer.factory.StemmerFactory;
import safar.modern_standard_arabic.basic.morphology.stemmer.interfaces.IStemmer;
import safar.modern_standard_arabic.basic.morphology.stemmer.model.WordStemmerAnalysis;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class StemmerRestController {
    public static String[] implementations = {"ISRI", "SAFAR", "MOTAZ", "KHOJA", "TASHA", "LIGHT"};
    private static IStemmer iSRIStemmer;
    private static IStemmer khojaStemmer;
    private static IStemmer motazStemmer;
    private static IStemmer safarStemmer;
    private static IStemmer tashaphyneStemmer;
    private static IStemmer light10Stemmer;

    @PostMapping("${safar.modern_standard_arabic.basic.morphology.stemmer}")
    public List<WordStemmerAnalysis> stem(@RequestBody StemmerRequestBody req,
                                          HttpServletResponse response) throws IOException {
        List<WordStemmerAnalysis> stemmerAnalyses = new ArrayList<>();
        try {
            if (req.getText().isEmpty())
                throw new Exception("text is empty !");
            else if (req.getImplementation().isEmpty()
                    || !ArrayUtils.contains(implementations, req.getImplementation()))
                throw new Exception("IMPLEMENTATION is empty or not in [ISRI,MOTAZ,SAFAR,TASHA,KHOJA,LIGHT]");

            switch (req.getImplementation()) {
                case "ISRI":
                    stemmerAnalyses = iSRIStemmer.stem(req.getText());
                    break;
                case "KHOJA":
                    stemmerAnalyses = khojaStemmer.stem(req.getText());
                    break;
                case "MOTAZ":
                    stemmerAnalyses = motazStemmer.stem(req.getText());
                    break;
                case "SAFAR":
                    stemmerAnalyses = safarStemmer.stem(req.getText());
                    break;
                case "TASHA":
                    stemmerAnalyses = tashaphyneStemmer.stem(req.getText());
                    break;
                default:
                    stemmerAnalyses = light10Stemmer.stem(req.getText());
                    break;
            }

        } catch (Exception e) {
            response.setHeader("Hint",
                    "verify that your : text is not null and the implementation is in [ISRI,MOTAZ,SAFAR,TASHA,KHOJA,LIGHT]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return stemmerAnalyses;
    }


    @Bean
    public static IStemmer getISRIStemmer() {
        iSRIStemmer = StemmerFactory.getISRIImplementation();
        return iSRIStemmer;
    }

    @Bean
    public static IStemmer getKhojaStemmer() {
        khojaStemmer = StemmerFactory.getKhojaImplementation();
        return khojaStemmer;
    }

    @Bean
    public static IStemmer getMotazStemmer() {
        motazStemmer = StemmerFactory.getMotazImplementation();
        return motazStemmer;
    }

    @Bean
    public static IStemmer getSafarStemmer() {
        safarStemmer = StemmerFactory.getSAFARImplementation();
        return safarStemmer;
    }

    @Bean
    public static IStemmer getTashaphyneStemmer() {
        tashaphyneStemmer = StemmerFactory.getTashaphyneImplementation();
        return tashaphyneStemmer;
    }

    @Bean
    public static IStemmer getLight10Stemmer() {
        light10Stemmer = StemmerFactory.getLight10Implementation();
        return light10Stemmer;
    }
}

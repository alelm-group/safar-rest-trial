package rest_api.safar.modern_standard_arabic.basic.morphology.stemmer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import safar.modern_standard_arabic.basic.morphology.stemmer.factory.StemmerFactory;
import safar.modern_standard_arabic.basic.morphology.stemmer.interfaces.IStemmer;
import safar.modern_standard_arabic.basic.morphology.stemmer.model.WordStemmerAnalysis;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class StemmerRestController {
    public static String[] implementations = {"LIGHT"};
    private final IStemmer light10Stemmer;

    public StemmerRestController() {
        light10Stemmer = StemmerFactory.getLight10Implementation();
    }

    @PostMapping("${safar.modern_standard_arabic.basic.morphology.stemmer}")
    public List<WordStemmerAnalysis> stem(@RequestBody StemmerRequestBody req,
                                          HttpServletResponse response) throws IOException {
        List<WordStemmerAnalysis> stemmerAnalyses = light10Stemmer.stem(req.getText());
        return stemmerAnalyses;
    }
}

package rest_api.safar.modern_standard_arabic.basic.morphology.lemmatizer;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.basic.morphology.lemmatizer.factory.LemmatizerFactory;
import safar.modern_standard_arabic.basic.morphology.lemmatizer.interfaces.ILemmatizer;
import safar.modern_standard_arabic.basic.morphology.lemmatizer.model.WordLemmatizerAnalysis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class LemmatizerRestController {
    public static ILemmatizer safarImplementation;
    public static ILemmatizer farasaImplementation;
    public static ILemmatizer khalilImplementation;
    public static String[] implementations = {"FARASA", "ALKHALIL", "SAFAR"};

    @PostMapping("${safar.modern_standard_arabic.basic.morphology.lemmatizer}")
    public List<WordLemmatizerAnalysis> lemmatize(@RequestBody LemmatizerRequestBody req,
                                                  HttpServletResponse response) throws IOException {
        List<WordLemmatizerAnalysis> lemmatizerAnalyses = new ArrayList<>();
        try {
            if (req.getText().isEmpty()) throw new Exception("Text is empty !!");
            else if (req.getImplementation().isEmpty() || !ArrayUtils.contains(implementations, req.getImplementation()))
                throw new Exception("IMPLEMENTATION is empty or not in [SAFAR,FARASA,ALKHALIL]");
            switch (req.getImplementation()) {
                case "SAFAR":
                    lemmatizerAnalyses = safarImplementation.lemmatize(req.getText());
                    break;
                case "FARASA":
                    lemmatizerAnalyses = farasaImplementation.lemmatize(req.getText());
                    break;
                default:
                    lemmatizerAnalyses = khalilImplementation.lemmatize(req.getText());
                    break;
            }
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty and the implementation in [ALKHALIL, FARASA, SAFAR]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return lemmatizerAnalyses;
    }

    @Bean
    public static ILemmatizer getSafarLemmatizerImplementation() {
        safarImplementation = LemmatizerFactory.getSAFARImplementation();
        return safarImplementation;
    }

    @Bean
    public static ILemmatizer getFarasaImplementation() {
        farasaImplementation = LemmatizerFactory.getFARASAImplementation();
        return farasaImplementation;
    }

    @Bean
    public static ILemmatizer getKhalilImplementation() {
        khalilImplementation = LemmatizerFactory.getALKALILImplementation();
        return khalilImplementation;
    }
}

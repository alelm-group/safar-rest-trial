package rest_api.safar.modern_standard_arabic.basic.syntax.posTagger;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest_api.safar.modern_standard_arabic.basic.morphology.analyser.AnalyzerRequestBody;
import safar.modern_standard_arabic.basic.syntax.posTagger.factory.PosFactory;
import safar.modern_standard_arabic.basic.syntax.posTagger.interfaces.IPos;
import safar.modern_standard_arabic.basic.syntax.posTagger.model.WordPOSAnalysis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PosTagRestController {

    public static String[] implementations = {"SAFARLIGHT", "FARASA"};
    private static IPos safarLigthPOSImplementation;
    private static IPos farsaImplementation;

    @PostMapping("${safar.modern_standard_arabic.basic.syntax.posTagger}")
    public List<WordPOSAnalysis> tag(@RequestBody AnalyzerRequestBody req,
                                     HttpServletResponse response) throws IOException {

        List<WordPOSAnalysis> wordPosAnalysis = new ArrayList<>();

        try {
            if (req.getText().isEmpty()) throw new Exception("Text is empty !!");
            else if (req.getImplementation().isEmpty()
                    || !ArrayUtils.contains(implementations, req.getImplementation()))
                throw new Exception("IMPLEMENTATION is empty or not in [SAFARLIGHT, FARASA]");

            switch (req.getImplementation()) {
                case "SAFARLIGHT":
                    wordPosAnalysis = safarLigthPOSImplementation.tag(req.getText());
                    break;
                default:
                    wordPosAnalysis = farsaImplementation.tag(req.getText());
                    break;
            }

        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty and the implementation in [SAFAR, FARASA]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return wordPosAnalysis;
    }


    @Bean
    public static IPos getFarsaPOSImplementation() {
        farsaImplementation = PosFactory.getSafarLightPOSImplementation();
        return farsaImplementation;
    }

    @Bean
    public static IPos getSafarPOSImplementation() {
        safarLigthPOSImplementation = PosFactory.getSafarLightPOSImplementation();
        return safarLigthPOSImplementation ;
    }
}

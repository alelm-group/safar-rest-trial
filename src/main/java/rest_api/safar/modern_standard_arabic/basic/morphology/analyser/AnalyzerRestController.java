package rest_api.safar.modern_standard_arabic.basic.morphology.analyser;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.basic.morphology.analyzer.factory.MorphologyAnalyzerFactory;
import safar.modern_standard_arabic.basic.morphology.analyzer.interfaces.IMorphologyAnalyzer;
import safar.modern_standard_arabic.basic.morphology.analyzer.model.WordMorphologyAnalysis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
//todo lot of todos.....
public class AnalyzerRestController {

    public static String[] implementations = {"ALKHALIL", "ALKHALIL2", "BAMA", "MADAMIRA"};
    private static IMorphologyAnalyzer AlkhalilAnalyzer;
    private static IMorphologyAnalyzer Alkhalil2Analyzer;
    private static IMorphologyAnalyzer BamaAnalyzer;
    private static IMorphologyAnalyzer MadamiraAnalyzer;

    @PostMapping("${safar.modern_standard_arabic.basic.morphology.analyzer}")
    public List<WordMorphologyAnalysis> analyze(@RequestBody AnalyzerRequestBody req,
                                                HttpServletResponse response) throws IOException {
        List<WordMorphologyAnalysis> wordMorphologyAnalyses = new ArrayList<>();
        try {
            if (req.getText().isEmpty()) throw new Exception("Text is empty !!");
            else if (req.getImplementation().isEmpty()
                    || !ArrayUtils.contains(implementations, req.getImplementation()))
                throw new Exception("IMPLEMENTATION is empty or not in [ALKHALIL,ALKHALIL2,BAMA,MADAMIRA]");

            switch (req.getImplementation()) {
                case "ALKHALIL":
                    wordMorphologyAnalyses = AlkhalilAnalyzer.analyze(req.getText());
                    break;
                case "ALKHALIL2":
                    wordMorphologyAnalyses = Alkhalil2Analyzer.analyze(req.getText());
                    break;
                case "BAMA":
                    wordMorphologyAnalyses = BamaAnalyzer.analyze(req.getText());
                    break;
                default:
                    wordMorphologyAnalyses = MadamiraAnalyzer.analyze(req.getText());
                    break;
            }
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty and the implementation in [ALKHALIL,ALKHALIL2,BAMA,MADAMIRA]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return wordMorphologyAnalyses;
    }

    @Bean
    public IMorphologyAnalyzer getAlkhalilImplementation() {
        AlkhalilAnalyzer = MorphologyAnalyzerFactory.getAlkhalilImplementation();
        return AlkhalilAnalyzer;
    }

    @Bean
    public IMorphologyAnalyzer getAlkhalil2Implementation() {
        Alkhalil2Analyzer = MorphologyAnalyzerFactory.getAlkhalil2Implementation();
        return Alkhalil2Analyzer;
    }

    //todo
    @Bean
    public IMorphologyAnalyzer getBAMAImplementation() {
        System.out.println("on loading BAMA ......");
        BamaAnalyzer = AlkhalilAnalyzer;
        return BamaAnalyzer;
    }

    //todo
    @Bean
    public IMorphologyAnalyzer getMADAMIRAImplementation() {
        MadamiraAnalyzer = AlkhalilAnalyzer;
        return MadamiraAnalyzer;
    }


}

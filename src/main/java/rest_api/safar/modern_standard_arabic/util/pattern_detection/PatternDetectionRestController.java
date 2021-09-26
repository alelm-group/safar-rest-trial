package rest_api.safar.modern_standard_arabic.util.pattern_detection;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.util.patternDetection.factory.PatternFactory;
import safar.modern_standard_arabic.util.patternDetection.interfaces.IPattern;
import safar.modern_standard_arabic.util.patternDetection.model.ArabicPattern;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PatternDetectionRestController {

    public static String[] implementations = {"SAFAR", "ALKHALIL"};
    private static IPattern safarPattenDetection;
    private static IPattern alkhalilPattenDetection;

    @PostMapping("${safar.modern_standard_arabic.util.pattern_detection}")
    public List detect_pattern(@RequestBody PatternDetectionRequestBody req,
                               HttpServletResponse response) throws IOException {
        //TODO fix pattern detection Alkhalil implementation
        List result = new ArrayList();
        try {
            if (req.getText().isEmpty()) throw new Exception("Text is empty !!");

            else if (req.getImplementation().isEmpty() ||

                    !ArrayUtils.contains(implementations, req.getImplementation())) {

                throw new Exception("IMPLEMENTATION is empty or not in [SAFAR, ALKHALIL]");
            }
            String[] splited = req.getText().split("\\s+");
            for (String i : splited) {
                System.out.println(i);
            }
            switch (req.getImplementation()) {
                case "SAFAR":
                    for (String i : splited) {
                        ArabicPattern pattern = safarPattenDetection.detectPattern(i).get(0);
                        result.add(pattern);
                    }
                    break;

                default:
                    for (String i : splited) {
                        List pattern = alkhalilPattenDetection.detectPattern(i);
                        result.add(pattern);
                    }
                    break;
            }


        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty and the implementation in [SAFAR, ALKHALIL]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }


    @Bean
    public static IPattern getSafarPattenDetection() {
        safarPattenDetection = PatternFactory.getSAFARPatternImplementation();
        return safarPattenDetection;
    }

    @Bean
    public static IPattern getAlkhalilPattenDetection() {
        alkhalilPattenDetection = PatternFactory.getAlKhalilPatternImplementation();
        return alkhalilPattenDetection;
    }

}
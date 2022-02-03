package rest_api.safar.machine_learning;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import safar.machine_learning.levenshtein.factory.LevenshteinFactory;
import safar.machine_learning.levenshtein.interfaces.ILevenshtein;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class MLRestController {

    public static String[] implementations = {"APACHE", "SAFAR"};
    private final ILevenshtein safarLev;
    private final ILevenshtein apacheLev;

    public MLRestController() {
        apacheLev = LevenshteinFactory.getApacheImplementation();
        safarLev = LevenshteinFactory.getSAFARImplementation();
    }

    @PostMapping("${safar.machine_learning.levenshtein}")
    public Map<String, Float> getLevenshtein(@RequestBody LevRequestBody req,
                                             HttpServletResponse response) throws IOException {
        float levScore;
        Map<String, Float> map = new HashMap<>();
        try {
            if (req.getText1().isEmpty() || req.getText2().isEmpty()) throw new Exception("Text1 or Text2 is empty !!");
            else if (req.getImplementation().isEmpty() || !ArrayUtils.contains(implementations, req.getImplementation())) {
                throw new Exception("IMPLEMENTATION is empty or not in [APACHE, SAFAR]");
            }

            switch (req.getImplementation()) {
                case "APACHE":
                    levScore = safarLev.getLevenshtein(req.getText1(), req.getText2());
                    break;
                default:
                    levScore = apacheLev.getLevenshtein(req.getText1(), req.getText2());
                    break;
            }
            map.put("safarLev", levScore);


        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty and the implementation in [APACHE, SAFAR]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return map;
    }
}

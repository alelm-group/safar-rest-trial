package rest_api.safar.modern_standard_arabic.util.stopwords;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import safar.modern_standard_arabic.util.stop_words.factory.StopWordFactory;
import safar.modern_standard_arabic.util.stop_words.interfaces.ISWsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class StopwordsController {
    private ISWsService sWFactory;

    @PostMapping("${safar.modern_standard_arabic.util.stop_words.is_stop_word}")
    public Map isStopword(@RequestBody StopwordsRequestBody req,
                          HttpServletResponse response) throws IOException {
        try {
            if (req.getText().isEmpty()) throw new Exception("Text is Empty !!");
        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        Map map = new HashMap();
        boolean isSW = sWFactory.isStopWord(req.getText());

        map.put("Word", req.getText());
        map.put("isStopWord", isSW);
        return map;
    }



    @Bean
    public ISWsService getsWFactory() {
        sWFactory = StopWordFactory.getSWsImplementation();
        return sWFactory;
    }
}

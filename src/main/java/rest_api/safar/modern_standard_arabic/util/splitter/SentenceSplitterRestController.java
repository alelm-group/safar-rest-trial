package rest_api.safar.modern_standard_arabic.util.splitter;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import safar.modern_standard_arabic.util.splitting.impl.SAFARSentenceSplitter;
import safar.modern_standard_arabic.util.splitting.interfaces.ISentenceSplitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin
public class SentenceSplitterRestController {
    private static ISentenceSplitter sentenceSplitter;

    @PostMapping("${safar.modern_standard_arabic.util.splitting}")
    public Map<Integer, String> split(@RequestBody SplitterRequestBody req,
                                      HttpServletResponse response) throws IOException {
        int num = 1;
        Map<Integer, String> map = new HashMap<Integer, String>();
        String[] sentences = null;
        try {
            if (req.getText().isEmpty()) throw new Exception("Text is empty !!");

            if (Objects.isNull(req.getDelimiter()) && Objects.isNull(req.getSpecialCases())) {

                sentences = sentenceSplitter.split(req.getText());

            } else if (Objects.isNull(req.getDelimiter())) {

                sentences = sentenceSplitter.split(req.getText(), "", req.getSpecialCases());

            } else if (Objects.isNull(req.getSpecialCases())) {

                sentences = sentenceSplitter.split(req.getText(), req.getDelimiter(), "");

            } else {

                sentences = sentenceSplitter.split(req.getText(), req.getDelimiter(), req.getSpecialCases());
            }

        } catch (Exception e) {
            response.setHeader("Hint", "verify that the text is not empty, you can also specify [specialCases, delimiter]");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }

        for (String i : sentences) {
            map.put((num++), i);
        }

        return map;
    }

    @Bean
    public ISentenceSplitter getSentenceSplitter() {
        sentenceSplitter = new SAFARSentenceSplitter();
        return sentenceSplitter;
    }


}

package rest_api.safar.modern_standard_arabic.basic.syntax.posTagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import safar.modern_standard_arabic.basic.syntax.posTagger.impl.SafarLightPOS;
import safar.modern_standard_arabic.basic.syntax.posTagger.interfaces.IPos;
import safar.modern_standard_arabic.basic.syntax.posTagger.model.WordPOSAnalysis;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class PosTagRestController {
    public static String[] implementations = {"SAFARLIGHT"};

    @Autowired
    private IPos safarLigthPOSImplementation;

    @Bean
    public IPos getSafarLigthPOSImplementation() {
        return new SafarLightPOS();
    }

    @PostMapping("${safar.modern_standard_arabic.basic.syntax.posTagger}")
    public List<WordPOSAnalysis> tag(@RequestBody PosTagRequestBody req,
                                     HttpServletResponse response) throws IOException {
        List<WordPOSAnalysis> wordPosAnalysis = safarLigthPOSImplementation.tag(req.getText());
        return wordPosAnalysis;
    }



}

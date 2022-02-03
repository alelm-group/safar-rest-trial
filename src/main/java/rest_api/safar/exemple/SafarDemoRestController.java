package rest_api.safar.exemple;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import safar.modern_standard_arabic.basic.syntax.posTagger.factory.PosFactory;
import safar.modern_standard_arabic.basic.syntax.posTagger.interfaces.IPos;
import safar.modern_standard_arabic.basic.syntax.posTagger.model.WordPOSAnalysis;

import java.util.List;

@RestController
@CrossOrigin
public class SafarDemoRestController {
    @GetMapping("/demo-pos-tagger")
    public List<WordPOSAnalysis> DemoNormalizer(){
        IPos pos = PosFactory.getSafarLightPOSImplementation();
        return pos.tag("صباح الخير");
    }
}

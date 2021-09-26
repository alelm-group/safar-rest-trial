package rest_api.safar.modern_standard_arabic.util.benchmark.syntax.parser;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
public class BenchmarkParserRestController {


    @RequestMapping(method = RequestMethod.POST, path = "/msa/utils/benchmark/parsers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map benchmark_parser(@RequestBody BenchmarkParserRequestBody req,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        return BenchmarkParserRequestBody.compare();
    }

}

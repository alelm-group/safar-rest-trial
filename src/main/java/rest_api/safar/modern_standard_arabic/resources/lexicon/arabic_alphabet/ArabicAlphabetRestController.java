package rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet;

import edu.stanford.nlp.util.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import rest_api.pagination.PaginationRequestBody;
import rest_api.safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.request_models.*;
import safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.factory.AlphabetFactory;
import safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.interfaces.IAlphabetService;
import safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.model.Letter;
import safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.model.Punctuation;
import safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.model.Vowel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class ArabicAlphabetRestController {
    public static String[] positions = {"begin", "middle", "end"};
    private IAlphabetService alphabetImplementation;

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.get_All}")
    public Map getLetters(@RequestBody PaginationRequestBody req) {
        List<Letter> result = alphabetImplementation.getLetters();
        return req.getPaginationData(result);
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_desc}")
    public Letter getLetterByDescription(
            HttpServletResponse response,
            @RequestBody ByDescriptionRequestBody req) throws IOException {
        Letter result = new Letter();

        try {
            if (req.getDesc().isEmpty()) throw new Exception("desc is empty !!");

            result = alphabetImplementation.getLetterByDescription(req.getDesc(), req.getLang());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : desc and lang values are not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_arabic_desc}")
    public Letter getLetterByArabicDesc(HttpServletResponse response,
                                        @RequestBody ByArabicDescRequestBody req) throws IOException {
        Letter result = new Letter();

        try {
            if (req.getArabicDesc().isEmpty()) throw new Exception("arabicDesc is empty !!");
            result = alphabetImplementation.getLetterByArabicDesc(req.getArabicDesc());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : arabicDesc is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_english_desc}")
    @RequestMapping(method = RequestMethod.POST, path = "/letters/by-english-desc")
    public Letter getLetterByEnglishDesc(
            HttpServletResponse response,
            @RequestBody ByEnglishDescRequestBody req) throws IOException {
        Letter result = new Letter();

        try {
            if (req.getEnglishDesc().isEmpty()) throw new Exception("englishDesc is empty !!");

            result = alphabetImplementation.getLetterByEnglishDesc(req.getEnglishDesc());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : englishDesc is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_display}")
    public Letter getLetterByDisplay(
            HttpServletResponse response,
            @RequestBody ByDisplayRequestBody req) throws IOException {
        Letter result = new Letter();
        try {
            if (req.getDisplay().isEmpty()) throw new Exception("display is empty !!");
            result = alphabetImplementation.getLetterByDisplay(req.getDisplay(), req.getPosition());
        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : display or position parameteris not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.letter}")
    public Letter getLetter(
            HttpServletResponse response,
            @RequestBody GetLetterRequestBody req) throws IOException {
        Letter result = new Letter();

        try {
            if (req.getLetter().isEmpty()) throw new Exception("letter is empty !!");

            result = alphabetImplementation.getLetter(req.getLetter());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : letter is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_isolated_display}")
    public Letter getLetterIsolatedDisplay(
            HttpServletResponse response,
            @RequestBody ByIsolatedDisplayRequestBody req) throws IOException {
        Letter result = new Letter();

        try {
            if (req.getIsolatedDisplay().isEmpty()) throw new Exception("isolatedDisplay is empty !!");

            result = alphabetImplementation.getLetterByIsolatedDisplay(req.getIsolatedDisplay());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : isolatedDisplay is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_position_display}")
    public Letter getLetterByPositionDisplay(
            HttpServletResponse response,
            @RequestBody ByPositionDisplayRequestBody req) throws IOException {
        Letter result = new Letter();

        try {
            if (req.getDisplay().isEmpty()) throw new Exception("display is empty !!");

            if (req.getPosition().isEmpty() || !ArrayUtils.contains(positions, req.getPosition()))
                throw new Exception("make sure the the position is "
                        + "not empty and in [begin, middle, end]");
            switch (req.getPosition()) {
                case "begin":
                    result = alphabetImplementation.getLetterByBeginDisplay(req.getDisplay());
                    break;

                case "middle":
                    result = alphabetImplementation.getLetterByMiddleDisplay(req.getDisplay());
                    break;

                default:
                    result = alphabetImplementation.getLetterByEndDisplay(req.getDisplay());
                    break;
            }

        } catch (Exception e) {
            response.setHeader("Hint", "verify that display's value is not empty and the position in in [begin, middle, end] !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_code}")
    public Letter getLetterByCode(
            HttpServletResponse response,
            @RequestBody ByCodeRequestBody req) throws IOException {
        Letter result = new Letter();
        try {
            if (req.getCode().isEmpty()) throw new Exception("code is empty !!");
            result = alphabetImplementation.getLetterByCode(req.getCode(), req.getEncoding());
        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : code is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_unicode}")
    public Letter getLetterByUniCode( HttpServletResponse response,
                                     @RequestBody ByUnicodeRequestBody req) throws IOException {
        Letter result = new Letter();
        try {
            if (req.getUnicode().isEmpty()) throw new Exception("unicode is empty !!");
            result = alphabetImplementation.getLetterByUnicode(req.getUnicode());
        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : unicode is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_translit}")
    public Letter getLetterByTranslit(
            HttpServletResponse response,
            @RequestBody ByTranslitRequestBody req) throws IOException {
        Letter result = new Letter();
        try {
            if (req.getTranslit().isEmpty()) throw new Exception("translit is empty !!");
            result = alphabetImplementation.getLetterByTranslit(req.getTranslit(), req.getType());
        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : translit is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_buckwalter}")
    public Letter getLetterByBwTranslit(HttpServletResponse response,
                                        @RequestBody ByBwTranslitRequestBody req) throws IOException {
        Letter result = new Letter();
        try {
            if (req.getBuckwalter().isEmpty()) throw new Exception("buckwalter is empty !!");
            result = alphabetImplementation.getLetterByBwTranslit(req.getBuckwalter());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : buckwalter is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.letters.by_wiki}")
    public Letter getLetterByWikiTranslit(HttpServletResponse response,
            @RequestBody ByWikiTranslationRequestBody req) throws IOException {
        Letter result = new Letter();
        try {
            if (req.getWiki().isEmpty()) throw new Exception("wiki is empty !!");
            result = alphabetImplementation.getLetterByWikiTranslit(req.getWiki());
        } catch (Exception e) {
            response.setHeader("Hint", "verify that : wiki is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.get_All}")
    public Map getVowels(@RequestBody PaginationRequestBody req) {

        List<Vowel> result = alphabetImplementation.getVowels();

        return req.getPaginationData(result);
    }


    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.by_desc}")
    public Vowel getVowelByDescription(HttpServletResponse response,
                                       @RequestBody ByDescriptionRequestBody req) throws IOException {
        Vowel result = new Vowel();

        try {
            // TODO: Lexicon/arabic_alphabet/getLetterByDescription ||||  lang and desc examples
            if (req.getDesc().isEmpty()) throw new Exception("desc is empty !!");

            result = alphabetImplementation.getVowelByDescription(req.getDesc(), req.getLang());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : desc and lang values are not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.by_arabic_desc}")
    public Vowel getVowelByArabicDesc(HttpServletResponse response,
                                      @RequestBody ByArabicDescRequestBody req) throws IOException {
        Vowel result = new Vowel();

        try {

            if (req.getArabicDesc().isEmpty()) throw new Exception("ArabicDesc is empty !!");

            result = alphabetImplementation.getVowelByArabicDesc(req.getArabicDesc());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : ArabicDesc is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.by_english_desc}")
    public Vowel getVowelByEnglishDesc(                                       HttpServletResponse response,
                                       @RequestBody ByEnglishDescRequestBody req) throws IOException {
        Vowel result = new Vowel();

        try {
            if (req.getEnglishDesc().isEmpty()) throw new Exception("englishDesc is empty !!");

            result = alphabetImplementation.getVowelByEnglishDesc(req.getEnglishDesc());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : englishDesc is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.by_display}")
    public Vowel getVowelByDisplay(HttpServletResponse response,
                                   @RequestBody ByDisplayRequestBody req) throws IOException {
        Vowel result = new Vowel();

        try {
            if (req.getDisplay().isEmpty()) throw new Exception("display is empty !!");

            result = alphabetImplementation.getVowelByDisplay(req.getDisplay());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : display parameteris not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }


    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.by_code}")
    public Vowel getVowelByCode(HttpServletResponse response,
                                @RequestBody ByCodeRequestBody req) throws IOException {

        Vowel result = new Vowel();

        try {
            if (req.getCode().isEmpty()) throw new Exception("code is empty !!");

            result = alphabetImplementation.getVowelByCode(req.getCode(), req.getEncoding());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : code is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.by_unicode}")
    public Vowel getVowelByUniCode(HttpServletResponse response,
                                   @RequestBody ByUnicodeRequestBody req) throws IOException {
        Vowel result = new Vowel();

        try {
            if (req.getUnicode().isEmpty()) throw new Exception("unicode is empty !!");

            result = alphabetImplementation.getVowelByUnicode(req.getUnicode());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : unicode is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.by_buckwalter}")
    public Vowel getVowelByBwTranslit(HttpServletResponse response,
                                      @RequestBody ByBwTranslitRequestBody req) throws IOException {
        Vowel result = new Vowel();

        try {
            if (req.getBuckwalter().isEmpty()) throw new Exception("buckwalter is empty !!");

            result = alphabetImplementation.getVowelByBwTranslit(req.getBuckwalter());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : buckwalter is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.by_wiki}")
    public Vowel getVowelByWikiTranslit(HttpServletResponse response,
                                        @RequestBody ByWikiTranslationRequestBody req) throws IOException {

        Vowel result = new Vowel();

        try {
            if (req.getWiki().isEmpty()) throw new Exception("wiki is empty !!");

            result = alphabetImplementation.getVowelByWikiTranslit(req.getWiki());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : buckwalter is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.vowels.by_translit}")
    public Vowel getVowelByTranslit( HttpServletResponse response,
                                    @RequestBody ByTranslitRequestBody req) throws IOException {
        Vowel result = new Vowel();

        try {
            if (req.getTranslit().isEmpty()) throw new Exception("translit is empty !!");

            result = alphabetImplementation.getVowelByTranslit(req.getTranslit(), req.getType());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : translit is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }


    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.punctuation.get_All}")
    public Map getPunctuations(@RequestBody PaginationRequestBody req) {
        List<Punctuation> result = alphabetImplementation.getPunctuations();
        return req.getPaginationData(result);
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.punctuation.by_desc}")
    public Punctuation getPunctuationByDescription(HttpServletResponse response,
                                                   @RequestBody ByDescriptionRequestBody req) throws IOException {
        Punctuation result = new Punctuation();

        try {
            // TODO: Lexicon/arabic_alphabet/getLetterByDescription ||||  lang and desc examples
            if (req.getDesc().isEmpty()) throw new Exception("desc is empty !!");

            result = alphabetImplementation.getPunctuationByDesc(req.getDesc(), req.getLang());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : desc and lang values are not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.punctuation.by_arabic_desc}")
    public Punctuation getPunctuationByArabicDesc(HttpServletResponse response,
                                                  @RequestBody ByArabicDescRequestBody req) throws IOException {
        Punctuation result = new Punctuation();
        try {
            // TODO: Lexicon/arabic_alphabet/getLetterByDescription ||||  lang and desc examples
            if (req.getArabicDesc().isEmpty()) throw new Exception("arabicDesc is empty !!");
            result = alphabetImplementation.getPunctuationByArabicDesc(req.getArabicDesc());
        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : arabicDesc is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.punctuation.by_english_desc}")
    public Punctuation getPunctuationByEnglishDesc(HttpServletResponse response,
            @RequestBody ByEnglishDescRequestBody req) throws IOException {
        Punctuation result = new Punctuation();
        try {
            if (req.getEnglishDesc().isEmpty()) throw new Exception("englishDesc is empty !!");
            result = alphabetImplementation.getPunctuationByEnglishDesc(req.getEnglishDesc());
        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : englishDesc is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.punctuation.by_display}")
    public Punctuation getPunctuationByDisplay(
            HttpServletResponse response,
            @RequestBody ByDisplayRequestBody req) throws IOException {
        Punctuation result = new Punctuation();

        try {
            if (req.getDisplay().isEmpty()) throw new Exception("display is empty !!");

            result = alphabetImplementation.getPunctuationByDisplay(req.getDisplay());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : display parameteris not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }


    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.punctuation.by_code}")
    public Punctuation getPunctuationByCode(HttpServletResponse response,
                                            @RequestBody ByCodeRequestBody req) throws IOException {

        Punctuation result = new Punctuation();

        try {
            if (req.getCode().isEmpty()) throw new Exception("code is empty !!");

            result = alphabetImplementation.getPunctuationByCode(req.getCode(), req.getEncoding());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : code is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.punctuation.by_unicode}")
    public Punctuation getPunctuationByUniCode(
            HttpServletResponse response,
            @RequestBody ByUnicodeRequestBody req) throws IOException {
        Punctuation result = new Punctuation();

        try {
            if (req.getUnicode().isEmpty()) throw new Exception("unicode is empty !!");

            result = alphabetImplementation.getPunctuationByUnicode(req.getUnicode());

        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : unicode is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("${safar.modern_standard_arabic.resources.lexicon.arabic_alphabet.punctuation.display_by_unicode}")
    public Map getDisplayByUnicode(HttpServletResponse response,
            @RequestBody GetDisplayByUnicodeRequestBody req) throws IOException {
        String result = null;
        Map resultMap = new HashMap();
        try {
            if (req.getUnicode().isEmpty()) throw new Exception("unicode is empty !!");
            result = alphabetImplementation.getDisplayByUnicode(req.getUnicode());
        } catch (Exception e) {
            response.setHeader("Hint", "verify that your : unicode is not empty !!");
            response.setHeader("error-message", e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        resultMap.put("unicode", req.getUnicode());
        resultMap.put("display", result);

        return resultMap;
    }

    @Bean
    public IAlphabetService getAlphabet() {
        alphabetImplementation = AlphabetFactory.getAlphabetImplementation();
        return alphabetImplementation;
    }

}

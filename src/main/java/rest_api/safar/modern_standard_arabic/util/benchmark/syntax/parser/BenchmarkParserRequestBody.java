package rest_api.safar.modern_standard_arabic.util.benchmark.syntax.parser;

import safar.modern_standard_arabic.basic.syntax.parser.factory.ParserFactory;
import safar.modern_standard_arabic.basic.syntax.parser.interfaces.IParser;
import safar.modern_standard_arabic.basic.syntax.parser.model.Leaf;
import safar.modern_standard_arabic.basic.syntax.parser.model.SentenceParsingAnalysis;
import safar.modern_standard_arabic.basic.syntax.parser.model.Tree;
import safar.modern_standard_arabic.basic.syntax.parser.util.PrintParserResults;
import safar.modern_standard_arabic.util.benchmark.syntax.MetricUnit;
import safar.modern_standard_arabic.util.benchmark.syntax.OntonotesCorpus;
import safar.modern_standard_arabic.util.benchmark.syntax.SyntacticUnit;

import java.text.DecimalFormat;
import java.util.*;

public class BenchmarkParserRequestBody {

    public static ArrayList<SyntacticUnit> ontonotesCorpusList1;
    private static String impl;
    private static ArrayList<SyntacticUnit> ontonotesCorpusList2;
    private static ArrayList<SyntacticUnit> ontonotesCorpusList3;
    private static int number;
    private static int index;
    private static double stanfordT1 = 0.0D;
    private static double stanfordT2 = 0.0D;
    private static double stanfordT3 = 0.0D;
    private static final ArrayList<String> allTags = new ArrayList();
    private static ArrayList<String> arcs = new ArrayList();


    public static Map compare() {
        System.out.println("Preparing Ontonotes corpus");

        try {
            OntonotesCorpus.prepareOntonotesCorpus();
            ontonotesCorpusList1 = OntonotesCorpus.getOntonotesTrees1();
            ontonotesCorpusList2 = OntonotesCorpus.getOntonotesTrees2();
            ontonotesCorpusList3 = OntonotesCorpus.getOntonotesTrees3();
        } catch (Exception var57) {
        }

        System.out.println("Launching Stanfor parser");


        IParser parser = ParserFactory.getImplementation(impl);
        ArrayList<SyntacticUnit> stanfordResultsList1 = new ArrayList();
        ArrayList<SyntacticUnit> stanfordResultsList2 = new ArrayList();
        ArrayList<SyntacticUnit> stanfordResultsList3 = new ArrayList();
        double startTime = (double) System.currentTimeMillis();
        System.out.println("Executing stanford on the list 1");
        Iterator var8 = ontonotesCorpusList1.iterator();

        SyntacticUnit suCorpus;
        SentenceParsingAnalysis parsed;
        SyntacticUnit su3;
        while (var8.hasNext()) {
            suCorpus = (SyntacticUnit) var8.next();
            parsed = parser.parse(suCorpus.getSentence());
            su3 = new SyntacticUnit();
            su3.setSentence(suCorpus.getSentence());
            su3.setTextTree(PrintParserResults.getTreeAsString(parsed.getListOfParsinganalysis().get(0).getTree()));
            stanfordResultsList1.add(su3);
        }

        double endTime = (double) System.currentTimeMillis();
        stanfordT1 = (endTime - startTime) / 1000.0D;
        System.out.println("Executing stanford on the list 2");
        startTime = (double) System.currentTimeMillis();
        var8 = ontonotesCorpusList2.iterator();

        while (var8.hasNext()) {
            suCorpus = (SyntacticUnit) var8.next();
            parsed = parser.parse(suCorpus.getSentence());
            su3 = new SyntacticUnit();
            su3.setSentence(suCorpus.getSentence());
            su3.setTextTree(PrintParserResults.getTreeAsString(parsed.getListOfParsinganalysis().get(0).getTree()));
            stanfordResultsList2.add(su3);
        }

        endTime = (double) System.currentTimeMillis();
        stanfordT2 = (endTime - startTime) / 1000.0D;
        System.out.println("Executing stanford on the list 3");
        startTime = (double) System.currentTimeMillis();
        var8 = ontonotesCorpusList3.iterator();

        while (var8.hasNext()) {
            suCorpus = (SyntacticUnit) var8.next();
            parsed = parser.parse(suCorpus.getSentence());
            su3 = new SyntacticUnit();
            su3.setSentence(suCorpus.getSentence());
            su3.setTextTree(PrintParserResults.getTreeAsString(parsed.getListOfParsinganalysis().get(0).getTree()));
            stanfordResultsList3.add(su3);
        }

        endTime = (double) System.currentTimeMillis();
        stanfordT3 = (endTime - startTime) / 1000.0D;
        System.out.println("Prepare Standard Tree and arcs for ontonotes");

        int i;
        String textTree;
        Tree t;
        for (i = 0; i < 50; ++i) {
            textTree = ontonotesCorpusList1.get(i).getTextTree();
            arcs = new ArrayList();
            t = textTreeToStandardTree(textTree);
            ontonotesCorpusList1.get(i).setStandardizedTree(t);
            ontonotesCorpusList1.get(i).setArcs(arcs);
            textTree = ontonotesCorpusList2.get(i).getTextTree();
            arcs = new ArrayList();
            t = textTreeToStandardTree(textTree);
            ontonotesCorpusList2.get(i).setStandardizedTree(t);
            ontonotesCorpusList2.get(i).setArcs(arcs);
            textTree = ontonotesCorpusList3.get(i).getTextTree();
            arcs = new ArrayList();
            t = textTreeToStandardTree(textTree);
            ontonotesCorpusList3.get(i).setStandardizedTree(t);
            ontonotesCorpusList3.get(i).setArcs(arcs);
        }

        System.out.println("Prepare Standard Tree and arcs for stanford");

        for (i = 0; i < 50; ++i) {
            textTree = stanfordResultsList1.get(i).getTextTree();
            arcs = new ArrayList();
            t = textTreeToStandardTree(textTree);
            stanfordResultsList1.get(i).setStandardizedTree(t);
            stanfordResultsList1.get(i).setArcs(arcs);
            stanfordResultsList1.get(i).setMetricU(getMetrics(arcs, ontonotesCorpusList1.get(i).getArcs()));
            textTree = stanfordResultsList2.get(i).getTextTree();
            arcs = new ArrayList();
            t = textTreeToStandardTree(textTree);
            stanfordResultsList2.get(i).setStandardizedTree(t);
            stanfordResultsList2.get(i).setArcs(arcs);
            stanfordResultsList2.get(i).setMetricU(getMetrics(arcs, ontonotesCorpusList2.get(i).getArcs()));
            textTree = stanfordResultsList3.get(i).getTextTree();
            arcs = new ArrayList();
            t = textTreeToStandardTree(textTree);
            stanfordResultsList3.get(i).setStandardizedTree(t);
            stanfordResultsList3.get(i).setArcs(arcs);
            stanfordResultsList3.get(i).setMetricU(getMetrics(arcs, ontonotesCorpusList3.get(i).getArcs()));
        }

        System.out.println("Calculating scores");
        double stanfordSigmaAW1 = 0.0D;
        double stanfordSigmaAW2 = 0.0D;
        double stanfordSigmaAW3 = 0.0D;
        double stanfordSigmaBW1 = 0.0D;
        double stanfordSigmaBW2 = 0.0D;
        double stanfordSigmaBW3 = 0.0D;
        double stanfordSigmaC1 = 0.0D;
        double stanfordSigmaC2 = 0.0D;
        double stanfordSigmaC3 = 0.0D;

        for (int j = 0; j < 50; ++j) {
            stanfordSigmaAW1 += stanfordResultsList1.get(j).getMetricU().getSigmaCorrectArcs();
            stanfordSigmaBW1 += stanfordResultsList1.get(j).getMetricU().getSigmaIncorrectArcs();
            stanfordSigmaC1 += stanfordResultsList1.get(j).getMetricU().getSigmaCorrectArcsNotReturned();
            stanfordSigmaAW2 += stanfordResultsList2.get(j).getMetricU().getSigmaCorrectArcs();
            stanfordSigmaBW2 += stanfordResultsList2.get(j).getMetricU().getSigmaIncorrectArcs();
            stanfordSigmaC2 += stanfordResultsList2.get(j).getMetricU().getSigmaCorrectArcsNotReturned();
            stanfordSigmaAW3 += stanfordResultsList3.get(j).getMetricU().getSigmaCorrectArcs();
            stanfordSigmaBW3 += stanfordResultsList3.get(j).getMetricU().getSigmaIncorrectArcs();
            stanfordSigmaC3 += stanfordResultsList3.get(j).getMetricU().getSigmaCorrectArcsNotReturned();
        }

        DecimalFormat df = new DecimalFormat("000.00");
        double stanfordPrecision1 = 0.0D;
        double stanfordPrecision2 = 0.0D;
        double stanfordPrecision3 = 0.0D;
        double stanfordRecall1 = 0.0D;
        double stanfordRecall2 = 0.0D;
        double stanfordRecall3 = 0.0D;
        double stanfordAccuracy1 = 0.0D;
        double stanfordAccuracy2 = 0.0D;
        double stanfordAccuracy3 = 0.0D;
        double stanfordFMeasure1 = 0.0D;
        double stanfordFMeasure2 = 0.0D;
        double stanfordFMeasure3 = 0.0D;
        double stanfordGscore1 = 0.0D;
        double stanfordGscore2 = 0.0D;
        double stanfordGscore3 = 0.0D;
        if (stanfordSigmaAW1 + stanfordSigmaBW1 != 0.0D) {
            stanfordPrecision1 = stanfordSigmaAW1 / (stanfordSigmaAW1 + stanfordSigmaBW1);
            stanfordPrecision1 = Double.parseDouble(df.format(stanfordPrecision1 * 100.0D).replace(",", "."));
        }

        if (stanfordSigmaAW1 + stanfordSigmaC1 != 0.0D) {
            stanfordRecall1 = stanfordSigmaAW1 / (stanfordSigmaAW1 + stanfordSigmaC1);
            stanfordRecall1 = Double.parseDouble(df.format(stanfordRecall1 * 100.0D).replace(",", "."));
        }

        if (stanfordSigmaAW1 + stanfordSigmaBW1 + stanfordSigmaC1 != 0.0D) {
            stanfordAccuracy1 = stanfordSigmaAW1 / (stanfordSigmaAW1 + stanfordSigmaBW1 + stanfordSigmaC1);
            stanfordAccuracy1 = Double.parseDouble(df.format(stanfordAccuracy1 * 100.0D).replace(",", "."));
        }

        if (stanfordPrecision1 + stanfordRecall1 != 0.0D) {
            stanfordFMeasure1 = 2.0D * stanfordPrecision1 * stanfordRecall1 / (stanfordPrecision1 + stanfordRecall1);
            stanfordFMeasure1 = Double.parseDouble(df.format(stanfordFMeasure1).replace(",", "."));
        }

        stanfordGscore1 = stanfordT1 / stanfordAccuracy1;
        if (stanfordSigmaAW2 + stanfordSigmaBW2 != 0.0D) {
            stanfordPrecision2 = stanfordSigmaAW2 / (stanfordSigmaAW2 + stanfordSigmaBW2);
            stanfordPrecision2 = Double.parseDouble(df.format(stanfordPrecision2 * 100.0D).replace(",", "."));
        }

        if (stanfordSigmaAW2 + stanfordSigmaC2 != 0.0D) {
            stanfordRecall2 = stanfordSigmaAW2 / (stanfordSigmaAW2 + stanfordSigmaC2);
            stanfordRecall2 = Double.parseDouble(df.format(stanfordRecall2 * 100.0D).replace(",", "."));
        }

        if (stanfordSigmaAW2 + stanfordSigmaBW2 + stanfordSigmaC2 != 0.0D) {
            stanfordAccuracy2 = stanfordSigmaAW2 / (stanfordSigmaAW2 + stanfordSigmaBW2 + stanfordSigmaC2);
            stanfordAccuracy2 = Double.parseDouble(df.format(stanfordAccuracy2 * 100.0D).replace(",", "."));
        }

        if (stanfordPrecision2 + stanfordRecall2 != 0.0D) {
            stanfordFMeasure2 = 2.0D * stanfordPrecision2 * stanfordRecall2 / (stanfordPrecision2 + stanfordRecall2);
            stanfordFMeasure2 = Double.parseDouble(df.format(stanfordFMeasure2).replace(",", "."));
        }

        stanfordGscore2 = stanfordT2 / stanfordAccuracy2;
        if (stanfordSigmaAW3 + stanfordSigmaBW3 != 0.0D) {
            stanfordPrecision3 = stanfordSigmaAW3 / (stanfordSigmaAW3 + stanfordSigmaBW3);
            stanfordPrecision3 = Double.parseDouble(df.format(stanfordPrecision3 * 100.0D).replace(",", "."));
        }

        if (stanfordSigmaAW3 + stanfordSigmaC3 != 0.0D) {
            stanfordRecall3 = stanfordSigmaAW3 / (stanfordSigmaAW3 + stanfordSigmaC3);
            stanfordRecall3 = Double.parseDouble(df.format(stanfordRecall3 * 100.0D).replace(",", "."));
        }

        if (stanfordSigmaAW3 + stanfordSigmaBW3 + stanfordSigmaC3 != 0.0D) {
            stanfordAccuracy3 = stanfordSigmaAW3 / (stanfordSigmaAW3 + stanfordSigmaBW3 + stanfordSigmaC3);
            stanfordAccuracy3 = Double.parseDouble(df.format(stanfordAccuracy3 * 100.0D).replace(",", "."));
        }

        if (stanfordPrecision3 + stanfordRecall3 != 0.0D) {
            stanfordFMeasure3 = 2.0D * stanfordPrecision3 * stanfordRecall3 / (stanfordPrecision3 + stanfordRecall3);
            stanfordFMeasure3 = Double.parseDouble(df.format(stanfordFMeasure3).replace(",", "."));
        }

        stanfordGscore3 = stanfordT3 / stanfordAccuracy3;
        Map result = new HashMap();
        System.out.println("============RESULTS============");
        result.put("List", 1);
        result.put("Execution time", stanfordT1);
        result.put("Precision: ", stanfordPrecision1);
        result.put("Recall: ", stanfordRecall1);
        result.put("Accuracy: ", stanfordAccuracy1);
        result.put("F-Measure: ", stanfordFMeasure1);
        result.put("G-score: ", stanfordGscore1);

        result.put("List", 2);
        result.put("Execution time: ", stanfordT2);
        result.put("Precision: ", stanfordPrecision2);
        result.put("Recall: ", stanfordRecall2);
        result.put("Accuracy: ", stanfordAccuracy2);
        result.put("F-Measure: ", stanfordFMeasure2);
        result.put("G-score: ", stanfordGscore2);

        result.put("List", 3);
        result.put("Execution time: ", stanfordT3);
        result.put("Precision: ", stanfordPrecision3);
        result.put("Recall: ", stanfordRecall3);
        result.put("Accuracy: ", stanfordAccuracy3);
        result.put("F-Measure: ", stanfordFMeasure3);
        result.put("G-score: ", stanfordGscore3);

        return result;
    }

    public static String getImpl() {
        return impl;
    }

    public static void setImpl(String impl) {
        BenchmarkParserRequestBody.impl = impl;
    }

    public static Tree textTreeToStandardTree(String inputTree) {
        Tree myFinalTree = new Tree();
        myFinalTree.setIndex(index);
        ++index;
        if (!inputTree.trim().isEmpty()) {
            String[] treeParts = inputTree.split("\\(");
            myFinalTree.setCategory(returnStandardTag(treeParts[1].trim()));
            arcs.add(returnStandardTag(treeParts[1].trim()));
            String newInputTree = inputTree.replaceFirst("\\(" + treeParts[1], "").trim();
            String exprs = "";

            for (int i = 0; i < newInputTree.length() - 1; ++i) {
                exprs = exprs + newInputTree.charAt(i);
                if (!exprs.trim().isEmpty()) {
                    if (isBalanced1(exprs)) {
                        if (isLeaf(exprs)) {
                            Leaf leaf = new Leaf();
                            leaf.setIndex(index);
                            leaf.setNumber(number);
                            ++index;
                            ++number;
                            String ss = exprs.replaceAll("\\(", "").replaceAll("\\)", "").trim();
                            String[] subExpr2 = ss.split("\\s+");
                            leaf.setCategory(returnStandardTag(subExpr2[0]));
                            arcs.add(returnStandardTag(subExpr2[0]));
                            leaf.setValue(subExpr2[1]);
                            myFinalTree.addToAllParsedElement(leaf);
                            exprs = "";
                        } else {
                            myFinalTree.addToAllParsedElement(textTreeToStandardTree(exprs));
                            exprs = "";
                        }
                    }
                } else {
                    exprs = "";
                }
            }
        }

        return myFinalTree;
    }

    public static boolean isBalanced1(String inputTextTree) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < inputTextTree.length(); ++i) {
            char c = inputTextTree.charAt(i);
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }

                    if (stack.peek() == c) {
                        stack.pop();
                    }
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
            }
        }

        return stack.isEmpty();
    }

    public static Boolean isLeaf(String textTree) {
        String newexpr2 = textTree.trim();
        int nchar1 = textTree.length();
        int nchar2 = textTree.replaceAll("\\(", "").replaceAll("\\)", "").length();
        return nchar1 - nchar2 == 2 && newexpr2.charAt(0) == '(' && newexpr2.charAt(newexpr2.length() - 1) == ')';
    }

    public static String returnStandardTag(String inputTag) {
        inputTag = inputTag.trim();
        if (!inputTag.startsWith("ADJ") && !inputTag.startsWith("DET+ADJ") && !inputTag.startsWith("DTJJ") && !inputTag.startsWith("DTADJ") && !inputTag.startsWith("JJR")) {
            if (!inputTag.startsWith("ADV") && !inputTag.startsWith("DTRB")) {
                if (!inputTag.startsWith("CONJ") && !inputTag.startsWith("SUB_CONJ")) {
                    if (inputTag.startsWith("DEM_")) {
                        return "DT";
                    } else if (!inputTag.startsWith("DET+NOUN") && !inputTag.startsWith("NOUN") && !inputTag.startsWith("DTNN")) {
                        if (inputTag.startsWith("NP")) {
                            return "NP";
                        } else if (inputTag.startsWith("IV")) {
                            return "VBP";
                        } else if (inputTag.startsWith("PP")) {
                            return "PP";
                        } else if (!inputTag.startsWith("PV") && !inputTag.startsWith("PRON")) {
                            if (inputTag.startsWith("POSS")) {
                                return "PRP$";
                            } else if (inputTag.startsWith("PREP")) {
                                return "IN";
                            } else if (inputTag.contains("SBAR")) {
                                return "SBAR";
                            } else if (!inputTag.contains("COLON") && !inputTag.startsWith("COMMA")) {
                                return inputTag.startsWith("NEG") ? "RP" : inputTag;
                            } else {
                                return "PUNC";
                            }
                        } else {
                            return "PRP";
                        }
                    } else {
                        return "NN";
                    }
                } else {
                    return "CC";
                }
            } else {
                return "RB";
            }
        } else {
            return "JJ";
        }
    }

    public static MetricUnit getMetrics(ArrayList<String> parserList, ArrayList<String> corpusList) {
        int totalCorrect = 0;
        int totalIncorrect = 0;
        int totalCorrectNotReturned = 0;
        Iterator var5 = parserList.iterator();

        String s;
        while (var5.hasNext()) {
            s = (String) var5.next();
            if (corpusList.contains(s)) {
                ++totalCorrect;
            } else {
                ++totalIncorrect;
            }
        }

        var5 = corpusList.iterator();

        while (var5.hasNext()) {
            s = (String) var5.next();
            if (!parserList.contains(s)) {
                ++totalCorrectNotReturned;
            }
        }

        MetricUnit mu = new MetricUnit();
        mu.setSigmaCorrectArcs(totalCorrect);
        mu.setSigmaIncorrectArcs(totalIncorrect);
        mu.setSigmaCorrectArcsNotReturned(totalCorrectNotReturned);
        return mu;
    }


}

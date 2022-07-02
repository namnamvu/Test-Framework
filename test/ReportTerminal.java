package test;

import java.util.List;

public class ReportTerminal implements TestReporter{

    @Override
    public void generateReport(List<TestResult> passed, List<TestResult> failed, List<TestResult> exception) {
        System.out.println("-- PASSED TESTS --");
        for (TestResult t : passed){
            System.out.println(t.toString());
        }

        System.out.println("\n\n-- FAILED TESTS --");
        for (TestResult t : failed){
            System.out.println(t.toString());
        }

        System.out.println("\n\n-- EXCEPTION TESTS --");
        for (TestResult t : exception){
            System.out.println(t.toString());
        }
    }
    
}

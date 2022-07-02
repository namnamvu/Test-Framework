package test;

import java.util.ArrayList;
import java.util.List;

public class TestControllerImpl implements TestController{

    private ArrayList<Test> testList = new ArrayList<Test>();
    private ArrayList<Double> priorities = new ArrayList<Double>();
    private TestResult[] result;
    private String[] specifiedTests;
    public String fileName = "";

    public TestControllerImpl(String[] arr){
        if (arr.length >= 2 && "-h".equals(arr[0]) && arr[1].contains(".html")){
            if (arr.length > 2) {
                specifiedTests = new String[arr.length-2];
                for (int i = 2; i < arr.length; i++){
                    specifiedTests[i-2] = arr[i];
                    System.out.println(arr[i]);
                }
            }
            fileName = arr[1];  
            System.out.println(fileName);
        }else if (arr.length > 0){
            specifiedTests = arr;
        }
    }

    @Override
    public void addTest(Test test, double rank) {
        // Add to the end, keep tree full and complete
		//The last thing in the list, is the last thing in the bottom layer
        // The test run from smaller rank to larger rank
		testList.add(test);
		priorities.add(rank);

		// Maintain heap property - parent have to be smaller than child
		percolateUp(testList.size() - 1);
    }

    private void percolateUp(int idx) {
        int parent = (idx-1)/2; //if idx == 0, parent == 0, so priorities are the same!
        Double parentPriority = priorities.get(parent);
        Double currentPriority = priorities.get(idx);
        // Small rank will be carried out first (parent rank must be smaller than child rank)
        if(currentPriority < parentPriority)
        {
            //Swap items
            Test parentTest = testList.get(parent);
            Test currentTest = testList.get(idx);
            testList.set(parent, currentTest);
            testList.set(idx, parentTest);

            //Swap priorities
            priorities.set(parent, currentPriority);
            priorities.set(idx, parentPriority);

            percolateUp(parent);
        }
    }

    private void processTestResult(int i, Test currentTest){
        try {
            result[i] = currentTest.runTest();
        } catch (Exception e) {
            result[i] = TestResult.createExceptionResult("null");
        }
        result[i].setTest(currentTest);
    }
    @Override
    public void runTests() {
        result = new TestResult[testList.size()];
        int i = 0;
        for (Test currentTest : testList){
            if (specifiedTests != null){
                for (String v : specifiedTests){
                    if (currentTest.getTestId().equals(v)){
                        processTestResult(i, currentTest);
                        i++;
                    }
                }
            }else{
                processTestResult(i, currentTest);
                i++;
            }
        }
        createReport();
    }

    @Override
    public void createReport() {
        List<TestResult> passed = new ArrayList<TestResult>();
        List<TestResult> failed = new ArrayList<TestResult>();
        List<TestResult> exception = new ArrayList<TestResult>();

        for (TestResult t : result){
            if (t != null){
                if (t.isPassed()){
                    passed.add(t);
                }else if (t.isFailed()){
                    failed.add(t);
                }else if (t.isException()){
                    exception.add(t);
                }
            }
        }
        if (fileName != ""){
            ReportHTML report = new ReportHTML(fileName);
            report.generateReport(passed, failed, exception);
        }else{
            ReportTerminal report = new ReportTerminal();
            report.generateReport(passed, failed, exception);
        }
    }  
}

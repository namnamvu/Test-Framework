package nvu1HW7;
import java.util.NoSuchElementException;

import test.Test;
import test.TestController;
import test.TestControllerImpl;
import test.TestResult;

public class TestHW7 {
    
    public static void main(String[] args) {
        
        TestController tc = new TestControllerImpl(args);
        ObjectHashMap table = new ObjectHashMap(0.8);
        table.put("Jake", 19);
        table.put("Anh", 5);

        // Test whether key and value are added to the table by put
        Test testAddNewKey = new Test("AddNewKey") {
            @Override
            public TestResult runTest() {
                table.put("Nam", 17);
                if ((Integer)table.find("Nam") == 17) {
                    return TestResult.createPassedResult("Successfully add to the table");
                } else {
                    return TestResult.createFailedResult("Fail to add key 'Nam' and value '17'");
                }
            }
        };
        tc.addTest(testAddNewKey, 1.0);

        // Check to see if put method updates the value
        Test testUpdate = new Test("UpdateValue") {
            @Override
            public TestResult runTest() {
                table.put("Anh", 20);
                if ((Integer)table.find("Anh") == 20) {
                    return TestResult.createPassedResult("Successfully update the value in the table");
                } else {
                    return TestResult.createFailedResult("Fail to update value to key 'Anh'");
                }
            }
        };
        tc.addTest(testUpdate, 7.0);

        // Test whether key and value are added to the table by put
        Test testSize = new Test("sizeMethod") {
            @Override
            public TestResult runTest() {
                if (table.size() == 3) {
                    return TestResult.createPassedResult();
                } else {
                    return TestResult.createFailedResult("Return the wrong size of table");
                }
            }
        };
        tc.addTest(testSize, 2.0);

        Test testIsEmpty = new Test("isEmpty") {
            @Override
            public TestResult runTest() {
                if (table.isEmpty() != true) {
                    return TestResult.createPassedResult("Table is not empty");
                } else {
                    return TestResult.createFailedResult("Table should not be empty");
                }
            }
        };
        tc.addTest(testIsEmpty, 9.0);
        
        // Check to see if method successfully throw an exception
        Test testFindNullKey = new Test("findNullKey") {
            @Override
            public TestResult runTest() {
                try{
                    table.find("Ice");
                }catch (NoSuchElementException e){
                    return TestResult.createPassedResult("Exception has been caught");
                }
                return TestResult.createFailedResult("FindKey should have thrown an exception");
            }
        };
        tc.addTest(testFindNullKey, 3.0);
        
        // Check if method find the key
        Test testFindExistKey = new Test("FindExistKey") {
            @Override
            public TestResult runTest() {
                if ((Integer)table.find("Jake") == 19) {
                    return TestResult.createPassedResult("Key is found");
                } else {
                    return TestResult.createFailedResult("Key is not found");
                }
            }
        };
        tc.addTest(testFindExistKey, 4.0);
        
        // Test to see whether containKey detect the right key
        Test testContainKey = new Test("containKeys") {
            @Override
            public TestResult runTest() {
                if (table.containsKey("Nam") == true) {
                    return TestResult.createPassedResult("Key is found in the table");
                } else {
                    return TestResult.createFailedResult("Should have found the key");
                }
            }
        };
        tc.addTest(testContainKey, 5.0);

        // Test to see whether containKey detect the key that is not in the hashMap
        Test testNotContainKey = new Test("NotContainKey") {
            @Override
            public TestResult runTest() {
                if (table.containsKey("Chi") == false) { // variable successfully changed
                    return TestResult.createPassedResult();
                } else {
                    return TestResult.createFailedResult("Should not find the key");
                }
            }
        };
        tc.addTest(testNotContainKey, 10.0);

        // Test to see if getEntries work correctly (should have entries)
        Test testGetEntries = new Test("GetEntries") {
            @Override
            public TestResult runTest() {
                boolean test = true;
                Entry[] curEntry = table.getEntries();
                for (Entry e : curEntry){
                    if (!e.equals(table.findEntry(e.key))){
                        test = false;
                    }
                }
                if (test) { 
                    return TestResult.createPassedResult("Correctly Detect Multiple Entries");
                } else {
                    return TestResult.createFailedResult("Should have found multiple Entries");
                }
            }
        };
        tc.addTest(testGetEntries, 15.0);

        // Find an entry that does not exist
        Test testNullEntry = new Test("findNullEntry") {
            @Override
            public TestResult runTest() {
                if (table.findEntry("Loki") == null) { 
                    return TestResult.createPassedResult("Correctly detect Null Entry");
                } else {
                    return TestResult.createFailedResult("Should have found a null entry");
                }  
            }
        };
        tc.addTest(testNullEntry, 15.0);  // same rank as previous test.  arbitrary which runs first

        // Find an entry that does not exist
        Test testFindEntry = new Test("findEntry") {
            @Override
            public TestResult runTest() {
                if ((table.findEntry("Anh")).value.equals(table.find("Anh"))) { 
                    return TestResult.createPassedResult("Correctly detect existing Entry");
                } else {
                    return TestResult.createFailedResult("Does not detect the existing entry");
                }  
            }
        };
        tc.addTest(testFindEntry, 17.0);  
        // run tests
        tc.runTests();
    }
}
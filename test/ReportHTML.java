package test;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ReportHTML implements TestReporter{
    private String fileName;

    public ReportHTML(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void generateReport(List<TestResult> passed, List<TestResult> failed, List<TestResult> exception) {
        PrintStream out;
        try {
            out = new PrintStream(new File(fileName));
            printToFile(out, passed, failed, exception);
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        }
    } 
    
    private void printToFile(PrintStream out, List<TestResult> passed, List<TestResult> failed, List<TestResult> exception){
        out.println("<!DOCTYPE html><html>\n <head>\n<title>Webpage for Test Results</title>\n</head>\n");
        out.println("<body>\n<h1 style = 'font-size: 45px;'>Test Results</h1>\n<h2 style='font-size:25px;'>PASSED Tests</h2>");
        out.println("<table>\n<tr bgcolor = '#0F4C81' height = 50>\n<th style='color: white; font-size:19px;' width = 200 >Results</th>");
        out.println("<th style='color: white; font-size:19px;' width = 200 >Test ID</th>");
        out.println("<th style='color: white; font-size:19px;' width = 700 >Message</th>\n</tr>");

        for (TestResult t : passed){
            out.println(t.toHtml());
        }
        out.println("</table>\n\n<h2 style='font-size:25px;'>FAILED Tests</h2>\n</table>\n");
        out.println("<table>\n<tr bgcolor = '#0F4C81' height = 50>\n<th style='color: white; font-size:19px;' width = 200 >Results</th>");
        out.println("<th style='color: white; font-size:19px;' width = 200 >Test ID</th>");
        out.println("<th style='color: white; font-size:19px;' width = 700 >Message</th>\n</tr>");

        for (TestResult t : failed){
            out.println(t.toHtml());
        }
        out.println("</table>\n\n<h2 style='font-size:25px;'>EXCEPTION Tests</h2>\n</table>\n</body>\n</html>");
        out.println("<table>\n<tr bgcolor = '#0F4C81' height = 50>\n<th style='color: white; font-size:19px;' width = 200 >Results</th>");
        out.println("<th style='color: white; font-size:19px;' width = 200 >Test ID</th>");
        out.println("<th style='color: white; font-size:19px;' width = 700 >Message</th>\n</tr>");

        for (TestResult t : exception){
            out.println(t.toHtml());
        }
        out.println("</body>\n</html>");

      
    //   <body>\n
    //     <h1>Test Results</h1>\n<h2 style='font-size:25px;''>PASSED Tests</h2>\n
    //     </body>\n</html>");
        
        // for (TestResult t : passed){
        //     out.println(t.toString());
        // }

        // out.println("\n\n-- FAILED TESTS --");
        // for (TestResult t : failed){
        //     out.println(t.toString());
        // }

        // out.println("\n\n-- EXCEPTION TESTS --");
        // for (TestResult t : exception){
        //     out.println(t.toString());
        // }
    }
}

package test;

public class PassedTestResult extends TestResult{

    public PassedTestResult(String msg) {
        super(PASSED, msg);
    }

    public PassedTestResult(){
        super(PASSED);
    }


    @Override
    public String toHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr width = 200 height = 50 align='center'>\n");
        sb.append("<td bgcolor='#00FF00' style = 'border-radius: 10px;'> " + this.type + "</td>\n");
        sb.append("<td bgcolor = 'lightgrey'>" + this.getTestId() + "</td>");
        if (this.getErrorMessage() != null){
            sb.append("<td bgcolor = 'lightgrey'>" + this.getErrorMessage() + "</td>\n</tr>");
        }else{
            sb.append("<td bgcolor = 'lightgrey'></td>\n</tr>");
        }
        return sb.toString();
    }
    
}

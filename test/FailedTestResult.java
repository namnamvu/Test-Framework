package test;

public class FailedTestResult extends TestResult{

    public FailedTestResult(String msg) {
        super(FAILED, msg);
    }

    public FailedTestResult(){
        super(FAILED);
    }

    @Override
    public String toHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr width = 200 height = 50 align='center'>\n");
        sb.append("<td bgcolor='#DD4124' style = 'border-radius: 10px;'>" + this.type + "</td>\n");
        sb.append("<td bgcolor = 'lightgrey'>" + this.getTestId() + "</td>\n");
        if (this.getErrorMessage() != null){
            sb.append("<td bgcolor = 'lightgrey'>" + this.getErrorMessage() + "</td>\n</tr>");
        }else{
            sb.append("<td bgcolor = 'lightgrey'></td>\n</tr>");
        }
        return sb.toString();
    }
    
}

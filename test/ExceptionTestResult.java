package test;

public class ExceptionTestResult extends TestResult{

    public ExceptionTestResult(){
        super(EXCEPTION);
    }

    public ExceptionTestResult(String msg){
        super(EXCEPTION, msg);
    }
    
    @Override
    public String toHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr width = 200 height = 50 align='center'>\n");
        sb.append("<td bgcolor='yellow' style = 'border-radius: 10px;'>" + this.type + "</td>\n");
        sb.append("<td bgcolor = 'lightgrey'>" + this.getTestId() + "</td>\n");
        if (this.getErrorMessage() != null){
            sb.append("<td bgcolor = 'lightgrey'>" + this.getErrorMessage() + "</td>\n</tr>");
        }else{
            sb.append("<td bgcolor = 'lightgrey'></td>\n</tr>");
        }
        return sb.toString();
    }
    
}

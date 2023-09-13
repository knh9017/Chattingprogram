package Login;
import java.util.regex.Pattern;
 
// Java에서 암호를 확인하는 Java 프로그램
public class cpd
{
    // 최소 1자리, 최소 1자리의 8-16자 비밀번호
    // 소문자, 하나 이상의 대문자, 하나 이상의
    // 공백이 없는 특수문자
    private static final String PASSWORD_REGEX =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
 
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
 
        public boolean ckpwd(String pwd){
        // 비밀번호 확인
        if (PASSWORD_PATTERN.matcher(pwd).matches()){
            return true;
        } 
        else
        {
            return false;
        }
    }
    
}
package Login;
import java.util.regex.Pattern;
 
// Java���� ��ȣ�� Ȯ���ϴ� Java ���α׷�
public class cpd
{
    // �ּ� 1�ڸ�, �ּ� 1�ڸ��� 8-16�� ��й�ȣ
    // �ҹ���, �ϳ� �̻��� �빮��, �ϳ� �̻���
    // ������ ���� Ư������
    private static final String PASSWORD_REGEX =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
 
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
 
        public boolean ckpwd(String pwd){
        // ��й�ȣ Ȯ��
        if (PASSWORD_PATTERN.matcher(pwd).matches()){
            return true;
        } 
        else
        {
            return false;
        }
    }
    
}
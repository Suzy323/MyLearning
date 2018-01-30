

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 // Ҫ��֤���ַ���
	    String str = "service@xsoftlab.net";
	    // ������֤����
	    String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
	    // ����������ʽ
	    Pattern pattern = Pattern.compile(regEx);
	    // ���Դ�Сд��д��
	    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(str);
	    // �ַ����Ƿ���������ʽ��ƥ��
	    boolean rs = matcher.matches();
	    System.out.println(rs);
	}
}
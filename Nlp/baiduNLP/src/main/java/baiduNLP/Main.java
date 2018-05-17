package baiduNLP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;

public class Main {
	//����APPID/AK/SK
    public static final String APP_ID = "11240602";
    public static final String API_KEY = "KKZ7cDj6fwp9TA82wLYZF10y";
    public static final String SECRET_KEY = "SXgxSklIgaHiYxeTnG9YETVYNT1Ib7Ki";

    public static void main(String[] args) {
        // ��ʼ��һ��AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // ��ѡ�������������Ӳ���
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // ��ѡ�����ô����������ַ, http��socket��ѡһ�����߾�������
        // client.setHttpProxy("proxy_host", proxy_port);  // ����http����
        // client.setSocketProxy("proxy_host", proxy_port);  // ����socket����

        // ��ѡ������log4j��־�����ʽ���������ã���ʹ��Ĭ������
        // Ҳ����ֱ��ͨ��jvm�����������ô˻�������
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // ���ýӿ�
        String text = getText();
        JSONObject res = client.lexer(text, null);
        System.out.println(res.toString(2));
        
    } 
    
	private static String getText() {
		String encoding = "UTF-8";
		File file = new File("D://textfile.txt"); 
		Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }
	}
}

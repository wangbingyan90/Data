import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

public class Main {

	public static void main(String[] args) {
		
		StanfordCoreNLP pipline = getStanfordCoreNLP();
		
		Annotation annotation = new Annotation(getText());
//		Annotation annotation = new Annotation("�����罻����������գ�����Ϊֹ����3.75�������ġ���������ͨѶ��8��Ա����31�������˵��û����һ�������ܱ������������ø���ʱ������ʡ��ˡ�����ʱ��13����23�����ң���������ʱ��13������11�����ң��������շ������ģ�");
		pipline.annotate(annotation);
		pipline.prettyPrint(annotation, System.out);
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



	static StanfordCoreNLP getStanfordCoreNLP() {
		
		StanfordCoreNLP pipline;
		
//		pipline = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties");
		
//		Properties properties = new Properties();
//		annotators: tokenize(�ִ�)��ssplit(�Ͼ�)�� pos(���Ա�ע)��lemma(���λ�ԭ)�� ner(����ʵ��ʶ��)��parse(�﷨����)
//		properties.setProperty("annotators", "tokenize");
//		pipline = new StanfordCoreNLP(properties);
		
		pipline = new StanfordCoreNLP(PropertiesUtils.asProperties(
                "annotators", "tokenize",
                "ssplit.isOneSentence", "true",
                "tokenize.language", "zh",
                "segment.model", "edu/stanford/nlp/models/segmenter/chinese/ctb.gz",
                "segment.sighanCorporaDict", "edu/stanford/nlp/models/segmenter/chinese",
                "segment.serDictionary", "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz",
                "segment.sighanPostProcessing", "true"
        ));
		
		return pipline;
	}

}

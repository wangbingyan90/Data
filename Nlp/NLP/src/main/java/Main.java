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
//		Annotation annotation = new Annotation("爱玩社交网络的特朗普，迄今为止发过3.75万条推文。对于中兴通讯的8万员工和31万股民来说，没有哪一条推特能比周日这条来得更及时更“解渴”了。北京时间13日晚23点左右（美国当地时间13日上午11点左右），特朗普发布推文：");
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
//		annotators: tokenize(分词)、ssplit(断句)、 pos(词性标注)、lemma(词形还原)、 ner(命名实体识别)、parse(语法解析)
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

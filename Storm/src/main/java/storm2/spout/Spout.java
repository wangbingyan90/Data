package storm2.spout;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

public class Spout extends BaseRichSpout {
	
    private SpoutOutputCollector collector;
    private static String[] sentences =new String[50];
    private int index=0;
    
 
    public Spout(String[] data) {
    	for(int i=0;i<data.length;i++)
    	{
    		sentences[i]=data[i];
    	}
	}
    
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }


    public void nextTuple() {

        this.collector.emit(new Values(sentences[index]));
        index++;
        if (index>=sentences.length) {
            index=0;
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("sentence"));//告诉组件发出数据流包含sentence字段
        
    }
    
    public  String[] readTxtFile(String filePath) {
        String []result=null;
        StringBuilder content = new StringBuilder("");  
        try {  
            String encoding = "UTF-8";
            int i=0;
            File file = new File(filePath);  
            System.out.println();
            
            if (file.isFile() && file.exists()) {  
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);  
                BufferedReader bufferedReader = new BufferedReader(read);  
                String lineTxt = null;  
                while ((lineTxt = bufferedReader.readLine()) != null) {  
                     result[i++] =lineTxt;  
                    System.out.println(lineTxt);  
                    content.append(result[0] + "----" + result[1]);  
                    content.append("\r\n");// txt换行  
                }  
                read.close();  
            } else {  
                System.out.println("找不到指定的文件");  
            }  
        } catch (Exception e) {  
            System.out.println("读取文件内容出错");  
            e.printStackTrace();  
        }  
        return result;  
    } 
  
}


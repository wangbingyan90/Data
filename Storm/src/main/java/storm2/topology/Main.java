package storm2.topology;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

import storm2.bolt.ReportBolt;
import storm2.bolt.SplitSentenceBolt;
import storm2.bolt.WordCountBolt;
import storm2.spout.Spout;

public class Main 
{
    
    public static void main( String[] args ) //throws Exception
    {   

        TopologyBuilder builder = new TopologyBuilder();
        
        builder.setSpout("Spout", new Spout(txt2String(new File("D:\\sentences.txt"))));
        
        builder.setBolt("SplitSentenceBolt", new SplitSentenceBolt()).shuffleGrouping("Spout");
        
        builder.setBolt("WordCountBolt", new WordCountBolt()).fieldsGrouping("SplitSentenceBolt", new Fields("word"));

        builder.setBolt("ReportBolt", new ReportBolt()).globalGrouping("SplitSentenceBolt");
        
        LocalCluster cluster = new LocalCluster();
        
        cluster.submitTopology("finish", new Config(), builder.createTopology());
        
        Utils.sleep(10000);
        cluster.killTopology("finish");        
        cluster.shutdown();
        
    }
    
    public static String[] txt2String(File file){
        StringBuilder result = new StringBuilder();
        int i=0;
        String []res=new String[50];
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){
            	res[i++]=s;
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
}



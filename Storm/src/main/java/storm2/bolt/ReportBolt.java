package storm2.bolt;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

public class ReportBolt extends BaseRichBolt {
    
    private HashMap<String, Long> counts = null;//保存单词和对应的计数

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.counts = new HashMap<String, Long>();
    }

    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Long count = input.getLongByField("count");
        this.counts.put(word, count);
        //实时输出
        System.out.println("结果:"+this.counts);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
    
    public void cleanup(){
        System.out.println("---------- FINAL COUNTS -----------");
        
        ArrayList<String> keys = new ArrayList<String>();
        keys.addAll(this.counts.keySet());
        Collections.sort(keys);
        for(String key : keys){
            System.out.println(key + " : " + this.counts.get(key));
        }
        System.out.println("----------------------------");
    }

}


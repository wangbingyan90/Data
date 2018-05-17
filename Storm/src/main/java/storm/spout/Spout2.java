package storm.spout;

import java.util.Date;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class Spout2 extends BaseRichSpout{

	private SpoutOutputCollector collector;
	
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	public void nextTuple() {
		String hello = "Hello world";
		String time  = new Date().toString();
		
		System.out.println("spout2: "+hello +", "+time);
		collector.emit(new Values(hello,time));
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("words","time"));
		
	}
	

}

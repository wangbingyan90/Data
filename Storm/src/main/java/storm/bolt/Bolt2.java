package storm.bolt;

import java.util.Date;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class Bolt2 extends BaseRichBolt {
	private OutputCollector collector;

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	public void execute(Tuple input) {
		String hello = "Hello world";
		String time  = new Date().toString();
		collector.emit(new Values(hello,time));
		
		System.out.println("bolt2: "+input.getString(0));

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("words","time"));

	}

}

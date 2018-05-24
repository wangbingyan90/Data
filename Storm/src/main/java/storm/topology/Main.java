package storm.topology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import storm.bolt.Bolt1;
import storm.bolt.Bolt2;
import storm.bolt.Bolt3;
import storm.spout.Spout1;
import storm.spout.Spout2;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TopologyBuilder builder = new TopologyBuilder();
		
		builder.setSpout("Spout1", new Spout1());
		builder.setSpout("Spout2", new Spout2());
		
//		builder.setBolt("Bolt1", new Bolt1()).shuffleGrouping("Spout1").shuffleGrouping("Spout2");
//		builder.setBolt("Bolt2", new Bolt2()).shuffleGrouping("Spout2");
//		
//		builder.setBolt("Bolt3", new Bolt3()).shuffleGrouping("Bolt1");
//		builder.setBolt("Bolt4", new Bolt3()).shuffleGrouping("Bolt2");
//		builder.setBolt("Bolt5", new Bolt3()).shuffleGrouping("Bolt2");	
//		
//		builder.setBolt("Bolt6", new Bolt3()).shuffleGrouping("Bolt3").shuffleGrouping("Bolt4").shuffleGrouping("Bolt5");
		
		
		LocalCluster  cluster = new LocalCluster();
		
		Config conf = new Config();
		cluster.submitTopology("finish", conf, builder.createTopology());

	}
	

}

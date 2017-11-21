import java.io.*;
import java.util.StringTokenizer;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class word{
	public static class TokenizerMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
		private final static IntWritable one=new IntWritable(1);
		private Text word=new Text();
		public void map(LongWritable key,Text value,org.apache.hadoop.mapreduce.Mapper.Context context) throws IOException,InterruptedException
	{
	StringTokenizer itr=new StringTokenizer(valur.toString());		
	while (itr.hasMoreTokens())
	{
		String myword=itr.nextToken().toLowerCase();
		word.set(myword);
		context.write(word, one);
		
	}
	}
	}
public static class IntSumReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
	private IntWritable result =new IntWritable();
	public void reduce(Text key,Iterable<IntWritable> values,org.apache.hadoop.mapreduce.Mapper.Context context) throws IOException, InterruptedException {
		int sum=0;
		for(IntWritable val:values){
			sum=sum+val.get();
		}
	result.set(sum);
	context.write(key, result);
	}
}
public static void main(String[] args) throws Exception {
	Configuration conf = new Configuration();
	Job job=job.getInstance(conf, "word count");
	job.setJarByClass(word.class);
	job.setMapperClass(TokenizerMapper.class);
	job.setCombinerClass(IntSumReducer.class);
	job.setReducerClass(IntSumReader.class);
	
}





}
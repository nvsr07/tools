package com.sas.mkt.kafka.clients;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import com.sas.mkt.kafka.base.KafkaConfigServer;

public class MessageConsumer {
	
	private static Properties props;

	public static void main(String[] args) {
	     props = new KafkaConfigServer().getConsumerProperties();

	     props.put("group.id", "baldur1");
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("session.timeout.ms", "30000");

		MessageConsumer mc = new MessageConsumer();
		mc.getMessages();
//		mc.getMessagesFromPartition();

	}

	private void getMessagesFromPartition() {
		String topic = "page-views";
		Properties props = new Properties();
		props.put("bootstrap.servers", "kafka0.cidev.sas.us:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "baldurbyui");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		// Subscribe to a particular topic AND partition
		// use assign vs subscribe (you can not mix the two)
		TopicPartition tp = new TopicPartition(topic, 0);
		consumer.assign(Arrays.asList(tp));
		consumer.seek(tp, 0); // this puts you back at the start of the message queue each time
		System.out.println("assigned consumer to partition: " + tp.toString());
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
				System.out.println("");
			}
		}
	}

	private void getMessages() {
		String topic = "four-prt";
		Properties props = new Properties();
		props.put("bootstrap.servers", "kafka0.cidev.sas.us:9092");
		props.put("group.id", "finnwiz");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		// subscribe to the topic - subscribe will let Kafka dynamically assign partitions to the consumers in the group
		consumer.subscribe(Arrays.asList(topic));
		System.out.println("subscribed to topics...");

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
				System.out.println("");
			}
		}

	}

}

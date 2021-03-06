package com.sas.mkt.kafka.performance.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class ProducerPerformanceRun {
	
	public ProducerPerformanceRun() {}
	
	public ProducerPerformanceRun(String runName, Timestamp startTime, Long numberRecordsAttempted) {
		super();
		this.runName = runName + System.currentTimeMillis();
		this.startTime = startTime;
		this.numberRecordsAttempted = numberRecordsAttempted;
	}

	@Id
	private String runName;
	private Long messagesPerSec;
	private Long kbytesPerSec;
	private String status;
	private String instanceType;
	private Timestamp startTime;
	private Long runTimeMilliSec;
	private Long numberRecordsAttempted;
	private Long numberRecordsReceived;
	private Double percentMessagesReceived;
	private Long averageRecordSizeBytes;
	private Long numberKeys;
	private String topicName;
	private Long numberPartitions;
	private Long replicationFactor;
	private Long numberOfProducers;
	
	// server properties
	private String javaVmVendor;
	private String javaVersion;
	private String osName;
	private String osVersion;
	private String osArch;
	private int numberProcessors;
	
	// control properties (not persisted)
	@JsonInclude
	@Transient
	private boolean useAllCores;
	
	public void setServerProperties(ServerProperties sp) {
		javaVmVendor = sp.getJavaVmVendor();
		javaVersion = sp.getJavaVersion();
		osName = sp.getOsName();
		osVersion = sp.getOsVersion();
		osArch = sp.getOsArch();
		numberProcessors = sp.getNumberProcessors();
	}
			
	@OneToMany(cascade=CascadeType.ALL, mappedBy="performanceRun", fetch=FetchType.EAGER)
	private Set<ProducerProperty> producerProperties;

	public String getRunName() {
		return runName;
	}

	public void setRunName(String runName) {
		this.runName = runName;
	}

	public Long getMessagesPerSec() {
		return messagesPerSec;
	}

	public void setMessagesPerSec(Long messagesPerSec) {
		this.messagesPerSec = messagesPerSec;
	}

	public Long getKbytesPerSec() {
		return kbytesPerSec;
	}

	public void setKbytesPerSec(Long kbytesPerSec) {
		this.kbytesPerSec = kbytesPerSec;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Long getRunTimeMilliSec() {
		return runTimeMilliSec;
	}

	public void setRunTimeMilliSec(Long runTimeMilliSec) {
		this.runTimeMilliSec = runTimeMilliSec;
	}

	public Long getNumberRecordsAttempted() {
		return numberRecordsAttempted;
	}

	public void setNumberRecordsAttempted(Long numberRecordsAttempted) {
		this.numberRecordsAttempted = numberRecordsAttempted;
	}

	public Long getNumberRecordsReceived() {
		return numberRecordsReceived;
	}

	public void setNumberRecordsReceived(Long numberRecordsReceived) {
		this.numberRecordsReceived = numberRecordsReceived;
	}

	public Double getPercentMessagesReceived() {
		return percentMessagesReceived;
	}

	public void setPercentMessagesReceived(Double percentMessagesReceived) {
		this.percentMessagesReceived = percentMessagesReceived;
	}

	public Long getAverageRecordSizeBytes() {
		return averageRecordSizeBytes;
	}

	public void setAverageRecordSizeBytes(Long averageRecordSizeBytes) {
		this.averageRecordSizeBytes = averageRecordSizeBytes;
	}

	public Long getNumberKeys() {
		return numberKeys;
	}

	public void setNumberKeys(Long numberKeys) {
		this.numberKeys = numberKeys;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Long getNumberPartitions() {
		return numberPartitions;
	}

	public void setNumberPartitions(Long numberPartitions) {
		this.numberPartitions = numberPartitions;
	}

	public Long getReplicationFactor() {
		return replicationFactor;
	}

	public void setReplicationFactor(Long replicationFactor) {
		this.replicationFactor = replicationFactor;
	}

	public Long getNumberOfProducers() {
		return numberOfProducers;
	}

	public void setNumberOfProducers(Long numberOfProducers) {
		this.numberOfProducers = numberOfProducers;
	}

	public Set<ProducerProperty> getProducerProperties() {
		return producerProperties;
	}

	public void setProducerProperties(Set<ProducerProperty> producerProperties) {
		this.producerProperties = producerProperties;
	}
	
	public void addProducerProperty(ProducerProperty producerProperty) {
		if (this.producerProperties == null) producerProperties = new HashSet<>();
		producerProperties.add(producerProperty);
		producerProperty.setPerformanceRun(this);
	}
	
	public void removeProducerProperty(ProducerProperty producerProperty) {
		if (this.producerProperties == null) return;
		producerProperties.remove(producerProperty);
	}

	public String getJavaVmVendor() {
		return javaVmVendor;
	}

	public void setJavaVmVendor(String javaVmVendor) {
		this.javaVmVendor = javaVmVendor;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getOsArch() {
		return osArch;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public int getNumberProcessors() {
		return numberProcessors;
	}

	public void setNumberProcessors(int numberProcessors) {
		this.numberProcessors = numberProcessors;
	}

	public boolean isUseAllCores() {
		return useAllCores;
	}

	public void setUseAllCores(boolean useAllCores) {
		this.useAllCores = useAllCores;
	}

	@Override
	public String toString() {
		return "ProducerPerformanceRun [runName=" + runName + ", messagesPerSec=" + messagesPerSec + ", kbytesPerSec="
				+ kbytesPerSec + ", status=" + status + ", instanceType=" + instanceType + ", startTime=" + startTime
				+ ", runTimeMilliSec=" + runTimeMilliSec + ", numberRecordsAttempted=" + numberRecordsAttempted
				+ ", numberRecordsReceived=" + numberRecordsReceived + ", percentMessagesReceived="
				+ percentMessagesReceived + ", averageRecordSizeBytes=" + averageRecordSizeBytes + ", numberKeys="
				+ numberKeys + ", topicName=" + topicName + ", numberPartitions=" + numberPartitions
				+ ", replicationFactor=" + replicationFactor + ", numberOfProducers=" + numberOfProducers
				+ ", javaVmVendor=" + javaVmVendor + ", javaVersion=" + javaVersion + ", osName=" + osName
				+ ", osVersion=" + osVersion + ", osArch=" + osArch + ", numberProcessors=" + numberProcessors
				+ ", useAllCores=" + useAllCores + ", producerProperties=" + producerProperties + "]";
	}

}
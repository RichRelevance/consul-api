/**
 * Copyright (C) 2015 Matt Christiansen (matt@nikore.net)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package net.nikore.consul.v1.agent.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Self {
  @JsonProperty("Config")
  private Config config;
  @JsonProperty("Member")
  private Member member;

  public Config getConfig() {
    return config;
  }

  public void setConfig(Config config) {
    this.config = config;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  @Override
  public String toString() {
    return "Self{" +
      "config=" + config +
      ", member=" + member +
      '}';
  }

  public enum LogLevel {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERR
  }

  public static class Config {

    @JsonProperty("Bootstrap")
    private boolean bootstrap;
    @JsonProperty("Server")
    private boolean server;
    @JsonProperty("Datacenter")
    private String datacenter;
    @JsonProperty("DataDir")
    private String dataDir;
    @JsonProperty("DNSRecursor")
    private String dnsRecursor;
    @JsonProperty("Domain")
    private String domain;
    @JsonProperty("LogLevel")
    private LogLevel logLevel;
    @JsonProperty("NodeName")
    private String nodeName;
    @JsonProperty("ClientAddr")
    private String clientAddress;
    @JsonProperty("BindAddr")
    private String bindAddress;
    @JsonProperty("AdvertiseAddr")
    private String advertiseAddress;
    @JsonProperty("Ports")
    private Map<String, Integer> ports;
    @JsonProperty("LeaveOnTerm")
    private boolean leaveOnTerm;
    @JsonProperty("SkipLeaveOnInt")
    private boolean skipLeaveOnInt;
    @JsonProperty("StatsiteAddr")
    private String statsiteAddress;
    @JsonProperty("Protocol")
    private int protocol;
    @JsonProperty("EnableDebug")
    private boolean enableDebug;
    @JsonProperty("VerifyIncoming")
    private boolean verifyIncoming;
    @JsonProperty("VerifyOutgoing")
    private boolean verifyOutgoing;
    @JsonProperty("CAFile")
    private String caFile;
    @JsonProperty("CertFile")
    private String certFile;
    @JsonProperty("KeyFile")
    private String keyFile;
    @JsonProperty("StartJoin")
    private List<String> startJoin;
    @JsonProperty("UiDir")
    private String uiDir;
    @JsonProperty("PidFile")
    private String pidFile;
    @JsonProperty("EnableSyslog")
    private boolean enableSyslog;
    @JsonProperty("RejoinAfterLeave")
    private boolean rejoinAfterLeave;

    public boolean isBootstrap() {
      return bootstrap;
    }

    public void setBootstrap(boolean bootstrap) {
      this.bootstrap = bootstrap;
    }

    public boolean isServer() {
      return server;
    }

    public void setServer(boolean server) {
      this.server = server;
    }

    public String getDatacenter() {
      return datacenter;
    }

    public void setDatacenter(String datacenter) {
      this.datacenter = datacenter;
    }

    public String getDataDir() {
      return dataDir;
    }

    public void setDataDir(String dataDir) {
      this.dataDir = dataDir;
    }

    public String getDnsRecursor() {
      return dnsRecursor;
    }

    public void setDnsRecursor(String dnsRecursor) {
      this.dnsRecursor = dnsRecursor;
    }

    public String getDomain() {
      return domain;
    }

    public void setDomain(String domain) {
      this.domain = domain;
    }

    public LogLevel getLogLevel() {
      return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
      this.logLevel = logLevel;
    }

    public String getNodeName() {
      return nodeName;
    }

    public void setNodeName(String nodeName) {
      this.nodeName = nodeName;
    }

    public String getClientAddress() {
      return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
      this.clientAddress = clientAddress;
    }

    public String getBindAddress() {
      return bindAddress;
    }

    public void setBindAddress(String bindAddress) {
      this.bindAddress = bindAddress;
    }

    public String getAdvertiseAddress() {
      return advertiseAddress;
    }

    public void setAdvertiseAddress(String advertiseAddress) {
      this.advertiseAddress = advertiseAddress;
    }

    public Map<String, Integer> getPorts() {
      return ports;
    }

    public void setPorts(Map<String, Integer> ports) {
      this.ports = ports;
    }

    public boolean isLeaveOnTerm() {
      return leaveOnTerm;
    }

    public void setLeaveOnTerm(boolean leaveOnTerm) {
      this.leaveOnTerm = leaveOnTerm;
    }

    public boolean isSkipLeaveOnInt() {
      return skipLeaveOnInt;
    }

    public void setSkipLeaveOnInt(boolean skipLeaveOnInt) {
      this.skipLeaveOnInt = skipLeaveOnInt;
    }

    public String getStatsiteAddress() {
      return statsiteAddress;
    }

    public void setStatsiteAddress(String statsiteAddress) {
      this.statsiteAddress = statsiteAddress;
    }

    public int getProtocol() {
      return protocol;
    }

    public void setProtocol(int protocol) {
      this.protocol = protocol;
    }

    public boolean isEnableDebug() {
      return enableDebug;
    }

    public void setEnableDebug(boolean enableDebug) {
      this.enableDebug = enableDebug;
    }

    public boolean isVerifyIncoming() {
      return verifyIncoming;
    }

    public void setVerifyIncoming(boolean verifyIncoming) {
      this.verifyIncoming = verifyIncoming;
    }

    public boolean isVerifyOutgoing() {
      return verifyOutgoing;
    }

    public void setVerifyOutgoing(boolean verifyOutgoing) {
      this.verifyOutgoing = verifyOutgoing;
    }

    public String getCaFile() {
      return caFile;
    }

    public void setCaFile(String caFile) {
      this.caFile = caFile;
    }

    public String getCertFile() {
      return certFile;
    }

    public void setCertFile(String certFile) {
      this.certFile = certFile;
    }

    public String getKeyFile() {
      return keyFile;
    }

    public void setKeyFile(String keyFile) {
      this.keyFile = keyFile;
    }

    public List<String> getStartJoin() {
      return startJoin;
    }

    public void setStartJoin(List<String> startJoin) {
      this.startJoin = startJoin;
    }

    public String getUiDir() {
      return uiDir;
    }

    public void setUiDir(String uiDir) {
      this.uiDir = uiDir;
    }

    public String getPidFile() {
      return pidFile;
    }

    public void setPidFile(String pidFile) {
      this.pidFile = pidFile;
    }

    public boolean isEnableSyslog() {
      return enableSyslog;
    }

    public void setEnableSyslog(boolean enableSyslog) {
      this.enableSyslog = enableSyslog;
    }

    public boolean isRejoinAfterLeave() {
      return rejoinAfterLeave;
    }

    public void setRejoinAfterLeave(boolean rejoinAfterLeave) {
      this.rejoinAfterLeave = rejoinAfterLeave;
    }

    @Override
    public String toString() {
      return "Config{" +
        "bootstrap=" + bootstrap +
        ", server=" + server +
        ", datacenter='" + datacenter + '\'' +
        ", dataDir='" + dataDir + '\'' +
        ", dnsRecursor='" + dnsRecursor + '\'' +
        ", domain='" + domain + '\'' +
        ", logLevel='" + logLevel + '\'' +
        ", nodeName='" + nodeName + '\'' +
        ", clientAddress='" + clientAddress + '\'' +
        ", bindAddress='" + bindAddress + '\'' +
        ", advertiseAddress='" + advertiseAddress + '\'' +
        ", ports=" + ports +
        ", leaveOnTerm=" + leaveOnTerm +
        ", skipLeaveOnInt=" + skipLeaveOnInt +
        ", statsiteAddress='" + statsiteAddress + '\'' +
        ", protocol=" + protocol +
        ", enableDebug=" + enableDebug +
        ", verifyIncoming=" + verifyIncoming +
        ", verifyOutgoing=" + verifyOutgoing +
        ", caFile='" + caFile + '\'' +
        ", certFile='" + certFile + '\'' +
        ", keyFile='" + keyFile + '\'' +
        ", startJoin=" + startJoin +
        ", uiDir='" + uiDir + '\'' +
        ", pidFile='" + pidFile + '\'' +
        ", enableSyslog=" + enableSyslog +
        ", rejoinAfterLeave=" + rejoinAfterLeave +
        '}';
    }
  }
}

package top.ppnt.eclipse.paho.emqx;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSample {
  public static void main(String[] args) {
    String topic = "test/topic";
    String content = "Hello World";
    int qos = 2;
    String broker = "tcp://192.168.56.1:1883";
    String clientId = MqttClient.generateClientId();
    // 持久化
    MemoryPersistence persistence = new MemoryPersistence();
    // MQTT 连接选项
    MqttConnectOptions connOpts = new MqttConnectOptions();
    // 设置认证信息
    connOpts.setUserName("admin");
    connOpts.setPassword("123456".toCharArray());

    try {
      MqttClient client = new MqttClient(broker, clientId, persistence);
      // 设置回调
      client.setCallback(new SampleCallback());
      // 建立连接
      System.out.println("Connecting to broker: " + broker);
      client.connect(connOpts);
      System.out.println("Connected to broker: " + broker);
      // 订阅 topic
      client.subscribe(topic, qos);
      System.out.println("Subscribed to topic: " + topic);
      // 发布消息
      MqttMessage message = new MqttMessage(content.getBytes());
      message.setQos(qos);
      client.publish(topic, message);
      System.out.println("Message published");
      client.disconnect();
      System.out.println("Disconnected");
      client.close();
      System.exit(0);
    } catch (MqttException me) {
      System.out.println("reason " + me.getReasonCode());
      System.out.println("msg " + me.getMessage());
      System.out.println("loc " + me.getLocalizedMessage());
      System.out.println("cause " + me.getCause());
      System.out.println("excep " + me);
      me.printStackTrace();
    }
  }
}
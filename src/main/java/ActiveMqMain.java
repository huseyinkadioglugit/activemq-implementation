import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqMain {

    public static void main(String[] args) {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Thread topicConsumerThread = new Thread(new Consumer(connectionFactory));
        topicConsumerThread.start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Thread topicProducerThread = new Thread(new Producer(connectionFactory));
        topicProducerThread.start();

    }
}

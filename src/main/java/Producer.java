import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer implements Runnable {

    ActiveMQConnectionFactory connectionFactory = null;

    public Producer(ActiveMQConnectionFactory activeMQConnectionFactory) {
        this.connectionFactory = activeMQConnectionFactory;
    }

    @Override
    public void run() {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("ActiveMQ-Topic");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            String text = "Topic!";
            TextMessage message = session.createTextMessage(text);

            producer.send(message);

            session.close();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

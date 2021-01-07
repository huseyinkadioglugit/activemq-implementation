import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer implements Runnable {

    ActiveMQConnectionFactory connectionFactory = null;

    public Consumer(ActiveMQConnectionFactory activeMQConnectionFactory) {
        this.connectionFactory = activeMQConnectionFactory;
    }

    @Override
    public void run() {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("ActiveMQ-Topic");

            MessageConsumer messageConsumer = session.createConsumer(destination);

            Message message = messageConsumer.receive();

            TextMessage textMessage = (TextMessage) message;

            System.out.println("Received : " + textMessage.getText());

            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

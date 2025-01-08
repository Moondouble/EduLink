package EduLink.service.stock;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockConsumer {

    private final List<String> stockData = new ArrayList<>();

    @KafkaListener(topics = "stock", groupId = "stock-group")
    public void consume(ConsumerRecord<String, String> record) {
        stockData.add(record.value());
        System.out.println("Consumed: " + record.value());
    }

    public List<String> getStockData() {
        return new ArrayList<>(stockData); // Return a copy to ensure thread-safety
    }
}
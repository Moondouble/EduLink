package EduLink.controller;

import EduLink.service.stock.StockConsumer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StockController {

    private final StockConsumer stockConsumer;

    public StockController(StockConsumer stockConsumer) {
        this.stockConsumer = stockConsumer;
    }

    @GetMapping("/api/stock")
    public List<String> getStockData() {
        return stockConsumer.getStockData();
    }
    
}

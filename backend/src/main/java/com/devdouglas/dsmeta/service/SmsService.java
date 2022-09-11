package com.devdouglas.dsmeta.service;

import com.devdouglas.dsmeta.entity.Sale;
import com.devdouglas.dsmeta.repository.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmsService {

    private final SaleRepository saleRepository;

    @Autowired
    public SmsService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    public void sendSms(Long saleId) {
        Optional<Sale> optSale = saleRepository.findById(saleId);

        if (optSale.isPresent()) {
            Sale sale = optSale.get();
            String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();

            String msg = "O vendedor " + sale.getSellerName() + " foi destaque em " + date
                    + " com um total de R$ " + String.format("%.2f", sale.getAmount());

            Twilio.init(twilioSid, twilioKey);

            PhoneNumber to = new PhoneNumber(twilioPhoneTo);
            PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

            Message message = Message.creator(to, from, msg).create();

            System.out.println(message.getSid());
        }
    }
}

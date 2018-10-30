package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.repositories.PaymentRepository;

public class HttpPaymentService implements PaymentService {

    private PaymentRepository paymentRepository;

    public HttpPaymentService(PaymentRepository repository) {
        this.paymentRepository = repository;
    }

    @Override
    public Payment createPayment(Payment payment) throws IOException {
        return paymentRepository.createPayment(payment);
    }
}

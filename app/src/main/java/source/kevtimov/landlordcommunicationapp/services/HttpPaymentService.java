package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;
import java.util.List;

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

    @Override
    public List<Payment> getAllPaymentsByLandlordId(int landlordId) throws IOException {
        return paymentRepository.getAllPaymentsByLandlordId(landlordId);
    }

    @Override
    public List<Payment> getAllPaymentsByTenantId(int tenantId) throws IOException {
        return paymentRepository.getAllPaymentsByTenantId(tenantId);
    }
}

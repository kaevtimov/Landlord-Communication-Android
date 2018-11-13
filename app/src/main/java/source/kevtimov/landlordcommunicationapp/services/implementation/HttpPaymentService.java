package source.kevtimov.landlordcommunicationapp.services.implementation;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.repositories.base.PaymentRepository;
import source.kevtimov.landlordcommunicationapp.services.base.PaymentService;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

public class HttpPaymentService implements PaymentService {

    private PaymentRepository paymentRepository;
    private Validator<Payment> mValidator;

    public HttpPaymentService(PaymentRepository repository, Validator<Payment> validator) {
        this.mValidator = validator;
        this.paymentRepository = repository;
    }

    @Override
    public Payment createPayment(Payment payment) throws IOException {

        if(!mValidator.isObjectValid(payment)){
            throw new IllegalArgumentException(Constants.PAYMENT_VALIDATOR_MESSAGE);
        }

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

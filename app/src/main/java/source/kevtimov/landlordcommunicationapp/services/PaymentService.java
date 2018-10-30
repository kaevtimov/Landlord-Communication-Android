package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Payment;

public interface PaymentService {

    Payment createPayment(Payment payment) throws IOException;
}

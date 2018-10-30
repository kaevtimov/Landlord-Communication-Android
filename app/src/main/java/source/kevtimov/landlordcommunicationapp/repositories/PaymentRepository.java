package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Payment;

public interface PaymentRepository {

    Payment createPayment(Payment payment) throws IOException;
}

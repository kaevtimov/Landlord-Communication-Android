package source.kevtimov.landlordcommunicationapp.services.base;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Payment;

public interface PaymentService {

    Payment createPayment(Payment payment) throws IOException;

    List<Payment> getAllPaymentsByLandlordId(int landlordId) throws IOException;

    List<Payment> getAllPaymentsByTenantId(int tenantId) throws IOException;
}

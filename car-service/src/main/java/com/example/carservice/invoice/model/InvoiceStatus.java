package com.example.carservice.invoice.model;

public enum InvoiceStatus {
        /**
         * The invoice has been created but not yet finalized or sent to the customer.
         * It can still be edited.
         */
        DRAFT,

        /**
         * The invoice has been finalized and sent to the customer for payment.
         */
        SENT,

        /**
         * The customer has paid the full amount of the invoice.
         */
        PAID,

        /**
         * The due date has passed, and the invoice has not been paid.
         */
        OVERDUE,

        /**
         * The invoice has been canceled and is no longer valid. This is better than deleting,
         * as it maintains a record for accounting.
         */
        VOID,

        /**
         * A partial payment has been made, but the full amount has not yet been received.
         * (Optional, but useful if you accept partial payments).
         */
        PARTIALLY_PAID
}

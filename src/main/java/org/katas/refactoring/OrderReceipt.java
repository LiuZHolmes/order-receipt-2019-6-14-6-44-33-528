package org.katas.refactoring;

import java.util.stream.Collectors;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;

    private final double TAXRATE = 0.1;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        printBeforeLineItems(output);

        printsLineItems(output);

        printAfterLineItems(output);

        return output.toString();
    }

    private void printAfterLineItems(StringBuilder output) {
        printTheStateTax(output, calculateTotalSalesTax());

        printTotalAmount(output, calculateTotalAmountOfLineItems());
    }

    private void printBeforeLineItems(StringBuilder output) {
        printHeader(output);

        printCustomer(output);
    }

    private void printTotalAmount(StringBuilder output, double totalAmountOfLineItems) {
        output.append("Total Amount").append('\t').append(totalAmountOfLineItems);
    }

    private double calculateTotalAmountOfLineItems() {
        return o.getLineItems().stream()
                .mapToDouble(x -> x.totalAmount() + x.totalAmount() * TAXRATE).sum();
    }

    private void printTheStateTax(StringBuilder output, double totalSalesTax) {
        output.append("Sales Tax").append('\t').append(totalSalesTax);
    }

    public double calculateTotalSalesTax() {
        return o.getLineItems().stream()
                .mapToDouble(x -> x.totalAmount() * TAXRATE).sum();
    }

    private void printsLineItems(StringBuilder output) {
        output.append(o.getLineItems().stream()
                .map(x -> String.format("%s\t%.1f\t%d\t%.1f", x.getDescription(), x.getPrice(), x.getQuantity(), x.totalAmount()))
                .collect(Collectors.joining("\n"))).append("\n");
    }

    private void printCustomer(StringBuilder output) {
        output.append(o.getCustomerName()).append(o.getCustomerAddress());
    }

    private void printHeader(StringBuilder output) {
        final String header = "======Printing Orders======\n";
        output.append(header);
    }
}
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

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        printHeader(output);

        printCustomer(output);

        printsLineItems(output);



        // prints the state tax
        printsTheStateTax(output);

        // print total amount
        printTotalAmount(output);
        return output.toString();
    }

    private void printTotalAmount(StringBuilder output) {
        double tot = o.getLineItems().stream()
                .mapToDouble(x -> x.totalAmount() + x.totalAmount() *  .10).sum();
        output.append("Total Amount").append('\t').append(tot);
    }

    private void printsTheStateTax(StringBuilder output) {
        double totSalesTx = o.getLineItems().stream()
                .mapToDouble(x -> x.totalAmount() *  .10).sum();
        output.append("Sales Tax").append('\t').append(totSalesTx);
    }

    private void printsLineItems(StringBuilder output) {
        output.append(o.getLineItems().stream()
                .map(x -> String.format("%s\t%.1f\t%d\t%.1f",x.getDescription(),x.getPrice(),x.getQuantity(),x.totalAmount()))
                .collect(Collectors.joining("\n")));
        output.append("\n");
    }

    private void printCustomer(StringBuilder output) {
        output.append(o.getCustomerName());
        output.append(o.getCustomerAddress());
    }

    private void printHeader(StringBuilder output) {
        final String header = "======Printing Orders======\n";
        output.append(header);
    }
}
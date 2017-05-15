package com.foo;

import java.util.Map;

public class Calculator {
	public static double rounding(double value) {
		return ( Math.floor(value * 100)) / 100;
	}

	/**
	 * receives a collection of orders. For each order, iterates on the order lines and calculate the total price which
	 * is the item's price * quantity * taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without taxes for this order
	 */
	public double calculate(Map<String, Order> o) {

		double grandtotal = 0;

		// Iterate through the orders
		
		for (Map.Entry<String, Order> entry : o.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");
		

			Order r = entry.getValue();
			
			double totalTax = 0;
			double total = 0;

			// Iterate through the items in the order
			for (int i = 0; i < r.size(); i++) {

				// Calculate the taxes
				double tax = 0;

				if (r.get(i).getItem().getDescription().contains("imported")||r.get(i).getItem().getDescription().contains("Imported")) {
					tax = (r.get(i).getItem().getPrice() * 0.15); // Extra 5% tax on
					// imported items
				} else {
					tax = (r.get(i).getItem().getPrice() * 0.10);
				}

				// Calculate the total price
				double totalprice = (r.get(i).getItem().getPrice() + (tax)) * r.get(i).getQuantity() ;

				// Print out the item's total price
				System.out.println(r.get(i).getQuantity()+" "+r.get(i).getItem().getDescription() + ": " +rounding(totalprice) );

				// Keep a running total
				totalTax += tax* r.get(i).getQuantity();
				total += r.get(i).getItem().getPrice()*r.get(i).getQuantity();
			}

			// Print out the total taxes
			System.out.println("Sales Tax: " +rounding(totalTax) );

			

			// Print out the total amount
			System.out.println("Total: " +rounding(total));
			grandtotal += total;
		}
		
		
		return rounding(grandtotal);
	}

}



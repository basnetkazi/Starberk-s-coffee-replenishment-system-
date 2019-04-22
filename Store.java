/**
 * 
 */

/**
 * @author arun.basnet
 *
 */
public class Store {
	private static Product best_product = null;
	private Product product1 = new Product();
	private Product product2 = new Product();
	private Product product3 = new Product();

	/**
	 * @return the best_product
	 */
	public static Product getBest_product() {
		return best_product;
	}

	/**
	 * @param best_product the best_product to set
	 */
	public static void setBest_product(Product best_product) {
		Store.best_product = best_product;
	}

	boolean isDatabaseFull() {
		return (product1.isFlg() == false && product2.isFlg() == false && product3.isFlg() == false);
	}

	boolean isEmpty() {
		return (product1.isFlg() == true && product2.isFlg() == true && product3.isFlg() == true);

	}

	boolean isproducttaken(String name) {
		if (product1.getName().equals(name) || product2.getName().equals(name) || product3.getName().equals(name)) {
			// System.out.println("You already have an product with the name " + name
			// + ". Do you want to change the Product name of Edit the object");
			return true;
		} else
			return false;
	}

	void objectCreation(String name, int demand_rate, double setup_cost, double unit_cost, double inventory_cost,
			double selling_price) {

		if (product1.isFlg()) {
			product1 = new Product(name, demand_rate, setup_cost, unit_cost, inventory_cost, selling_price);
			if (product1.getEOQ() < product1.getDemand_rate()) {
				System.out.println(
						"The data of the Product is not reliable as the Replenishment amount is less than weekly demand");
				System.out.println("Please re enter the suaitable data");
				System.out.println("Returning to the main menu");
				objectDeletion(name);
			} else
				System.out.println("Product " + name + " inserted Sucessfully.");

		}

		else if (product2.isFlg()) {
			product2 = new Product(name, demand_rate, setup_cost, unit_cost, inventory_cost, selling_price);
			System.out.println("Product" + name + " inserted Sucessfully.");
		}

		else if (product3.isFlg()) {
			product3 = new Product(name, demand_rate, setup_cost, unit_cost, inventory_cost, selling_price);
			System.out.println("Product" + name + " inserted Sucessfully.");
		}

		else
			System.out.println("You already have Database full");
	}

	void objectDeletion(String name) {
		if (product1.getName().equals(name)) {
			if (best_product == product1) {
				if (product2.getProfit() != 0 || product3.getProfit() != 0) {
					best_product = product2.getProfit() > product3.getProfit() ? product2 : product3;
				} else
					best_product = null;
			}
			product1 = new Product();
		}

		else if (product2.getName().equals(name)) {
			if (best_product == product2) {
				if (product1.getProfit() != 0 || product3.getProfit() != 0) {
					best_product = product1.getProfit() > product3.getProfit() ? product1 : product3;
				} else
					best_product = null;

			}
			product2 = new Product();
		}

		else {
			if (best_product == product3) {

				if (product2.getProfit() != 0 || product1.getProfit() != 0) {
					best_product = product2.getProfit() > product1.getProfit() ? product2 : product1;
				} else
					best_product = null;

			}
			product3 = new Product();
		}
	}

//	void objectEdition(String name, float d_r, float s_c, float u_c) {
//		if (product1.getName().equals(name)) {
//			product1 = new Product(name, d_r, s_c, u_c);
//		}
//
//		else if (product2.getName().equals(name)) {
//			product2 = new Product(name, d_r, s_c, u_c);
//		}
//
//		else if (product3.getName().equals(name)) {
//			product3 = new Product(name, d_r, s_c, u_c);
//		}
//		System.out.println("The product with the name " + name + " is Edited sucessfuly");
//	}

	void objectDisplaySelection(String name) {
		if (product1.getName().equals(name)) {
			display(product1);
		}

		else if (product2.getName().equals(name)) {
			display(product2);
		}

		else if (product3.getName().equals(name)) {
			display(product3);
		}

	}

	void display(Product P) {
		System.out.println("The detail of the Product " + P.getName() + " are as follows");
		System.out.println("the demand rate is" + P.getDemand_rate() + ".");
		System.out.println("the setup cost is" + P.getSetup_cost() + ".");
		System.out.println("the unit cost is" + P.getUnit_cost() + ".");
		System.out.println("the Inventory cost is" + P.getInventory_cost() + ".");
		System.out.println("the Selling Price is" + P.getSelling_Price() + ".");
	}

	void selectReplnProduct(String name, int weeks) {
		if (product1.getName().equals(name)) {
			calculateRepln(product1, weeks);
		}

		else if (product2.getName().equals(name)) {
			calculateRepln(product2, weeks);
		}

		else if (product3.getName().equals(name)) {
			calculateRepln(product3, weeks);
		}
	}

	void calculateRepln(Product P, int weeks) {
		int setup_time = 1;
		int EOQ = P.getEOQ();
		int ordered_no = EOQ;
		int inventory_stored = 0;
		int itemRemaining = EOQ - P.getDemand_rate();
		System.out.println(" ----------------------------------------------");
		System.out.println("|Week   |  Quantity Order  | Demand | Inventory|");
		System.out.println(" ----------------------------------------------");
		System.out.println("|  " + 1 + "    |     " + EOQ + "          |  " + P.getDemand_rate() + "    |     "
				+ itemRemaining + "   |");
		for (int i = 2; i <= weeks; i++) {
			inventory_stored += itemRemaining;
			if (itemRemaining >= P.getDemand_rate()) {
				itemRemaining = itemRemaining - P.getDemand_rate();
				System.out.println("|  " + i + "    |      0           |  " + P.getDemand_rate() + "    |     "
						+ itemRemaining + "   |");
			} else {
				if (itemRemaining + P.getEOQ() - ((weeks - i + 1) * P.getDemand_rate()) > 0) {
					EOQ = ((weeks - i + 1) * P.getDemand_rate()) - itemRemaining;
					ordered_no += EOQ;
					itemRemaining = itemRemaining + EOQ - P.getDemand_rate();
				} else {
					EOQ = P.getEOQ();
					ordered_no += EOQ;
					itemRemaining = itemRemaining + EOQ - P.getDemand_rate();
				}
				setup_time++;

				System.out.println("|  " + i + "    |     " + EOQ + "          |  " + P.getDemand_rate() + "    |     "
						+ itemRemaining + "   |");
			}
		}
		System.out.println(" ----------------------------------------------");
		ordered_no -= itemRemaining;
		double profit = P.getDemand_rate() * weeks * P.getSelling_Price() - (setup_time * P.getSetup_cost()
				+ ordered_no * P.getUnit_cost() + inventory_stored * P.getInventory_cost());
		P.setProfit(profit);
		if (best_product == null) {
			best_product = P;
		} else {
			best_product = best_product.getProfit() > P.getProfit() ? best_product : P;
		}
	}

}

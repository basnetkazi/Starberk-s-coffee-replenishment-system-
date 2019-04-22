import java.util.Scanner;

/**
 * 
 */

/**
 * @author arun.basnet
 *
 */
public class StarberksInterface {

	/**
	 * @param args
	 */
	private Store str = new Store();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StarberksInterface object = new StarberksInterface();
		object.run();

	}

	void run() {
		System.out.println(
				"*********************************************************************************************");
		System.out.println(
				"------------------------------------------***Main Menu***------------------------------------");
		System.out.println(
				"*********************************************************************************************");
		System.out.println(
				"---------------------***Choose the respective number for the event***------------------------");
		System.out.println(
				"*********************************************************************************************");
		System.out.println("***1. Input data for one product\r\n" + "***2. Show data from one product\r\n"
				+ "***3. Show the replenishment strategy for a product\r\n" + "***4. Exit program");
		Scanner reader = new Scanner(System.in);
		int no = reader.nextInt();
		// reader.close();
		switch (no) {
		case 1: {
			if (str.isDatabaseFull()) {
				System.out.println(
						"Databse already contains three products do you want to delete any of product to replace it");
				System.out.println("If Yes Press Y else Any key to continue");
				Scanner scanner = new Scanner(System.in);
				String key = scanner.next().toLowerCase();
				if (key.equals("y")) {
					System.out.println("Select the product you want to delete and proceed");
					showData();
					System.out.println("Please type the name of product you want to delete");
					key = scanner.next().toLowerCase();
					if (str.isproducttaken(key)) {
						str.objectDeletion(key);
						System.out.println("The product with the name " + key + " is Deleted sucessfuly");
						insertData();
					} else {
						System.out.println("Invalid Product Name Proceeding to Main Menu");
						run();
					}
				} else {
					System.out.println("Returning to the Main Menu");
				}
			} else {
				insertData();
			}
			break;
		}

		case 2:
			showData();
			break;
		case 3:
			showReplenishment();
			break;
		case 4:
			bestProduct();
			break;
		default:
			System.out.println(
					"*********************************************************************************************");
			System.out.println(
					"------------------------***Please select between 1 and 4 to proceed***----------------------");
			System.out.println(
					"*********************************************************************************************");

		}

	}

	void insertData() {
		System.out.println(
				"*********************************************************************************************");
		System.out.println(
				"----------------------***Please insert the following data as required***---------------------");
		System.out.println(
				"*********************************************************************************************");
		System.out.println("***Enter the name of Product:");
		Scanner scan = new Scanner(System.in);
		String name = scan.next();
		// scan.close();
		name = name.toLowerCase();
		if (name.length() > 10 || name.length() < 3) {
			System.out.println(
					"*********************************************************************************************");
			System.out.println(
					"----------------***Please enter the product name of length between 3 and 10***---------------");
			System.out.println(
					"*********************************************************************************************");
			insertData();
		}
		System.out.println("***Enter demand rate of the Product:");
		int demand_rate = scan.nextInt();
		System.out.println("***Enter setup cost of the Product:");
		double setup_cost = scan.nextFloat();
		System.out.println("***Enter unit cost of the Product:");
		double unit_cost = scan.nextFloat();
		System.out.println("***Enter inventory cost of the Product:");
		double inventory_cost = scan.nextFloat();
		System.out.println("***Enter selling price of the Product:");
		double selling_price = scan.nextFloat();
		if (str.isproducttaken(name)) {
			name = handlingProductTaken(name);
			if (name.equals("Failed")) {
				run();
			}
		}
		{
			str.objectCreation(name, demand_rate, setup_cost, unit_cost, inventory_cost, selling_price);

			run();
		}

	}

	String handlingProductTaken(String oldName) {
		System.out.println(
				"*********************************************************************************************");
		System.out.println(
				"-------------------------------------***Product already exist***-----------------------------");
		System.out.println(
				"-----------------------------***Edit the exixting Product (E)***----------------------------");
		System.out.println(
				"--------------------------***Change the Name for the New Product (C)***----------------------");
		System.out.println(
				"***-----Enter C to proceed with Different Name and E to Edit the Exixting Product***---------");
		System.out.println(
				"*********************************************************************************************");
		Scanner scan = new Scanner(System.in);
		String confirmation = scan.nextLine();
		confirmation = confirmation.toLowerCase();
		if (confirmation.equals("c")) {
			System.out.println("Enter the different name for the Product");
			String name = scan.next();
			name = name.toLowerCase();
			if (str.isproducttaken(name)) {
				return handlingProductTaken(name);
			} else {
				return name;
			}

		} else if (confirmation.equals("e")) {
			str.objectDeletion(oldName);
			System.out.println("The product with the name " + oldName + " is Edited sucessfuly");
			return oldName;
		}

		else {
			System.out.println("Wrong input Proceeding to Main Menu");
			return "Failed";
		}
	}

	void showData() {
		if (str.isEmpty()) {
			System.out.println("Sorry there are no any products to display");
			System.out.println("Proceeding to main Menu");
			run();
		}

		else {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please insert the Product to Display");
			String search = scan.nextLine().toLowerCase();
			if (str.isproducttaken(search)) {
				str.objectDisplaySelection(search);
				run();
			} else {
				System.out.println("There is no product of name" + search + ".");
				System.out.println("Please try with new name again");
				showData();
			}
		}
	}

	void showReplenishment() {
		if (str.isEmpty()) {
			System.out.println("Sorry there are no any products to calculate Replenishment");
			System.out.println("Proceeding to main Menu");
			run();
		}

		else {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please insert the Product to Calculate Replenishment");
			String search = scan.nextLine().toLowerCase();
			System.out.println("Please insert the Week to Calculate Replenishment");
			int weeks = scan.nextInt();
			if (str.isproducttaken(search)) {
				str.selectReplnProduct(search, weeks);
				run();
			} else {
				System.out.println("There is no product of name" + search + ".");
				System.out.println("Please try with new name again");
				showReplenishment();
			}
		}
	}

	void bestProduct() {
		if (str.getBest_product() != null) {
			System.out.println(
					"*********************************************************************************************");
			System.out.println(
					"--------------------------------------***The Best Product is:***-----------------------------");
			System.out.println(
					"*********************************************************************************************");
			System.out.println("***Product Name:" + str.getBest_product().getName() + "***");
			System.out.println("***The Profit is: " + str.getBest_product().getProfit() + "***");
			System.out.println(
					"*********************************************************************************************");

		}
		System.out.println(
				"*********************************************************************************************");
		System.out.println(
				"------------------------------------------***Thank You***------------------------------------");
		System.out.println(
				"*********************************************************************************************");
		Scanner scan = new Scanner(System.in);
		scan.close();
	}
}

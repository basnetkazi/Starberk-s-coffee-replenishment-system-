/**
 * 
 */

/**
 * @author arun.basnet
 */
public class Product {
	private String name = new String();
	private int demand_rate = 0;
	private double setup_cost = 0;
	private double unit_cost = 0;
	private double inventory_cost = 0;
	private double selling_Price = 0;
	private double profit = 0;
	private boolean flg = true;
	private int EOQ = 0;

	// Constructors
	public Product() {

	}

	public Product(String name, int d_r, double s_c, double u_c, double i_c, double s_p) {
		this.name = name;
		this.demand_rate = d_r;
		this.setup_cost = s_c;
		this.unit_cost = u_c;
		this.inventory_cost = i_c;
		this.selling_Price = s_p;
		this.EOQ = (int) Math.round(Math.sqrt(2 * setup_cost * demand_rate / inventory_cost));
		this.flg = false;
	}

	// Getters and Setters

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the demand_rate
	 */
	public int getDemand_rate() {
		return demand_rate;
	}

	/**
	 * @param demand_rate the demand_rate to set
	 */
	public void setDemand_rate(int demand_rate) {
		this.demand_rate = demand_rate;
	}

	/**
	 * @return the setup_cost
	 */
	public double getSetup_cost() {
		return setup_cost;
	}

	/**
	 * @param setup_cost the setup_cost to set
	 */
	public void setSetup_cost(double setup_cost) {
		this.setup_cost = setup_cost;
	}

	/**
	 * @return the unit_cost
	 */
	public double getUnit_cost() {
		return unit_cost;
	}

	/**
	 * @param unit_cost the unit_cost to set
	 */
	public void setUnit_cost(double unit_cost) {
		this.unit_cost = unit_cost;
	}

	/**
	 * @return the inventory_cost
	 */
	public double getInventory_cost() {
		return inventory_cost;
	}

	/**
	 * @param inventory_cost the inventory_cost to set
	 */
	public void setInventory_cost(double inventory_cost) {
		this.inventory_cost = inventory_cost;
	}

	/**
	 * @return the selling_Price
	 */
	public double getSelling_Price() {
		return selling_Price;
	}

	/**
	 * @param selling_Price the selling_Price to set
	 */
	public void setSelling_Price(double selling_Price) {
		this.selling_Price = selling_Price;
	}

	/**
	 * @return the profit
	 */
	public double getProfit() {
		return profit;
	}

	/**
	 * @return the eOQ
	 */
	public int getEOQ() {
		return EOQ;
	}

	/**
	 * @param eOQ the eOQ to set
	 */
	public void setEOQ(int eOQ) {
		EOQ = eOQ;
	}

	/**
	 * @param profit the profit to set
	 */
	public void setProfit(double profit) {
		this.profit = profit;
	}

	/**
	 * @return the flg
	 */
	public boolean isFlg() {
		return flg;
	}

	/**
	 * @param flg the flg to set
	 */
	public void setFlg(boolean flg) {
		this.flg = flg;
	}

}

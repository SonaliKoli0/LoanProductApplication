package product;

import java.sql.Date;

public class Cashflow {
	protected Date startDate;
	protected Date endDate;
	protected int productId;
	protected String type;
	protected String direction;

	public void setStartDate(Date startDate) {

	}

	public void setEndDate(Date endDate) {

	}

	public Date getStartDate() {
		Date d = new Date(12 / 2 / 2023);
		return d;

	}

	public Date getEndDate() {
		Date d = new Date(12 / 2 / 2023);
		return d;
	}

	public void setDirection() {

	}

	public String getDirection() {
		return "";
	}

	public void setType() {

	}

	public String getType() {
		return "";
	}

}

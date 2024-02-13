package databaseConnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import databaseHelper.DatabaseHelper;
import product.AppStarter;
import product.Product;
import utils.DbUtils;
import utils.Util;

public class ProductSQL {
	protected static final String SAVE_PRODUCT = "INSERT INTO Product (productType, startDate, endDate) VALUES (?, ?, ?)";
    //Method to insert the product in to the database
	public static void insert(Product p) throws Exception {
		PreparedStatement stmt = null;
		int j = 1;
		long id = -1;

		try {
			Connection con = DatabaseHelper.getConnection();
			stmt = con.prepareStatement(SAVE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
			if (p.getStartDate() != null) {
				stmt.setString(j++, p.getProductType());
				stmt.setDate(j++, Util.toSQLDate(p.getStartDate()));
				stmt.setDate(j++, Util.toSQLDate(p.getEndDate()));

			} else {
				stmt.setNull(j++, Types.DATE);
			}

			// Retrieve the generated keys
			// Execute the update operation
			int affectedRows = stmt.executeUpdate();
			System.out.println("Created successfully");

			if (affectedRows == 0) {
				throw new SQLException("Inserting product failed, no rows affected.");
			}

			// Execute a separate query to retrieve the last inserted ID
			try (ResultSet generatedKeys = stmt
					.executeQuery("SELECT product_id FROM Product WHERE rownum = 1 ORDER BY product_id DESC")) {
				if (generatedKeys.next()) {
					id = generatedKeys.getLong("product_id");

					p.setProductId((int) id);
				} else {
					throw new SQLException("Inserting product failed, no ID obtained.");
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {

			DbUtils.close(stmt);
		}
		LoanProductSQL.insertLoanProduct(p);
	}
    //Method to get the product details from the database based on the productId
	public static Product readProduct(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		Product p = null;
		try {
			con = DatabaseHelper.getConnection();
			stmt = con.prepareStatement("SELECT * FROM PRODUCT WHERE PRODUCT_ID=?");
			stmt.setLong(1, id);

			rs = stmt.executeQuery();

			if (rs.next()) {
				int productId = rs.getInt("PRODUCT_ID");
				Date startDate = rs.getDate("STARTDATE");
				String productType = rs.getString("PRODUCTTYPE");
				Date endDate = rs.getDate("ENDDATE");
				p = new Product(productId, productType, startDate, endDate, null);

			} else {
				System.out.println("There is no product with id " + id);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DbUtils.close(stmt);
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}
    //Method to delete the product details from the database based on the productId
	public static void deleteProduct(int id) {
		PreparedStatement stmt = null;

		Connection con = null;

		try {
			con = DatabaseHelper.getConnection();
			stmt = con.prepareStatement("DELETE  FROM PRODUCT WHERE PRODUCT_ID=?");
			stmt.setLong(1, id);

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DbUtils.close(stmt);
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	//Method to delete the product details from the database based on the productId
	public static void updateProduct( int id) {
		int index = 1;
		PreparedStatement stmt = null;
		Connection con = null;
		try {
			String a="UPDATE product SET";
			if (AppStarter.inputs.containsKey("startDate")) {
				a+=" startDate =?";
			} 
			else if (AppStarter.inputs.containsKey("endDate")) {
				a+=" endDate =?";
			}
			else{
				return;
			}
			a+=" WHERE PRODUCT_ID = ?";
			
			con = DatabaseHelper.getConnection();
			stmt = con.prepareStatement(a);
			
			if (AppStarter.inputs.containsKey("startDate")) {
				stmt.setDate(index++, Util.toSQLDate(Util.parseDate(AppStarter.inputs.get("startDate"))));
			} 
			if (AppStarter.inputs.containsKey("endDate")) {
				stmt.setDate(index++, Util.toSQLDate(Util.parseDate(AppStarter.inputs.get("endDate"))));
			} 
//			Date oldStartDate = (Date) p.getStartDate();
//			Date oldEndDate = (Date) p.getEndDate();
			stmt.setInt(index++, id);
			stmt.executeUpdate();
			System.out.println("product updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(stmt);
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	
	

}
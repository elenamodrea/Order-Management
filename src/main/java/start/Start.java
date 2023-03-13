package start;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import bll.OrdersBLL;
import bll.ProductBLL;
import connection.ConnectionFactory;
import model.Orders;
import model.Product;
import presentation.Management;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */

/**
 * aceasta clasa contine metoda main in care vom apela interfata grafica
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException, IllegalAccessException {



		//Connection connection= ConnectionFactory.getConnection();
		new Management();


	}

}

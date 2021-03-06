package com.mcmproxibanque.testUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mcmproxibanque.config.MCMConfig;
import com.mcmproxibanque.dao.impl.CustomerDaoImpl;
import com.mcmproxibanque.dao.impl.EmployeeDaoImpl;
import com.mcmproxibanque.dao.interfaces.ICustomerDao;
import com.mcmproxibanque.model.Advisor;
import com.mcmproxibanque.model.CurrentAccount;
import com.mcmproxibanque.model.Customer;
import com.mcmproxibanque.model.SavingAccount;

@ContextConfiguration(classes=MCMConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class persistenceTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	ICustomerDao customerDao;

	@Autowired
	EmployeeDaoImpl employeeDao;

	@Test
	@Transactional
	public void testDaoCustomerImpl() throws Exception {
		Customer customer = new Customer();
		long countn1 = customerDao.count();
		customerDao.persist(customer);
		assertEquals(countn1 + 1, customerDao.count());
	}

	@Test
	@Transactional
	public void testAdvisorImpl() throws Exception {
		Advisor advisor = new Advisor();
		long countn1 = employeeDao.count();
		employeeDao.persist(advisor);
		assertEquals(countn1 + 1, employeeDao.count());
	}

	@Test
	@Transactional
	public void testAddCurrentAccountSaveAndGet() throws Exception {
		Customer customer = new Customer();
		customer.setCurrentAccount(new CurrentAccount());
		entityManager.persist(customer);
		entityManager.flush();

		entityManager.clear();
		Customer otherCustomer = (Customer) entityManager.find(Customer.class, customer.getId());
		assertNotNull(otherCustomer.getCurrentAccount());

	}

	@Test
	@Transactional
	public void testAddSavingAccountSaveAndGet() throws Exception {
		Customer customer = new Customer();
		customer.setSavingAccount(new SavingAccount());
		entityManager.persist(customer);
		entityManager.flush();

		entityManager.clear();
		Customer otherCustomer = (Customer) entityManager.find(Customer.class, customer.getId());
		assertNotNull(otherCustomer.getSavingAccount());

	}

	//
	// @PersistenceContext
	// private EntityManager entityManager;
	//
	//
	// @Autowired
	// OrdersDao orderDao;
	//
	// @Autowired
	// ItemsDao itemDao;
	//
	//
	//
	//
	// @Test
	// @Transactional
	// public void testDaoImpl() throws Exception {
	// Item item = new Item();
	// itemDao.persist(item);
	// assertEquals(1, itemDao.count());
	// }
	//
	// @Test
	// @Transactional
	// public void testDaoServices() throws Exception{
	// Order order = new Order();
	// order.getItems().add(new Item());
	// orderDao.persist(order);
	// assertEquals(1, orderDao.count());
	// }
	//
	// @Test
	// @Transactional
	// public void testSaveOrderWithItems() throws Exception {
	// Order order = new Order();
	// order.getItems().add(new Item());
	// entityManager.persist(order);
	// entityManager.flush();
	// assertNotNull(order.getId());
	// }
	//
	// @Test
	// @Transactional
	// public void testSaveAndGet() throws Exception {
	// Order order = new Order();
	// order.getItems().add(new Item());
	// entityManager.persist(order);
	// entityManager.flush();
	// // Otherwise the query returns the existing order (and we didn't set the
	// // parent in the item)...
	// entityManager.clear();
	// Order other = (Order) entityManager.find(Order.class, order.getId());
	// assertEquals(1, other.getItems().size());
	// assertEquals(other, other.getItems().iterator().next().getOrder());
	// }
	//
	// @Test
	// @Transactional
	// public void testSaveAndFind() throws Exception {
	// Order order = new Order();
	// Item item = new Item();
	// item.setProduct("foo");
	// order.getItems().add(item);
	// entityManager.persist(order);
	// entityManager.flush();
	// // Otherwise the query returns the existing order (and we didn't set the
	// // parent in the item)...
	// entityManager.clear();
	// Order other = (Order) entityManager
	// .createQuery(
	// "select o from Order o join o.items i where i.product=:product")
	// .setParameter("product", "foo").getSingleResult();
	// assertEquals(1, other.getItems().size());
	// assertEquals(other, other.getItems().iterator().next().getOrder());
	// }
	//

}

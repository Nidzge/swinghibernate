package pl.mirco;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import pl.mirco.model.Person;

public class Aplikacja {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure();

		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();
		SessionFactory sf = configuration.buildSessionFactory(sr);

		Session ss = sf.openSession();
		
		Person user1 = new Person();
		user1.setFirstName("Adam");
		user1.setLastName("Kowaliski");

		ss.beginTransaction();
		// saving objects to session
		ss.save(user1);
		// ss.save(user2);
		ss.getTransaction().commit();
		ss.close();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplikacja window = new Aplikacja();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplikacja() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 984, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

package customer;
import general.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Handles the payment aspect
 *
 * @author Hamza MOUDDENE
 * @version 1.0
 */
public class Payment extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Attributs of class
     */
    private JFrame window;
    private JLabel header, l1, l3, l4, l5, l6, footer;
    private JTextField t1, t2;
    private JButton abandon, pay;
    private JRadioButton creditCard, cash, here, takeOut;
    private ButtonGroup paymentMethod, place;
    @SuppressWarnings("rawtypes")
	private JComboBox table;
    private MenuSwing menu;
    String msgabd, msgdiscount;
    /**
     * Constructor of payment class
     *
     * @param menu of the user
     */
    public Payment(MenuSwing menu) {
        this.menu = menu;
        GUI payment = new GUI();
        JSONParser parser = new JSONParser();
		String paymsg = null  , Payment = null, msg1=null, msg2 ="", msg3="", msg4="", msg5="", credit ="",
				take="", aband="", paym ="";
		this.msgabd="";
		this.msgdiscount=""; 
		      

		String file = "data/langueEn.json";
      	        try {
        	if (menu.getLangue().equals("fr")) {
        		file = "data/langueFr.json";
        	}
                JSONArray lang = (JSONArray) parser.parse(new FileReader(file));

           	for (Object word : lang) {
				JSONObject msg = (JSONObject) word;
				paym = (String) msg.get("payment");
				paymsg = (String) msg.get("pay");
				Payment = (String) msg.get("Payment");
				msg1 = (String) msg.get("mssgpay1");
				msg2 = (String) msg.get("mssgpay2");
				msg3 = (String) msg.get("mssgpay3");
				msg4 = (String) msg.get("mssgpay4");
				msg5 = (String) msg.get("mssgpay5");
				credit = (String) msg.get("credit");
				take = (String) msg.get("takeout");
				msgabd = (String) msg.get("msgabd");
				aband = (String) msg.get("abandon");
				msgdiscount = (String) msg.get("msgdiscount");
				}
        } catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} catch (ParseException e) {
    			e.printStackTrace();
    		} catch (NumberFormatException e) {
    			System.out.print(true);
    		} catch (Exception e) {
		}
        this.window = payment.createWindow(paym, 600, 800);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.window.setLocation((screen.width / 2) - 300, (screen.height / 2) - 400);

        // HEADER
        this.header = payment.createLabel(Payment, 100, 50, 100, 50);
        this.header.setFont(new Font("Serif", Font.PLAIN, 18));
        this.window.add(this.header);

        // BODY
        this.l1 = payment.createLabel(msg1, 100, 110, 300, 30);
        this.window.add(this.l1);

        this.creditCard = payment.createRadioButton(credit, 100, 150, 150, 30);
        this.creditCard.addActionListener(this);
        this.cash = payment.createRadioButton("Cash", 250, 150, 100, 30);
        this.cash.addActionListener(this);
        this.paymentMethod = new ButtonGroup();
        this.paymentMethod.add(this.creditCard);
        this.paymentMethod.add(this.cash);
        window.add(this.cash);
        window.add(this.creditCard);

        this.l5 = payment.createLabel(msg2, 100, 190, 300, 30);
        this.window.add(this.l5);

        this.t1 = payment.createText(100, 230, 300, 30, 16);
        this.t1.setEditable(false);
        this.window.add(this.t1);

        this.l6 = payment.createLabel(msg3, 100, 270, 300, 30);
        this.window.add(this.l6);

        this.t2 = payment.createText(100, 310, 100, 30, 3);
        this.t2.setEditable(false);
        this.window.add(this.t2);

        this.here = payment.createRadioButton("Here", 100, 350, 150, 30);
        this.here.addActionListener(this);
        this.takeOut = payment.createRadioButton(take, 250, 350, 100, 30);
        this.takeOut.addActionListener(this);
        this.place = new ButtonGroup();
        this.place.add(this.here);
        this.place.add(this.takeOut);
        window.add(this.here);
        window.add(this.takeOut);

        this.l3 = payment.createLabel(msg4, 100, 390, 300, 30);
        this.window.add(this.l3);

        String[] list = new String[50];
        for (int i = 1; i <= 50; i++) {
            list[i - 1] = Integer.toString(i);
        }
        this.table = payment.createComboBox(list, 100, 430, 150, 30);
        this.table.setEnabled(false);
        this.window.add(this.table);

        this.l4 = payment.createLabel(msg5, 100, 470, 300, 50);
        this.window.add(this.l4);

        this.abandon = payment.createButton(aband, 350, 600, 100, 50, Color.RED, Color.WHITE);
        this.abandon.addActionListener(this);
        this.window.add(this.abandon);

        this.pay = payment.createButton(paymsg, 460, 600, 100, 50, new Color(76, 175, 80), Color.WHITE);
        this.pay.addActionListener(this);
        this.pay.setEnabled(false);
        this.window.add(this.pay);

        // FOOTER
        this.footer = payment.createLabel("E24 Restaurant 2020", 225, 700, 150, 50);
        this.window.add(this.footer);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.abandon) {
            int a = JOptionPane.showConfirmDialog(this.window, msgabd);
            if (a == JOptionPane.YES_OPTION) {
                this.window.setVisible(false);
            }
        } else if (e.getSource() == this.creditCard) {
            this.t1.setEditable(true);
            this.t2.setEditable(true);
        } else if (e.getSource() == this.cash) {
            this.t1.setEditable(false);
            this.t2.setEditable(false);
        } else if (e.getSource() == this.here) {
            this.table.setEnabled(true);
        } else if (e.getSource() == this.takeOut) {
            this.table.setEnabled(false);
        }
        if ((this.creditCard.isSelected() || this.cash.isSelected()) && (this.here.isSelected() || this.takeOut.isSelected())) {
            this.pay.setEnabled(true);
        }
        if (e.getSource() == this.pay) {
            this.window.setVisible(false);
            JSONParser parser = new JSONParser();
            if (!this.menu.getChoix().toStringCommandePAY().equals("")) {
                try {
                    JSONArray orderList = (JSONArray) parser.parse(new FileReader("data/orders.json"));
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    //Date of creation
                    Date date = new Date();
                    // new Order
                    JSONObject newOrder = new JSONObject();
                    newOrder.put("OrderNm", UUID.randomUUID().toString());
                    if (this.takeOut.isSelected()) {
                        newOrder.put("TableNm", "TAKEOUT");
                    } else if (this.here.isSelected()) {
                        String tmp = (String) this.table.getItemAt(this.table.getSelectedIndex());
                        if (Integer.parseInt(tmp) < 10) {
                            newOrder.put("TableNm", "N.0" + this.table.getItemAt(this.table.getSelectedIndex()));
                        } else {
                            newOrder.put("TableNm", "N." + this.table.getItemAt(this.table.getSelectedIndex()));
                        }
                    }
                    if (this.creditCard.isSelected()) {
                        newOrder.put("Payment", "Credit Card");
                    } else if (this.cash.isSelected()) {
                        newOrder.put("Payment", "Cash");
                    }
                    newOrder.put("Status", "In Progress");
                    newOrder.put("Order", this.menu.getChoix().toStringCommandePAY());
                    newOrder.put("Price", this.menu.getChoix().getPrixTotal() + " \u20AC");
                    newOrder.put("Customer", this.menu.getCustomer().getFirstName() + " " + this.menu.getCustomer().getLastName());
                    newOrder.put("Edits", "");
                    newOrder.put("Created", formatter.format(date));
                    newOrder.put("UserID", this.menu.getCustomer().getId());

                    // Add the new order to the order list after making its format more Human-readable
                    orderList.add(newOrder);

                    //Add the reward Points
                    int pt = (int) Math.floor(this.menu.getChoix().getPrixTotal());
                    RewardPoints rwd = new RewardPoints(this.menu.getCustomer().getId());
                    int pointinitial = rwd.getPoints();
                    rwd.addPoints(pt, this.menu.getCustomer().getId());
                    menu.getCustomer().addLoyaltyPoints(pt);
                    JSONParser parser1 = new JSONParser();
                    try {
                        JSONArray userList = (JSONArray) parser1.parse(new FileReader("data/users.json"));

                        for (Object user : userList) {
                            JSONObject usr = (JSONObject) user;
                            String UserID = (String) usr.get("UserID");
                            boolean IsCustomer = (((String) usr.get("Function")).equals("Null"));
                            if (IsCustomer) {
                                if (this.menu.getCustomer().getId().equals(UserID)) {
                                    usr.remove("Discount");
                                    usr.put("Discount", "No");
                                    if (((rwd.getPoints() % 1000) / 100) == ((pointinitial % 1000) / 100) + 1) {
                                        usr.remove("Discount");
                                        usr.put("Discount", "Yes");
                                        JOptionPane.showMessageDialog(window, msgdiscount);
                                    }

                                }

                            }
                        }
                        try (FileWriter file = new FileWriter("data/users.json")) {

                            file.write(userList.toJSONString());
                            file.flush();

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    } catch (ParseException e2) {
                        e2.printStackTrace();
                    }


                    // Write the new orderList to the JSON file
                    FileWriter file = new FileWriter("data/orders.json");

                    file.write(orderList.toJSONString());
                    file.flush();
                    file.close();

                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                } catch (IOException exception) {
                    exception.printStackTrace();
                } catch (ParseException exception) {
                    exception.printStackTrace();
                }
                this.menu.getChoix().clearMap();
                this.menu.getFenetre().dispose();
                new MenuSwing(this.menu.getMenu(), this.menu.getChoix(), this.menu.getCustomer(), this.menu.getLangue());
            }
        }
    }
}

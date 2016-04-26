import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;


public class stockBoard extends JFrame {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 800;
	
	private JFrame panelFrame;

	public static JButton scrollButton;
	
	private int action;
	private int direction;
	private int strat;
	
	private JLabel stockSymbol;
	private JLabel currPrice;
	private JLabel tradeDate;
	private JLabel tradeTime;
	private JLabel openTime;
	private JLabel dayHigh;
	private JLabel dayLow;
	private JLabel closePrice;
	private JLabel tradeVolume;
	private JLabel companyName;
	
	private JToggleButton strat1;
	private JToggleButton strat2;
	private JToggleButton strat3;
	private JToggleButton strat4;
	
	private JButton nextButton;
	private JButton backButton;
	private JButton buyButton;
	private JButton sellButton;
	private JButton holdButton;
	
	private JTextArea stratAdvise;
	
	public stockBoard()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		
		//Initialize button for loading stocks you've seen.
		backButton = new JButton();
		backButton.setText("Back");
		backButton.setBackground(Color.black);
		backButton.setForeground(Color.white);
		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0){
				//Goes back to the last stock info.
				direction = 1;
			}
		});
		
		//Initialize button for loading in new stocks
		nextButton = new JButton();
		nextButton.setText("Next");
		nextButton.setBackground(Color.black);
		nextButton.setForeground(Color.white);
		nextButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent moveAction){
				//Loads in the next stock info.
				direction = 2;
			}
		});
		
		buyButton = new JButton();
		buyButton.setText("Buy");
		buyButton.setBackground(Color.black);
		buyButton.setForeground(Color.white);
		buyButton.setSize(20,10);
		buyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				action = 1;
			}
		});
		
		sellButton = new JButton();
		sellButton.setText("Sell");
		sellButton.setBackground(Color.black);
		sellButton.setForeground(Color.white);
		sellButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				action = 2;
			}
		});
		
		holdButton =  new JButton();
		holdButton.setText("Hold");
		holdButton.setBackground(Color.black);
		holdButton.setForeground(Color.white);
		holdButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				action = 3;
			}
		});
		
		strat1 = new JToggleButton();
		strat1.setText("Strategy 1");
		strat1.setBackground(Color.black);
		strat1.setForeground(Color.white);
		strat1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				strat = 1;
			}
		});
		
		strat2 = new JToggleButton();
		strat2.setText("Strategy 2");
		strat2.setBackground(Color.black);
		strat2.setForeground(Color.white);
		strat2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				strat = 2;
			}
		});
		
		strat3 = new JToggleButton();
		strat3.setText("Strategy 3");
		strat3.setBackground(Color.black);
		strat3.setForeground(Color.white);
		strat3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				strat = 3;
			}
		});
		
		strat4 = new JToggleButton();
		strat4.setText("Strategy 4");
		strat4.setBackground(Color.black);
		strat4.setForeground(Color.white);
		strat4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				strat = 4;
			}
		});
		
		//Makes sure only one can be activated at one time
		ButtonGroup group = new ButtonGroup();
		group.add(strat1);
		group.add(strat2);
		group.add(strat3);
		group.add(strat4);
		
		Font font = new Font("Times New Roman", Font.BOLD, 24);
		
		JLabel Whitespace = new JLabel(" ");
		JLabel stock = new JLabel("88", SwingConstants.LEFT);
		stock.setFont(font);
		JLabel nowPrice = new JLabel("88", SwingConstants.LEFT);
		nowPrice.setFont(font);
		JLabel dayTime = new JLabel("88", SwingConstants.LEFT);
		dayTime.setFont(font);
		JLabel timeTrade = new JLabel("88", SwingConstants.LEFT);
		timeTrade.setFont(font);
		JLabel timeOpen = new JLabel("88", SwingConstants.LEFT);
		timeOpen.setFont(font);
		JLabel highest = new JLabel("88", SwingConstants.LEFT);
		highest.setFont(font);
		JLabel lowest = new JLabel("88", SwingConstants.LEFT);
		lowest.setFont(font);
		JLabel priceClose = new JLabel("88", SwingConstants.LEFT);
		priceClose.setFont(font);
		JLabel volTrade = new JLabel("88", SwingConstants.LEFT);
		volTrade.setFont(font);
		JLabel name = new JLabel("88", SwingConstants.LEFT);
		name.setFont(font);
		
		stockSymbol = new JLabel(("Symbol: "), SwingConstants.LEFT);
		stockSymbol.setFont(font);
		currPrice = new JLabel(("Current Price: "), SwingConstants.LEFT);
		currPrice.setFont(font);
		tradeDate = new JLabel(("Day of Trade: "), SwingConstants.LEFT);
		tradeDate.setFont(font);
		tradeTime = new JLabel(("Time of trade: "), SwingConstants.LEFT);
		tradeTime.setFont(font);
		openTime = new JLabel(("Time of open: "), SwingConstants.LEFT);
		openTime.setFont(font);
		dayHigh = new JLabel(("Highest value: "), SwingConstants.LEFT);
		dayHigh.setFont(font);
		dayLow = new JLabel(("Lowest value: "), SwingConstants.LEFT);
		dayLow.setFont(font);
		closePrice = new JLabel(("Close price: "), SwingConstants.LEFT);
		closePrice.setFont(font);
		tradeVolume = new JLabel(("Total trades: "), SwingConstants.LEFT);
		tradeVolume.setFont(font);
		companyName = new JLabel(("Company: "), SwingConstants.LEFT);
		companyName.setFont(font);
		
		JPanel Panel = new JPanel();
		Panel.setBackground(Color.white);
		Panel.setLayout(new GridLayout(30,30));

		JFrame frame = new JFrame();
		frame.setContentPane(Panel);
		frame.setSize(800,1000);
		frame.setVisible(true);
		
		Panel.add(backButton, BorderLayout.CENTER);
		Panel.add(nextButton);
		
		
		Panel.add(strat1);
		Panel.add(strat2);
		Panel.add(strat3);
		Panel.add(strat4);
		
		Panel.add(stockSymbol);	
		Panel.add(stock);
		Panel.add(Whitespace);
		Panel.add(currPrice);
		Panel.add(nowPrice);
		Panel.add(Whitespace);
		Panel.add(tradeDate);
		Panel.add(dayTime);	
		Panel.add(Whitespace);
		Panel.add(tradeTime);	
		Panel.add(timeTrade);
		Panel.add(Whitespace);
		Panel.add(openTime);		
		Panel.add(timeOpen);
		Panel.add(Whitespace);
		Panel.add(dayHigh);	
		Panel.add(highest);	
		Panel.add(Whitespace);
		Panel.add(dayLow);
		Panel.add(lowest);
		Panel.add(Whitespace);
		Panel.add(closePrice);
		Panel.add(priceClose);
		Panel.add(Whitespace);
		Panel.add(tradeVolume);
		Panel.add(volTrade);
		Panel.add(Whitespace);
		Panel.add(companyName);
		Panel.add(name);
		
		Panel.add(buyButton);
		Panel.add(sellButton);
		Panel.add(holdButton);
		
		Panel.setVisible(true);
		Panel.setSize(WIDTH, HEIGHT);
	}
	
	public static void main(String [] args)
	{
		stockBoard stock = new stockBoard();
	}
}

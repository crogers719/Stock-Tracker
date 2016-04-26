import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class GUI extends JFrame{
	private String[]symbolNameHeader={"Stock Symbols"};
	private int[]selectedRows;
	
	private ArrayList<Integer> stockIndex=new ArrayList<Integer>();
	Object[][] addedList;
	String [] getColumns;
	
	Strategy strategy=new BuyLow();
	JToggleButton buyLowButton;
	JToggleButton buyIncreaseButton;
	JToggleButton buyRandomButton;
	JToggleButton buyCustomButton;
	
	private boolean strategySelected = false;
	ArrayList<Object> advice = new ArrayList<Object>();
	JPanel stockListPanel1;
	JPanel strategyPanel;
	JPanel stockDisplayPanel;
	JTable stockTable;
	JTable symbolTable;
	JScrollPane displayScrollPane;
	JScrollPane stockScrollPane;
	JButton stockButton = new JButton ("Select Stock");
	
	DefaultTableModel stockModel;
	
	GUI(Object[][] objects, String[] getColumnLabels){
		addedList=objects;
		getColumns=getColumnLabels;
		init (addedList, getColumns);
	}

	public void init(final Object[][] addedList2, final String[]getColumns ){
		stockListPanel1=new JPanel();
		strategyPanel=new JPanel();
		stockDisplayPanel=new JPanel();
		
		//Strategy Radio Buttons
		buyLowButton = new JRadioButton("Buy low");
		buyIncreaseButton=new JRadioButton("Buy when price rises");
		buyRandomButton= new JRadioButton("Buy Random");
		buyCustomButton=new JRadioButton("Buy Custom");
		
		buyLowButton.setEnabled(false);
		buyIncreaseButton.setEnabled(false);
		buyRandomButton.setEnabled(false);
		buyCustomButton.setEnabled(false);
		
		ButtonGroup stratButtonGroup = new ButtonGroup();
		stratButtonGroup.add(buyLowButton);
		stratButtonGroup.add(buyIncreaseButton);
		stratButtonGroup.add(buyRandomButton);
		stratButtonGroup.add(buyCustomButton);
		
		buyLowButton.setActionCommand("b_low");
		buyLowButton.setSelected(true);
		buyIncreaseButton.setActionCommand("b_inc");
		buyRandomButton.setActionCommand("b_rand");
		buyCustomButton.setActionCommand("b_cust");
		
		stockTable=new JTable();
		stockModel=(DefaultTableModel) stockTable.getModel();
		for (int i=0; i<getColumns.length;i++){
			stockModel.addColumn(getColumns[i]);
		}
		
		symbolTable = new JTable (addedList2, symbolNameHeader);
		displayScrollPane=new JScrollPane(stockTable);
		stockScrollPane = new JScrollPane(symbolTable);
		
		stockListPanel1.add(stockScrollPane);
		stockDisplayPanel.add(displayScrollPane);
		
		stockListPanel1.setBorder(BorderFactory.createTitledBorder(null, "Stock List Panel", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));		
		strategyPanel.setBorder(BorderFactory.createTitledBorder(null, "Strategy Panel", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		stockDisplayPanel.setBorder(BorderFactory.createTitledBorder(null, "Stock Display Panel", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		
		displayScrollPane.setPreferredSize(new Dimension(980,160));
		
		stockButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				boolean check= true;
				selectedRows=symbolTable.getSelectedRows();
				
				if (selectedRows.length>0){
					buyLowButton.setEnabled(true);
					buyIncreaseButton.setEnabled(true);
					buyRandomButton.setEnabled(true);
					buyCustomButton.setEnabled(true);
					
				}
				for(int i=0; i<selectedRows.length;i++){
					for (int j=0; j<stockIndex.size();j++){
						if(stockIndex.get(j)==selectedRows[i]){
							check=false;
					
						}
					}
				}
				if (check){
					for (int i=0;i<selectedRows.length; i++){
						stockModel.addRow(addedList2[selectedRows[i]]);
						stockIndex.add(selectedRows[i]);
					}
					strategySelected=true;
					advice=notifyStrategy(stockIndex);
					for(int i=0;i<stockIndex.size();i++){
						stockModel.setValueAt(advice.get(i), i, 11);
					}
					
				}
				
			}
			
		});
		ActionListener myListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getActionCommand()=="b_low"){
					setStrategy(new BuyLow());
				}
				else if (e.getActionCommand()=="b_inc"){
					setStrategy(new BuyIncrease());
				}
				else if(e.getActionCommand()=="b_rand"){
					setStrategy(new BuyRandom());
				}
				else{
					//setStrategy(new BuyCustom());
				}
				
			}

		
		};
		buyLowButton.addActionListener(myListener);
		buyIncreaseButton.addActionListener(myListener);
		buyRandomButton.addActionListener(myListener);
		buyCustomButton.addActionListener(myListener);
		
		strategyPanel.add(buyLowButton);
		strategyPanel.add(buyIncreaseButton);
		strategyPanel.add(buyRandomButton);
		strategyPanel.add(buyCustomButton);
		
		GroupLayout stratPanelLayout=new GroupLayout(strategyPanel);
		strategyPanel.setLayout(stratPanelLayout);
        stratPanelLayout.setHorizontalGroup(
            stratPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(stratPanelLayout.createSequentialGroup()
                .addGroup(stratPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buyCustomButton)
                    .addComponent(buyRandomButton)
                    .addComponent(buyIncreaseButton)
                    .addComponent(buyLowButton))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        stratPanelLayout.setVerticalGroup(
            stratPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(stratPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(buyLowButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buyIncreaseButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buyRandomButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buyCustomButton)
                .addContainerGap(30, Short.MAX_VALUE))
        );

	
	GroupLayout layout = new GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	
	
	layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            		.addGap(20,200,200)
                .addComponent(stockScrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockButton)
                .addGap(20,20,20)
                .addComponent(strategyPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                )
            .addComponent(stockDisplayPanel, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
	
	layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	            		.addContainerGap(23, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)	
	                            .addComponent(strategyPanel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
	                            .addComponent(stockScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
	                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addGap(0,150, 1000)
	                        .addComponent(stockButton)
	                        .addGap(0, 5, 1000)))
	                .addComponent(stockDisplayPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
	        );

	
	setResizable(false);
	pack();
	
	}
	public void setStrategy(Strategy s){
		strategy = s;
	}
	
	public ArrayList<Object> notifyStrategy(ArrayList<Integer> stockIndex){
		advice.clear();
		for(int i = 0; i < stockIndex.size(); i++){
			advice.add(strategy.getRecommendation(stockIndex.get(i)));
			
		}
		
		return advice;
	}
	
	public void refreshTable(Object[][] stockList){
		for(int i = 0; i < stockIndex.size(); i++){
			//changed from Excel.totalcols to 11????
			for(int j = 0; j < 11; j++){
				stockModel.setValueAt(stockList[stockIndex.get(i)][j], i, j);
				
			}
		}
		
		if(strategySelected){
			advice = notifyStrategy(stockIndex);
			for(int i = 0; i < stockIndex.size(); i++){
				stockModel.setValueAt(advice.get(i), i, 11);
				
				
				
			}
		}
		
		super.repaint();
		super.revalidate();
			
	}
		
}

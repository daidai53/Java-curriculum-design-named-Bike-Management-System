package materialstore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class Search extends JFrame {

	private JPanel Details;
	private JTextField mNumInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Search() {
		setTitle("\u914D\u4EF6\u5E93\u5B58\u67E5\u8BE2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1053, 832);
		Details = new JPanel();
		Details.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Details);
		Details.setLayout(new BorderLayout(0, 0));
		
		JPanel searchDetails = new JPanel();
		searchDetails.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Details.add(searchDetails, BorderLayout.WEST);
		searchDetails.setLayout(new BorderLayout(0, 0));
		
		JLabel nullLeft = new JLabel("          ");
		searchDetails.add(nullLeft, BorderLayout.WEST);
		
		JLabel nullRight = new JLabel("          ");
		searchDetails.add(nullRight, BorderLayout.EAST);
		
		JPanel searchFunction = new JPanel();
		searchDetails.add(searchFunction, BorderLayout.CENTER);
		GridBagLayout gbl_searchFunction = new GridBagLayout();
		gbl_searchFunction.columnWidths = new int[] {0, 0, 0};
		gbl_searchFunction.rowHeights = new int[]{90, 16, 21, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_searchFunction.columnWeights = new double[]{1.0, 0.0, 0.0};
		gbl_searchFunction.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		searchFunction.setLayout(gbl_searchFunction);
		
		JLabel innerLeft = new JLabel("   ");
		GridBagConstraints gbc_innerLeft = new GridBagConstraints();
		gbc_innerLeft.insets = new Insets(0, 0, 5, 5);
		gbc_innerLeft.gridx = 0;
		gbc_innerLeft.gridy = 1;
		searchFunction.add(innerLeft, gbc_innerLeft);
		
		JLabel innerRight = new JLabel("   ");
		GridBagConstraints gbc_innerRight = new GridBagConstraints();
		gbc_innerRight.insets = new Insets(0, 0, 5, 0);
		gbc_innerRight.gridx = 2;
		gbc_innerRight.gridy = 1;
		searchFunction.add(innerRight, gbc_innerRight);
		
		JLabel mNum = new JLabel("\u914D\u4EF6\u7F16\u53F7");
		mNum.setFont(new Font("¿¬Ìå", Font.PLAIN, 14));
		GridBagConstraints gbc_mNum = new GridBagConstraints();
		gbc_mNum.insets = new Insets(0, 0, 5, 5);
		gbc_mNum.gridx = 1;
		gbc_mNum.gridy = 7;
		searchFunction.add(mNum, gbc_mNum);
		
		mNumInput = new JTextField();
		GridBagConstraints gbc_mNumInput = new GridBagConstraints();
		gbc_mNumInput.insets = new Insets(0, 0, 5, 5);
		gbc_mNumInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_mNumInput.gridx = 1;
		gbc_mNumInput.gridy = 8;
		searchFunction.add(mNumInput, gbc_mNumInput);
		mNumInput.setColumns(10);
		
		JLabel rowGab = new JLabel("               ");
		GridBagConstraints gbc_rowGab = new GridBagConstraints();
		gbc_rowGab.insets = new Insets(0, 0, 5, 5);
		gbc_rowGab.gridx = 1;
		gbc_rowGab.gridy = 9;
		searchFunction.add(rowGab, gbc_rowGab);
		
		JButton search = new JButton("\u67E5\u8BE2");
		search.setFont(new Font("¿¬Ìå", Font.PLAIN, 14));
		GridBagConstraints gbc_search = new GridBagConstraints();
		gbc_search.insets = new Insets(0, 0, 5, 5);
		gbc_search.gridx = 1;
		gbc_search.gridy = 10;
		searchFunction.add(search, gbc_search);
		
		JLabel nullBottom = new JLabel("");
		GridBagConstraints gbc_nullBottom = new GridBagConstraints();
		gbc_nullBottom.gridheight = 6;
		gbc_nullBottom.insets = new Insets(0, 0, 0, 5);
		gbc_nullBottom.gridx = 1;
		gbc_nullBottom.gridy = 12;
		searchFunction.add(nullBottom, gbc_nullBottom);
		
		JScrollPane scrollPane = new JScrollPane();
		Details.add(scrollPane, BorderLayout.CENTER);
	}

}

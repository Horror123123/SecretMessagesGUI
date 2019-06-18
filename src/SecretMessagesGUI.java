import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
// import javax.xml.ws.handler.MessageContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SecretMessagesGUI extends JFrame {

	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private String message, sMessage;
	private int iKey;
	//String playAgain = ""; //$NON-NLS-1$

	public String encode(String message, int keyVal) {
		String output = ""; //$NON-NLS-1$

		char key = (char) keyVal;
		//char key1 = (char) keyVal;

		for (int x = 0; x < message.length(); x++) {
			//tRier++;
			char input = message.charAt(x);
			if (input >= 'А' && input <= 'Я') {
				input += key;
				if (input > 'Я')
					input -= 32;
				if (input < 'А')
					input += 32;
			} else if (input >= 'а' && input <= 'я') {
				input += key;
				if (input > 'я')
					input -= 32;
				if (input < 'а')
					input += 32;
			} else if (input >= '0' && input <= '9') {
				//key1 += input;
				input += (keyVal % 10);
				if (input > '9')
					input -= 10;
				if (input < '0')
					input += 10;
			} //English

			if (input >= 'A' && input <= 'Z') {
				input += key;
				if (input > 'Z')
					input -= 26;
				if (input < 'A')
					input += 26;
			} else if (input >= 'a' && input <= 'z') {
				input += key;
				if (input > 'z')
					input -= 26;
				if (input < 'a')
					input += 26;						

			} else if (Character.isWhitespace(input)) {
				input = '\u0459';						
			} else if (input == '\u0459') {
				input = ' ';
			} else if (input == ',') {
				input = '\u045A';
			} else if (input == '\u045A') {
				input = ',';
			} output += input;

		} 

		return output;			
	}

	public SecretMessagesGUI() {		
		setSize(new Dimension(600, 400));
		setTitle("\u0410\u043B\u0435\u043A\u0441\u0430\u043D\u0434\u0440 \u0415\u0440\u0451\u043C\u0438\u043D - Secret Message App"); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		txtIn = new JTextArea();
		txtIn.setFont(new Font("Arial", Font.PLAIN, 16)); //$NON-NLS-1$
		txtIn.setLineWrap(true);
		txtIn.setSize(new Dimension(564, 140));
		txtIn.setBounds(12, 13, 558, 121);
		getContentPane().add(txtIn);

		txtOut = new JTextArea();
		txtOut.setFont(new Font("Arial", Font.PLAIN, 16)); //$NON-NLS-1$
		txtOut.setLineWrap(true);
		txtOut.setSize(new Dimension(564, 140));
		txtOut.setBounds(12, 219, 558, 121);
		getContentPane().add(txtOut);

		txtKey = new JTextField();
		txtKey.setText("0"); //$NON-NLS-1$
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setBounds(276, 165, 56, 22);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);

		JLabel lblNewLabel = new JLabel("Key:  "); //$NON-NLS-1$
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(236, 168, 43, 16);
		getContentPane().add(lblNewLabel);

		JButton btnCodeEncode = new JButton("Encode/Decode"); //$NON-NLS-1$
		btnCodeEncode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (iKey >= 0) {
					message = txtIn.getText();
				}
				while (message.length() == 0) {
					sMessage = "Введите текст для шифровки/расшифровки"; //$NON-NLS-1$
					// Всплывающее окно
					JOptionPane.showMessageDialog(null, 
							sMessage,  
							"Внимание!", //$NON-NLS-1$
							JOptionPane.WARNING_MESSAGE);					
					message = " "; //$NON-NLS-1$
				}
				
				try {
					if (iKey >= 0) {
						iKey = Integer.parseInt(txtKey.getText());
					}
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();					
					sMessage = "Введите зачение ключа  -25 до 25"; //$NON-NLS-1$
					// Всплывающее окно
					JOptionPane.showMessageDialog(null, 
							sMessage,  
							"Внимание!", //$NON-NLS-1$
							JOptionPane.WARNING_MESSAGE);					
				}
				
				String result = encode(message, iKey);
				txtOut.setText(result);
				txtKey.setText(Integer.toString(iKey * -1));
				/*sMessage = message + " " + iKey ; //$NON-NLS-1$
				// Всплывающее окно
				JOptionPane.showMessageDialog(null, 
						sMessage,  
						"Внимание!", //$NON-NLS-1$
						JOptionPane.WARNING_MESSAGE);*/
				message = txtOut.getText();
				iKey = iKey * -1;
			}
		});

		btnCodeEncode.setBounds(345, 164, 138, 25);
		getContentPane().add(btnCodeEncode);		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecretMessagesGUI smStart = new SecretMessagesGUI();
		smStart.setLocationRelativeTo(null);
		smStart.setVisible(true);

	}


}

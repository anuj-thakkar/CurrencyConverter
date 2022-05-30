import javax.swing.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class MainPage extends JFrame implements ActionListener {

    HashMap<String, String> currencies;

    // Container for the MainPage
    Container content = getContentPane();


    // Labels for text field and text field objects
    JLabel fromLabel;
    JLabel toLabel;
    JTextField fromTextField;

    JComboBox fromComboBox;
    JComboBox toComboBox;

    //The buttons that allow you to convert the currencies
    JButton convertButton;

    ObjectOutputStream oos;
    ObjectInputStream ois;
    ConverterClient client;

    /**
     * This function maps all String words in array with its respective symbol
     *
     * @param currencyWord
     * @return
     */
    public HashMap<String, String> setCurrencySymbols(String[] currencyWord) {

        HashMap<String, String> currencySymbols = new HashMap<>();
        currencySymbols.put(currencyWord[0], "USD");
        currencySymbols.put(currencyWord[1], "INR");
        currencySymbols.put(currencyWord[2], "GBP");
        currencySymbols.put(currencyWord[3], "EUR");
        currencySymbols.put(currencyWord[4], "CAD");
        currencySymbols.put(currencyWord[5], "AED");
        currencySymbols.put(currencyWord[6], "CNY");
        currencySymbols.put(currencyWord[7], "AFN");
        currencySymbols.put(currencyWord[8], "AUD");
        currencySymbols.put(currencyWord[9], "AWG");
        currencySymbols.put(currencyWord[10], "BTC");
        currencySymbols.put(currencyWord[11], "CHF");
        currencySymbols.put(currencyWord[12], "HKD");
        currencySymbols.put(currencyWord[13], "JPY");
        currencySymbols.put(currencyWord[14], "MXN");
        currencySymbols.put(currencyWord[15], "PKR");
        currencySymbols.put(currencyWord[16], "RUB");
        currencySymbols.put(currencyWord[17], "SEK");
        return currencySymbols;

    }

    public MainPage() {

        this.client = client;

        String[] currencyWord ={"US Dollar","Indian Rupee","British Pound","Euro",
                "Canadian Dollar","United Arab Emirates Dirham","Chinese Yuan", "Afghan Afghani",
                "Australian Dollar", "Aruban Florin", "Bitcoin", "Swiss Franc", "Hong Kong Dollar",
                "Japanese Yen", "Mexican Peso", "Pakistani Rupee", "Russian Ruble", "Swedish Krona"};

        // creates a map of all currency words with its respective symbol
        this.currencies = setCurrencySymbols(currencyWord);

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel fromPanel = new JPanel();
        fromLabel = new JLabel("From: ");
        fromTextField = new JTextField(10);
        fromComboBox = new JComboBox(currencyWord);
        fromComboBox.setSelectedIndex(0); // default option (USD)
        fromPanel.add(fromLabel);
        fromPanel.add(fromTextField);
        fromPanel.add(fromComboBox);
        content.add(fromPanel);


        JPanel toPanel = new JPanel();
        toLabel = new JLabel("To: ");
        toComboBox = new JComboBox(currencyWord);
        toComboBox.setSelectedIndex(1); // Converts to next item in currency array
        toPanel.add(toLabel);
        toPanel.add(toComboBox);
        content.add(toPanel);

        convertButton = new JButton("Convert");
        content.add(convertButton);

        convertButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
        String title = "Currency Converter";

        if (e.getSource() == convertButton && !fromTextField.getText().isEmpty()) {

            try {
                String fromAmount = fromTextField.getText();
                String fromCurrency = fromComboBox.getSelectedItem().toString();
                String fromCurrencySymbol = currencies.get(fromCurrency);
                String toCurrency = toComboBox.getSelectedItem().toString();
                String toCurrencySymbol = currencies.get(toCurrency);

                String request = ("from: " + fromAmount + " " +
                        fromCurrencySymbol + ", " + "to: " + toCurrencySymbol);
                System.out.println(request);
                client = new ConverterClient(toCurrencySymbol, fromCurrencySymbol, fromAmount);

                JOptionPane pane = new JOptionPane();
                pane.showMessageDialog( null, "Thanks for using the converter!",
                        title, JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        } else if (e.getSource() == convertButton && fromTextField.getText().isEmpty()) {
            JOptionPane pane = new JOptionPane();
            pane.showMessageDialog( null,"Please enter an amount!1",
                    title, JOptionPane.ERROR_MESSAGE);
        }
    }
}


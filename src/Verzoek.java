import javax.management.Query;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Verzoek extends JPanel implements ActionListener {

    JTextArea queryTextArea = new JTextArea(20,30);
    JButton run = new JButton("Run");
    JTextArea result = new JTextArea(20,30);
    ApiController apiController = new ApiController();
    public Verzoek(){
        add(queryTextArea);
        add(run);
        run.addActionListener(this);
        result.setEnabled(false);
        add(result);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        result.setText(apiController.runJsonQuery(queryTextArea.getText()));

    }


}

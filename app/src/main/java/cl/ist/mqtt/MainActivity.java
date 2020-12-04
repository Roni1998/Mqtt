package cl.ist.mqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MainActivity extends AppCompatActivity {


    // static String MQTTHOST = "tcp://209.126.106.184:1883";
    static String MQTTHOST = "tcp://mqtt.eclipse.org:1883 ";

    static String USERNAME = "";
    static String PASSWORD = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client = new MqttAndroidClient(this.getApplicationContext(),MQTTHOST, clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        //options.setUserName(USERNAME);
        //options.setPassword(PASSWORD.toCharArray());
        try {


            final IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this, "Conexión Establecida!", Toast.LENGTH_LONG).show();
                    //setSubscription();
                    String topic = "ipcftst/ChillanAndroid/Alumno";
                    String message = "apagar";

                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this, "Conexión Fallida!", Toast.LENGTH_LONG).show();
                }});
        } catch (MqttException e) {
            e.printStackTrace();
        }

        try {
            client.publish("MqttSanto", "hola".getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }


    }
}
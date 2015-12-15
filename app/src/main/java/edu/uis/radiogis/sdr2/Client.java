package edu.uis.radiogis.sdr2;

import android.app.AlertDialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Client extends AppCompatActivity {

    public Handler mHandler;
    private Socket socket;
    private Handler handler;
    private static final int SERVERPORT = 9999;
    private static final String SERVER_IP = "192.168.1.104";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        handler = new Handler();
        //TODO:verificar si la delclaracion de estos botones es necesaria
        Button frb;
        Button spanb;
        Button gananciab;
        Button ventanab;
        Button baseb;
        Button escalab;
        frb = (Button) findViewById(R.id.frb);
        spanb = (Button) findViewById(R.id.spanb);
        gananciab = (Button) findViewById(R.id.gananciab);
        ventanab = (Button) findViewById(R.id.ventanab);
        baseb = (Button) findViewById(R.id.baseb);
        escalab = (Button) findViewById(R.id.escalab);

        //se llenan los campos tipo spinner de la lista
        Spinner ventana = (Spinner) findViewById(R.id.ventana);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ventana_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ventana.setAdapter(adapter);
        Spinner base = (Spinner) findViewById(R.id.base);
        ArrayAdapter<CharSequence> adapterb = ArrayAdapter.createFromResource(this, R.array.base_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        base.setAdapter(adapterb);
        Spinner escala = (Spinner) findViewById(R.id.escala);
        ArrayAdapter<CharSequence> adaptere = ArrayAdapter.createFromResource(this, R.array.escala_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        escala.setAdapter(adaptere);

        //se inicia tarea en segundo plano, esta controla la conexion de el socket
        new Thread(new ClientThread()).start();


    }


    // metodos correspondientes a el envio mediante un mensaje a la tarea secundaria de el parametro a cambiar.
    public void fr(View view) {
            EditText et = (EditText) findViewById(R.id.fr);
            String str = "{\"fc\":"+et.getText().toString()+"}";
            Message msg = Message.obtain();
            msg.obj =  str;
            mHandler.sendMessage(msg);

    }

    public void span(View view) {

        EditText et = (EditText) findViewById(R.id.span);
        String str = "{\"ab\":"+et.getText().toString()+"}";
        Message msg1 = Message.obtain();
        msg1.obj =  str;
        mHandler.sendMessage(msg1);

    }

    public void ganancia(View view) {

        EditText et = (EditText) findViewById(R.id.ganancia);
        String str = "{\"gan\":"+et.getText().toString()+"}";
        Message msg2 = Message.obtain();
        msg2.obj =  str;
        mHandler.sendMessage(msg2);
    }

    public void ventana(View view) {

        Spinner et = (Spinner) findViewById(R.id.ventana);
        String str = "{\"ventana\":\""+et.getSelectedItem().toString()+"\"}";
        Message msg3 = Message.obtain();
        msg3.obj =  str;
        mHandler.sendMessage(msg3);

    }

    public void base(View view) {

            Spinner et = (Spinner) findViewById(R.id.base);
            String str = "{\"base\":\""+et.getSelectedItem().toString()+"\"}";
            Message msg4 = Message.obtain();
            msg4.obj =  str;
            mHandler.sendMessage(msg4);

    }

    public void escala(View view) {

            Spinner et = (Spinner) findViewById(R.id.escala);
        //TODO: cambiar escala por la palabra apropiada 
            String str = "{\"escala\":\""+et.getSelectedItem().toString()+"\"}";
            Message msg5 = Message.obtain();
            msg5.obj =  str;
            mHandler.sendMessage(msg5);

    }




    // esta clase es la tarea en segundo plano encargada de la conexion y envio de mensajes
    class ClientThread implements Runnable {
        @Override
        public void run() {
                Looper.prepare();
                mHandler = new Handler() {
                    public void handleMessage(Message msg) {
                        // Act on the message
                        InetAddress serverAddr = null;
                        try {
                            serverAddr = InetAddress.getByName(SERVER_IP);
                            socket = new Socket(serverAddr, SERVERPORT);
                            String str = (String)msg.obj;
                            Log.d("run","conexion acertada"+str);
                            PrintWriter out = new PrintWriter(new BufferedWriter(
                                    new OutputStreamWriter(socket.getOutputStream())),
                                    true);
                            out.println(str);
                            socket.close();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                };
                Looper.loop();

        }

    }


}
package edu.uis.radiogis.sdr2;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Client extends AppCompatActivity {

    private Socket socket;
    private static final int SERVERPORT = 9999;
    private static final String SERVER_IP = "192.168.1.103";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
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

        // se establece conexcion con el cliente
        new Thread(new ClientThread()).start();

    }

    public void fr(View view) {
        try {
            EditText et = (EditText) findViewById(R.id.fr);
            String str = "{'fr:'"+et.getText().toString()+"}";
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void spanb(View view) {
        try {
            EditText et = (EditText) findViewById(R.id.span);
            String str = "{'sp:'"+et.getText().toString()+"}";
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gananciab(View view) {
        try {
            EditText et = (EditText) findViewById(R.id.ganancia);
            String str = "{'gan:'"+et.getText().toString()+"}";
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ventanab(View view) {
        try {
            Spinner et = (Spinner) findViewById(R.id.ventana);
            String str = "{'ven:'"+et.getSelectedItem().toString()+"}";
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void baseb(View view) {
        try {
            Spinner et = (Spinner) findViewById(R.id.base);
            String str = "{'ab:'"+et.getSelectedItem().toString()+"}";
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escalab(View view) {
        try {
            Spinner et = (Spinner) findViewById(R.id.escala);
            String str = "{'es:'"+et.getSelectedItem().toString()+"}";
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(View view) {
        Thread close = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (socket.isConnected()) {
                        socket.close();
                    }
                    else
                    {}
                } catch (SocketException se) {
                    se.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }



    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

                socket = new Socket();
                socket.connect(new InetSocketAddress(serverAddr, SERVERPORT), 10000);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }


}
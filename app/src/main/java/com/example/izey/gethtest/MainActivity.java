package com.example.izey.gethtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import org.ethereum.geth.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Android In-Process Node");
        final TextView textbox = (TextView) findViewById(R.id.textbox);

        Context ctx = new Context();
        Log.d("GETH",getFilesDir() + "/.ethereum");
        try {
            NodeConfig nc = new NodeConfig();

            Node node = Geth.newNode(getFilesDir() + "/.ethereum", nc);
            node.start();

            NodeInfo info = node.getNodeInfo();
            textbox.append("My name: " + info.getName() + "\n");
            textbox.append("My address: " + info.getListenerAddress() + "\n");
            textbox.append("My protocols: " + info.getProtocols() + "\n\n");

            EthereumClient ec = node.getEthereumClient();
            textbox.append("Latest block: " + ec.getBlockByNumber(ctx, -1).getNumber() + ", syncing...\n");

//            NewHeadHandler handler = new NewHeadHandler() {
//                @Override public void onError(String error) { }
//                @Override public void onNewHead(final Header header) {
//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        public void run() { textbox.append("#" /*+ header.getNumber() + ": " + header.getHash().getHex().substring(0, 10) + "…\n"*/); }
//                    });
//                }
//            };
//            ec.subscribeNewHead(ctx, handler,  16);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

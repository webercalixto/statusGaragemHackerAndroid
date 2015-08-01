package br.com.selfwork.www.statusgaragemhacker;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends Activity {
    TextView estado;
    thStatus status = new thStatus();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        estado=(TextView)findViewById(R.id.estado);
        estado.setText("Perguntao ao garagem se está aberto....");
        status.execute("");

    }


    private class thStatus extends AsyncTask<String, Void, String> {
        String retorno;
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL("http://garagemhacker.org/status.txxt");
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String str;
                while ((str = in.readLine()) != null) {
                    retorno = str;
                }
                in.close();
            } catch (MalformedURLException e) {
                retorno = "erro!";
            } catch (IOException e) {
                retorno = "erro!";
            }
            return "ok";
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            retorno = retorno.substring(0, 1).toUpperCase() + retorno.substring(1);
            estado.setText(retorno);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

package com.msu.smartqs.activity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.msu.smartqs.R;
import com.msu.smartqs.base.BaseActivity;
import com.msu.smartqs.modle.Ad;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 74514 on 2018/3/29.
 */

public class AdsActivity extends BaseActivity implements View.OnClickListener {

    private Ad ad ;
    private EditText edtPrice;
    private EditText edtMax;
    private EditText edtMin;
    private EditText edtAmount;
    private Button btn;
    private TextView txlink;
    private TextView txreturn ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_layout);
        initView();

        if(isConnected()){
            txlink.setBackgroundColor(0xFF00CC00);
            txlink.setText("You are conncted");
        }
        else{
            txlink.setText("You are NOT conncted");
        }
        btn.setOnClickListener(this);

    }
    private void initView(){
        edtPrice = (EditText)findViewById(R.id.edt_price);
        edtMax = (EditText)findViewById(R.id.edt_max);
        edtMin = (EditText)findViewById(R.id.edt_min);
        edtAmount = (EditText)findViewById(R.id.edt_amount);
        btn = (Button)findViewById(R.id.btp);
        txlink = (TextView)findViewById(R.id.link);
        txreturn = (TextView)findViewById(R.id.txreturn);
    }

    public static String POST(String url, Ad ad){
        InputStream inputStream = null;
        String result = "";
       // txreturn.setText("abvc");
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("adType", ad.getAdType());//出售和购买
            jsonObject.accumulate("amount", ad.getAmount());
            jsonObject.accumulate("coin", ad.getCoin());
            jsonObject.accumulate("min", ad.getMin());
            jsonObject.accumulate("max", ad.getMax());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();


            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            // 7. Set some headers to inform server about the type of the content
            //            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
            /* 处理*/

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();


            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

            //txreturn.setText(result);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btp:
                if(!validate()){
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                }
                // call AsynTask to perform network operation on separate thread
                new HttpAsyncTask().execute("http://10.1.1.179:8080/test");
                //以get方式读取网络数据的方法
                /* /api/register/test
                new AsyncTask<String ,Void ,Void>(){

                    @Override
                    protected Void doInBackground(String... urls) {
                        try {
                            URL url = new URL(urls[0]);
                            //URLConnection connection = url.openConnection();//get 方式
                            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(),"utf-8");
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("");

                            InputStream is = connection.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is,"utf-8");
                            BufferedReader br = new BufferedReader(isr);
                            String line ;
                            while( ( line = br.readLine() )!= null){
                                //System.out.println("sas: "+line);
                                txreturn.setText(line);
                            }
                            br.close();
                            isr.close();
                            is.close();

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute("http://10.1.1.179:8080/test");
                */
                break;
        }

    }

    /**
     * 用于测试手机是否连上网络
     * @return
     */
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            ad = new Ad();
            ad.setAdType((byte) 1);
            String price = edtPrice.getText().toString();
            String max = edtMax.getText().toString();
            String min = edtMin.getText().toString();
            String amount = edtAmount.getText().toString();
            ad.setAmount(BigDecimal.valueOf(Double.valueOf(amount)));
            ad.setMax(BigDecimal.valueOf(Double.valueOf(max)));
            ad.setMin(BigDecimal.valueOf(Double.valueOf(min)));
            ad.setPrice(BigDecimal.valueOf(Double.valueOf(price)));
            String res = POST(urls[0],ad);
            txreturn.setText(res);
            return res;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }


    private boolean validate(){
        return true;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}

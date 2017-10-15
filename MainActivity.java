package ngame.kinoman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPlay(View view) {
        Intent intent = new Intent(MainActivity.this, GenreActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickInfo(View view) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("InfoOrStatistics", 0);
        startActivity(intent);
        finish();
    }

    public void onClickStatistics(View view) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("InfoOrStatistics", 1);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.exit)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}

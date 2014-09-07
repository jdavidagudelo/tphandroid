package com.artica.telesalud.tph.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.service.LoginService;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class LoginActivity  extends BaseActivity {
    private UserDto user = null;
    private DatabaseHelper databaseHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button buttonLogin = (Button)findViewById(R.id.buttonLogin);
        final EditText editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        final EditText editTextUserName = (EditText)findViewById(R.id.editTextUserName);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                UserInSession.setPassword(password);
                new LoginService(LoginActivity.this).execute(username, password);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setUser(UserDto user)
    {
        this.user = user;
        if(user != null)
        {
            Intent intent = new Intent(this,
                    ListEventsActivity.class);
            intent.putExtra(
                    UserDto.class.getCanonicalName(),
                    (Parcelable)user);
            startActivity(intent);
        }
        else
        {
            showErrorMessage("Usuario o clave inválidas, por favor intente nuevamente");
        }
    }
    @Override
    public void showErrorMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
}

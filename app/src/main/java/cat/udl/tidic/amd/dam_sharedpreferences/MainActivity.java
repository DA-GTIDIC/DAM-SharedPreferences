package cat.udl.tidic.amd.dam_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.charset.StandardCharsets;

import cat.udl.tidic.amd.dam_sharedpreferences.preferences.PreferencesProvider;

public class MainActivity extends AppCompatActivity {


    private EditText usernameET;
    private EditText passwordET;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mPreferences = PreferencesProvider.providePreferences();

        usernameET = findViewById(R.id.editText_username);
        passwordET = findViewById(R.id.editText_password);
        TextView tokenTV = findViewById(R.id.textView_token);
        Button createToken = findViewById(R.id.button_createToken);
        TextView tokenLabel = findViewById(R.id.token_label);

       String token = this.mPreferences.getString("token","");
        tokenLabel.setVisibility(View.INVISIBLE);

       if (!token.equals("")){

            tokenLabel.setVisibility(View.VISIBLE);
            tokenTV.setText(token);
            usernameET.setVisibility(View.INVISIBLE);
            passwordET.setVisibility(View.INVISIBLE);
            createToken.setVisibility(View.INVISIBLE);

       }

       createToken.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String username = usernameET.getText().toString();
               String password = passwordET.getText().toString();
               String token_decoded = username + ":" + password;
               byte[] bytes = token_decoded.getBytes(StandardCharsets.UTF_8);
               String _token = Base64.encodeToString(bytes,Base64.DEFAULT);
               mPreferences.edit().putString("token",_token).apply();
               Toast.makeText( getApplicationContext(),
                       "Token obtained properly", Toast.LENGTH_SHORT).show();
           }
       });



    }
}

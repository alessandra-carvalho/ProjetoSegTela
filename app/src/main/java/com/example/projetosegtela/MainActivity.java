package com.example.projetosegtela;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.projetosegtela.model.User;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //objetos que estão na view
    EditText editEmail;
    EditText editPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //chama a tela inicial
        setContentView(R.layout.activity_main);

        //faz a ligação com o id de cada objeto da activity
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);

        //faz com que o foco seja o e-mail na tela principal
        editEmail.requestFocus();

        // popula lista de usuarios
        User user1 = new User(20, "ale1", "ale1@dominio.com", "1234");
        User user2 = new User(25, "ana2", "ana2@dominio.com", "5678");
        User user3 = new User(30, "eva3", "eva3@dominio.com", "4321");
        User user4 = new User(35, "joao4", "joao4@dominio.com", "8765");
        User user5 = new User(40, "jonas5", "jonas5@dominio.com", "0123");
        User user6 = new User(45, "Rodrigo6", "rod6@dominio.com", "4567");

        //add os usuários na lista
        List<User> listUser = new ArrayList<>();
        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);
        listUser.add(user4);
        listUser.add(user5);
        listUser.add(user6);

        //validação do evento do botão login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            private View view;

            @Override
            public void onClick(View view) {
                this.view = view;

                //valida se o login e senha estão na lista de usuários cadastrados
                int n = listUser.size();
                boolean boolUserValido = false;

                //percorre a lista comparando se os dados estão corretos
                for (int i=0; i<n; i++){

                    //verifica se os dados de e-mail e senha estão corretos
                    if (editEmail.getText().toString().equals(listUser.get(i).getUserLogin().toString())
                        &&  editPassword.getText().toString().equals(listUser.get(i).getPassword().toString())){
                        boolUserValido = true;
                    }
                }
                //verifica se os campos estão em branco mostra a msg
                if(editEmail.getText().toString().isEmpty() || editPassword.getText().toString().isEmpty()){
                    //Caso as opções acima ocorram, mostrará a msg abaixo e .show serve para mostrar na tela
                    mensagem("Insira seus dados!");

                } // teste para ver se os dados estão corretos
                else if(boolUserValido){

                    //após as verificações vai para uma nova activity
                    Intent intent = new Intent(getApplicationContext(), TelaPrincipal.class);
                    startActivity(intent);

                } else{
                    mensagem("Dados incorretos!");
                }
            }
        });
    }
    //método para passar a msg
    private void mensagem(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        editEmail.requestFocus();
    }
}

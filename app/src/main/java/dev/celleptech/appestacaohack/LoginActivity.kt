package dev.celleptech.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Escutando o clique do botão entrar
        btnLoginEntrar.setOnClickListener {

            // Capturar os dados digitados
            val email = edtLoginEmail.text.toString().trim()
            val senha = edtLoginSenha.text.toString().trim()

            // Validação dos campos
            if (email.isEmpty()) {
                edtLoginEmail.error = "Campo obrigatório"
                edtLoginEmail.requestFocus()

            } else if (senha.isEmpty()) {
                edtLoginSenha.error = "Campo obrigatório"
                edtLoginSenha.requestFocus()

            } else {

                // Acessar o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE
                )

                // Recuperando dados no arquivo de preferências compartilhadas
                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")

                // Verificar o email e senha
                if (email == emailPrefs && senha == senhaPrefs) {

                    Toast.makeText(this, "Usuário Logado", Toast.LENGTH_LONG).show()

                    // Abrir a MainActivity
                    val mIntent = Intent(this, MainActivity::class.java)

                    // Passando informações através da Intent
                    mIntent.putExtra("INTENT_EMAIL", email)

                    startActivity(mIntent)
                    finish()

                } else {
                    // Apresentar mensagem de erro ao usuário
                    Toast.makeText(this, "Email ou senha inválidos", Toast.LENGTH_LONG).show()
                }
            }
        }

        // Escutando o clique do botão cadastrar
        btnLoginCadastrar.setOnClickListener {
            // Abrir a tela de cadastro
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
        }
    }
}
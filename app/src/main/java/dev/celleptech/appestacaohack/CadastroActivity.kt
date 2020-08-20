package dev.celleptech.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Criar uma lista de opções para o Spinner
        val listaGenero = arrayListOf("Selecione o Gênero", "Feminino", "Masculino", "Outros")

        // Criar um adaptador para o Spinner
        val generoAdapter = ArrayAdapter(
            this,                                           // Contexto
            android.R.layout.simple_spinner_dropdown_item,  // Layout
            listaGenero                                     // Dados
        )

        // Plugar o adaptador no Spinner
        spnCadastroGenero.adapter = generoAdapter

        // Escutar o clique do botão cadastrar
        btnCadastroCadastrar.setOnClickListener {

            // Obter os dados digitados
            val nome = edtCadastroNome.text.toString().trim()
            val sobrenome = edtCadastroSobrenome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim()
            val senha = edtCadastroSenha.text.toString().trim()

            // Atribuição condicional
            val genero = if (spnCadastroGenero.selectedItemId == 0L) {
                ""
            } else {
                spnCadastroGenero.selectedItem.toString()
            }

            // Validação dos campos
            if (nome.isEmpty() || sobrenome.isEmpty() ||
                email.isEmpty() || senha.isEmpty() || genero.isEmpty()) {

                // Apresentando uma mensagem de erro
                Toast.makeText(
                    this, "Preencha todos os campos", Toast.LENGTH_LONG).show()

            } else {

                // Todos os campos foram preenchidos

                // Criar ou acessar o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE
                )

                // Editar o arquivo
                val editPrefs = sharedPrefs.edit()

                // Preparando os dados a serem salvos
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("GENERO", genero)

                // Salvar os dados no arquivo de preferências compartilhadas
                editPrefs.apply()

                // Abrir a MainActivity
                val mIntent = Intent(this, MainActivity::class.java)

                // Passando informações através da Intent
                mIntent.putExtra("INTENT_EMAIL", email)

                startActivity(mIntent)

                // Tirar todas as telas do empilhamento
                finishAffinity()

            }
        }
    }
}
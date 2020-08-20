package dev.celleptech.appestacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recuperar o email passado por meio da Intent
        val email = intent.getStringExtra("INTENT_EMAIL")

        // Acessar o arquivo de preferencias compartilhadas
        val sharedPrefs = getSharedPreferences(
            "cadastro_$email",
            Context.MODE_PRIVATE
        )

        // Recuperar dados no arquivo de preferencias compartilhadas
        val nome = sharedPrefs.getString("NOME", "")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val genero = sharedPrefs.getString("GENERO", "")

        // Exibir os dados recuperados na tela
        txvMainNome.text = "$nome $sobrenome"
        txvMainEmail.text = email
        txvMainGenero.text = genero

        // Escutando o clique do botão Sair
        btnMainSair.setOnClickListener {

            // Criar a caixa de diálogo
            val alert = AlertDialog.Builder(this)

            // Definir o título da caixa de diálogo
            alert.setTitle("App Estação Hack")

            // Definir o corpo da mensagem
            alert.setMessage("Deseja sair?")

            // Definir o rótulo do botão Positivo e escutar seu clique
            // Lambda -> Função anônima
            alert.setPositiveButton("Sim") { dialog, which ->

                val mIntent = Intent(this, LoginActivity::class.java)
                startActivity(mIntent)
                // Eliminando as telas da pilha
                finishAffinity()

            }

            // Definir o rótulo do botão Neutro e escutar seu clique
            alert.setNeutralButton("Não") { dialog, which -> }

            // Exibir a caixa de diálogo
            alert.show()

        }

        // Escutando o clique do botão Site Cel.Lep
        btnMainSite.setOnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
        }
    }
}
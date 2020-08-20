package dev.celleptech.appestacaohack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        // Habilitando a execução de códigos JavaScript
        wbvWeb.settings.javaScriptEnabled = true

        // Carregando um endereço Web
        wbvWeb.loadUrl("http://br.cellep.com/estacaohack")

        // Definindo o WebView como cliente Web padrão
        wbvWeb.webViewClient = WebViewClient()

    }

}
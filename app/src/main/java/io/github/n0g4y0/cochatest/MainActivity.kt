package io.github.n0g4y0.cochatest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var botonVerdad : Button
    private lateinit var botonFalso : Button
    private lateinit var botonSiguiente : Button
    private lateinit var botonAnterior : Button


    private var indiceActual = 0

    private val bancoPreguntas = listOf(
        Pregunta(R.string.question_1, false),
        Pregunta(R.string.question_2,false),
        Pregunta(R.string.question_3,true),
        Pregunta(R.string.question_4,false),
        Pregunta(R.string.question_5,true),
        Pregunta(R.string.question_6,false),
        Pregunta(R.string.question_7,true),
        Pregunta(R.string.question_8,false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonVerdad = findViewById(R.id.verdad_button)
        botonFalso = findViewById(R.id.falso_button)
        botonSiguiente = findViewById(R.id.boton_siguiente)
        botonAnterior = findViewById(R.id.boton_anterior)

        botonVerdad.setOnClickListener {view : View ->
            verificarRespuesta(true)
        }

        botonFalso.setOnClickListener {view : View ->

            verificarRespuesta(false)
        }

        botonSiguiente.setOnClickListener {

            if(indiceActual < bancoPreguntas.size-1) {
                indiceActual++
                actualizarPregunta()
            }else{
                if(indiceActual == bancoPreguntas.size-1){
                    indiceActual = 0
                    actualizarPregunta()
                }
            }
        }
        botonAnterior.setOnClickListener {

            if(indiceActual > 0) {
                indiceActual--
                actualizarPregunta()
            }else {
                if (indiceActual == 0) {
                    indiceActual = bancoPreguntas.size - 1
                    actualizarPregunta()
                }
            }
        }
        actualizarPregunta()
    }

    private fun actualizarPregunta(){

        val textoPreguntaID = bancoPreguntas[indiceActual].idTexto
        pregunta_text_view.setText(textoPreguntaID)

    }

    private fun verificarRespuesta(respuestaUsuario: Boolean){

        val respuestaCorrecta = bancoPreguntas[indiceActual].respuesta
        val mostrarMensajeID =  if (respuestaUsuario == respuestaCorrecta){
            "Correcto"
        } else {
            "Incorrecto"
        }

        val t = Toast.makeText(this, mostrarMensajeID,Toast.LENGTH_SHORT)
        t.setGravity(Gravity.TOP,0,0)
        t.show()

    }
}

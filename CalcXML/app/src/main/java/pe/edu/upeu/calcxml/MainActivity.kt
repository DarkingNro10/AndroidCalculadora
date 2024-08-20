package pe.edu.upeu.calcxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.math.PI

class MainActivity : AppCompatActivity() {
    private lateinit var txtResultado: EditText
    private var valAnt = 0.0
    private var valAct = 0.0
    private var operador = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtResultado = findViewById(R.id.txtResult)
        botones()
    }

    fun botones() {
        val buttons = arrayOf(
            R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btnSum, R.id.btnMult, R.id.btnIgual, R.id.button, R.id.button2,
            R.id.button3, R.id.button4
        )
        for (button in buttons) {
            val btn = findViewById<Button>(button)
            btn.setOnClickListener { onClickListener(btn) }
        }
    }

    fun onClickListener(view: View) {
        val botonX = findViewById<Button>(view.id)
        when (view.id) {
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9 -> {
                appendContent(botonX.text.toString())
            }
            R.id.btnSum -> {
                setOperador(botonX.text.toString())
            }
            R.id.btnMult -> {
                setOperador(botonX.text.toString())
            }
            R.id.btnIgual -> {
                operacion()
            }
            R.id.button -> { // Raíz cuadrada
                valAnt = txtResultado.text.toString().toDouble()
                valAct = sqrt(valAnt)
                txtResultado.setText(valAct.toString())
            }
            R.id.button2 -> { // División
                setOperador("/")
            }
            R.id.button3 -> { // Potencia
                setOperador("^")
            }
            R.id.button4 -> { // PI
                txtResultado.setText(PI.toString())
            }
        }
    }

    fun appendContent(valor: String) {
        txtResultado.append(valor)
    }

    fun setOperador(oper: String) {
        operador = oper
        valAnt = txtResultado.text.toString().toDouble()
        txtResultado.text.clear()
    }

    fun operacion() {
        valAct = txtResultado.text.toString().toDouble()
        val resultx = when (operador) {
            "*" -> valAnt * valAct
            "+" -> valAnt + valAct
            "/" -> valAnt / valAct
            "^" -> valAnt.pow(valAct)
            "%" -> valAnt % valAct
            else -> valAct
        }
        txtResultado.setText(resultx.toString())
        operador = ""
    }
}

package com.haroldg.firstapp.IMC_Calculator

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.haroldg.firstapp.R

class IMCActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var defaultPeso:Int = 50
    private var defaultEdad:Int = 18
    private var imc:Float= 0.0F
    private var defaultAltura:Int = 120

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var valAlt:TextView
    private lateinit var _slider:RangeSlider
    private lateinit var btnMenosPeso: FloatingActionButton
    private lateinit var  btnMasPeso:FloatingActionButton
    private lateinit var txtPeso:TextView
    private lateinit var btnMenosEdad:FloatingActionButton
    private lateinit var btnMasEdad:FloatingActionButton
    private lateinit var txtEdad:TextView
    private lateinit var btnCalcular:Button
    private lateinit var viewResultado:CardView
    private lateinit var txtResultado:TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        valAlt = findViewById(R.id.valorAlt)
        _slider = findViewById(R.id.slider)
        txtPeso = findViewById(R.id.valPeso)
        btnMenosPeso = findViewById(R.id.btnMenosPeso)
        btnMasPeso = findViewById(R.id.btnMasPeso)
        txtEdad = findViewById(R.id.valEdad)
        btnMenosEdad = findViewById(R.id.btnMenosEdad)
        btnMasEdad = findViewById(R.id.btnMasEdad)
        btnCalcular = findViewById(R.id.btnCalcular)
        viewResultado = findViewById(R.id.viewResultado)
        txtResultado = findViewById(R.id.resultadoIMC)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender(true)
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender(false)
            setGenderColor()
        }
        _slider.addOnChangeListener { _, value, _ ->
            updateAltura(value)
        }
        btnMenosPeso.setOnClickListener {
            updatePeso(defaultPeso - 1)
        }
        btnMasPeso.setOnClickListener {
            updatePeso(defaultPeso + 1)
        }
        btnMenosEdad.setOnClickListener {
            updateEdad(defaultEdad - 1)
        }
        btnMasEdad.setOnClickListener {
            updateEdad(defaultEdad + 1)
        }
        btnCalcular.setOnClickListener {
            calcular()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateAltura(value: Float) {
        val df = DecimalFormat("#.##")
        defaultAltura = df.format(value).toInt()
        valAlt.text = String.format("%s cm", defaultAltura)
        reset()
    }

    private fun updatePeso(nuevoPeso: Int) {
        defaultPeso = nuevoPeso
        setPeso()
        reset()
    }

    private fun updateEdad(nuevaEdad: Int) {
        defaultEdad = nuevaEdad
        setEdad()
        reset()
    }

    private fun reset() {
        viewResultado.isVisible = false
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun calcular() {

        val alturaEnMetros = defaultAltura / 100.0  // Convertir a metros
        imc = (defaultPeso / (alturaEnMetros * alturaEnMetros)).toFloat()
        val df = DecimalFormat("#.##")
        var resultado = df.format(imc)
        val flecha = "\u2192"

        resultado = when(imc){
            in 18.51..24.49 ->{
                "$resultado $flecha Peso normal"
            }

            in 25.00..29.99->{
                "$resultado $flecha Sobrepeso"
            }

            in 30.00..99.00 ->{
                "$resultado $flecha Obesidad"
            }

            else ->{
                "$resultado $flecha Bajo peso"
            }
        }
        txtResultado.text = resultado
        viewResultado.isVisible = true
        Log.i("haroldg", "Tu IMC es ${df.format(imc)}")
    }

    private fun changeGender(isMale: Boolean) {
        if (isMale) {
            isMaleSelected = true
            isFemaleSelected = false
        } else {
            isMaleSelected = false
            isFemaleSelected = true
        }
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean): Int {
        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun setPeso(){
        txtPeso.text = getString(R.string.peso_text, defaultPeso)
    }

    private fun setEdad() {
        txtEdad.text = getString(R.string.edad_text, defaultEdad)
    }

    private fun initUI() {
        setGenderColor()
        setPeso()
    }

}
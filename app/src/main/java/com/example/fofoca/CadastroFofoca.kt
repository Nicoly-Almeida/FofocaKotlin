package com.example.fofoca

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

class CadastroFofoca : AppCompatActivity() {
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var rbTrue: RadioButton
    private lateinit var etDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrofofoca)

        this.btnSave = findViewById(R.id.btnSave)
        this.btnCancel = findViewById(R.id.btnCancel)
        this.rbTrue = findViewById(R.id.rbTrue)
        this.etDescription = findViewById(R.id.etDescription)


        this.btnSave.setOnClickListener { save() }
        this.btnCancel.setOnClickListener { finish() }
    }

    private fun save() {
        val description = this.etDescription.text.toString()
        val isTrue = this.rbTrue.isChecked
        val fofoca = Fofoca(description, isTrue)
        val intent = Intent().apply {
            putExtra("Fofoca", fofoca)
        }

        setResult(RESULT_OK, intent)
        finish()
    }
}
package com.example.fofoca

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var btnPlay: Button
    private lateinit var btnRegister: Button
    private var game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnPlay = findViewById(R.id.btnPlay)
        this.btnRegister = findViewById(R.id.btnRegister)

        val addGossipResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) {
                val gossip = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("GOSSIP", Fofoca::class.java)
                } else {
                    it.data?.getSerializableExtra("GOSSIP")
                } as Fofoca

                this.game.addFofoca(gossip)
                Toast.makeText(this, "Gossip added!", Toast.LENGTH_SHORT).show()
            }
        }

        val guessIfTrueResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) {
                Toast.makeText(this, "You were right!!", Toast.LENGTH_SHORT).show()
            } else if(it.resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Given up so easy, huh?", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Not good enough, try again!", Toast.LENGTH_SHORT).show()
            }
        }

        this.btnRegister.setOnClickListener {
            val intent = Intent(this, CadastroFofoca::class.java)
            addGossipResult.launch(intent)
        }

        this.btnPlay.setOnClickListener{
            val intent = Intent(this, FofocaActivity::class.java).apply {
                putExtra("GOSSIP", game.getRandomFofoca())
            }
            guessIfTrueResult.launch(intent)
        }
    }
}
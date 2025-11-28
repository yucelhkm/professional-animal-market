package com.animalmarket.professional

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.animalmarket.professional.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Uygulama başladığında yapılacak işlemler
        initializeApp()
    }

    private fun initializeApp() {
        // Kullanıcı rolüne göre yönlendirme yapılacak
        // USER -> Kullanıcı Dashboard
        // VETERINARIAN -> Veteriner Dashboard  
        // ADMIN -> Admin Dashboard
        
        println("🐾 Professional Animal Market uygulaması başlatıldı!")
    }
}

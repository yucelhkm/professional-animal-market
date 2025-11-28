package com.animalmarket.professional.data.model

import java.util.*

/**
 * Hayvan türleri - Filtreleme için
 */
enum class AnimalType {
    CATTLE,      // Sığır
    SHEEP,       // Koyun
    GOAT,        // Keçi
    CHICKEN,     // Tavuk
    HORSE,       // At
    DUCK,        // Ördek
    TURKEY,      // Hindi
    OTHER        // Diğer
}

/**
 * Cinsiyet - Filtreleme için
 */
enum class Gender {
    MALE,        // Erkek
    FEMALE       // Dişi
}

/**
 * İlan durumu - Onay süreci için
 */
enum class ListingStatus {
    DRAFT,               // Taslak
    PENDING_VET_REVIEW,  // Veteriner incelemesi bekliyor
    VET_APPROVED,        // Veteriner onayladı
    VET_REJECTED,        // Veteriner reddetti
    PENDING_ADMIN_REVIEW,// Yönetici incelemesi bekliyor
    ADMIN_APPROVED,      // Yönetici onayladı
    ADMIN_REJECTED,      // Yönetici reddetti
    PUBLISHED,           // Yayında
    EXPIRED              // Süresi dolmuş
}

/**
 * Hayvan ilanı modeli
 * Filtreleme: Tür, Irk, Cinsiyet, Adres, Fiyat
 * Onay Süreci: Veteriner → Yönetici
 */
data class AnimalListing(
    val id: String = "",
    val ownerId: String = "", // İlan sahibi
    val title: String = "",
    val description: String = "",

    // Filtreleme bilgileri
    val animalType: AnimalType = AnimalType.CATTLE,
    val breed: String = "", // Irk - Filtreleme için
    val age: Int = 0, // Ay cinsinden
    val gender: Gender = Gender.MALE,
    val weight: Double? = null, // Kg cinsinden

    // Konum bilgileri - Filtreleme için
    val location: String = "", // Şehir
    val address: String = "", // Detaylı adres
    val latitude: Double? = null,
    val longitude: Double? = null,

    // Fiyat bilgisi - Filtreleme için
    val price: Double = 0.0,
    val currency: String = "TL",

    // Görseller
    val photos: List<String> = emptyList(),

    // Sağlık bilgileri (Veteriner onayı için)
    val healthStatus: HealthStatus? = null,
    val vaccinations: List<Vaccination> = emptyList(),

    // Onay süreci
    val status: ListingStatus = ListingStatus.DRAFT,
    val vetReviewerId: String? = null, // İnceleyen veteriner
    val adminReviewerId: String? = null, // İnceleyen admin
    val vetReviewDate: Date? = null,
    val adminReviewDate: Date? = null,
    val rejectionReason: String? = null, // Reddedilme sebebi

    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) {
    /**
     * Veteriner incelemesine gönderilebilir mi?
     */
    fun canSubmitToVet(): Boolean {
        return status == ListingStatus.DRAFT &&
                title.isNotEmpty() &&
                description.isNotEmpty() &&
                photos.isNotEmpty()
    }

    /**
     * Yönetici incelemesine gönderilebilir mi?
     */
    fun canSubmitToAdmin(): Boolean {
        return status == ListingStatus.DRAFT ||
               status == ListingStatus.VET_APPROVED
    }

    /**
     * Filtreleme için kullanılacak anahtar kelimeler
     */
    fun getSearchKeywords(): List<String> {
        return listOf(
            title,
            description,
            animalType.name,
            breed,
            location,
            address
        ).filter { it.isNotEmpty() }
    }
}

/**
 * Sağlık durumu (Veteriner onayı için)
 */
data class HealthStatus(
    val overallHealth: String = "", // Genel sağlık durumu
    val temperature: Double? = null, // Vücut sıcaklığı
    val heartRate: Int? = null, // Kalp atışı
    val respiratoryRate: Int? = null, // Solunum sayısı
    val notes: String = "", // Veteriner notları
    val examinationDate: Date = Date(),
    val veterinarianId: String = "" // Muayene eden veteriner
)

/**
 * Aşı bilgileri
 */
data class Vaccination(
    val vaccineName: String = "",
    val administrationDate: Date = Date(),
    val nextDueDate: Date? = null,
    val veterinarianId: String = "" // Aşıyı yapan veteriner
)

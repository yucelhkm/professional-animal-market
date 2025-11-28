package com.animalmarket.professional.data.model

import java.util.*

/**
 * Kullanıcı rolleri:
 * - USER: Normal kullanıcı (hayvan satıcı/alıcı)
 * - VETERINARIAN: Veteriner hekim
 * - ADMIN: Yönetici
 */
enum class UserRole {
    USER, VETERINARIAN, ADMIN
}

/**
 * Kullanıcı modeli
 */
data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val phone: String = "",
    val role: UserRole = UserRole.USER,
    val location: String = "",
    val profileImage: String? = null,
    val createdAt: Date = Date(),
    val isVerified: Boolean = false,
    val veterinaryLicense: String? = null, // Sadece veterinerler için
    val adminPermissions: List<String> = emptyList() // Sadece adminler için
) {
    fun isVeterinarian(): Boolean = role == UserRole.VETERINARIAN
    fun isAdmin(): Boolean = role == UserRole.ADMIN
    fun isRegularUser(): Boolean = role == UserRole.USER
}

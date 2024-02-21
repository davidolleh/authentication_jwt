package com.example.authentication.authentication_jwt.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

private const val USER_ID_KEY = "userPk"

@Component
class JwtTokenProvider @Autowired constructor(
    @Value("\${spring.jwt.secret}")
    private val secretToken: String,
) {
//    private val key: String = Base64.getEncoder().encodeToString(secretToken.toByteArray(Charsets.UTF_8))
    private val key = Keys.hmacShaKeyFor(secretToken.toByteArray(Charsets.UTF_8))
    private final val random: SecureRandom = SecureRandom.getInstanceStrong()
    private val accessTokenExp: Date = Date.from(
        Instant.now().plus((random.nextInt(30) + 60).toLong(), ChronoUnit.MINUTES)
    )
    private val refreshTokenExp: Date = Date.from(Instant.now().plus(24, ChronoUnit.HOURS))


    fun createToken(
        userPk: String,
        exp: Date = Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)),
        isAccessToken: Boolean = false,
        isRefreshToken: Boolean = false
    ): String {
        if (isAccessToken && isRefreshToken) throw Exception()

        val customClaims = Jwts.claims().apply {
            add(USER_ID_KEY, userPk)
        }.build()


        return try {
            Jwts.builder()
            .claims(customClaims)
            .issuedAt(exp)
            .expiration(
                when {
                    isAccessToken -> accessTokenExp
                    isRefreshToken -> refreshTokenExp
                    else -> exp
                }
            )
            .signWith(key, Jwts.SIG.HS256)
            .compact()
        } catch (e: Exception) {
            throw e
        }
    }

    fun isInvalidToken(token: String): Boolean {
        try {
            parseClaims(token)
            return false
        } catch (e: SignatureException) {

        } catch (e: MalformedJwtException) {

        } catch (e: ExpiredJwtException) {

        } catch (e: UnsupportedJwtException) {

        } catch (e: IllegalArgumentException) {

        }

        return true
    }

    private fun parseClaims(token: String) = Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
}
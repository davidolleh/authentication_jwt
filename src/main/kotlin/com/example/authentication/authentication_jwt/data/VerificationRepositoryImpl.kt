package com.example.authentication.authentication_jwt.data

import com.example.authentication.authentication_jwt.domain.verification.Verification
import com.example.authentication.authentication_jwt.domain.verification.VerificationCode
import com.example.authentication.authentication_jwt.domain.verification.VerificationRepository
import com.linecorp.kotlinjdsl.QueryFactory
import com.linecorp.kotlinjdsl.listQuery
import com.linecorp.kotlinjdsl.querydsl.expression.column
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
class VerificationRepositoryImpl @Autowired constructor(
    val queryFactory: QueryFactory,
    val entityManager: EntityManager
): VerificationRepository {
    override fun save(verification: Verification): Verification {
        try {
            entityManager.persist(verification)
            return verification
        } catch (e: Exception) {
            println(e.message)
            throw Exception()
        }
    }

    override fun delete(): Verification {
//        queryFactory.deleteQuery{
//
//        }
        TODO("Not yet implemented")
    }

    override fun findById(): Verification {
        TODO("Not yet implemented")
    }

    override fun findByContact(contact: String): Verification? {
        println("startFindContact")
        try {
            val verification: List<Verification> =  queryFactory.listQuery<Verification> {
                select(entity(Verification::class))
                from(entity(Verification::class))
                where(
                    column(VerificationCode::verificationNumber).equal(contact)
//                name?.run {
//                    column(VerificationCode::verificationNumber).equal(contact)
//                }
                )
            }

            println(verification)

            return if (verification.isEmpty()) null else verification[0]
        } catch (e: Exception) {
            println(e.message)
            throw Exception()
        }

    }
}
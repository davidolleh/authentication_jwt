package com.example.authentication.authentication_jwt.data

import com.example.authentication.authentication_jwt.domain.verification.Verification
import com.example.authentication.authentication_jwt.domain.verification.VerificationCode
import com.example.authentication.authentication_jwt.domain.verification.VerificationRepository
import com.linecorp.kotlinjdsl.QueryFactory
import com.linecorp.kotlinjdsl.deleteQuery
import com.linecorp.kotlinjdsl.listQuery
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.querydsl.expression.column
import jakarta.persistence.EntityManager
import jakarta.persistence.Query
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
class VerificationRepositoryImpl @Autowired constructor(
    val queryFactory: QueryFactory,
    val entityManager: EntityManager
): VerificationRepository {
    @Transactional
    override fun save(verification: Verification): Verification {
        try {
            entityManager.persist(VerificationEntity.fromDomain(domainV = verification))
            return verification
        } catch (e: Exception) {
            println(e.message)
            throw Exception()
        }
    }

    @Transactional
    override fun deleteByContact(contact: String): Boolean {
        try {
            val deleteQuery: Query = queryFactory.deleteQuery<VerificationEntity> {
                where(
                    col(VerificationEntity::contact).equal(contact)
                )
            }

            return true
        } catch (e: Exception) {
            println(e.message)
            throw Exception()
        }
    }

    override fun findById(): Verification {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun findByContact(contact: String): Verification? {
        try {
            val verification: List<VerificationEntity> =  queryFactory.listQuery<VerificationEntity> {
                select(entity(VerificationEntity::class))
                from(entity(VerificationEntity::class))
                where(
                    column(VerificationEntity::contact).equal(contact)
//                name?.run {
//                    column(VerificationCode::verificationNumber).equal(contact)
//                }
                )
            }


            return if (verification.isEmpty()) null else verification[0].toDomain()
        } catch (e: Exception) {
            println(e.message)
            throw Exception()
        }
    }
}
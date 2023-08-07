package com.example.authentication.authentication_jwt.config

import com.linecorp.kotlinjdsl.QueryFactory
import com.linecorp.kotlinjdsl.QueryFactoryImpl
import com.linecorp.kotlinjdsl.query.creator.CriteriaQueryCreator
import com.linecorp.kotlinjdsl.query.creator.CriteriaQueryCreatorImpl
import com.linecorp.kotlinjdsl.query.creator.SubqueryCreatorImpl
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryJdslConfiguration(
    @PersistenceContext
    val entityManager: EntityManager
) {
    @Bean
    fun criteriaQueryFactory(): CriteriaQueryCreator = CriteriaQueryCreatorImpl(entityManager)


    @Bean
    fun jpaQueryFactory(): QueryFactory = QueryFactoryImpl(
        criteriaQueryCreator = criteriaQueryFactory(),
        subqueryCreator = SubqueryCreatorImpl()
    )
}
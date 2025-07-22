package com.example.webfluxstudy.common.exception

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import jakarta.validation.ConstraintViolationException
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component

@Component
class GraphQLValidationExceptionHandler : DataFetcherExceptionResolverAdapter() {
    override fun resolveToSingleError(
        ex: Throwable,
        env: DataFetchingEnvironment
    ): GraphQLError? {
        if (ex !is ConstraintViolationException) return null

        val fieldErrors: Map<String, String> = ex.constraintViolations
            .groupBy { it.propertyPath.last().name }
            .mapValues { (_, violations) -> violations.joinToString(", ") { it.message } }

        return GraphqlErrorBuilder.newError(env)
            .message("Validation failed")
            .extensions(mapOf("validationErrors" to fieldErrors))
            .build()
    }
}
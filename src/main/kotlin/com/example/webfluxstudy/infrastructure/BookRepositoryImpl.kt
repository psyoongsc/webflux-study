package com.example.webfluxstudy.infrastructure

import com.example.webfluxstudy.domain.entity.Book
import com.example.webfluxstudy.domain.repository.BookRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    private val mongoTemplate: ReactiveMongoTemplate
): BookRepository {

    override suspend fun findById(id: String): Book? {
        return mongoTemplate.findById(id, Book::class.java)
            .awaitSingleOrNull()
    }

    override suspend fun findAll(offset: Int, limit: Int): List<Book> {
        val query = Query()
            .skip(offset.toLong())
            .limit(limit)
        return mongoTemplate.find(query, Book::class.java)
            .collectList()
            .awaitSingle()
    }

    override suspend fun findWithTitle(titleContains: String?, offset: Int, limit: Int): List<Book> {
        val criteria = mutableListOf<Criteria>()
        if(!titleContains.isNullOrBlank()) {
            // 대소문자 구분 없이 title 컬럼에서 titleContains 값이 포함되어 있으면 검색
            criteria += Criteria.where(Book::title.name).regex(".*$titleContains.*", "i")
        }

        val query = Query().apply {
            if(criteria.isNotEmpty()) {
                addCriteria(Criteria().andOperator(*criteria.toTypedArray()))
            }
            skip(offset.toLong())
            limit(limit)
        }

        return mongoTemplate.find(query, Book::class.java)
            .collectList()
            .awaitSingle()
    }

    override suspend fun count(): Long {
        return mongoTemplate.count(Query(), Book::class.java)
            .awaitSingle()
    }

    override suspend fun save(book: Book): Book {
        return mongoTemplate.save(book)
            .awaitSingle()
    }
}

package com.knockknock.server.entity

import jakarta.persistence.*

@Entity
@Table(name = "accounts")
class Account(
        email: String
): PrimaryKeyEntity() {
    @Column(nullable = false, unique = true)
    var email: String = email

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "author")
    protected val mutableBoards: MutableList<Board> = mutableListOf()

    val boards: List<Board> get() = mutableBoards.toList()

    fun writeBoard(board: Board) {
        mutableBoards.add(board);
    }
}
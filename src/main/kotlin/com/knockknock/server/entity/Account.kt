package com.knockknock.server.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "accounts")
class Account(
        email: String,
        phoneNo: String,
        password: String?,
        nickname: String,
        birthYear: Int?,
        gender: String?,
        job: String?,
        lastLoginTimestamp: LocalDateTime?,
        hashtagApperanceYn: Boolean
): PrimaryKeyEntity() {

    @Column(nullable = false, unique = true)
    var email: String = email

    @Column(nullable = false)
    var phoneNo: String = phoneNo

    @Column
    var password: String = password?: ""

    @Column(nullable = false, unique = true)
    var nickname: String = nickname

    @Column(nullable = true)
    var birthYear: Int? = birthYear

    @Column
    var gender: String = gender?: "O";

    @Column
    var job: String = job?: "Not Selected";

    @Column(nullable = false)
    var regTimestamp: LocalDateTime = LocalDateTime.now()

    @Column(nullable = true)
    var lastLoginTimestamp: LocalDateTime = lastLoginTimestamp?: LocalDateTime.now();

    @Column(nullable = false)
    var hashtagApperanceYn: Boolean = hashtagApperanceYn

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "author")
    protected val mutableBoards: MutableList<Board> = mutableListOf()

    val boards: List<Board> get() = mutableBoards.toList()

    fun writeBoard(board: Board) {
        mutableBoards.add(board);
    }
}
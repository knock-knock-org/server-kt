package com.knockknock.server.entity.account

import com.knockknock.server.entity.Board
import com.knockknock.server.entity.PrimaryKeyEntity
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import lombok.RequiredArgsConstructor
import java.time.LocalDateTime

@Entity
@Table(name = "accounts")
@RequiredArgsConstructor
class Account(
        email: String,
        phoneNo: String,
        password: String?,
        nickname: String,
        birthYear: Int?,
        gender: String?,
        job: String?,
        lastLoginTimestamp: LocalDateTime?,
        hashtagApperanceYn: Boolean?
): PrimaryKeyEntity() {

    @Column(nullable = false, unique = true)
    var email: String = email

    @Column(nullable = false)
    var phoneNo: String = phoneNo

    @Column
    var password: String? = password

    @Column(nullable = false, unique = true)
    var nickname: String = nickname

    @Column(nullable = true)
    var birthYear: Int? = birthYear

    @Column
    var gender: String = gender?: "O";

    @Column
    var job: String = job?: "Not Selected";

    @Column(nullable = false)
    @Schema(hidden = true)
    var regTimestamp: LocalDateTime = LocalDateTime.now()

    @Column(nullable = true)
    @Hidden
    var lastLoginTimestamp: LocalDateTime? = lastLoginTimestamp

    @Column(nullable = false)
    @Schema(hidden = true)
    var hashtagApperanceYn: Boolean = hashtagApperanceYn?: true

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "author")
    protected val mutableBoards: MutableList<Board> = mutableListOf()

    /**
    * TODO: method로 반환되는 변수를 swagger에서 hidden처리할 때 get을 붙여주나 보다 (알아보자).
    * */
    @get:Schema(hidden = true)
    val boards: List<Board> get() = mutableBoards.toList()

    fun writeBoard(board: Board) {
        mutableBoards.add(board);
    }
}
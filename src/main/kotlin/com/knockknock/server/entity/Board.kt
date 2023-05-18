package com.knockknock.server.entity

import jakarta.persistence.Entity

@Entity
class Board(
        title: String,
        author: String
): PrimaryKeyEntity() {

    var title: String = title;

    var author: String = author;
}
package com.knockknock.server

import com.knockknock.server.utils.convertPhoneNoWithoutDash
import com.knockknock.server.utils.validatePhoneNoFormat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class UtilTests {

    @Test
    @DisplayName("휴대폰 번호 포맷 확인(Valid)")
    fun 휴대폰_번호_확인() {
        val phoneNo = "010-1234-1234"

        assertEquals(true, validatePhoneNoFormat(phoneNo));
    }

    @Test
    @DisplayName("휴대폰 번호 포맷 확인(Invalid)")
    fun 휴대폰_번호_확인2() {
        val phoneNo = "010-1234-12345"

        assertEquals(false, validatePhoneNoFormat(phoneNo));
    }

    @Test
    @DisplayName("폰번호 포맷 변환 테스트")
    fun 폰번호_포맷_변환() {
        val phoneNo = "010-1234-5678";
        val convertedPhoneNo = convertPhoneNoWithoutDash(phoneNo);

        assertEquals("01012345678", convertedPhoneNo);
    }
}
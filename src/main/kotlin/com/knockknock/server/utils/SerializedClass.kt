package com.knockknock.server.utils

import kotlinx.serialization.Serializable;
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json;

@Serializable
data class SerializedClass<T>( val obj: T );

fun <T> getSerializedObject (obj: T): java.io.Serializable {
    val serializedObject = SerializedClass<T>(obj);

    return Json.encodeToString(serializedObject);
}

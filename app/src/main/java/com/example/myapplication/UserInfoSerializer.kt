package com.example.myapplication

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

//DataStore serializer for UserInfo class
object UserInfoSerializer: Serializer<UserInfo> {
    override val defaultValue: UserInfo = UserInfo.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserInfo {
        try {
            return UserInfo.parseFrom(input)
        }catch(e: InvalidProtocolBufferException){
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: UserInfo, output: OutputStream) = t.writeTo(output)
}
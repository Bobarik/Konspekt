package com.bobarik.konspekt.database.di

import com.bobarik.konspekt.database.KorgyDatabase
import com.bobarik.konspekt.database.NoteRepositoryImpl
import com.bobarik.konspekt.database.dao.NoteDao
import com.bobarik.konspekt.domain.repository.NoteRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DatabaseModule = module {
    includes(DatabaseBuilderModule)

    singleOf(::NoteRepositoryImpl) bind NoteRepository::class
    single<NoteDao> { get<KorgyDatabase>().noteDao() }
}

internal expect val DatabaseBuilderModule: Module

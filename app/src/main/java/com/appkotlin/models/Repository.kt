package com.appkotlin.models

import com.appkotlin.models.local.LocalRepository
import com.appkotlin.remote.RemoteRepository

interface Repository:RemoteRepository,LocalRepository
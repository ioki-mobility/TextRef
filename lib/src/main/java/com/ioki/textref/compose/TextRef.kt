package com.ioki.textref.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ioki.textref.TextRef

@Composable
fun textRef(textRef: TextRef): String = textRef.resolve(LocalContext.current)

@Composable
fun textRefOrNull(textRef: TextRef?): String? = textRef?.resolve(LocalContext.current)

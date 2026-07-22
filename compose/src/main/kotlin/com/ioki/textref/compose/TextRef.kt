package com.ioki.textref.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalResources
import com.ioki.textref.TextRef

@Composable
fun textRef(textRef: TextRef): String = textRef.resolve(LocalResources.current)

@Composable
fun textRefOrNull(textRef: TextRef?): String? = textRef?.resolve(LocalResources.current)

package com.qastand.android

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.editableText
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setText
import androidx.compose.ui.text.AnnotatedString

fun Modifier.appiumTag(tag: String): Modifier = this
    .testTag(tag)
    .semantics { contentDescription = tag }

fun Modifier.appiumEditableTag(
    tag: String,
    value: String,
    onValueChange: (String) -> Unit,
): Modifier = this
    .testTag(tag)
    .semantics(mergeDescendants = true) {
        contentDescription = tag
        editableText = AnnotatedString(value)
        setText { text ->
            onValueChange(text.text)
            true
        }
    }

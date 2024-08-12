package com.example.heartstonetestapp.presentation.components

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.core.text.toSpanned

@Composable
fun RichText(
  text: String,
  modifier: Modifier = Modifier,
  color: Color = LocalContentColor.current.copy()
) {
   val formattedText = text.replace("\\n", "<br>")

  var spanned = HtmlCompat.fromHtml(formattedText, HtmlCompat.FROM_HTML_MODE_COMPACT)
  spanned = spanned.subSequence(0, spanned.toString().trim().length).toSpanned()

  AndroidView(
    factory = { context ->
      with(TextView(context)) {
        setTextColor(color.toArgb())
        movementMethod = LinkMovementMethod.getInstance()
        this
      }
    },
    update = { it.text = spanned },
    modifier = modifier
  )
}


@Preview(showBackground = true)
@Composable
fun PreviewHtmlText() {
  RichText(text = "<b>Bold Text</b><br><i>Italic Text</i><br><u>Underlined Text</u><br><font color=\"#FF0000\">Red Text</font>")
}